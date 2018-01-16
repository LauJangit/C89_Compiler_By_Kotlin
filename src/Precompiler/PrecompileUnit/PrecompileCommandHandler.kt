package Precompiler.PrecompileUnit

import LexicalAnalyzer.LexicalBaseHandler
import java.util.regex.Pattern

/**
 * Created by lauya on 2018/1/8.
 */
class PrecompileCommandHandler : PreCompileBaseHandler() {
    //转换后的词法结构
    override var lexicalStruct = ArrayList<LexicalBaseHandler>();
    //在状态机中的状态为2
    override val handlerType = 2;
    //该DFA获取的代码
    override var Code = "";
    var PrecompileCommandType = 0;

    //是否进入预编译命令中
    var precompile_command_state = 0;
    //是否遇到换行转义符
    var keep_precompile_command_state = 0;
    override fun putChar(chr: Char) {
        if (precompile_command_state == 0) {
            //如果没有进入预编译命令
            if (chr == '#') {
                //如果遇到#号则进入预编译状态
                precompile_command_state = 1;
            } else {
                //如果既没有进入预编译状态也没有即将进入预编译状态。则需要切换状态
                shouldSwitch = true;
                new_handler_chr = chr;
                return;
            }
        } else if (precompile_command_state == 1) {
            //如果已经在预编译命令中
            if (keep_precompile_command_state == 0) {
                //如果之前没有遇到换行转义符
                if (chr == '\\') {
                    //如果现在遇到换行转义符
                    keep_precompile_command_state = 1;
                } else if (chr == '\n') {
                    //如果没有遇到换行转义符，且当前遇到换行标识
                    precompile_command_state = 0;
                    shouldSwitch = true;
                    new_handler_chr = chr;
                    return;
                } else {
                    //如果之前没有遇到换行转义符，现在遇到其他不敏感字符
                }
            } else {
                if (chr == '\\') {
                    //之前遇到换行转义符，且当前又遇到换行转义符
                    keep_precompile_command_state = 1;
                } else if (chr == '\n') {
                    //之前遇到换行转义符，且当前开始换行
                    Code = Code.trimEnd('\\');
                    keep_precompile_command_state = 0;
                    return;
                } else {
                    //之前遇到换行转义符，现在遇到其他字符
                    keep_precompile_command_state = 0;
                }
            }

        } else {

        }
        Code += chr;
        //println(precompile_command_state.toString()+chr);
    }

    fun processType(sCode: String): Int {
        if (sCode.startsWith("#if")) {
            return 1;
        } else if (sCode.startsWith("#ifdef")) {
            return 2;
        } else if (sCode.startsWith("#ifndef")) {
            return 3;
        } else if (sCode.startsWith("#elif")) {
            return 4;
        } else if (sCode.startsWith("#else")) {
            return 5;
        } else if (sCode.startsWith("#endif")) {
            return 6;
        } else if (sCode.startsWith("#include")) {
            return 7;
        } else if (sCode.startsWith("#define")) {
            return 8;
        } else if (sCode.startsWith("#undef")) {
            return 9;
        } else if (sCode.startsWith("#line")) {
            return 10;
        } else if (sCode.startsWith("#error")) {
            return 11;
        } else if (sCode.startsWith("#pragma")) {
            return 12;
        } else {
            return -1;
        }
    }

    //遇到无法识别的字符时，如果需要转换状态，需要使用该缓冲区记录该无法识别的字符
    override var new_handler_chr = '\u0000';

    //是否需要转换状态
    override var shouldSwitch = false;


    //转换状态到新的DFA处理机中
    override fun getNewHandler(): PreCompileBaseHandler {
        PrecompileCommandType = processType(Code);
        if (new_handler_chr == '#') {
            var precompileCommandHandler = PrecompileCommandHandler();
            precompileCommandHandler.putChar(new_handler_chr);
            return precompileCommandHandler;
        }
        if (new_handler_chr == '\"') {
            var stringCodeHandler = StringHandler();
            stringCodeHandler.putChar(new_handler_chr);
            return stringCodeHandler;
        } else {
            var cSourceCodeHandler = CSourceCodeHandler();
            cSourceCodeHandler.putChar(new_handler_chr);
            return cSourceCodeHandler;
        }
    }
}
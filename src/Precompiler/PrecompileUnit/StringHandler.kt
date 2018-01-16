package Precompiler.PrecompileUnit

import LexicalAnalyzer.LexicalBaseHandler

/**
 * Created by lauya on 2018/1/8.
 * summary:处理代码中的字符串
 */
class StringHandler : PreCompileBaseHandler() {
    //转换后的词法结构
    override var lexicalStruct = ArrayList<LexicalBaseHandler>();
    //该DFA获取的代码
    override var Code = "";
    //在状态机中的状态为3
    override val handlerType = 3;

    //游标是否处于字符串内
    var string_state = 0; //0 不在字符串内 1 在字符串内
    //游标是否处于转义符号内
    var escapes_state = 0; //0 不在转义符号内， 1 在转义符号内
    override fun putChar(chr: Char) {
        if (string_state == 0) {
            //如果不在字符串内
            if (chr == '\"') {
                //如果遇到引号(字符串开始)
                string_state = 1;
            } else {
                //如果不在字符串内且也没有准备进入字符串
                shouldSwitch = true;
                new_handler_chr = chr;
                return;
            }
        } else if (string_state == 1) {
            //如果在字符串内
            if (escapes_state == 0) {
                //如果没有字符串终结的转义字符
                if (chr == '\"') {
                    //如果遇到了转义符号
                    string_state = 0;
                } else {
                    //如果没有遇到转义字符，则继续
                }
            } else if (escapes_state == 1) {
                //如果遇到转义字符，无论当前是不是换行号，都继续
                escapes_state = 0;
            } else {

            }
        } else {

        }
        Code += chr;
    }

    //遇到无法识别的字符时，如果需要转换状态，需要使用该缓冲区记录该无法识别的字符
    override var new_handler_chr = '\u0000'

    //转换状态到新的DFA处理机中
    override fun getNewHandler(): PreCompileBaseHandler {
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

    //是否需要转换状态
    override var shouldSwitch = false;
}
package Precompiler.PrecompileUnit

import java.util.regex.Pattern

/**
 * Created by lauya on 2018/1/8.
 */
class PrecompileCommandHandler : BaseHandler() {
    override val handlerType = 2;
    override var Code = "";
    var PrecompileCommandType=0;

    var precompile_command_state = 0;
    var keep_precompile_command_state = 0;
    override fun putChar(chr: Char) {
        if (precompile_command_state == 0) {
            if (chr == '#') {
                precompile_command_state = 1;
            } else {
                shouldSwitch = true;
                new_handler_chr = chr;
                return;
            }
        } else if (precompile_command_state == 1) {
            if (keep_precompile_command_state == 0) {
                if (chr == '\\') {
                    keep_precompile_command_state = 1;
                } else if (chr == '\n') {
                    precompile_command_state = 0;
                    shouldSwitch = true;
                    new_handler_chr = chr;
                    return;
                } else {

                }
            } else {
                if (chr == '\\') {
                    keep_precompile_command_state = 1;
                } else if(chr=='\n'){
                    Code=Code.trimEnd('\\');
                    keep_precompile_command_state=0;
                    return;
                }else{
                    keep_precompile_command_state = 0;
                }
            }

        } else {

        }
        Code += chr;
        //println(precompile_command_state.toString()+chr);
    }

    override var new_handler_chr = '\u0000';
    override var shouldSwitch = false;

    fun processType(sCode:String):Int{
        if(sCode.startsWith("#if")){
            return 1;
        }else if(sCode.startsWith("#ifdef")){
            return 2;
        }else if(sCode.startsWith("#ifndef")){
            return 3;
        }else if(sCode.startsWith("#elif")){
            return 4;
        }else if(sCode.startsWith("#else")){
            return 5;
        }else if(sCode.startsWith("#endif")){
            return 6;
        }else if(sCode.startsWith("#include")){
            return 7;
        }else if(sCode.startsWith("#define")){
            return 8;
        }else if(sCode.startsWith("#undef")){
            return 9;
        }else if(sCode.startsWith("#line")){
            return 10;
        }else if(sCode.startsWith("#error")){
            return 11;
        }else if(sCode.startsWith("#pragma")){
            return 12;
        }else{
            return -1;
        }
    }

    override fun getNewHandler(): BaseHandler {
        PrecompileCommandType=processType(Code);
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
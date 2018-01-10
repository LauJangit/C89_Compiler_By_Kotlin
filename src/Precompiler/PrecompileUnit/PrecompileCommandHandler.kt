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
            PrecompileCommandType=1;
        }else if(sCode.startsWith("#ifdef")){
            PrecompileCommandType=2;
        }else if(sCode.startsWith("#ifndef")){
            PrecompileCommandType=3;
        }else if(sCode.startsWith("#elif")){
            PrecompileCommandType=4;
        }else if(sCode.startsWith("#else")){
            PrecompileCommandType=5;
        }else if(sCode.startsWith("#endif")){
            PrecompileCommandType=6;
        }else if(sCode.startsWith("#include")){
            PrecompileCommandType=7;
        }else if(sCode.startsWith("#define")){
            PrecompileCommandType=8;
        }else if(sCode.startsWith("#undef")){
            PrecompileCommandType=9;
        }else if(sCode.startsWith("#line")){
            PrecompileCommandType=10;
        }else if(sCode.startsWith("#error")){
            PrecompileCommandType=11;
        }else if(sCode.startsWith("#pragma")){
            PrecompileCommandType=12;
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
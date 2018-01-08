package Precompiler.Unit

/**
 * Created by lauya on 2018/1/8.
 */
class PrecompileCommandHandler :BaseHandler() {
    override val handlerType=1;
    override var Code = "";

    var precompile_command_state = 0;
    override fun putChar(chr: Char) {
        if (precompile_command_state == 0) {
            if (chr == '#') {
                precompile_command_state = 1;
            }else{
                shouldSwitch = true;
                new_handler_chr = chr;
                return;
            }
        } else if (precompile_command_state == 1) {
            if (chr == '\n') {
                precompile_command_state = 0;
            } else {

            }
        } else {

        }
        Code+=chr;
        //println(precompile_command_state.toString()+chr);
    }

    override var new_handler_chr = '\u0000';
    override var shouldSwitch = false;

    override fun getNewHandler(): BaseHandler {
        if (new_handler_chr == '#') {
            var precompileCommandHandler = PrecompileCommandHandler();
            precompileCommandHandler.putChar(new_handler_chr);
            return precompileCommandHandler;
        }
        if (new_handler_chr == '/') {
            var commentCodeHandler = CommentCodeHandler();
            commentCodeHandler.putChar(new_handler_chr);
            return commentCodeHandler;
        }
        if (new_handler_chr =='\"') {
            var stringCodeHandler = StringHandler();
            stringCodeHandler.putChar(new_handler_chr);
            return stringCodeHandler;
        }else{
            var cSourceCodeHandler=CSourceCodeHandler();
            cSourceCodeHandler.putChar(new_handler_chr);
            return cSourceCodeHandler;
        }
    }
}
package Precompiler.PreprocessUnit

/**
 * Created by lauya on 2018/1/8.
 */
class CSourceCodeHandler : PreProcessBaseHandler() {
    override var Code = "";
    override val handlerType = 3;

    override var new_handler_chr = '\u0000'
    override var shouldSwitch = false;

    override fun putChar(chr: Char) {
        if (chr == '"' || chr == '/') {
            new_handler_chr = chr;
            shouldSwitch = true;
            return;
        } else {
            Code += chr;
        }
    }

    override fun getNewHandler(): PreProcessBaseHandler {
        if (new_handler_chr == '/') {
            var commentCodeHandler = CommentCodeHandler();
            commentCodeHandler.putChar(new_handler_chr);
            return commentCodeHandler;
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
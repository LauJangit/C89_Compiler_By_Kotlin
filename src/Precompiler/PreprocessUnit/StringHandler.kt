package Precompiler.PreprocessUnit

/**
 * Created by lauya on 2018/1/8.
 */
class StringHandler : PreProcessBaseHandler() {
    override var Code = "";
    override val handlerType = 4;

    var string_state = 0; //0 不在字符串内 1 在字符串内
    var escapes_state = 0; //0 不在转义符号内， 1 在转义符号内
    override fun putChar(chr: Char) {
        if (string_state == 0) {
            if (chr == '\"') {
                string_state = 1;
            } else {
                shouldSwitch = true;
                new_handler_chr = chr;
                return;
            }
        } else if (string_state == 1) {
            if (escapes_state == 0) {
                if (chr == '\"') {
                    string_state = 0;
                } else {

                }
            } else if (escapes_state == 1) {
                escapes_state = 0;
            } else {

            }
        } else {

        }
        Code += chr;
    }

    override var new_handler_chr = '\u0000'

    override fun getNewHandler(): PreProcessBaseHandler {
//        println(Code)
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

    override var shouldSwitch = false;
}
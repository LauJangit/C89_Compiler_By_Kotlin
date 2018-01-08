package Precompiler.Unit

/**
 * Created by lauya on 2018/1/8.
 */
class CSourceCodeHandler :BaseHandler() {
    override var Code="";
    override val handlerType = 3;

    override var new_handler_chr = '\u0000'
    override var shouldSwitch = false;

    override fun putChar(chr: Char) {
        //println("\tCSourceCodeHandler:\t"+chr)
        if(chr=='#'||chr=='"'||chr=='/'){
            new_handler_chr=chr;
            shouldSwitch=true;
            return;
        }else{
            Code+=chr;
        }
    }

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
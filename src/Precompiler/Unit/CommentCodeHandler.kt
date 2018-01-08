package Precompiler.Unit

/**
 * Created by lauya on 2018/1/8.
 */
class CommentCodeHandler :BaseHandler() {
    override val handlerType = 2;
    override var Code = "";
    //state
    var in_comment_state = 0;
    var comment_state = 0; //0 无注释 1 "//"型注释 2 "/**/"型注释
    var out_comment_state = 0; //0 没有退出注释的标识符


    override fun putChar(chr: Char) {
        //println("\tCommentCodeHandler:\t"+chr)
        if (comment_state == 0) {

            if (in_comment_state == 0) {
                if (chr == '/') {//当遇到“/”时
                    in_comment_state = 1;
                } else {
                    shouldSwitch = true;
                    new_handler_chr = chr;
                    return;
                }
            } else if (in_comment_state == 1) {
                if (chr == '/') {//当前一个字符已经时“/”时，第二个字符也是“/”
                    comment_state = 1;
                    in_comment_state = 0;
                } else if (chr == '*') {//当前一个字符已经时“/”时，第二个字符是“*”
                    comment_state = 2;
                    in_comment_state = 0;
                } else {//当前一个字符已经时“/”时，第二个字符不是“/”也不是“*”
                    in_comment_state = 0;
                    shouldSwitch = true;
                    new_handler_chr = chr;
                    return;
                }
            }
        } else if (comment_state == 1) {
            if (chr == '\n') {
                comment_state = 0;
            } else {
                //当有单行注释时，如果遇到非换行符，无需退出注释状态
            }
        } else if (comment_state == 2) {
            if (out_comment_state == 0) {//当之前没有遇到退出字符时
                if (chr == '*') {
                    out_comment_state = 1;
                } else {
                    //当之前没有遇到退出字符,现在也没有遇到退出字符
                }
            } else if (out_comment_state == 1) {
                if (chr == '/') {
                    out_comment_state = 0;
                    comment_state = 0;
                } else {
                    out_comment_state = 0;
                }
            } else {
                //当退出注释状态值非法时
            }
        } else {
            //当注释状态值非法时
        }
        Code += chr;
    }

    override var new_handler_chr='\u0000'
    override var shouldSwitch=false;


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


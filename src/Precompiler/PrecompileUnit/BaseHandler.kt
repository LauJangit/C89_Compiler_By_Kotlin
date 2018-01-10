package Precompiler.PrecompileUnit

/**
 * Created by lauya on 2018/1/8.
 */
abstract class BaseHandler {
    abstract var Code: String;
    abstract val handlerType: Int;
    abstract fun putChar(chr: Char): Unit;
    abstract var new_handler_chr: Char;
    abstract fun getNewHandler(): BaseHandler;
    abstract var shouldSwitch: Boolean;

    fun shouldSwitch(): Boolean {
        return shouldSwitch;
    };

    fun getProcessedCode(): String {
        return this.Code;
    }

//    fun aaa():Unit{
//        var typeStr=when(handlerType){
//            1->"PrecompileCommandHandler"
//            2->"CommentCodeHandler"
//            3->"CSourceCodeHandler"
//            4->"StringHandler"
//            else->"unknown"
//        }
//        print(typeStr+"\n");
//    }
}

package Precompiler

import Precompiler.Unit.BaseHandler
import Precompiler.Unit.CSourceCodeHandler
import Precompiler.Unit.StringHandler
import Precompiler.Unit.CommentCodeHandler
import java.util.*

/**
 * Created by lauya on 2018/1/8.
 */
class PrecompilerHandler {
    private val codeList = ArrayList<BaseHandler>();

    init {
        codeList.add(CSourceCodeHandler())
    }

    public fun putChar(chr: Char) {

        if (!codeList.last().shouldSwitch()) {
            codeList.last().putChar(chr);
        } else {
            var baseHandler = codeList.last().getNewHandler();
            codeList.add(baseHandler);
            codeList.last().putChar(chr);
            //baseHandler.aaa();

        }
    }

    fun showCode(){
        print("\n\n\n\n\n");
        for (basehandler in codeList) {
            var typeStr = when (basehandler.handlerType) {
                1 -> "PrecompileCommandHandler"
                2 -> "CommentCodeHandler"
                3 -> "CSourceCodeHandler"
                4 -> "StringHandler"
                else -> "unknown"
            }
            println(typeStr+":");
            println("Code:"+basehandler.Code)
        }
    }
}

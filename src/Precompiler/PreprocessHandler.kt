package Precompiler

import InputStr.ReadStr
import Precompiler.PrecompileUnit.PreCompileBaseHandler
import Precompiler.PreprocessUnit.CSourceCodeHandler
import Precompiler.PreprocessUnit.PreProcessBaseHandler
import java.util.*

/**
 * Created by lauya on 2018/1/8.
 */
class PreprocessHandler {
    private val codeList = ArrayList<PreProcessBaseHandler>();//刚被分开的代码块

    init {
        codeList.add(CSourceCodeHandler())
    }

    fun putChar(chr: Char) {
        if (!codeList.last().shouldSwitch()) {
            codeList.last().putChar(chr);
        } else {
            var baseHandler = codeList.last().getNewHandler();
            codeList.add(baseHandler);
            codeList.last().putChar(chr);
        }
    }

    fun getCode(): ReadStr {
        var sCode = "";
        for (basehandler in codeList) {
            if (basehandler.handlerType == 3 || basehandler.handlerType == 4) {
                sCode += basehandler.getProcessedCode();
            }
        }
        return ReadStr(sCode);
    }

    fun showCode() {
        print("\n\n\n\n\n");
        for (basehandler in codeList) {
            var typeStr = when (basehandler.handlerType) {
                1 -> "CSourceCodeHandler"
                2 -> "CommentCodeHandler"
                3 -> "CSourceCodeHandler"
                4 -> "StringHandler"
                else -> "unknown"
            }
            println(typeStr + ":");
            println("Code:" + basehandler.Code);

        }
    }
}
package Precompiler

import InputStr.ReadStr
import Precompiler.PrecompileUnit.CSourceCodeHandler
import Precompiler.PrecompileUnit.PreCompileBaseHandler
import java.util.ArrayList

class PrecompilerHandler {
    private val codeList = ArrayList<PreCompileBaseHandler>();//刚被分开的代码块

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
        print("\n\n\n");
        for (basehandler in codeList) {
            var typeStr = when (basehandler.handlerType) {
                1 -> "CSourceCodeHandler"
                2 -> "PrecompileCommandHandler"
                3 -> "StringHandler"
                else -> "unknown"
            }
            println(typeStr + ":");
            println("Code:" + basehandler.Code);
        }
    }
}
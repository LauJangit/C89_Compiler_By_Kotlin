package Precompiler

import InputStr.ReadFile

/**
 * Created by lauya on 2018/1/8.
 */
class MainPrecompiler constructor(mainFile: ReadFile){
    val code="";
    var preCompilerHandler=PrecompilerHandler();
    init {
        while (mainFile.hasNext()) {
            preCompilerHandler.putChar(mainFile.getChar());
        }
        preCompilerHandler.showCode();
    }
}

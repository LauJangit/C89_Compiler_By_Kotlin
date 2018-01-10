package Precompiler

import InputStr.ReadFile

/**
 * Created by lauya on 2018/1/8.
 */
class MainPrecompiler constructor(mainFile: ReadFile){
    val code="";
    var preprocessHandler = PreprocessHandler();
    var precompilerHandler = PrecompilerHandler();
    init {
        while (mainFile.hasNext()) {
            preprocessHandler.putChar(mainFile.getChar());
        }
        var sPreProcessedStr= preprocessHandler.getCode();
        while(sPreProcessedStr.hasNext()){
            precompilerHandler.putChar(sPreProcessedStr.getChar());
        }
        precompilerHandler.showCode();
    }
}

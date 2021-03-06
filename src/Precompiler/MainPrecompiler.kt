package Precompiler

import InputStr.ReadFile
import LexicalAnalyzer.LexicalBaseHandler

/**
 * Created by lauya on 2018/1/8.
 */
class MainPrecompiler constructor(mainFile: ReadFile) {
    val code = "";
    var preprocessHandler = PreprocessHandler();
    var precompilerHandler = PrecompilerHandler();
    //词法分析后的词法结构
    var lexicalStruct = ArrayList<LexicalBaseHandler>();

    init {
        /*  预处理(删除注释)，方便进行预编译  */
        //让预处理器读取字符串
        while (mainFile.hasNext()) {
            preprocessHandler.putChar(mainFile.getChar());
        }
        //获取预处理后的字符串
        var sPreProcessedStr = preprocessHandler.getCode();
        //print(sPreProcessedStr.toString())
        //让预编译处理器读取处理后的代码
        while (sPreProcessedStr.hasNext()) {
            precompilerHandler.putChar(sPreProcessedStr.getChar());
        }
//        precompilerHandler.showCode()
        this.lexicalStruct=precompilerHandler.getLexicalStruct()
//        var readStr =precompilerHandler.getCode();
//        while (readStr.hasNext()){
//            print(readStr.getChar())
//        }
    }
}

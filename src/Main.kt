import InputStr.ReadFile
import LexicalAnalyzer.MainAnalyzer
import Precompiler.MainPrecompiler

/**
 * Created by lauya on 2018/1/7.
 */
object Main {
    @JvmStatic fun main(args: Array<String>) {
        val mainSrcFile=ReadFile(".\\c_source\\helloworld2.c");
        val mainPrecompiler= MainPrecompiler(mainSrcFile);
    }
}

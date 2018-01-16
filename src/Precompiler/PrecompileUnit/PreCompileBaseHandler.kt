package Precompiler.PrecompileUnit

import LexicalAnalyzer.LexicalBaseHandler

/**
 * Created by lauya on 2018/1/8.
 */
abstract class PreCompileBaseHandler {
    abstract var Code: String;
    abstract val handlerType: Int;
    abstract fun putChar(chr: Char): Unit;
    abstract var new_handler_chr: Char;
    abstract fun getNewHandler(): PreCompileBaseHandler;
    abstract var shouldSwitch: Boolean;
    abstract var lexicalStruct: ArrayList<LexicalBaseHandler>;

    fun shouldSwitch(): Boolean {
        return shouldSwitch;
    };

    fun getProcessedCode(): String {
        return this.Code;
    }

    fun getLexStruct(): ArrayList<LexicalBaseHandler> {
        return this.lexicalStruct;
    }
}

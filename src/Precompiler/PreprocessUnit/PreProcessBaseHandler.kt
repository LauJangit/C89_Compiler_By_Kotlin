package Precompiler.PreprocessUnit

abstract class PreProcessBaseHandler {
    abstract var Code: String;
    abstract val handlerType: Int;
    abstract fun putChar(chr: Char): Unit;
    abstract var new_handler_chr: Char;
    abstract fun getNewHandler(): PreProcessBaseHandler;
    abstract var shouldSwitch: Boolean;

    fun shouldSwitch(): Boolean {
        return shouldSwitch;
    };

    fun getProcessedCode(): String {
        return this.Code;
    }
}
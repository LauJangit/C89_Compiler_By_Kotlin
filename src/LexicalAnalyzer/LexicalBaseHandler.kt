package LexicalAnalyzer

abstract class LexicalBaseHandler {
    abstract var lexType:Int;
    abstract var sCode:String;

    fun getLexicalType():Int{
        return lexType;
    }

    fun getsCode():String{
        return sCode;
    }
}
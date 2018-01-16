package LexicalAnalyzer

abstract class LexicalBaseHandler {
    abstract var lexType:Int;

    fun getLexicalType():Int{
        return lexType;
    }

}
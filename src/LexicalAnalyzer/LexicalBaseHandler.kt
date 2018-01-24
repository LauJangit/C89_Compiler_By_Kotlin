package LexicalAnalyzer

import LexicalAnalyzer.LexicalUnit.*
import Precompiler.PrecompileUnit.PreCompileBaseHandler

abstract class LexicalBaseHandler {
    abstract var lexType:Int;
    abstract var sCode:String;
    abstract fun putChar(chr: Char): Unit;
    abstract var new_handler_chr: Char;
    abstract var shouldSwitch: Boolean;
    fun getNewHandler(): LexicalBaseHandler{
        lateinit var lexicalBaseHandler:LexicalBaseHandler;
        if(new_handler_chr in '0'..'9'){
            lexicalBaseHandler=ConstantHandler();
        }else if(new_handler_chr in 'A'..'Z'||new_handler_chr in 'a'..'z'){
            lexicalBaseHandler=IdentifierHandler()
        }else if(new_handler_chr in charArrayOf('+','-','*','/','%','>','<','!','&','|','^','~','=','?')){
            lexicalBaseHandler= OperatorHandler();
        }else if(new_handler_chr in charArrayOf(',',';',':','{','}','(',')','[',']','.')){
            lexicalBaseHandler= SeparatorHandler();
        }else{
//            print("unkonwn char:"+new_handler_chr)
            lexicalBaseHandler=MeanlessCharHandler();
        }
        lexicalBaseHandler.putChar(new_handler_chr);
        return lexicalBaseHandler;
    }

    fun getLexicalType():Int{
        return lexType;
    }

    fun getsCode():String{
        return sCode;
    }
}
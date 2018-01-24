package LexicalAnalyzer.LexicalUnit

import LexicalAnalyzer.LexicalBaseHandler
import Precompiler.PrecompileUnit.PreCompileBaseHandler

/*
* 无意义字符(空格)
*/
class MeanlessCharHandler : LexicalBaseHandler() {
    override fun putChar(chr: Char) {
//        if(chr==' '||chr=='\n'||chr=='\r'||chr=='\t'||chr=='\b'){
//            sCode+=chr;
//        }else{
//            shouldSwitch=true;
//            new_handler_chr=chr;
//            return;
//        }
        if(chr in '0'..'9'){
//            print('a')
            shouldSwitch=true;
            new_handler_chr=chr;
            return;
        }else if(chr in 'A'..'Z'||chr in 'a'..'z'){
//            print('b')
            shouldSwitch=true;
            new_handler_chr=chr;
            return;
        }else if(chr in charArrayOf('+','-','*','/','%','>','<','!','&','|','^','~','=','?')){
            shouldSwitch=true;
            new_handler_chr=chr;
            return;
        }else if(chr in charArrayOf(',',';',':','{','}','(',')','[',']','.')){
            shouldSwitch=true;
            new_handler_chr=chr;
            return;
        }else{
//            println("c:"+chr+" "+(chr in 'a'..'z')+" "+chr.toInt())
            sCode+=chr;
        }
    }

    override var new_handler_chr='\u0000'

    override var shouldSwitch=false
    override var sCode: String = "";
    override var lexType = 7;
}
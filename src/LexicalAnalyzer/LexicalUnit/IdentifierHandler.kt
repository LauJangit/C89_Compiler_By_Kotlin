package LexicalAnalyzer.LexicalUnit

import LexicalAnalyzer.LexicalBaseHandler
import Precompiler.PrecompileUnit.PreCompileBaseHandler

/*
* 标识符(包含关键字)
*/
class IdentifierHandler : LexicalBaseHandler() {
    override var sCode: String = "";

    override fun putChar(chr: Char) {
        if(sCode.length==0){
            //如果是第一个字符
            if(!((chr>='A'&&chr<='Z')||(chr>='a'&&chr<='z'))){
                shouldSwitch=true;
                new_handler_chr=chr;
                return;
            }else{

            }
        }else{
            if(!((chr>='A'&&chr<='Z')||(chr>='a'&&chr<='z')||chr=='_'||(chr>='0'&&chr<='9'))){
                shouldSwitch=true;
                new_handler_chr=chr;
                return;
            }else{

            }
        }
        sCode+=chr;
    }

    override var new_handler_chr='\u0000'

    override var shouldSwitch=false
    override var lexType = 1;
}
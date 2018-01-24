package LexicalAnalyzer.LexicalUnit

import LexicalAnalyzer.LexicalBaseHandler
import Precompiler.PrecompileUnit.PreCompileBaseHandler

/*
* 分隔符
*/
class SeparatorHandler: LexicalBaseHandler() {
    /*
    * 0:未定义
    * 一般分隔符 [ 1:, 2:; 3:: ]
    * 界定符 [ 4:{ 5:} 6:( 7:) 8:[ 9:] ]
    * 其他分隔符 [ 10.. ]
     */
    var separator_type=0;
    override fun putChar(chr: Char) {
        if(sCode.length==0){
            separator_type=when(chr){
                ','->1
                ';'->2
                ':'->3
                '{'->4
                '}'->5
                '('->6
                ')'->7
                '['->8
                ']'->9
                '.'->10
                else->{
                    shouldSwitch=true;
                    new_handler_chr=chr;
                    return
                }
            }
            sCode+=chr
        }else{
            shouldSwitch=true;
            new_handler_chr=chr;
            return
        }
    }

    override var new_handler_chr='\u0000'

    override var shouldSwitch=false
    override var sCode: String = "";
    override var lexType = 6;
}
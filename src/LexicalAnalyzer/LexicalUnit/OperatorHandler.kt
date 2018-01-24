package LexicalAnalyzer.LexicalUnit

import LexicalAnalyzer.LexicalBaseHandler
import Precompiler.PrecompileUnit.PreCompileBaseHandler

/*
* 操作符
*/
class OperatorHandler : LexicalBaseHandler() {
    /*
    * 0:未定义
    * 算术运算符 [ 1:+ 2:- 3:*(指针运算符) 4:/ 5:% 6:++ 7:-- ]
    * 关系运算符 [ 8:== 9:!= 10:> 11:< 12:>= 13:<= ]
    * 逻辑运算符 [ 14:&& 15:|| 16:! ]
    * 位运算符  [ 17:&(指针运算符) 18:| 19:^ 20:~ 21:<< 22:>> ]
    * 赋值运算符 [ 23:= 24:+= 25:-= 26:*= 27:/= 28:%= 29:<<= 30:>>= 31:&= 32:^= 33:|= ]
    * 条件运算符 [ 34:? (冒号强制属于分隔符)  ]
    * */
    var operator_type = 0
    override fun putChar(chr: Char) {
        if (sCode.length==0) {
            operator_type=when(chr){
                '+'->1
                '-'->2
                '*'->3
                '/'->4
                '%'->5
                '>'->10
                '<'->11
                '!'->16
                '&'->17
                '|'->18
                '^'->19
                '~'->20
                '='->23
                '?'->34
                else->{
                    shouldSwitch=true;
                    new_handler_chr=chr;
                    return
                }
            }
        }else if (sCode.length==1) {
            operator_type=when(operator_type){
                1->when(chr){ //+
                    '+'->6 //++
                    '='->24 //+=
                    else->{
                        shouldSwitch=true;
                        new_handler_chr=chr;
                        return
                    }
                }
                2->when(chr){ //-
                    '-'->7 //--
                    '='->25 //-=
                    else->{
                        shouldSwitch=true;
                        new_handler_chr=chr;
                        return
                    }
                }
                3->when(chr){//*
                    '='->26 //*=
                    else->{
                        shouldSwitch=true;
                        new_handler_chr=chr;
                        return
                    }
                }
                4->when(chr){ // /
                    '='->27 // /=
                    else->{
                        shouldSwitch=true;
                        new_handler_chr=chr;
                        return
                    }
                }
                5->when(chr){ //%
                    '='->28 // %=
                    else->{
                        shouldSwitch=true;
                        new_handler_chr=chr;
                        return
                    }
                }
                10->when(chr){ //>
                    '='->12 // >=
                    '>'->22 //>>
                    else->{
                        shouldSwitch=true;
                        new_handler_chr=chr;
                        return
                    }
                }
                11->when(chr){ //<
                    '='->13 // <=
                    '<'->21 // <<
                    else->{
                        shouldSwitch=true;
                        new_handler_chr=chr;
                        return
                    }
                }
                16->when(chr){ //!
                    '='->9
                    else->{
                        shouldSwitch=true;
                        new_handler_chr=chr;
                        return
                    }
                }
                17->when(chr){ //&
                    '&'->14 //&&
                    '='->31 //&=
                    else->{
                        shouldSwitch=true;
                        new_handler_chr=chr;
                        return
                    }
                }
                18->when(chr){ //|
                    '|'->15 //||
                    '='->33 //|=
                    else->{
                        shouldSwitch=true;
                        new_handler_chr=chr;
                        return
                    }
                }
                19->when(chr){ //^
                    '='->32 // ^=
                    else->{
                        shouldSwitch=true;
                        new_handler_chr=chr;
                        return
                    }
                }
                23->when(chr){ //=
                    '='->8 // ==
                    else->{
                        shouldSwitch=true;
                        new_handler_chr=chr;
                        return
                    }
                }
                else->{
                    shouldSwitch=true;
                    new_handler_chr=chr;
                    return
                }
            }
        }else{
            operator_type=when(operator_type){
                21->when(chr){ //<<
                    '='->29 // <<=
                    else->{
                        shouldSwitch=true;
                        new_handler_chr=chr;
                        return
                    }
                }
                22->when(chr){//>>
                    '='->30 // >>=
                    else->{
                        shouldSwitch=true;
                        new_handler_chr=chr;
                        return
                    }
                }
                else->{
                    shouldSwitch=true;
                    new_handler_chr=chr;
                    return
                }
            }
        }
        sCode+=chr
    }

    override var new_handler_chr = '\u0000'


    override var shouldSwitch = false
    override var sCode: String = "";
    override var lexType = 5;
}
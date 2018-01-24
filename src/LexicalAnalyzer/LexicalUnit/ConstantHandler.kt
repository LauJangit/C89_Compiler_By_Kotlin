package LexicalAnalyzer.LexicalUnit

import LexicalAnalyzer.LexicalBaseHandler
import LexicalAnalyzer.LexicalException
import Precompiler.PrecompileUnit.PreCompileBaseHandler

/*
* 数字常量
*/

class ConstantHandler : LexicalBaseHandler() {
    var radix_type =0;//进制的类别: 0 未确定类别 1 八进制常量 2 十进制常量 3 十六进制常量
    var is_long=false;
    var is_float=false;
    var is_unsigned=false;
    var is_end=false;
    override var sCode: String = "";
    var is_start_with_zero=false;

    override fun putChar(chr: Char) {
        if(sCode.length==0){
            if(chr=='0'){
                //如果常量以0开始
                is_start_with_zero=true;
            }else if(!(chr>='0'&&chr<='9')){
                //如果常量不以数字开始
                shouldSwitch=true;
                new_handler_chr=chr;
                return;
            }else{
                //如果常量以数字(不包括0)开始
            }
        }else{
            if(is_start_with_zero&&sCode.length==1){
                if(chr=='u'||chr=='U'){
                    //如果后缀包含U
                    radix_type=2;
                    is_unsigned=true;
                    is_end=true;
                    return;
                }else if(chr=='F'||chr=='f'){
                    //如果后缀包含F
                    radix_type=2;
                    is_float=true;
                    is_end=true;
                    return;
                }else if(chr=='L'||chr=='l'){
                    //如果后缀包含L
                    radix_type=2;
                    is_long=true;
                    is_end=true;
                    return;
                }else if(chr=='x'||chr=='X'){
                    //如果常量第一位以0开始，第二位是x，则代表十六进制的数
                    radix_type =3;
                }else if(chr>='0'&&chr<='7'){
                    //如果常量第一位以0开始，第二位是数字
                    radix_type =1;
                }else{
                    shouldSwitch=true;
                    new_handler_chr=chr;
                    return;
                }
            }else{
                if(radix_type==0){
                    //为十进制
                    if(chr=='u'||chr=='U'){
                        //如果后缀包含U
                        is_unsigned=true;
                        is_end=true;
                        return;
                    }else if(chr=='F'||chr=='f'){
                        //如果后缀包含F
                        is_float=true;
                        is_end=true;
                        return;
                    }else if(chr=='L'||chr=='l'){
                        //如果后缀包含L
                        is_long=true;
                        is_end=true;
                        return;
                    }else if((chr in '0'..'9'||chr=='.')){
                        sCode+=chr;
                    }else{
                        shouldSwitch=true;
                        new_handler_chr=chr;
                        return;
                    }
                    if(is_end){
                        shouldSwitch=true;
                        new_handler_chr=chr;
                        return;
                    }
                }else if(radix_type ==1){
                    //为8进制
                    if(chr=='u'||chr=='U'){
                        //如果后缀包含U
                        is_unsigned=true;
                        is_end=true;
                        return;
                    }else if(chr=='F'||chr=='f'){
                        //如果后缀包含F
                        is_float=true;
                        is_end=true;
                        return;
                    }else if(chr=='L'||chr=='l'){
                        //如果后缀包含L
                        is_long=true;
                        is_end=true;
                        return;
                    }else if(chr in '0'..'7'){
                        sCode+=chr;
                    }else{
                        shouldSwitch=true;
                        new_handler_chr=chr;
                        return;
                    }
                    if(is_end){
                        shouldSwitch=true;
                        new_handler_chr=chr;
                        return;
                    }
                }else if(radix_type==2){
                    //为十进制
                    if(chr=='u'||chr=='U'){
                        //如果后缀包含U
                        is_unsigned=true;
                        is_end=true;
                        return;
                    }else if(chr=='F'||chr=='f'){
                        //如果后缀包含F
                        is_float=true;
                        is_end=true;
                        return;
                    }else if(chr=='L'||chr=='l'){
                        //如果后缀包含L
                        is_long=true;
                        is_end=true;
                        return;
                    }else if(chr=='.'||chr in '0'..'9'){
                        sCode+=chr;
                    }else{
                        shouldSwitch=true;
                        new_handler_chr=chr;
                        return;
                    }
                    if(is_end){
                        shouldSwitch=true;
                        new_handler_chr=chr;
                        return;
                    }
                }else if(radix_type==3){
                    //为十六进制
                    if(chr=='u'||chr=='U'){
                        //如果后缀包含U
                        is_unsigned=true;
                        is_end=true;
                        return;
                    }else if(chr=='F'||chr=='f'){
                        //如果后缀包含F
                        is_float=true;
                        is_end=true;
                        return;
                    }else if(chr=='L'||chr=='l'){
                        //如果后缀包含L
                        is_long=true;
                        is_end=true;
                        return;
                    }else if((chr>='0'&&chr<='9')||(chr>='a'&&chr<='f')||(chr>='A'&&chr<='F')){
                        sCode+=chr;
                    }else{
                        shouldSwitch=true;
                        new_handler_chr=chr;
                        return;
                    }
                    if(is_end){
                        shouldSwitch=true;
                        new_handler_chr=chr;
                        return;
                    }
                }else{
                    //未知的表示方法

                }
            }
        }
        sCode+=chr;
    }

    override var new_handler_chr='\u0000'

    override var shouldSwitch=false

    override var lexType = 3;
}
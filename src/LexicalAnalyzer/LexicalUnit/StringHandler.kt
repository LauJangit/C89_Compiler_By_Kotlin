package LexicalAnalyzer.LexicalUnit

import LexicalAnalyzer.LexicalBaseHandler
/*
* 字符串常量
*/
class StringHandler : LexicalBaseHandler() {
    override var sCode: String = "";
    override var lexType = 4;
    var sValue: String = "";

    fun doIdentify(sCode: String) {
        var code=sCode.trim('\"');

        var in_escape=0;
        for(chr in sCode){
            if(in_escape==0){
                if(chr=='\\'){
                    in_escape=1;
                    continue;
                }else{

                }
            }else if(in_escape==1){
                sValue+=when(chr){
                    'n'->'\n'
                    't'->'\t'
                    'v'->' '//<------------------error detected (unsupported escape character)
                    'b'->'\b'
                    'r'->'\r'
                    'f'->' '//<------------------error detected (unsupported escape character)
                    'a'->' '//<------------------error detected (unsupported escape character)
                    '\\'->'\\'
                    '?'->' '//<------------------error detected (unsupported escape character)
                    '\''->'\''
                    '\"'->'\"'
                    else->' ' //<------------------error detected (meaningless escape character)
                }
                in_escape=0;
                continue;
            }else{

            }
            sValue+=chr;
        }
    }
}
package Precompiler.PrecompileUnit

import LexicalAnalyzer.LexicalBaseHandler
import LexicalAnalyzer.LexicalUnit.MeanlessCharHandler


class CSourceCodeHandler : PreCompileBaseHandler() {
    //转换后的词法结构
    override var lexicalStruct = ArrayList<LexicalBaseHandler>();
    //该DFA获取的代码
    override var Code = "";
    //在状态机中的状态为1
    override val handlerType = 1
    override fun putChar(chr: Char) {
        if (chr == '"' || chr == '#') {
            new_handler_chr = chr;
            shouldSwitch = true;
            return;
        } else {
            Code += chr;
        }
        return;
    }

    fun process_code(sCode:String){
        lexicalStruct.add(MeanlessCharHandler())
        for(chr in sCode){
            if (!lexicalStruct.last().shouldSwitch) {
                lexicalStruct.last().putChar(chr);
            } else {
                var baseHandler = lexicalStruct.last().getNewHandler();
                lexicalStruct.add(baseHandler);
                lexicalStruct.last().putChar(chr);
            }
        }
        for(lexUnit in lexicalStruct){
            if(lexUnit.getLexicalType()==7){
                continue;
            }
            var type=when(lexUnit.getLexicalType()){
                1->"标识符"
                3->"数字常量"
                4->"字符串常量"
                5->"运算符"
                6->"间隔符"
                7->"无意义字符"
                else->"unknown"
            }
//            println("\n< ========== >\nLex("+type+"):\n"+lexUnit.sCode+"\n> ========== <");
            println("\nLex("+type+"):\n"+lexUnit.sCode);
        }
    }

    //遇到无法识别的字符时，如果需要转换状态，需要使用该缓冲区记录该无法识别的字符
    override var new_handler_chr = '\u0000'

    //转换状态到新的DFA处理机中
    override fun getNewHandler(): PreCompileBaseHandler {
        process_code(Code);
        if (new_handler_chr == '#') {
            var precompileCommandHandler = PrecompileCommandHandler();
            precompileCommandHandler.putChar(new_handler_chr);
            return precompileCommandHandler;
        }
        if (new_handler_chr == '\"') {
            var stringCodeHandler = StringHandler();
            stringCodeHandler.putChar(new_handler_chr);
            return stringCodeHandler;
        } else {
            var cSourceCodeHandler = CSourceCodeHandler();
            cSourceCodeHandler.putChar(new_handler_chr);
            return cSourceCodeHandler;
        }
    }

    //是否需要转换状态
    override var shouldSwitch = false;
}
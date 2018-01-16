package LexicalAnalyzer.LexicalUnit

import LexicalAnalyzer.LexicalBaseHandler

/*
* 数字常量
*/

class ConstantHandler : LexicalBaseHandler() {
    override var sCode: String = "";
    override var lexType = 3;
}
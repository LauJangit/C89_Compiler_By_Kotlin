package LexicalAnalyzer.LexicalUnit

import LexicalAnalyzer.LexicalBaseHandler
/*
* 无意义字符(空格)
*/
class MeanlessCharHandler : LexicalBaseHandler() {
    override var lexType = 7;
}
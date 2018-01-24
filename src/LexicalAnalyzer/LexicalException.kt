package LexicalAnalyzer

class LexicalException constructor(errMsg:String):Throwable(){
    init {
        print(errMsg)
    }
}
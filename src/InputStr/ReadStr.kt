package InputStr

import java.io.File

class ReadStr constructor( _fileContent:String) {
    private var fileContent = ""; //文件字符计数器
    private var cursor = 0;

    init {
        this.fileContent = _fileContent;
    }

    fun hasNext(): Boolean = cursor != fileContent.length

    fun getChar(): Char = fileContent[cursor++];

    override fun toString(): String {
        return fileContent
    }
}
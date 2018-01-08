package InputStr

import java.io.File

/**
 * Created by lauya on 2018/1/8.
 */
class ReadFile constructor(fileName:String){
    private var fileContent=""; //文件字符计数器
    private var cursor=0;
    init {
        this.fileContent = "\n" + File(fileName).readText() + "\n";
    }

    public fun hasNext():Boolean=cursor!=fileContent.length

    public fun getChar():Char=fileContent[cursor++];
}

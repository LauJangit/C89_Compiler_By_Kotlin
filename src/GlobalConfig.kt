import InputStr.ReadFile

class GlobalConfig {
    fun getLibFile(sPath:String):ReadFile{
        return ReadFile(sPath);
    }

    fun getSrcFile(sPath:String):ReadFile{
        return ReadFile(sPath);
    }
}
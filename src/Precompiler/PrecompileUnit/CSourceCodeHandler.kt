package Precompiler.PrecompileUnit


class CSourceCodeHandler : BaseHandler() {
    override var Code = "";
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

    override var new_handler_chr = '\u0000'

    override fun getNewHandler(): BaseHandler {
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

    override var shouldSwitch = false;
}
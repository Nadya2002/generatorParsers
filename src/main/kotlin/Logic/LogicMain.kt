import java.text.ParseException

class LogicMain {
    @Throws(ParseException::class)
    fun testLogic(input : String): Node {
        return LogicParser(input).parse()
    }
}
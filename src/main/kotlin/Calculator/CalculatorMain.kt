import java.text.ParseException

class CalculatorMain {
    @Throws(ParseException::class)
    fun testCalculator(input : String): Double {
        return CalculatorParser(input).parse()
    }
}
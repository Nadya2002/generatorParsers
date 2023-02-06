import java.text.ParseException

class CalculatorLexer (val input : String) {
lateinit var currentToken: CalculatorToken
var currentIndex = 0
var currentChar = 0.toChar()
var currentValue = ""

init {
    currentIndex = 0
}

fun getCurToken(): CalculatorToken {
    return currentToken
}

fun getCurIndex(): Int {
    return currentIndex
}

fun getCurValue(): String {
    return currentValue
}

fun nextChar() {
    if (currentIndex < input.length) {
        currentChar = input[currentIndex]
        currentIndex++
    } else {
        currentChar = '\u0000' //END
    }
}

fun find(str: String): Boolean {
    if (currentIndex < input.length) {
        val regex = str.toRegex()
        val match = regex.find(input, currentIndex)
        if (match != null && match.range.first == currentIndex) {
            currentValue = match.value
            currentIndex = match.range.last
            return true
        } else {
            return false
        }
    } else {
        return str[0] == 0.toChar()
    }
}
@Throws(ParseException::class)
fun nextToken() {
    skip()
    
    when {
find("\\Q**\\E") -> currentToken = CalculatorToken.POW
find("\\Q+\\E") -> currentToken = CalculatorToken.ADD
find("\\Q-\\E") -> currentToken = CalculatorToken.MINUS
find("\\Q*\\E") -> currentToken = CalculatorToken.MUL
find("\\Q/\\E") -> currentToken = CalculatorToken.DIV
find("\\Q(\\E") -> currentToken = CalculatorToken.LEFT
find("\\Q)\\E") -> currentToken = CalculatorToken.RIGHT
find("[0-9]+") -> currentToken = CalculatorToken.NUMBER
find(0.toChar().toString()) -> currentToken = CalculatorToken.EOF
else -> throw ParseException("Illegal character $currentChar", currentIndex)
}
nextChar()
}


fun skip() {
while (find("[ \t\r\n]")) {
     nextChar()
}
}
}
enum class CalculatorToken {
POW, 
ADD, 
MINUS, 
MUL, 
DIV, 
LEFT, 
RIGHT, 
NUMBER, 
EOF
}
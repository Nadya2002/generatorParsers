import java.text.ParseException

class LogicLexer (val input : String) {
lateinit var currentToken: LogicToken
var currentIndex = 0
var currentChar = 0.toChar()
var currentValue = ""

init {
    currentIndex = 0
}

fun getCurToken(): LogicToken {
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
find("\\Q&\\E") -> currentToken = LogicToken.AND
find("\\Q|\\E") -> currentToken = LogicToken.OR
find("\\Q^\\E") -> currentToken = LogicToken.XOR
find("\\Q!\\E") -> currentToken = LogicToken.NOT
find("\\Q(\\E") -> currentToken = LogicToken.LEFT
find("\\Q)\\E") -> currentToken = LogicToken.RIGHT
find("[a-zA-Z]") -> currentToken = LogicToken.VAR
find(0.toChar().toString()) -> currentToken = LogicToken.EOF
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
enum class LogicToken {
AND, 
OR, 
XOR, 
NOT, 
LEFT, 
RIGHT, 
VAR, 
EOF
}
import java.text.ParseException

class CalculatorParser (val input : String) {
var lexer: CalculatorLexer = CalculatorLexer(input)
var error = "Unexpected token : "

fun check(token : CalculatorToken ) : String {
    if(lexer.getCurToken() !== token) {
        throw ParseException(error + token, lexer.getCurIndex())
    }
    val result = lexer.getCurValue()
    lexer.nextToken()
    return result
}

@Throws(ParseException::class)
fun parse(  ) : Double {
    lexer.nextToken()
    val res: Double = Expr() 
    return if (lexer.currentToken === CalculatorToken.EOF) {
        res
    } else {
        throw ParseException("Syntax error", lexer.getCurIndex())
    }  
}      

@Throws(ParseException::class)
fun ExprLine ( value : Double ) : Double {   
    val token: CalculatorToken = lexer.getCurToken()
    return when(token) {
CalculatorToken.ADD -> {
val ADD = check(CalculatorToken.ADD)
val term = Term()
 val res = value + term 
val exprLine = ExprLine(res)
 exprLine 
}
CalculatorToken.MINUS -> {
val MINUS = check(CalculatorToken.MINUS)
val term = Term()
 val res = value - term 
val exprLine = ExprLine(res)
 exprLine 
}
CalculatorToken.EOF -> {
 value 
}
CalculatorToken.RIGHT -> {
 value 
}
else -> throw ParseException(error + token, lexer.getCurIndex())
}
}
@Throws(ParseException::class)
fun Fact (  ) : Double {   
    val token: CalculatorToken = lexer.getCurToken()
    return when(token) {
CalculatorToken.MINUS -> {
val MINUS = check(CalculatorToken.MINUS)
val fact = Fact()
 -fact 
}
CalculatorToken.LEFT -> {
val LEFT = check(CalculatorToken.LEFT)
val expr = Expr()
val RIGHT = check(CalculatorToken.RIGHT)
 expr 
}
CalculatorToken.NUMBER -> {
val NUMBER = check(CalculatorToken.NUMBER)
 NUMBER.toDouble() 
}
else -> throw ParseException(error + token, lexer.getCurIndex())
}
}
@Throws(ParseException::class)
fun Kpart (  ) : Double {   
    val token: CalculatorToken = lexer.getCurToken()
    return when(token) {
CalculatorToken.MINUS -> {
val fact = Fact()
val kpartLine = KpartLine(fact)
 kpartLine 
}
CalculatorToken.LEFT -> {
val fact = Fact()
val kpartLine = KpartLine(fact)
 kpartLine 
}
CalculatorToken.NUMBER -> {
val fact = Fact()
val kpartLine = KpartLine(fact)
 kpartLine 
}
else -> throw ParseException(error + token, lexer.getCurIndex())
}
}
@Throws(ParseException::class)
fun Expr (  ) : Double {   
    val token: CalculatorToken = lexer.getCurToken()
    return when(token) {
CalculatorToken.MINUS -> {
val term = Term()
val exprLine = ExprLine(term)
 exprLine 
}
CalculatorToken.LEFT -> {
val term = Term()
val exprLine = ExprLine(term)
 exprLine 
}
CalculatorToken.NUMBER -> {
val term = Term()
val exprLine = ExprLine(term)
 exprLine 
}
else -> throw ParseException(error + token, lexer.getCurIndex())
}
}
@Throws(ParseException::class)
fun Term (  ) : Double {   
    val token: CalculatorToken = lexer.getCurToken()
    return when(token) {
CalculatorToken.MINUS -> {
val kpart = Kpart()
val termLine = TermLine(kpart)
 termLine 
}
CalculatorToken.LEFT -> {
val kpart = Kpart()
val termLine = TermLine(kpart)
 termLine 
}
CalculatorToken.NUMBER -> {
val kpart = Kpart()
val termLine = TermLine(kpart)
 termLine 
}
else -> throw ParseException(error + token, lexer.getCurIndex())
}
}
@Throws(ParseException::class)
fun TermLine ( value : Double ) : Double {   
    val token: CalculatorToken = lexer.getCurToken()
    return when(token) {
CalculatorToken.MUL -> {
val MUL = check(CalculatorToken.MUL)
val kpart = Kpart()
 val res = value * kpart 
val termLine = TermLine(res)
 termLine 
}
CalculatorToken.DIV -> {
val DIV = check(CalculatorToken.DIV)
val kpart = Kpart()
 val res = value / kpart 
val termLine = TermLine(res)
 termLine 
}
CalculatorToken.ADD -> {
 value 
}
CalculatorToken.MINUS -> {
 value 
}
CalculatorToken.EOF -> {
 value 
}
CalculatorToken.RIGHT -> {
 value 
}
else -> throw ParseException(error + token, lexer.getCurIndex())
}
}
@Throws(ParseException::class)
fun KpartLine ( value : Double ) : Double {   
    val token: CalculatorToken = lexer.getCurToken()
    return when(token) {
CalculatorToken.POW -> {
val POW = check(CalculatorToken.POW)
val kpart = Kpart()
 Math.pow(value.toDouble(), kpart.toDouble()) 
}
CalculatorToken.MUL -> {
 value 
}
CalculatorToken.DIV -> {
 value 
}
CalculatorToken.ADD -> {
 value 
}
CalculatorToken.MINUS -> {
 value 
}
CalculatorToken.EOF -> {
 value 
}
CalculatorToken.RIGHT -> {
 value 
}
else -> throw ParseException(error + token, lexer.getCurIndex())
}
}
}
import java.text.ParseException

class LogicParser (val input : String) {
var lexer: LogicLexer = LogicLexer(input)
var error = "Unexpected token : "

fun check(token : LogicToken ) : String {
    if(lexer.getCurToken() !== token) {
        throw ParseException(error + token, lexer.getCurIndex())
    }
    val result = lexer.getCurValue()
    lexer.nextToken()
    return result
}

@Throws(ParseException::class)
fun parse(  ) : Node {
    lexer.nextToken()
    val res: Node = Expr() 
    return if (lexer.currentToken === LogicToken.EOF) {
        res
    } else {
        throw ParseException("Syntax error", lexer.getCurIndex())
    }  
}      

@Throws(ParseException::class)
fun ExprLine (  ) : Node {   
    val token: LogicToken = lexer.getCurToken()
    return when(token) {
LogicToken.OR -> {
val OR = check(LogicToken.OR)
val term = Term()
 val node = Node("E1").push(listOf(Node("|"), term)) 
val exprLine = ExprLine()
 node.push(exprLine) 
}
LogicToken.EOF -> {
 Node("E1").push(Node("EPS")) 
}
LogicToken.RIGHT -> {
 Node("E1").push(Node("EPS")) 
}
else -> throw ParseException(error + token, lexer.getCurIndex())
}
}
@Throws(ParseException::class)
fun Fact (  ) : Node {   
    val token: LogicToken = lexer.getCurToken()
    return when(token) {
LogicToken.LEFT -> {
val LEFT = check(LogicToken.LEFT)
val expr = Expr()
val RIGHT = check(LogicToken.RIGHT)
 Node("F").push(listOf(Node("("), expr, Node(")"))) 
}
LogicToken.VAR -> {
val VAR = check(LogicToken.VAR)
 Node("F").push(Node("n")) 
}
else -> throw ParseException(error + token, lexer.getCurIndex())
}
}
@Throws(ParseException::class)
fun Kpart (  ) : Node {   
    val token: LogicToken = lexer.getCurToken()
    return when(token) {
LogicToken.LEFT -> {
val fact = Fact()
Node("K").push(fact)
}
LogicToken.VAR -> {
val fact = Fact()
Node("K").push(fact)
}
LogicToken.NOT -> {
val NOT = check(LogicToken.NOT)
val kpart = Kpart()
 Node("K").push(listOf(Node("!"), kpart)) 
}
else -> throw ParseException(error + token, lexer.getCurIndex())
}
}
@Throws(ParseException::class)
fun BpartLine (  ) : Node {   
    val token: LogicToken = lexer.getCurToken()
    return when(token) {
LogicToken.AND -> {
val AND = check(LogicToken.AND)
val kpart = Kpart()
 val node = Node("B1").push(listOf(Node("&"), kpart)) 
val bpartLine = BpartLine()
 node.push(bpartLine) 
}
LogicToken.XOR -> {
 Node("B1").push(Node("EPS")) 
}
LogicToken.OR -> {
 Node("B1").push(Node("EPS")) 
}
LogicToken.EOF -> {
 Node("B1").push(Node("EPS")) 
}
LogicToken.RIGHT -> {
 Node("B1").push(Node("EPS")) 
}
else -> throw ParseException(error + token, lexer.getCurIndex())
}
}
@Throws(ParseException::class)
fun Expr (  ) : Node {   
    val token: LogicToken = lexer.getCurToken()
    return when(token) {
LogicToken.LEFT -> {
val term = Term()
val exprLine = ExprLine()
 Node("E").push(listOf(term, exprLine)) 
}
LogicToken.VAR -> {
val term = Term()
val exprLine = ExprLine()
 Node("E").push(listOf(term, exprLine)) 
}
LogicToken.NOT -> {
val term = Term()
val exprLine = ExprLine()
 Node("E").push(listOf(term, exprLine)) 
}
else -> throw ParseException(error + token, lexer.getCurIndex())
}
}
@Throws(ParseException::class)
fun Term (  ) : Node {   
    val token: LogicToken = lexer.getCurToken()
    return when(token) {
LogicToken.LEFT -> {
val bpart = Bpart()
val termLine = TermLine()
 Node("T").push(listOf(bpart, termLine)) 
}
LogicToken.VAR -> {
val bpart = Bpart()
val termLine = TermLine()
 Node("T").push(listOf(bpart, termLine)) 
}
LogicToken.NOT -> {
val bpart = Bpart()
val termLine = TermLine()
 Node("T").push(listOf(bpart, termLine)) 
}
else -> throw ParseException(error + token, lexer.getCurIndex())
}
}
@Throws(ParseException::class)
fun TermLine (  ) : Node {   
    val token: LogicToken = lexer.getCurToken()
    return when(token) {
LogicToken.XOR -> {
val XOR = check(LogicToken.XOR)
val bpart = Bpart()
 val node = Node("T1").push(listOf(Node("^"), bpart)) 
val termLine = TermLine()
 node.push(termLine) 
}
LogicToken.OR -> {
 Node("T1").push(Node("EPS")) 
}
LogicToken.EOF -> {
 Node("T1").push(Node("EPS")) 
}
LogicToken.RIGHT -> {
 Node("T1").push(Node("EPS")) 
}
else -> throw ParseException(error + token, lexer.getCurIndex())
}
}
@Throws(ParseException::class)
fun Bpart (  ) : Node {   
    val token: LogicToken = lexer.getCurToken()
    return when(token) {
LogicToken.LEFT -> {
val kpart = Kpart()
val bpartLine = BpartLine()
 Node("B").push(listOf(kpart, bpartLine)) 
}
LogicToken.VAR -> {
val kpart = Kpart()
val bpartLine = BpartLine()
 Node("B").push(listOf(kpart, bpartLine)) 
}
LogicToken.NOT -> {
val kpart = Kpart()
val bpartLine = BpartLine()
 Node("B").push(listOf(kpart, bpartLine)) 
}
else -> throw ParseException(error + token, lexer.getCurIndex())
}
}
}
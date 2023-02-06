package generators

import ImplGrammarListener
import java.util.TreeMap

class LexerGenerator(grammar: ImplGrammarListener) : BaseGenerator(grammar) {
    val lexerName = grammar.lexerName
    val tokenName = grammar.tokenName

    val skipTokens = grammar.skipTokens
    val termTokens = grammar.termTokens
    val imports = grammar.imports

    override fun generate(): String {
        val result = StringBuilder()

        result.append("import java.text.ParseException")
        newLine(result)

        imports.map {
            result.append("import $it")
            newLine(result)
        }

        newLine(result)
        result.append("class $lexerName (val input : String)")
        result.append(" {")
        newLine(result)
        result.append(
            """
    lateinit var currentToken: $tokenName
    var currentIndex = 0
    var currentChar = 0.toChar()
    var currentValue = ""
        """.trimIndent()
        )

        newLine(result)
        newLine(result)

        result.append(
            """
    init {
        currentIndex = 0
    }

    fun getCurToken(): $tokenName {
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
        """.trimIndent()
        )

        //nextToken
        newLine(result)
        result.append(
            """
    @Throws(ParseException::class)
    fun nextToken() {
        skip()
        
        when {
        """.trimIndent()
        )

        newLine(result)
        termTokens.map {
            if (it.value.toString().contains("\\Q")) {
                val temp = it.value.toString().substring(0, it.value.toString().length - 1)
                result.append("find(\"\\${temp}\\E\") -> currentToken = $tokenName.${it.key}")
            } else {
                result.append("find(\"${it.value}\") -> currentToken = $tokenName.${it.key}")
            }

            newLine(result)
        }
        result.append("find(0.toChar().toString()) -> currentToken = $tokenName.EOF")
        newLine(result)
        result.append("else -> throw ParseException(\"Illegal character \$currentChar\", currentIndex)")
        newLine(result)
        result.append("}")
        newLine(result)
        result.append("nextChar()")
        newLine(result)
        result.append("}")
        newLine(result)
        newLine(result)

        //skip
        newLine(result)
        result.append("fun skip() {")
        newLine(result)
        skipTokens.map {
            result.append("while (find(\"${it.value}\")) {")
            newLine(result)
            result.append("     nextChar()")
            newLine(result)
            result.append("}")
        }
        newLine(result)
        result.append("}")

        newLine(result)
        result.append("}")
        newLine(result)

        //enum class
        result.append("enum class $tokenName {")
        newLine(result)
        termTokens.map {
            result.append("${it.key}, ")
            newLine(result)
        }
        result.append("EOF")
        newLine(result)
        result.append("}")

        return result.toString()
    }


}
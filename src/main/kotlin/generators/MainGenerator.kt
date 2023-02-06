package generators

import ImplGrammarListener

class MainGenerator(val grammar: ImplGrammarListener) : BaseGenerator(grammar) {
    val parserName = grammar.parserName

    override fun generate(): String {
        val result = StringBuilder()

        result.append(
            """
   import java.text.ParseException
   
   class ${grammar.grammarName}Main {
       @Throws(ParseException::class)
       fun test${grammar.grammarName}(input : String): ${getTypeRule(grammar.rules.getValue(grammar.startRule))} {
           return $parserName(input).parse()
       }
   }
        """.trimIndent()
        )

        return result.toString()
    }

}
package generators

import Code
import Element
import ImplGrammarListener
import NonTerm
import Part
import Rule
import Term

class ParserGenerator(val grammar: ImplGrammarListener) : BaseGenerator(grammar) {
    val parserName = grammar.parserName
    val tokenName = grammar.tokenName
    val lexerName = grammar.lexerName

    val imports = grammar.imports

    val EPS = "EPS"
    val rules = grammar.rules
    val termTokens = grammar.termTokens + Pair(EPS, Regex(""))

    var first = mutableMapOf<String, MutableSet<String>>()
    var follow = mutableMapOf<String, MutableSet<String>>()

    fun buildFirst(): MutableMap<String, MutableSet<String>> {
        termTokens.map { (key, value) -> first[key] = mutableSetOf(key) }
        rules.map { (key, rule) ->              // add EPS
            first[key] = mutableSetOf()
            for (action in rule.actions) {
                for (elem in action.elements) {
                    if (elem is Term) {
                        if (elem.name == EPS) {
                            first[key]!!.add(EPS)
                            break
                        }
                    }
                }
            }
        }

        var changed = true
        while (changed) {
            changed = false
            for ((ruleName, rule) in rules) {
                for (action in rule.actions) {
                    for (elem in action.elements) {
                        if (elem is Element) {
                            val elemName = elem.name
                            changed = changed || first.getValue(ruleName).addAll(first.getValue(elemName) - EPS)

                            if (EPS !in first.getValue(elemName)) {
                                break
                            }
                        }
                    }
                }
            }
        }
        return first
    }

    fun buildFollow(): MutableMap<String, MutableSet<String>> {
        rules.map { (nameRule, rule) -> follow[nameRule] = mutableSetOf() }
        follow.getValue(grammar.startRule).add("EOF")

        first = buildFirst()

        var changed = true
        while (changed) {
            changed = false
            for ((ruleName, rule) in rules) {
                for (action in rule.actions) {
                    val termAndNonTerm = action.elements.filter { it !is Code }
                    for (i in (0..termAndNonTerm.size - 2)) {
                        // alfa -> ksi B ny
                        val elem = termAndNonTerm[i] as Element   //B
                        val next = termAndNonTerm[i + 1] as Element //ny

                        if (elem is Term) {
                            continue
                        }

                        changed = changed ||
                                follow.getValue(elem.name).addAll(first.getValue(next.name) - EPS)

                        if (EPS in first.getValue(next.name)) {
                            changed = changed ||
                                    follow.getValue(elem.name).addAll(follow.getValue(ruleName))
                        }
                    }

                    val elem = termAndNonTerm.last() as Element
                    if (elem is Term) {
                        continue
                    }
                    changed = changed ||
                            follow.getValue(elem.name).addAll(follow.getValue(ruleName))
                }
            }
        }

        return follow
    }

    override fun generate(): String {
        first = buildFirst()
        follow = buildFollow()

        val result = StringBuilder()

        result.append("import java.text.ParseException")
        newLine(result)

        imports.map {
            result.append("import $it")
            newLine(result)
        }

        newLine(result)
        result.append("class $parserName (val input : String)")
        result.append(" {")
        newLine(result)

        result.append(
            """
        var lexer: $lexerName = $lexerName(input)
        var error = "Unexpected token : "
        
        fun check(token : $tokenName ) : String {
            if(lexer.getCurToken() !== token) {
                throw ParseException(error + token, lexer.getCurIndex())
            }
            val result = lexer.getCurValue()
            lexer.nextToken()
            return result
        }
        """.trimIndent()
        )
        newLine(result)
        newLine(result)

        val startRule = rules.getValue(grammar.startRule)

        result.append(
            """
        @Throws(ParseException::class)
        fun parse( ${getArgsRuleWithType(startRule)} ) : ${getTypeRule(startRule)} {
            lexer.nextToken()
            val res: ${getTypeRule(startRule)} = ${startRule.name.capitalize()}(${getArgsRule(startRule)}) 
            return if (lexer.currentToken === $tokenName.EOF) {
                res
            } else {
                throw ParseException("Syntax error", lexer.getCurIndex())
            }  
        }      
        """.trimIndent()
        )
        newLine(result)
        newLine(result)

        val first_1 = buildFirstOne()

        //rules function
        for ((nameRule, rule) in rules) {
            result.append(
                """
        @Throws(ParseException::class)
        fun ${nameRule.capitalize()} ( ${getArgsRuleWithType(rule)} ) : ${getTypeRule(rule)} {   
            val token: $tokenName = lexer.getCurToken()
            return when(token) {
        """.trimIndent()
            )
            for (token in first_1.getValue(nameRule)) {
                newLine(result)
                result.append("$tokenName.$token -> {")
                newLine(result)
                var elements: List<Part>? = null
                var save: List<Part>? = null

                actions@ for (action in rule.actions) {
                    val terminals = action.elements.filter { it is Term } as List<Term>
                    for (term in terminals) {
                        if (term.name == token) {
                            elements = action.elements
                            break@actions
                        }
                        if (term.name == EPS) {
                            save = action.elements
                        }
                    }
                }

                if (elements == null) {
                    elements = save
                }
                if(elements == null){
                    elements = rule.actions[0].elements
                }

                for (element in elements) {
                    if (element is Code) {
                        result.append(element.code)
                        newLine(result)
                    }
                    if (element is Term) {
                        if (element.name == EPS) continue
                        else {
                            result.append("val ${element.name} = check($tokenName.${element.name})")
                            newLine(result)
                        }
                    }
                    if (element is NonTerm) {
                        result.append("val ${element.name} = ${element.name.capitalize()}(${getAttrsFun(element)})")
                        newLine(result)
                    }
                }
                result.append("}")
            }

            newLine(result)
            result.append("else -> throw ParseException(error + token, lexer.getCurIndex())")
            newLine(result)
            result.append("}")  //when

            newLine(result)
            result.append("}")
            newLine(result)
        }

        result.append("}")
        return result.toString()
    }

    private fun buildFirstOne(): MutableMap<String, MutableSet<String>> {
        val first_1 = mutableMapOf<String, MutableSet<String>>()
        for ((name, _) in follow) {
            first_1[name] = mutableSetOf()
            first_1.getValue(name).addAll(first.getValue(name) - EPS)
            if (first.getValue(name).contains(EPS)) {
                first_1.getValue(name).addAll(follow.getValue(name))
            }
        }
        return first_1
    }
}
package generators

import ImplGrammarListener
import NonTerm
import Rule

abstract class BaseGenerator(private val grammar: ImplGrammarListener) {
    fun newLine(str : StringBuilder){
        str.append(System.lineSeparator())
    }

    abstract fun generate(): String

    fun getArgsRuleWithType(rule: Rule): String {
        return rule.arguments?.joinToString { (name, type) -> "$name : $type" }.orEmpty()
    }

    fun getArgsRule(rule: Rule): String {
        return rule.arguments?.joinToString { (name) -> name }.orEmpty()
    }

    fun getAttrsFun(nonTerm: NonTerm): String {
        return nonTerm.attributes?.joinToString().orEmpty()
    }

    fun getTypeRule(rule: Rule): String {
        if (rule.returnType == null) {
            return "Unit"
        } else {
            return rule.returnType
        }
    }
}
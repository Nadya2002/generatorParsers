class ImplGrammarListener(val grammarName: String) : GrammarBaseListener() {
    val parserName: String = grammarName + "Parser"
    val lexerName: String = grammarName + "Lexer"
    val tokenName: String = grammarName + "Token"

    val skipTokens = hashMapOf<String, Regex>()
    val termTokens = LinkedHashMap<String, Regex>()
    val rules = hashMapOf<String, Rule>()
    val imports = mutableListOf<String>()

    lateinit var startRule: String

    override fun exitStartRule(ctx: GrammarParser.StartRuleContext) {
        startRule = ctx.PARSID().text
    }

    override fun enterImportOne(ctx: GrammarParser.ImportOneContext) {
        imports += ctx.IMPORT().text
    }

    override fun exitParseRule(ctx: GrammarParser.ParseRuleContext) {
        val name = ctx.PARSID().text

        val returnType = ctx.type().text.substring(1)
        val arguments = mutableListOf<Argument>()
        val actions = mutableListOf<Action>()

        if (ctx.arguments() != null) {
            for (arg in ctx.arguments().argument()) {
                arguments += Argument(arg.PARSID().text, arg.type().text.substring(1))
            }
        }

        for (action in ctx.actions().action()) {
            val elements = mutableListOf<Part>()

            for (part in action.elements()) {
                val code = part.CODE()?.text

                val element = part.element()
                var nameEl = ""
                var attributes: List<String>? = null

                if (element != null) {
                    if (element.LEXID() != null) {
                        nameEl = element.LEXID().text
                        elements += Term(nameEl)
                    } else {
                        nameEl = element.PARSID().text
                        if (element.attributes() != null) {
                            attributes = element.attributes().PARSID().map { it.text }
                        }
                        elements += NonTerm(nameEl, attributes)
                    }
                }

                if (code != null) {
                    if (elements.isEmpty() || elements[elements.size - 1] is Code) {
                        elements += Term("EPS")
                    }

                    elements += Code(code.substring(1, code.length - 1))
                }
            }

            actions += Action(elements)
        }

        rules.putIfAbsent(name, Rule(name, arguments, actions, returnType))

    }

    override fun exitSkip(ctx: GrammarParser.SkipContext) {
        val regex: Regex? = transToRegex(ctx.terminal())
        if (regex != null) {
            skipTokens.putIfAbsent(ctx.LEXID().text, regex)
        }
    }

    override fun exitTerm(ctx: GrammarParser.TermContext) {
        val regex: Regex? = transToRegex(ctx.terminal())
        if (regex != null) {
            termTokens.putIfAbsent(ctx.LEXID().text, regex)
        }
    }

    private fun transToRegex(terminal: GrammarParser.TerminalContext): Regex? {
        if (terminal.REGEXP() != null) {
            val text = terminal.REGEXP().text
            return text.substring(1, text.length - 1).toRegex()
        }

        if (terminal.STRING() != null) {
            val text = terminal.STRING().text
            return Regex.escape(text.substring(1, text.length - 1)).toRegex()
        }

        return null
    }
}
import java.util.jar.Attributes

data class Argument(val name: String, val type: String)

data class Rule(
    val name: String,
    val arguments: List<Argument>? = null,
    val actions: List<Action>,
    val returnType: String? = null
)

data class Action(
    val elements: List<Part>
)

open class Element(
    val name: String
) : Part()

class Term(
    name : String,
) : Element(name)

class NonTerm(
    name : String,
    val attributes: List<String>?
) : Element(name)

class Code(val code : String) : Part()

open class Part()

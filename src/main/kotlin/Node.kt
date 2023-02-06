import java.io.Writer

var index = 0

class Node(val type: String) {
    var children = ArrayList<Node>()

    fun push(ch: List<Node>): Node {
        children.addAll(ch)
        return this
    }

    fun push(ch: Node): Node {
        children.add(ch)
        return this
    }

    fun getChildrenNode(): ArrayList<Node> {
        return children
    }
}

class TreePrinter(private val tree: Node, var name: String = "") {
    private lateinit var writer: Writer

    fun printTo(output: Writer) {
        this.writer = output
        writer.write("digraph $name {")
        writer.write(System.lineSeparator())
        printNodeNamesRec(tree)
        printNodeLinksRec(tree)
        writer.write("}")
    }

    private fun Node.uniqueId() = "node" + System.identityHashCode(this)

    private fun printNodeNamesRec(cur: Node) {
        printNodeName(cur)
        cur.children.forEach { printNodeNamesRec(it) }
    }

    private fun printNodeLinksRec(cur: Node) {
        printNodeLinks(cur)
        cur.children.forEach { printNodeLinksRec(it) }
    }

    private fun printNodeName(cur: Node) {
        val name = cur.type
        val attrs = mutableMapOf("label" to "\"$name\"")

        if (cur.children.isEmpty()) {
            attrs["color"] = "red"
        }
        val attrString = attrs.map { (k, v) -> "$k=$v" }.joinToString()
        writer.write("${cur.uniqueId()}[$attrString]")
        writer.write(System.lineSeparator())
    }

    private fun printNodeLinks(cur: Node) {
        if (cur.children.isEmpty()) return
        val l = cur.children.joinToString(separator = " ") { it.uniqueId() }
        writer.write("${cur.uniqueId()} -> {$l}")
        writer.write(System.lineSeparator())
    }
}
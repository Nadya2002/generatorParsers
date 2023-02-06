import generators.LexerGenerator
import generators.MainGenerator
import generators.ParserGenerator
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import java.io.*
import java.nio.file.Files
import java.nio.file.Paths
import java.text.ParseException

fun main(args: Array<String>) {

    val grammars = arrayOf("Calculator", "Logic")
    val pathToGram = "D:\\University\\MethodsTranslation\\Lab4\\src\\main\\grammars\\"
    val pathToGenerated = "D:\\University\\MethodsTranslation\\Lab4\\src\\main\\kotlin\\"

    for (grammar in grammars) {
        val input = StringBuilder()
        val file = "$pathToGram$grammar.gr"
        val dir = pathToGenerated + grammar + "\\"
        try {
            BufferedReader(FileReader(file)).use { reader ->
                var string = reader.readLine()
                while (string != null) {
                    input.append(string).append("\n")
                    string = reader.readLine()
                }
            }
        } catch (e: IOException) {
            println(e.message)
        }

        try {
            val lexer = GrammarLexer(CharStreams.fromString(input.toString()))
            val tokens = CommonTokenStream(lexer)
            val parser = GrammarParser(tokens)
            val walker = ParseTreeWalker()
            val tree = parser.start()
            val listener = ImplGrammarListener(grammar)

            walker.walk(listener, tree)
            val lexerText = LexerGenerator(listener).generate()
            val parserText = ParserGenerator(listener).generate()
            val mainText = MainGenerator(listener).generate()

            Files.createDirectories(Paths.get(dir))
            File(dir + grammar + "Lexer.kt").writeText(lexerText)
            File(dir + grammar + "Parser.kt").writeText(parserText)
            File(dir + grammar + "Main.kt").writeText(mainText)
        } catch (e: Exception) {
            println("Error : " + e.message)
        }
    }

    while(true){
        val test = readLine()
        if (test != null) {
            if(test == "q"){
                break
            }
            println(CalculatorMain().testCalculator(test))
        }
    }

//    testCalcGenerators()
//    println()
//    testLogicGenerators()
}

//
//fun testCalcGenerators() {
//    println("Calculator")
//    val testFile = "D:\\University\\MethodsTranslation\\Lab4\\src\\testCalc.txt"
//    try {
//        BufferedReader(FileReader(testFile)).use { reader ->
//            var string = reader.readLine()
//            while (string != null) {
//                try {
//                    val result = CalculatorMain().testCalculator(string)
//                    println("test : $string = $result - OK")
//                } catch (e: ParseException) {
//                    println("test : " + string + " - FAIL : " + e.message)
//                }
//                string = reader.readLine()
//            }
//        }
//    } catch (e: IOException) {
//        println(e.message)
//    }
//}

//fun testLogicGenerators() {
//    println("Logic")
//    val outputFile = "D:\\University\\MethodsTranslation\\logic-dots"
//    val testFile = "D:\\University\\MethodsTranslation\\Lab4\\src\\testLogic.txt"
//    var count = 0
//    val endFile = ".gv"
//    try {
//        BufferedReader(FileReader(testFile)).use { reader ->
//            var string = reader.readLine()
//            while (string != null) {
//                try {
//                    val result = LogicMain().testLogic(string)
//                    Files.newBufferedWriter(Paths.get(outputFile + count + endFile)).use {
//                        TreePrinter(result).printTo(it)
//                    }
//                    println("test : $string - OK")
//                    count++
//                } catch (e: ParseException) {
//                    println("test : " + string + " - FAIL : " + e.message)
//                }
//                string = reader.readLine()
//            }
//        }
//    } catch (e: IOException) {
//        println(e.message)
//    }
//}

//D:\University\MethodsTranslation>dot logic-dots.gv -Tpng -o image.png
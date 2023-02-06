// Generated from D:/University/MethodsTranslation/Lab4/src/main/antlr\Grammar.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(GrammarParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(GrammarParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(GrammarParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(GrammarParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#importOne}.
	 * @param ctx the parse tree
	 */
	void enterImportOne(GrammarParser.ImportOneContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#importOne}.
	 * @param ctx the parse tree
	 */
	void exitImportOne(GrammarParser.ImportOneContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#startRule}.
	 * @param ctx the parse tree
	 */
	void enterStartRule(GrammarParser.StartRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#startRule}.
	 * @param ctx the parse tree
	 */
	void exitStartRule(GrammarParser.StartRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#grammarRule}.
	 * @param ctx the parse tree
	 */
	void enterGrammarRule(GrammarParser.GrammarRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#grammarRule}.
	 * @param ctx the parse tree
	 */
	void exitGrammarRule(GrammarParser.GrammarRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#parseRule}.
	 * @param ctx the parse tree
	 */
	void enterParseRule(GrammarParser.ParseRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#parseRule}.
	 * @param ctx the parse tree
	 */
	void exitParseRule(GrammarParser.ParseRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(GrammarParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(GrammarParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(GrammarParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(GrammarParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(GrammarParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(GrammarParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code term}
	 * labeled alternative in {@link GrammarParser#lexerRule}.
	 * @param ctx the parse tree
	 */
	void enterTerm(GrammarParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by the {@code term}
	 * labeled alternative in {@link GrammarParser#lexerRule}.
	 * @param ctx the parse tree
	 */
	void exitTerm(GrammarParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by the {@code skip}
	 * labeled alternative in {@link GrammarParser#lexerRule}.
	 * @param ctx the parse tree
	 */
	void enterSkip(GrammarParser.SkipContext ctx);
	/**
	 * Exit a parse tree produced by the {@code skip}
	 * labeled alternative in {@link GrammarParser#lexerRule}.
	 * @param ctx the parse tree
	 */
	void exitSkip(GrammarParser.SkipContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#terminal}.
	 * @param ctx the parse tree
	 */
	void enterTerminal(GrammarParser.TerminalContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#terminal}.
	 * @param ctx the parse tree
	 */
	void exitTerminal(GrammarParser.TerminalContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#actions}.
	 * @param ctx the parse tree
	 */
	void enterActions(GrammarParser.ActionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#actions}.
	 * @param ctx the parse tree
	 */
	void exitActions(GrammarParser.ActionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAction(GrammarParser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAction(GrammarParser.ActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#elements}.
	 * @param ctx the parse tree
	 */
	void enterElements(GrammarParser.ElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#elements}.
	 * @param ctx the parse tree
	 */
	void exitElements(GrammarParser.ElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(GrammarParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(GrammarParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#attributes}.
	 * @param ctx the parse tree
	 */
	void enterAttributes(GrammarParser.AttributesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#attributes}.
	 * @param ctx the parse tree
	 */
	void exitAttributes(GrammarParser.AttributesContext ctx);
}
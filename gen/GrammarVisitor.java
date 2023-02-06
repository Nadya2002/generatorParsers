// Generated from D:/University/MethodsTranslation/Lab4/src/main/antlr\Grammar.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GrammarParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(GrammarParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(GrammarParser.HeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#importOne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportOne(GrammarParser.ImportOneContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#startRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartRule(GrammarParser.StartRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#grammarRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrammarRule(GrammarParser.GrammarRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#parseRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseRule(GrammarParser.ParseRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(GrammarParser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(GrammarParser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(GrammarParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code term}
	 * labeled alternative in {@link GrammarParser#lexerRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(GrammarParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code skip}
	 * labeled alternative in {@link GrammarParser#lexerRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkip(GrammarParser.SkipContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#terminal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerminal(GrammarParser.TerminalContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#actions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActions(GrammarParser.ActionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAction(GrammarParser.ActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#elements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElements(GrammarParser.ElementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement(GrammarParser.ElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#attributes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributes(GrammarParser.AttributesContext ctx);
}
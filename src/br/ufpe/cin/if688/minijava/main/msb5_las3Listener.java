package br.ufpe.cin.if688.minijava.main;

// Generated from msb5_las3.g4 by ANTLR 4.4
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link msb5_las3Parser}.
 */
public interface msb5_las3Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link msb5_las3Parser#mainclass}.
	 * @param ctx the parse tree
	 */
	void enterMainclass(@NotNull msb5_las3Parser.MainclassContext ctx);
	/**
	 * Exit a parse tree produced by {@link msb5_las3Parser#mainclass}.
	 * @param ctx the parse tree
	 */
	void exitMainclass(@NotNull msb5_las3Parser.MainclassContext ctx);
	/**
	 * Enter a parse tree produced by {@link msb5_las3Parser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(@NotNull msb5_las3Parser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link msb5_las3Parser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(@NotNull msb5_las3Parser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link msb5_las3Parser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull msb5_las3Parser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link msb5_las3Parser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull msb5_las3Parser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link msb5_las3Parser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull msb5_las3Parser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link msb5_las3Parser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull msb5_las3Parser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link msb5_las3Parser#methodDecl}.
	 * @param ctx the parse tree
	 */
	void enterMethodDecl(@NotNull msb5_las3Parser.MethodDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link msb5_las3Parser#methodDecl}.
	 * @param ctx the parse tree
	 */
	void exitMethodDecl(@NotNull msb5_las3Parser.MethodDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link msb5_las3Parser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(@NotNull msb5_las3Parser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link msb5_las3Parser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(@NotNull msb5_las3Parser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link msb5_las3Parser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(@NotNull msb5_las3Parser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link msb5_las3Parser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(@NotNull msb5_las3Parser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link msb5_las3Parser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(@NotNull msb5_las3Parser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link msb5_las3Parser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(@NotNull msb5_las3Parser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link msb5_las3Parser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(@NotNull msb5_las3Parser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link msb5_las3Parser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(@NotNull msb5_las3Parser.ClassDeclarationContext ctx);
}
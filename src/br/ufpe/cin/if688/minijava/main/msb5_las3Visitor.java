package br.ufpe.cin.if688.minijava.main;

// Generated from msb5_las3.g4 by ANTLR 4.4
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link msb5_las3Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface msb5_las3Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link msb5_las3Parser#mainclass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainclass(@NotNull msb5_las3Parser.MainclassContext ctx);
	/**
	 * Visit a parse tree produced by {@link msb5_las3Parser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(@NotNull msb5_las3Parser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link msb5_las3Parser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(@NotNull msb5_las3Parser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link msb5_las3Parser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(@NotNull msb5_las3Parser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link msb5_las3Parser#methodDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDecl(@NotNull msb5_las3Parser.MethodDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link msb5_las3Parser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(@NotNull msb5_las3Parser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link msb5_las3Parser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(@NotNull msb5_las3Parser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link msb5_las3Parser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(@NotNull msb5_las3Parser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link msb5_las3Parser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(@NotNull msb5_las3Parser.ClassDeclarationContext ctx);
}
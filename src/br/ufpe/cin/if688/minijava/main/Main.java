package br.ufpe.cin.if688.minijava.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import br.ufpe.cin.if688.minijava.ast.Program;
import br.ufpe.cin.if688.minijava.visitor.BuildSymbolTableVisitor;
import br.ufpe.cin.if688.minijava.visitor.TypeCheckVisitor;



public class Main {

	public static void main(String[] args) throws IOException {
		InputStream stream = new FileInputStream("src/teste.txt");
		ANTLRInputStream input = new ANTLRInputStream(stream);
		msb5_las3Lexer lexer = new msb5_las3Lexer(input);
		CommonTokenStream token = new CommonTokenStream(lexer);
		msb5_las3Parser parser = new msb5_las3Parser(token);
		ParseTree tree = parser.program();

		Visitor visitor = new Visitor();
		Program prog = (Program) visitor.visit(tree);
		
		BuildSymbolTableVisitor stVis = new BuildSymbolTableVisitor();
		//construindo tabela de símbolos
		prog.accept(stVis); 
		//fazendo a checagem de tipos
		prog.accept(new TypeCheckVisitor(stVis.getSymbolTable())); 

	}

}

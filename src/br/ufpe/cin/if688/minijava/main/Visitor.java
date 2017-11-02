package br.ufpe.cin.if688.minijava.main;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

import br.ufpe.cin.if688.minijava.ast.And;
import br.ufpe.cin.if688.minijava.ast.ArrayAssign;
import br.ufpe.cin.if688.minijava.ast.ArrayLength;
import br.ufpe.cin.if688.minijava.ast.ArrayLookup;
import br.ufpe.cin.if688.minijava.ast.Assign;
import br.ufpe.cin.if688.minijava.ast.Block;
import br.ufpe.cin.if688.minijava.ast.BooleanType;
import br.ufpe.cin.if688.minijava.ast.Call;
import br.ufpe.cin.if688.minijava.ast.ClassDecl;
import br.ufpe.cin.if688.minijava.ast.ClassDeclExtends;
import br.ufpe.cin.if688.minijava.ast.ClassDeclList;
import br.ufpe.cin.if688.minijava.ast.ClassDeclSimple;
import br.ufpe.cin.if688.minijava.ast.Exp;
import br.ufpe.cin.if688.minijava.ast.ExpList;
import br.ufpe.cin.if688.minijava.ast.False;
import br.ufpe.cin.if688.minijava.ast.Formal;
import br.ufpe.cin.if688.minijava.ast.FormalList;
import br.ufpe.cin.if688.minijava.ast.Identifier;
import br.ufpe.cin.if688.minijava.ast.IdentifierExp;
import br.ufpe.cin.if688.minijava.ast.IdentifierType;
import br.ufpe.cin.if688.minijava.ast.If;
import br.ufpe.cin.if688.minijava.ast.IntArrayType;
import br.ufpe.cin.if688.minijava.ast.IntegerLiteral;
import br.ufpe.cin.if688.minijava.ast.IntegerType;
import br.ufpe.cin.if688.minijava.ast.LessThan;
import br.ufpe.cin.if688.minijava.ast.MainClass;
import br.ufpe.cin.if688.minijava.ast.MethodDecl;
import br.ufpe.cin.if688.minijava.ast.MethodDeclList;
import br.ufpe.cin.if688.minijava.ast.Minus;
import br.ufpe.cin.if688.minijava.ast.NewArray;
import br.ufpe.cin.if688.minijava.ast.NewObject;
import br.ufpe.cin.if688.minijava.ast.Not;
import br.ufpe.cin.if688.minijava.ast.Plus;
import br.ufpe.cin.if688.minijava.ast.Print;
import br.ufpe.cin.if688.minijava.ast.Program;
import br.ufpe.cin.if688.minijava.ast.Statement;
import br.ufpe.cin.if688.minijava.ast.StatementList;
import br.ufpe.cin.if688.minijava.ast.This;
import br.ufpe.cin.if688.minijava.ast.Times;
import br.ufpe.cin.if688.minijava.ast.True;
import br.ufpe.cin.if688.minijava.ast.Type;
import br.ufpe.cin.if688.minijava.ast.VarDecl;
import br.ufpe.cin.if688.minijava.ast.VarDeclList;
import br.ufpe.cin.if688.minijava.ast.While;
import br.ufpe.cin.if688.minijava.main.msb5_las3Parser.ClassDeclarationContext;
import br.ufpe.cin.if688.minijava.main.msb5_las3Parser.ExpContext;
import br.ufpe.cin.if688.minijava.main.msb5_las3Parser.IdentifierContext;
import br.ufpe.cin.if688.minijava.main.msb5_las3Parser.MainclassContext;
import br.ufpe.cin.if688.minijava.main.msb5_las3Parser.MethodDeclContext;
import br.ufpe.cin.if688.minijava.main.msb5_las3Parser.ProgramContext;
import br.ufpe.cin.if688.minijava.main.msb5_las3Parser.StatementContext;
import br.ufpe.cin.if688.minijava.main.msb5_las3Parser.TypeContext;
import br.ufpe.cin.if688.minijava.main.msb5_las3Parser.VarDeclContext;

public class Visitor extends AbstractParseTreeVisitor<Object> implements msb5_las3Visitor<Object> {

	@Override
	public Object visitMainclass(MainclassContext ctx) {
		Identifier id1 = (Identifier) this.visit(ctx.identifier(0));
		Identifier id2 = (Identifier) this.visit(ctx.identifier(1));
		Statement stmt = (Statement) this.visit(ctx.statement());
		return new MainClass(id1, id2, stmt);
	}

	@Override
	public Object visitStatement(StatementContext ctx) {
		Statement stmt = null;
		
		if(ctx.getChild(0).getText().equals("{")){
			StatementList sl = new StatementList();
			for(StatementContext sc : ctx.statement()) 
				sl.addElement((Statement) this.visit(sc));
			stmt = new Block(sl);		
		} else if(ctx.getChild(0).getText().equals("if")){
			Exp exp = (Exp) this.visit(ctx.exp(0));
			Statement stmt1 = (Statement) this.visit(ctx.statement(0));
			Statement stmt2 = (Statement) this.visit(ctx.statement(1));
			stmt = new If(exp, stmt1, stmt2);
		} else if(ctx.getChild(0).getText().equals("while")){
			Exp exp = (Exp) this.visit(ctx.exp(0));
			Statement id = (Statement) this.visit(ctx.statement(0));
			stmt = new While(exp, id);
		} else if(ctx.getChild(0).getText().equals("System.out.println")){
			Exp exp = (Exp) this.visit(ctx.exp(0));
			stmt = new Print(exp);
		} else if(ctx.getChild(1).getText().equals("=")) {
			Identifier id = (Identifier) this.visit(ctx.identifier());
			Exp exp = (Exp) this.visit(ctx.exp(0));
			stmt = new Assign(id, exp);
		} else if(ctx.getChild(1).getText().equals("[")) {
			Identifier id = (Identifier) this.visit(ctx.identifier());
			Exp exp1 = (Exp) this.visit(ctx.exp(0));
			Exp exp2 = (Exp) this.visit(ctx.exp(1));
			stmt = new ArrayAssign(id, exp1, exp2);
		}
		
		return stmt;
	}

	@Override
	public Object visitProgram(ProgramContext ctx) {
		MainClass mc = (MainClass) this.visit(ctx.mainclass());
		ClassDeclList cdl = new ClassDeclList();
		for(ClassDeclarationContext cdc: ctx.classDeclaration()) {
			cdl.addElement((ClassDecl) this.visit(cdc));
		}
		return new Program(mc, cdl);
	}

	@Override
	public Object visitMethodDecl(MethodDeclContext ctx) {
		Type t = (Type) this.visit(ctx.type(0));
		Identifier id = (Identifier) this.visit(ctx.identifier(0));
		
		VarDeclList vdl = new VarDeclList();
		for(VarDeclContext vdc: ctx.varDecl())
			vdl.addElement((VarDecl) this.visit(vdc));
		
		StatementList sl = new StatementList();
		for(StatementContext sc: ctx.statement())
			sl.addElement((Statement) this.visit(sc));
		
		FormalList fl = new FormalList();
		for(int i = 1; i < ctx.type().size(); ++i)
			fl.addElement(new Formal((Type) this.visit(ctx.type(i)), (Identifier) this.visit(ctx.identifier(i))));		
		
		Exp e = (Exp) this.visit(ctx.exp());
		
		return new MethodDecl(t, id, fl, vdl, sl, e);
	}

	@Override
	public Object visitExp(ExpContext ctx) {
		Exp expression = null;
		switch(ctx.getChildCount()) {
		case 1: {
			switch(ctx.getChild(0).getText()) {
			case "true": {
				expression = new True();
				break;
			} case "false": {
				expression = new False();
				break;
			} case "this": {
				expression = new This();
				break;
			} default: {
				if(ctx.getChild(0).getText().charAt(0) >= '0' && ctx.getChild(0).getText().charAt(0) <= '9')
					expression = new IntegerLiteral(Integer.parseInt(ctx.getChild(0).getText()));
				else
					expression = new IdentifierExp(ctx.getChild(0).getText());
				break;
			}
			}
			break;
		} case 2: {
			Exp exp = (Exp) this.visit(ctx.exp(0));
			expression = new Not(exp);
			break;
		} case 3: {
			switch(ctx.exp().size()) {
			case 1: {
				if(ctx.getChild(0) instanceof ExpContext) {
					Exp exp = (Exp) this.visit(ctx.exp(0));
					expression = new ArrayLength(exp);
				} else {
					Exp exp = (Exp) this.visit(ctx.exp(0));
					expression = exp;
				}
				break;
			} case 2: {
				Exp exp1 = (Exp) this.visit(ctx.exp(0)), exp2 = (Exp) this.visit(ctx.exp(1));
				switch(ctx.getChild(1).getText()) {
				case "&": {
					expression = new And(exp1, exp2);
					break;
				} case "<": {
					expression = new LessThan(exp1, exp2);
					break;
				} case "+": {
					expression = new Plus(exp1, exp2);
					break;
				} case "-": {
					expression = new Minus(exp1, exp2);
					break;
				} case "*": {
					expression = new Times(exp1, exp2);
					break;
				}
				}
				break;
			}
			}
			break;
		} case 4: {
			if(ctx.exp().size() == 2) {
				Exp exp1 = (Exp) this.visit(ctx.exp(0)), exp2 = (Exp) this.visit(ctx.exp(1));
				expression = new ArrayLookup(exp1, exp2);
			} else {
				Identifier id = (Identifier) this.visit(ctx.identifier());
				expression = new NewObject(id);
			}
			break;
		} default: {
			if(ctx.getChild(0) instanceof ExpContext) {
				Exp exp = (Exp) this.visit(ctx.exp(0));
				Identifier id = (Identifier) this.visit(ctx.identifier());
				ExpList el = new ExpList();
				for(int i = 1; i < ctx.exp().size(); ++i)
					el.addElement((Exp) this.visit(ctx.exp(i)));
				expression = new Call(exp, id, el);
			} else {
				Exp exp = (Exp) this.visit(ctx.exp(0));
				expression = new NewArray(exp);
			}
			break;
		}
		}
		return expression;
	}

	@Override
	public Object visitVarDecl(VarDeclContext ctx) {
		Type t = (Type) this.visit(ctx.type());
		Identifier id = (Identifier) this.visit(ctx.identifier());
		return new VarDecl(t, id);
	}

	@Override
	public Object visitClassDeclaration(ClassDeclarationContext ctx) {
		Identifier id_class = (Identifier) this.visit(ctx.identifier(0));
		Identifier id_extends = null;
		ClassDecl cd = null;
		
		if(ctx.identifier().size() > 1) id_extends = (Identifier) this.visit(ctx.identifier(1));
		
		VarDeclList vl = new VarDeclList();
		MethodDeclList ml = new MethodDeclList();
		
		for(VarDeclContext vdc : ctx.varDecl()) vl.addElement((VarDecl)this.visit(vdc));
		
		for(MethodDeclContext mdc : ctx.methodDecl()) ml.addElement((MethodDecl)this.visit(mdc));		
		
		if(id_extends != null) cd = new ClassDeclExtends(id_class,id_extends,vl,ml);
		else cd = new ClassDeclSimple(id_class, vl, ml);
		
		return cd;
	}

	@Override
	public Object visitIdentifier(IdentifierContext ctx) {
		String s = ctx.getText();
		return new Identifier(s);
	}

	@Override
	public Object visitType(TypeContext ctx) {
		Type t = null;
		
		if(ctx.getChild(0).toString().equals("int")) {
			if(ctx.getChildCount() > 1) t = new IntArrayType();
			else t = new IntegerType();
		} else if(ctx.getChild(0).toString().equals("boolean")) t = new BooleanType();
		else t = new IdentifierType(ctx.getText());
		
		return t;
	}
}
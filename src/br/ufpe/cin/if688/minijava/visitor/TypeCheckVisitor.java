package br.ufpe.cin.if688.minijava.visitor;

import br.ufpe.cin.if688.minijava.ast.And;
import br.ufpe.cin.if688.minijava.ast.ArrayAssign;
import br.ufpe.cin.if688.minijava.ast.ArrayLength;
import br.ufpe.cin.if688.minijava.ast.ArrayLookup;
import br.ufpe.cin.if688.minijava.ast.Assign;
import br.ufpe.cin.if688.minijava.ast.Block;
import br.ufpe.cin.if688.minijava.ast.BooleanType;
import br.ufpe.cin.if688.minijava.ast.Call;
import br.ufpe.cin.if688.minijava.ast.ClassDeclExtends;
import br.ufpe.cin.if688.minijava.ast.ClassDeclSimple;
import br.ufpe.cin.if688.minijava.ast.Exp;
import br.ufpe.cin.if688.minijava.ast.False;
import br.ufpe.cin.if688.minijava.ast.Formal;
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
import br.ufpe.cin.if688.minijava.ast.Minus;
import br.ufpe.cin.if688.minijava.ast.NewArray;
import br.ufpe.cin.if688.minijava.ast.NewObject;
import br.ufpe.cin.if688.minijava.ast.Not;
import br.ufpe.cin.if688.minijava.ast.Plus;
import br.ufpe.cin.if688.minijava.ast.Print;
import br.ufpe.cin.if688.minijava.ast.Program;
import br.ufpe.cin.if688.minijava.ast.This;
import br.ufpe.cin.if688.minijava.ast.Times;
import br.ufpe.cin.if688.minijava.ast.True;
import br.ufpe.cin.if688.minijava.ast.Type;
import br.ufpe.cin.if688.minijava.ast.VarDecl;
import br.ufpe.cin.if688.minijava.ast.While;
import br.ufpe.cin.if688.minijava.symboltable.Method;
import br.ufpe.cin.if688.minijava.symboltable.SymbolTable;
import br.ufpe.cin.if688.minijava.symboltable.Class;

public class TypeCheckVisitor implements IVisitor<Type> {

	private SymbolTable symbolTable;
	private Class currClass;
	private Method currMethod;

	public TypeCheckVisitor(SymbolTable st) {
		symbolTable = st;
	}

	// MainClass m;
	// ClassDeclList cl;
	public Type visit(Program n) {
		n.m.accept(this);
		for (int i = 0; i < n.cl.size(); i++) {
			n.cl.elementAt(i).accept(this);
		}
		return null;
	}

	// Identifier i1,i2;
	// Statement s;
	public Type visit(MainClass n) {
		currClass = symbolTable.getClass(n.i1.s);
		
		n.i1.accept(this);
		n.i2.accept(this);
		n.s.accept(this);
		return null;
	}

	// Identifier i;
	// VarDeclList vl;
	// MethodDeclList ml;
	public Type visit(ClassDeclSimple n) {
		currClass = symbolTable.getClass(n.i.s);
		
		n.i.accept(this);
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
		}
		return null;
	}

	// Identifier i;
	// Identifier j;
	// VarDeclList vl;
	// MethodDeclList ml;
	public Type visit(ClassDeclExtends n) {
		currClass = symbolTable.getClass(n.i.s);
		
		n.i.accept(this);
		n.j.accept(this);
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
		}
		return null;
	}

	// Type t;
	// Identifier i;
	public Type visit(VarDecl n) {
		n.t.accept(this);
		n.i.accept(this);
		return null;
	}

	// Type t;
	// Identifier i;
	// FormalList fl;
	// VarDeclList vl;
	// StatementList sl;
	// Exp e;
	public Type visit(MethodDecl n) {
		currMethod = symbolTable.getMethod(n.i.s, currClass.getId());
		
		n.t.accept(this);
		n.i.accept(this);
		for (int i = 0; i < n.fl.size(); i++) {
			n.fl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.sl.size(); i++) {
			n.sl.elementAt(i).accept(this);
		}

		Type tipo = n.e.accept(this);
		if(!(symbolTable.compareTypes(n.t, tipo))) 
			throw new RuntimeException("O método " + n.i.s + " retorna o tipo " + tipo.toString() + " tipo esperado " + n.t.toString());
		return null;
	}

	// Type t;
	// Identifier i;
	public Type visit(Formal n) {
		n.t.accept(this);
		n.i.accept(this);
		return null;
	}

	public Type visit(IntArrayType n) {
		return n;
	}

	public Type visit(BooleanType n) {
		return n;
	}

	public Type visit(IntegerType n) {
		return n;
	}

	// String s;
	public Type visit(IdentifierType n) {
		if(!(symbolTable.containsClass(n.s))) 
			throw new RuntimeException("A classe " + n.s + " não existe na tabela.");
		return n;
	}

	// StatementList sl;
	public Type visit(Block n) {
		for (int i = 0; i < n.sl.size(); i++) {
			n.sl.elementAt(i).accept(this);
		}
		return null;
	}

	// Exp e;
	// Statement s1,s2;
	public Type visit(If n) {
		if(!verifyBooleanType(n.e)) throw new RuntimeException("Expressão inválida.");
		n.e.accept(this);
		n.s1.accept(this);
		n.s2.accept(this);
		return null;
	}

	// Exp e;
	// Statement s;
	public Type visit(While n) {
		if(!verifyBooleanType(n.e)) throw new RuntimeException("Expressão inválida.");
		n.e.accept(this);
		n.s.accept(this);
		return null;
	}

	// Exp e;
	public Type visit(Print n) {
		n.e.accept(this);
		return null;
	}

	// Identifier i;
	// Exp e;
	public Type visit(Assign n) {
		Type tipoI = symbolTable.getVarType(currMethod, currClass, n.i.s);
		Type tipoE = n.e.accept(this);
		
		if(!symbolTable.compareTypes(tipoI, tipoE))
			throw new RuntimeException("Atribuição com tipos diferentes. O ID " + n.i.s + " espera um tipo " + tipoI.toString() + " mas foi assinalado " + tipoE.toString());
		
		n.i.accept(this);
		return null;
	}

	// Identifier i;
	// Exp e1,e2;
	public Type visit(ArrayAssign n) {
		Type tipoI = symbolTable.getVarType(currMethod, currClass, n.i.s);
		if(!(tipoI instanceof IntArrayType))
			throw new RuntimeException("O ID " + n.i.s + " não é do tipo esperado: IntArrayType");
		
		n.i.accept(this);
		
		Type tipoIndex = n.e1.accept(this);		
		if(!(tipoIndex instanceof IntegerType))
			throw new RuntimeException("O indice não é do tipo IntegerType");
		
		Type tipoValor = n.e2.accept(this);
		if(!(tipoValor instanceof IntegerType))
			throw new RuntimeException("O valor não é do tipo IntegerType");
		return null;
	}

	// Exp e1,e2;
	public Type visit(And n) {
		Type exp1 = n.e1.accept(this);
		Type exp2 = n.e2.accept(this);
		
		if(!(symbolTable.compareTypes(exp1, exp2)))
			throw new RuntimeException("As expressões " + n.e1.toString() + " e " + n.e2.toString() + "não são do mesmo tipo.");
		
		if(!(exp1 instanceof BooleanType))
			throw new RuntimeException(n.e1.toString() + " não são do tipo BooleanType");
		if(!(exp2 instanceof BooleanType))
			throw new RuntimeException(n.e2.toString() + " não são do tipo BooleanType");
		
		return new BooleanType();
	}

	// Exp e1,e2;
	public Type visit(LessThan n) {
		Type exp1 = n.e1.accept(this);
		Type exp2 = n.e2.accept(this);
		
		if(!(symbolTable.compareTypes(exp1, exp2)))
			throw new RuntimeException("As expressões " + n.e1.toString() + " e " + n.e2.toString() + "não são do mesmo tipo.");
		
		if(!(exp1 instanceof IntegerType))
			throw new RuntimeException(n.e1.toString() + " não são do tipo IntegerType");
		if(!(exp2 instanceof IntegerType))
			throw new RuntimeException(n.e2.toString() + " não são do tipo IntegerType");
		return new BooleanType();
	}

	// Exp e1,e2;
	public Type visit(Plus n) {
		Type exp1 = n.e1.accept(this);
		Type exp2 = n.e2.accept(this);
		
		if(!(symbolTable.compareTypes(exp1, exp2)))
			throw new RuntimeException("As expressões " + n.e1.toString() + " e " + n.e2.toString() + "não são do mesmo tipo.");
		
		if(!(exp1 instanceof IntegerType))
			throw new RuntimeException(n.e1.toString() + " não são do tipo IntegerType");
		if(!(exp2 instanceof IntegerType))
			throw new RuntimeException(n.e2.toString() + " não são do tipo IntegerType");
		return new IntegerType();
	}

	// Exp e1,e2;
	public Type visit(Minus n) {
		Type exp1 = n.e1.accept(this);
		Type exp2 = n.e2.accept(this);
		
		if(!(symbolTable.compareTypes(exp1, exp2)))
			throw new RuntimeException("As expressões " + n.e1.toString() + " e " + n.e2.toString() + "não são do mesmo tipo.");
		
		if(!(exp1 instanceof IntegerType))
			throw new RuntimeException(n.e1.toString() + " não são do tipo IntegerType");
		if(!(exp2 instanceof IntegerType))
			throw new RuntimeException(n.e2.toString() + " não são do tipo IntegerType");
		return new IntegerType();
	}

	// Exp e1,e2;
	public Type visit(Times n) {
		Type exp1 = n.e1.accept(this);
		Type exp2 = n.e2.accept(this);
		
		if(!(symbolTable.compareTypes(exp1, exp2)))
			throw new RuntimeException("As expressões " + n.e1.toString() + " e " + n.e2.toString() + "não são do mesmo tipo.");
		
		if(!(exp1 instanceof IntegerType))
			throw new RuntimeException(n.e1.toString() + " não são do tipo IntegerType");
		if(!(exp2 instanceof IntegerType))
			throw new RuntimeException(n.e2.toString() + " não são do tipo IntegerType");
		return new IntegerType();
	}

	// Exp e1,e2;
	public Type visit(ArrayLookup n) {
		Type exp1 = n.e1.accept(this);
		Type exp2 = n.e2.accept(this);
		
		if(!(exp1 instanceof IntArrayType))
			throw new RuntimeException(n.e1.toString() + " não são do tipo IntArrayType");
		if(!(exp2 instanceof IntegerType))
			throw new RuntimeException(n.e2.toString() + " não são do tipo IntegerType");
		return new IntegerType();
	}

	// Exp e;
	public Type visit(ArrayLength n) {
		Type exp1 = n.e.accept(this);
		
		if(!(exp1 instanceof IntArrayType))
			throw new RuntimeException(n.e.toString() + " não são do tipo IntArrayType");
		return new IntegerType();
	}

	// Exp e;
	// Identifier i;
	// ExpList el;
	public Type visit(Call n) {
		Type tipoE = n.e.accept(this);
		n.i.accept(this);
		
		if(tipoE instanceof IdentifierType){
			String s = ((IdentifierType)tipoE).s;
			if(symbolTable.containsClass(s)) {
				Class c = symbolTable.getClass(s);
				if(c.containsMethod(n.i.s)) {
					Method m = c.getMethod(n.i.s);
					
					for (int i = 0; i < n.el.size(); i++) {
						if(!symbolTable.compareTypes(n.el.elementAt(i).accept(this), m.getParamAt(i).type())){
							throw new RuntimeException("Parametros incorretos no metodo " + m.getId());
						}
					}
					
					return m.type();
				} else throw new RuntimeException("O método " + n.i.s + " não existe.");
			} else {
				throw new RuntimeException("A classe " + s + " não existe na tabela.");
			}
		} else 
			throw new RuntimeException("O identificador não tem o tipo correto.");
	}

	// int i;
	public Type visit(IntegerLiteral n) {
		return new IntegerType();
	}

	public Type visit(True n) {
		return new BooleanType();
	}

	public Type visit(False n) {
		return new BooleanType();
	}

	// String s;
	public Type visit(IdentifierExp n) {
		return symbolTable.getVarType(currMethod, currClass, n.s);
	}

	public Type visit(This n) {
		return currClass.type();
	}

	// Exp e;
	public Type visit(NewArray n) {
		Type tipoE = n.e.accept(this);
		
		if(!(tipoE instanceof IntegerType))
			throw new RuntimeException("O tipo esperado da expressão é um IntegerType.");
		
		return new IntArrayType();
	}

	// Identifier i;
	public Type visit(NewObject n) {
		if(symbolTable.containsClass(n.i.s))
			return symbolTable.getClass(n.i.s).type();
		else 
			throw new RuntimeException("A classe " + n.i.s + " não existe.");

	}

	// Exp e;
	public Type visit(Not n) {
		Type tipoE = n.e.accept(this);
		if(tipoE instanceof BooleanType) return new BooleanType(); 
		else 
			throw new RuntimeException("O tipo esperado da expressão é um BooleanType.");
	}

	// String s;
	public Type visit(Identifier n) {
		return new IdentifierType(n.s);
	}
	
	private boolean verifyBooleanType(Exp e) {
		if(e instanceof And 
			|| e instanceof False
			|| e instanceof LessThan
			|| e instanceof Not
			|| e instanceof True) return true;
		
		return false;
	}
}

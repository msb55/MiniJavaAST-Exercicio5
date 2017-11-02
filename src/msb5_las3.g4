grammar msb5_las3;

program: mainclass (classDeclaration)* EOF;

mainclass: 'class' identifier '{' 'public' 'static' 'void' 'main' '(' 'String' '[' ']' identifier ')' '{' statement '}' '}';

classDeclaration : 'class' identifier ( 'extends' identifier )? '{' ( varDecl )* ( methodDecl )* '}';

varDecl : type identifier ';';

methodDecl : 'public' type identifier '(' ( type identifier ( ',' type identifier )* )? ')' '{' ( varDecl )* ( statement )* 'return' exp ';' '}';

type : 'int' '[' ']'
| 'boolean'
| 'int'
| identifier;

statement : '{' ( statement )* '}'
| 'if' '(' exp ')' statement 'else' statement
| 'while' '(' exp ')' statement
| 'System.out.println' '(' exp ')' ';'
| identifier '=' exp ';'
| identifier '[' exp ']' '=' exp ';';

exp : exp ( '&&' | '<' | '+' | '-' | '*' ) exp
| exp '[' exp ']'
| exp '.' 'length'
| exp '.' identifier '(' ( exp ( ',' exp )* )? ')'
| INTEGER_LITERAL
| 'true'
| 'false'
| IDENTIFIER
| 'this'
| 'new' 'int' '[' exp ']'
| 'new' identifier '(' ')'
| '!' exp
| '(' exp ')';

identifier: IDENTIFIER;
IDENTIFIER: [a-zA-Z_]([a-zA-Z0-9_])*;

INTEGER_LITERAL : ([0-9])+;
WHITE_SPACE: [ \r\t\n] -> skip;
SINGLE_LINE_COMMENT: '//'(~[\n\r])* -> skip;
MULTI_LINE_COMENT: '/*' .*? '*/' -> skip;
// Define a grammar for simplified Modelica files
grammar Mo;

@header
{
	import java.util.List;
	import java.util.Arrays;

	import org.power_systems_modelica.psm.modelica.*;
}

@members
{
	public ModelicaDocument modelicaDocument = new ModelicaDocument();
}

document
   : within model
   ;

within
   : 'within' ID? ';'
{
	modelicaDocument.setWithin($ID.text);
}
   ;

model
   : 'model' ID
{
	ModelicaSystemModel systemModel = new ModelicaSystemModel($ID.text);
	modelicaDocument.setSystemModel(systemModel);
}
   declaration_stmt_list 'equation' equation_stmt_list 'end' ID ';'
   ;

declaration_stmt_list
   : ( declaration_stmt ';' )*
   ;

equation_stmt_list
   : ( equation_stmt ';' )*
   ;

declaration_stmt locals [boolean isParameter = false]
   : ( 'parameter'
{
   $isParameter = true;
}
   | )
   type_name instantiation annotation?
{
	String type = $type_name.text;
	String id = $instantiation.id;
	ModelicaDeclaration declaration;
	if ($instantiation.arguments != null)
		declaration = new ModelicaDeclaration(type, id, $instantiation.arguments, $isParameter);
	else
		declaration = new ModelicaDeclaration(type, id, $instantiation.value, $isParameter);
	modelicaDocument.getSystemModel().addDeclaration(declaration);
}
   ;

instantiation returns [ String id, Object value, List<ModelicaArgument> arguments ]
   : ID
{
	$id = $ID.text;
	$value = null;
}
   | ( ID '=' argument_value
{
	$id = $ID.text;
	$value = $argument_value.text;
}
   | ID '(' instantiation_argument_list ')'
{
	$id = $ID.text;
	$value = null;
	$arguments = $instantiation_argument_list.arguments;
}
   )
   ;

type_name
   : ID
   ;

instantiation_argument_list returns [ List<ModelicaArgument> arguments = new ArrayList<>() ]
   : ( instantiation_argument
{
ModelicaArgument argument = $instantiation_argument.argument;
if (argument != null) $arguments.add(argument);
}
   ','? )*
   ;

instantiation_argument returns [ ModelicaArgument argument ]
   : instantiation
{
if ($instantiation.arguments == null) $argument = new ModelicaArgument($instantiation.id, $instantiation.value);
// FIXME Allow complex arguments (Modelica argument accepts and id and a list of Modelica arguments instead of a single value)
// As an example:
//iPSL.Electrical.Loads.PSSE.Load load_load__f17696e0_9aeb_11e5_91da_b8763fd99c5f (
//	 S_p(re=2.63322, im=0.89094),
//	 S_i(re=0, im=0),
//	 S_y(re=0, im=0),
//	 a(re=1, im=0),
//	 b(re=0, im=1),
//	 V_0 = 1.0,
//	 PQBRAK = 0.7,
}
   ;

argument_name
   : ID
   ;

argument_value
   : ( NUMBER | STRING | BOOLEAN | ID )
   ;

equation_stmt
   : equation_connect_stmt
   | equal_stmt
{
	ModelicaEquation equation = new ModelicaEquation($equal_stmt.text);
	modelicaDocument.getSystemModel().addEquation(equation);
}
   ;

equation_connect_stmt
locals [String ref1, String ref2]
@after
{
	ModelicaConnect eq = new ModelicaConnect($ref1,$ref2);
	modelicaDocument.getSystemModel().addEquation(eq);
}
   : 'connect' '(' ID
{
$ref1 = $ID.text;
}
   ',' ID ')' annotation?
{
$ref2 = $ID.text;
}
   ;

equal_stmt
   : algebraic_expression '=' algebraic_expression
   ;

algebraic_expression:
   ( NUMBER | ID | ALGEBRAIC_SYMBOL | '(' | ')' )*
   ;

ALGEBRAIC_SYMBOL:
   ( '*' | '/' | '+' )
   ;

annotation
   : 'annotation' '(' annotation_content ')'
   ;

annotation_content
   : ( STRING | ID '(' instantiation_argument_list ')' )
   ;

BOOLEAN
   : ( 'true' | 'false' )
   ;

/** "a numeral [-]?(.[0-9]+ | [0-9]+(.[0-9]*)? )" */ NUMBER
   : '-'? ( '.' DIGIT+ | DIGIT+ ( '.' DIGIT* )? ) ( ('e'|'E') '-'? DIGIT+ )?
   ;


fragment DIGIT
   : [0-9]
   ;


fragment DOT
   : [\.]
   ;

/** "any double-quoted string ("...") possibly containing escaped quotes" */ STRING
   : '"' ( '\\"' | . )*? '"'
   ;


/** "Any string of alphabetic ([a-zA-Z\200-\377]) characters, underscores
 *  ('_'), dots or digits ([0-9]), not beginning with a digit"
 */ ID
   : LETTER ( LETTER | DIGIT | DOT )*
   ;

fragment LETTER
   : [a-zA-Z\u0080-\u00FF_]
   ;

LINE_COMMENT
   : '//' .*? '\r'? '\n' -> skip
   ;

WS
   : [ \t\n\r]+ -> skip
   ;

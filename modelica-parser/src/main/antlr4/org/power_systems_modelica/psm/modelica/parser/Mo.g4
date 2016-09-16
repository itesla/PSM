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
   : 'parameter'?
{
	$isParameter = true;
}
   type_name instantiation annotation?
{
	String type = $type_name.text;
	String id = $instantiation.id;
	ModelicaDeclaration declaration;
	if ($instantiation.isAssignment) declaration = new ModelicaDeclaration(type, id, $instantiation.value, $isParameter);
	else declaration = new ModelicaDeclaration(type, id, $instantiation.arguments, $isParameter);
	modelicaDocument.getSystemModel().addDeclaration(declaration);
}
   ;

instantiation returns [ String id, boolean isAssignment, String value, List<ModelicaArgument> arguments ]
   : ( ID '=' argument_value
{
	$id = $ID.text;
	$isAssignment = true;
	$value = $argument_value.text;
}
   | ID '(' instantiation_argument_list ')'
{
	$id = $ID.text;
	$isAssignment = false;
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
if ($instantiation.isAssignment) $argument = new ModelicaArgument($instantiation.id, $instantiation.value);
// FIXME allow complex arguments
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
locals [String ref1, String ref2]
@after
{
	ModelicaEquation equation = new ModelicaConnect($ref1,$ref2);
	modelicaDocument.getSystemModel().addEquation(equation);
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

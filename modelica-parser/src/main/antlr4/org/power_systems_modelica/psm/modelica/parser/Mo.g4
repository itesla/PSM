/*
 * #%L
 * Commons
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

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

equation_stmt_list
   : ( equation_stmt ';'
   | annotation
{
	if ($annotation.ctx != null && !$annotation.a.isEmpty()) modelicaDocument.getSystemModel().getAnnotation().addItems($annotation.a.getItems());
}
   ';' )*
   ;

declaration_stmt_list
   : ( declaration_stmt ';'
   | annotation
{
	if ($annotation.ctx != null && !$annotation.a.isEmpty()) modelicaDocument.getSystemModel().getAnnotation().addItems($annotation.a.getItems());
}
   ';' )*
   ;

declaration_stmt locals [boolean isParameter = false]
   : ( 'parameter'
{
   $isParameter = true;
}
   | )
   qualifier? type_name instantiation annotation?
{
	String type = $type_name.text;
	if ($qualifier.ctx != null) type = $qualifier.text + " " + $type_name.text;
	String id = $instantiation.id;
	Annotation annotation = null;
	if ($annotation.ctx != null && !$annotation.a.isEmpty()) annotation = $annotation.a;
	BaseModelicaDeclaration declaration;
	if ($instantiation.arguments != null)
		declaration = new ModelicaDeclaration(type, id, $instantiation.arguments, $isParameter, annotation);
	else
		declaration = new ModelicaAssignment(type, id, $instantiation.value, $isParameter, annotation);
	modelicaDocument.getSystemModel().addDeclaration(declaration);
}
   ;
   
qualifier: ('inner' | 'outer')
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
   : ( NUMBER | STRING | BOOLEAN | ID | any_array )
   ;

equation_stmt
   : equation_connect_stmt
   | equal_stmt
   ;

equation_connect_stmt
locals [String ref1, String ref2, Annotation eqa]
@after
{
	ModelicaConnect eq = new ModelicaConnect($ref1,$ref2);
	if ($eqa != null) eq.setAnnotation($eqa);
	modelicaDocument.getSystemModel().addEquation(eq);
}
   : 'connect' '(' ID
{
	$ref1 = $ID.text;
}
   ',' ID ')' annotation?
{
	$ref2 = $ID.text;
	$eqa = null;
	if ($annotation.ctx != null && !$annotation.a.isEmpty()) $eqa = $annotation.a;
}
   ;

equal_stmt
locals [String left, String right]
@after
{
	// Re-expand the spaces that have been eaten by the parser
	String text = $left.concat(" = ").concat($right);
    text = text.replace("+", " + ");
	text = text.replace("/", " / ");
	ModelicaEquation equation = new ModelicaEquation(text);
	modelicaDocument.getSystemModel().addEquation(equation);
}
   : algebraic_expression
{
	$left = $algebraic_expression.text;
}
   '='
   algebraic_expression
{
	$right = $algebraic_expression.text;
}
   ;

algebraic_expression:
   ( NUMBER | ID | ALGEBRAIC_SYMBOL | '(' | ')' )*
   ;

annotation returns [ Annotation a = new Annotation() ]
	: 'annotation' '(' annotation_items ')'
{
	$a.addItems($annotation_items.items);
}
   ;

annotation_items returns [ List<AnnotationItem> items = new ArrayList<>() ]
   : ( annotation_name '(' instantiation_argument_list ')'
{
	String itemText = String.format("%s(%s)", $annotation_name.text, $instantiation_argument_list.text);
	$items.add(new AnnotationItem(itemText));
}
   ','? )+
   ;

annotation_name
   : (STRING | ID)
   ;

ALGEBRAIC_SYMBOL
   : ( '*' | '/' | '+' )
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
 */
 // Consider array accessors part of the identifier (arrays of connections in omegaRef)
 ID
   : LETTER ( LETTER | DIGIT | DOT | '[' | ']')*
   ;

any_array : '{' any_array_content '}'
{
	// System.out.println("any_array: " + $any_array_content.text);
}
   ;

any_array_content
   : ( argument_value ','? )*
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

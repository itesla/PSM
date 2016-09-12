// Define a grammar for simplified Modelica files
grammar Mo;

@header {
import java.util.List;
import java.util.Arrays;

import org.power_systems_modelica.psm.modelica.*;
}

@members {
public ModelicaDocument modelicaDocument = new ModelicaDocument();
List<ModelicaArgument> arguments;
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
   model_stmt_list 'equation' equation_stmt_list 'end' ID ';'
   ;   

model_stmt_list
   : ( model_stmt ';' )* 
   ;
   
equation_stmt_list
   : ( equation_stmt ';' )*
   ;
      
model_stmt
   : ( parameter_stmt | model_instantiation_stmt )
   ;

parameter_stmt 
   : 'parameter' parameter_type parameter_name '=' value
{
ModelicaParameter parameter = new ModelicaParameter(ModelicaType.valueOf($parameter_type.text),$parameter_name.text,$value.text);
modelicaDocument.getSystemModel().addParameters(Arrays.asList(parameter));
}
   ;
   
parameter_type
   : ID
   ;
   
parameter_name
   : ID
   ;
      
model_instantiation_stmt
locals [String type, String name]
@init
{
arguments = new ArrayList<ModelicaArgument>();
}
@after
{
ModelicaModelInstantiation modelInstantiation = new ModelicaModelInstantiation($type,$name,arguments);
modelicaDocument.getSystemModel().addModelInstantiations(Arrays.asList(modelInstantiation));
}
   : model_type_name model_instantiation annotation?
{
$type = $model_type_name.text;
$name = $model_instantiation.name;
}
   ;

model_instantiation returns [String name]
   : ID '(' instantiation_argument_list ')'
{
$name = $ID.text;
}
   ;
   
model_type_name
   : ID
   ;

instantiation_argument_list
   : ( instantiation_argument ','? )*
   ;

instantiation_argument
   : ( argument_name '=' value
{
ModelicaArgument argument = new ModelicaArgument($argument_name.text,$value.text);
arguments.add(argument);
} 
   | model_instantiation )
   ;
   
argument_name
   : ID
   ;
            
value
   : ( NUMBER | STRING | BOOLEAN | ID )
   ;
               
equation_stmt  
locals [String ref1, String ref2]
@after
{
ModelicaEquation equation = new ModelicaConnect($ref1,$ref2);
modelicaDocument.getSystemModel().addEquations(Arrays.asList(equation));
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
   : ( STRING | model_instantiation )
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

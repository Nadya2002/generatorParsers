grammar Grammar;

start : header? startRule grammarRule+ EOF;

header : HEADER '[' importOne (importOne)* ']' ;

importOne : 'import' IMPORT;

startRule : '->' PARSID;

grammarRule : parseRule | lexerRule;

parseRule : PARSID arguments? type? '->' actions ';' ;

arguments : '(' argument (',' argument)* ')' ;

argument : PARSID type ;

type : ':' LEXID ;

lexerRule :
    LEXID '->' terminal ';'   # term
    | LEXID '=>' terminal ';'  # skip ;

terminal : STRING | REGEXP;

actions : action ('|' action)* ;

action : (elements)*;

elements : element | CODE ;

element : LEXID
    | PARSID attributes? ;

attributes : '(' PARSID (',' PARSID)* ')' ;



LEXID : [A-Z][a-zA-Z0-9<>,]*;
PARSID : [a-z][a-zA-Z0-9]*;
REGEXP : '\'' .*? '\'';
STRING: '"' .*? '"';
CODE : '{' .*? '}';
WS : [ \t\r\n]+ -> skip ;

HEADER : '@header';
IMPORT : [a-zA-Z.]+;
grammar EugeneRules;

@lexer::header{
    package org.cidarlab.phoenix.dom.library.rules;
}
@parser::header{
    package org.cidarlab.phoenix.dom.library.rules;
}


eugeneRule
    : left=VARIABLE erule=op right=VARIABLE
    ;

op 
    : 'NOTWITH'
    | 'notwith'
    | 'BEFORE'
    | 'before'
    | 'AFTER'
    | 'after'
    ;
       
VARIABLE : ([a-z]|[A-Z])([a-z]|[A-Z]|[0-9]|'_')*;
//WS : ( ' ' | '\t' | '\r' | '\n' )+ { skip(); };
//WS : [ \t\r\n]+ -> skip ;
WS: (' ' | '\t') -> skip;
NL: '\r'? '\n' -> skip;
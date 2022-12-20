package codigo;
import static codigo.Tokens.*;
%%
%class Lexer
%type Tokens

L=[a-zA-Z_]+
D=[0-9]+
Puntuacion = [;,]
Agrupacion = [\(\)]
OperadorArit = [-/\+\*]
OperadorAsig = [=]
Espacio=[ \t\r]+
SaltoLinea = [\n]

%{
    public String lexeme;
%}
%%

"//".* {/*Ignore*/}
{Espacio} {/*Ignore*/}
{Puntuacion} {lexeme=yytext(); return Puntuacion;}
{Agrupacion} {lexeme=yytext(); return Agrupacion;}
{OperadorArit} {lexeme=yytext(); return OperadorArit;}
{OperadorAsig} {lexeme=yytext(); return OperadorAsig;}
{SaltoLinea} {lexeme=yytext(); return SaltoLinea;}

int | float | char {lexeme=yytext(); return Reservada;}
{L}({L}|{D})* {lexeme=yytext(); return id;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return num;}
"$" {lexeme=yytext(); return Fin;}
 . {return ERROR;}

/**********************************************
Descri��o : A regra inserir facto tem a fun��o
de inserir novos factos na base de conhecimento
***********************************************/
inserir_facto(Facto) :-
         open('base de conhecimento.pl',append,Stream),
         write(Stream,Facto),  nl(Stream),
         close(Stream).
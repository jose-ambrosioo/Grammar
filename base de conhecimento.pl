:- discontiguous pergunta/3.
:- discontiguous classeGramatical/2.
:- discontiguous artigo/5.
:- discontiguous substantivo/5.
:- discontiguous verbo/4.
:- discontiguous sinalPontuacao/3.

/**********************************************
Descri��o : A regra frase tem a fun��o de
verificar se uma determinada afirma��o possui sujeito
e predicado
**********************************************/
frase(fra(Sujeito, Predicado)) --> sujeito(Numero, Sujeito),
                                   predicado(Numero, Predicado).

/**********************************************
Descri��o : A regra sujeito tem a fun��o de
verificar se uma determinad afirma��o possui
"sujeito". O sujeito � constituido por um artigo
seguido do substantivo.
**********************************************/
sujeito(Numero, suj(Artigo, Substantivo)) --> artigo(Numero, Genero, Artigo),
                                     substantivo(Numero, Genero, Substantivo).

/**********************************************
Descri��o : A regra predicado tem a fun��o de
verificar se uma determinada afirma��o possui
"predicado". O predicado � constituido por um
verbo seguido de um artigo e depois um
substantivo.
**********************************************/
predicado(Numero, pred(Verbo, Artigo, Substantivo)) --> verbo(Numero, Verbo),
                                             artigo(Numero2, Genero, Artigo),
                                   substantivo(Numero2, Genero, Substantivo).


/****************************************
Descri��o : Estrutura das pergunta e sua
respectiva resposta
*****************************************/
pergunta('a capital de angola � luanda') --> ['qual � a capital de angola ?'].

/*******************************************
Descri��o : Esta regra armazena as classes
gramaticais que o sistema reconhece inicialmente
ou seja antes de aprender novas classes gramaticais
********************************************/
classeGramatical --> [artigo].
classeGramatical--> [substantivo].
classeGramatical --> [verbo].
classeGramatical --> [sinalPontuacao].

/**********************************************
Descri��o : Palavras armazenadas por defeito no
na base de conhecimento
***********************************************/

artigo(singular, masculino, artigo(o)) --> [o].
artigo(plural,masculino,artigo(os)) --> [os].
artigo(singular,femenino,artigo(a)) --> [a].
artigo(plural,femenino,artigo(as)) --> [as].

verbo(singular, verbo(ca�ou)) --> [ca�ou].
verbo(plural,verbo(ca�aram)) --> [ca�aram].
verbo(singular,verbo(ca�a)) --> [ca�a].
verbo(plural,verbo(ca�am)) --> [ca�am].
verbo(singular,verbo(ca�ar�)) --> [ca�ar�].
verbo(plural,verbo(ca�ar�o)) --> [ca�ar�o].

substantivo(singular, masculino, substantivo(gato)) --> [gato].
substantivo(singular, masculino, substantivo(rato)) --> [rato].
substantivo(plural,masculino,substantivo(X)) --> [X], {member(X,[gatos,ratos])}.
substantivo(singular,femenino,substantivo(X)) --> [X], {member(X,[gata,rata])}.
substantivo(plural,femenino,substantivo(X)) --> [X], {member(X,[gatas,ratas])}.


sinalPontuacao(sinalPontuacao(?)) --> [?].
sinalPontuacao(sinalPontuacao(.)) --> [.].


/*************************************************
Novas regras ou factos v�o estar por baixo desse 
comentario
***************************************************/
substantivo(singular, masculino, substantivo(jo�o)) --> [jo�o].
verbo(singular, verbo(�)) --> [�].
substantivo(singular, masculino, substantivo(bom)) --> [bom].
substantivo(singular, masculino, substantivo(fato)) --> [fato].
substantivo(singular, masculino, substantivo(bonito)) --> [bonito].
substantivo(singular, masculino, substantivo(carro)) --> [carro].
:- discontiguous adjectivo/3.
classeGramatical --> [adjectivo].
adjectivo(adjectivo(veloz)) --> [veloz].
substantivo(plural, masculino, substantivo(carros)) --> [carros].
verbo(plural, verbo(s�o)) --> [s�o].
adjectivo(adjectivo(velozes)) --> [velozes].
substantivo(singular, masculino, substantivo(trator)) --> [trator].
substantivo(singular, masculino, substantivo(pesado)) --> [pesado].
verbo(singular, verbo(comeu)) --> [comeu].
pergunta('Sim') --> ['o gato comeu o rato ?'].

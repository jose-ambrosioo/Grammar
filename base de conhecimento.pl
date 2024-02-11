:- discontiguous pergunta/3.
:- discontiguous classeGramatical/2.
:- discontiguous artigo/5.
:- discontiguous substantivo/5.
:- discontiguous verbo/4.
:- discontiguous sinalPontuacao/3.

/**********************************************
Descrição : A regra frase tem a função de
verificar se uma determinada afirmação possui sujeito
e predicado
**********************************************/
frase(fra(Sujeito, Predicado)) --> sujeito(Numero, Sujeito),
                                   predicado(Numero, Predicado).

/**********************************************
Descrição : A regra sujeito tem a função de
verificar se uma determinad afirmação possui
"sujeito". O sujeito é constituido por um artigo
seguido do substantivo.
**********************************************/
sujeito(Numero, suj(Artigo, Substantivo)) --> artigo(Numero, Genero, Artigo),
                                     substantivo(Numero, Genero, Substantivo).

/**********************************************
Descrição : A regra predicado tem a função de
verificar se uma determinada afirmação possui
"predicado". O predicado é constituido por um
verbo seguido de um artigo e depois um
substantivo.
**********************************************/
predicado(Numero, pred(Verbo, Artigo, Substantivo)) --> verbo(Numero, Verbo),
                                             artigo(Numero2, Genero, Artigo),
                                   substantivo(Numero2, Genero, Substantivo).


/****************************************
Descrição : Estrutura das pergunta e sua
respectiva resposta
*****************************************/
pergunta('a capital de angola é luanda') --> ['qual é a capital de angola ?'].

/*******************************************
Descrição : Esta regra armazena as classes
gramaticais que o sistema reconhece inicialmente
ou seja antes de aprender novas classes gramaticais
********************************************/
classeGramatical --> [artigo].
classeGramatical--> [substantivo].
classeGramatical --> [verbo].
classeGramatical --> [sinalPontuacao].

/**********************************************
Descrição : Palavras armazenadas por defeito no
na base de conhecimento
***********************************************/

artigo(singular, masculino, artigo(o)) --> [o].
artigo(plural,masculino,artigo(os)) --> [os].
artigo(singular,femenino,artigo(a)) --> [a].
artigo(plural,femenino,artigo(as)) --> [as].

verbo(singular, verbo(caçou)) --> [caçou].
verbo(plural,verbo(caçaram)) --> [caçaram].
verbo(singular,verbo(caça)) --> [caça].
verbo(plural,verbo(caçam)) --> [caçam].
verbo(singular,verbo(caçará)) --> [caçará].
verbo(plural,verbo(caçarão)) --> [caçarão].

substantivo(singular, masculino, substantivo(gato)) --> [gato].
substantivo(singular, masculino, substantivo(rato)) --> [rato].
substantivo(plural,masculino,substantivo(X)) --> [X], {member(X,[gatos,ratos])}.
substantivo(singular,femenino,substantivo(X)) --> [X], {member(X,[gata,rata])}.
substantivo(plural,femenino,substantivo(X)) --> [X], {member(X,[gatas,ratas])}.


sinalPontuacao(sinalPontuacao(?)) --> [?].
sinalPontuacao(sinalPontuacao(.)) --> [.].


/*************************************************
Novas regras ou factos vão estar por baixo desse 
comentario
***************************************************/
substantivo(singular, masculino, substantivo(joão)) --> [joão].
verbo(singular, verbo(é)) --> [é].
substantivo(singular, masculino, substantivo(bom)) --> [bom].
substantivo(singular, masculino, substantivo(fato)) --> [fato].
substantivo(singular, masculino, substantivo(bonito)) --> [bonito].
substantivo(singular, masculino, substantivo(carro)) --> [carro].
:- discontiguous adjectivo/3.
classeGramatical --> [adjectivo].
adjectivo(adjectivo(veloz)) --> [veloz].
substantivo(plural, masculino, substantivo(carros)) --> [carros].
verbo(plural, verbo(são)) --> [são].
adjectivo(adjectivo(velozes)) --> [velozes].
substantivo(singular, masculino, substantivo(trator)) --> [trator].
substantivo(singular, masculino, substantivo(pesado)) --> [pesado].
verbo(singular, verbo(comeu)) --> [comeu].
pergunta('Sim') --> ['o gato comeu o rato ?'].

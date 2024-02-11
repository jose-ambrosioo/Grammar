/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import modelo.ResultadoModelo;
import org.jpl7.Query;
import org.jpl7.Term;
import modelo.Util;

/**
 *
 * @author Ademaro
 */
public class Prolog {
    
    
    public static String fraseProcessada(String fraseFormatada)
    {
        Util.conectarComFicheiroBaseDeConhecimento();
        
        Query consulta = new Query("frase(Resultado, ["+fraseFormatada+"], []).");
        consulta.hasMoreSolutions();        

        if(consulta.isOpen())
        {
            Map<String, Term> mapa = consulta.nextSolution();
            if(mapa != null)
            {
                String resultado = mapa.get("Resultado").toString();
                String aux = resultado;
                
                // Extrair apenas as palavras da strfrase
                aux = aux.replace("fra(suj(", "");
                aux = aux.replace(" pred(", "");
                String[] palavras = aux.split(","); 
                for(int pos = 0; pos < palavras.length; pos++)
                {
                    palavras[pos] = palavras[pos].substring(palavras[pos].indexOf("(")+1,palavras[pos].indexOf(")"));
                }
                
                return RespostaComSujeito_e_Predidcaco(resultado) + RespostaComAsClassesGramaticiais(palavras);
            }
        }
        else
        {
            String[] palavras = fraseFormatada.split(","); 
            return RespostaComAsClassesGramaticiais(palavras);
        }
        
        return "";
    }

    private static String RespostaComSujeito_e_Predidcaco(String resultado)
    {
        if(resultado.contains("fra(suj(") && resultado.contains(")), pred("))
        {
            String[] strTemp = resultado.split("\\)\\), pred\\("); /*Refere-se a essa string :  ")), pred(" */
            String sujeito, predicado;
            sujeito = strTemp[0];
            predicado = strTemp[1];
            
            sujeito = sujeito.replace("fra(suj(artigo(", "");
            sujeito = sujeito.replace("), substantivo(", " ");
            
            predicado = predicado.replace("verbo(", "");
            predicado = predicado.replace("), artigo(", " ");
            predicado = predicado.replace("), substantivo(", " ");
            predicado = predicado.replace(")))", "");
                    
            
            return Util.formatarRespostaComSujeito_e_Predicado(sujeito, predicado);
        }   
        return "";
    }
    
    private static String RespostaComAsClassesGramaticiais(String[] palavras)
    {
        ArrayList<ResultadoModelo> respostas = new ArrayList<>();
        
        Util.conectarComFicheiroBaseDeConhecimento();
                 
        List<String> listaClassesGramaticais = Util.retornarTodasClassesGramaticais();
        
        // Percorre cada uma das classes gramatiais 
        for(String classeGram : listaClassesGramaticais)
        {
            ResultadoModelo respTemp = new ResultadoModelo();
            
            // Percorre cada palavra e verifica se pertence a classe gramatical em foco
            for(int x = 0; x < palavras.length; x++)
            {
                Query classeGramatical = Util.retornarQueryDaClasseGramatical(classeGram, palavras[x]);
                
//                if(classeGram.equalsIgnoreCase("artigo") || classeGram.equalsIgnoreCase("substantivo")){
//                    classeGramatical= new Query(classeGram+"(_, _, A, ["+palavras[x]+"], []).");                
//                }else if(classeGram.equalsIgnoreCase("verbo")){
//                    classeGramatical= new Query(classeGram+"(_, A, ["+palavras[x]+"], []).");
//                }else{
//                    classeGramatical= new Query(classeGram+"(A, ["+palavras[x]+"], []).");
//                }
                
                classeGramatical.hasMoreSolutions();        
                if(classeGramatical.isOpen())   
                     respTemp.inserirPalavra(palavras[x]);
            }
            
            if(respTemp.getPalavras().size() > 0)
            {
                respTemp.setClasseGramatical(classeGram);                
                respostas.add(respTemp);
            }
        }
       return Util.formatarRespostaComAsClassesGramaticiais(respostas);
    }
    
    public static void salvarNovoTermo(String termo, String classeGramatical)
    {
        Util.conectarComFicheiroMotorDeInferencia();
        String str = classeGramatical+"("+classeGramatical+"("+termo+")) --> ["+termo+"].";         
        Query consulta = new Query("inserir_facto('"+str+"').");
        consulta.hasSolution();       
    }
   
    public static void salvarNovoTermoComNumero_e_Genero(String termo, String classeGramatical, String numero, String genero)
    {
        Util.conectarComFicheiroMotorDeInferencia();
        String str = classeGramatical+"("+numero+", "+genero+", "+classeGramatical+"("+termo+")) --> ["+termo+"].";         
        Query consulta = new Query("inserir_facto('"+str+"').");
        consulta.hasSolution();       
    }
    
    public static void salvarNovoTermoComNumero(String termo, String classeGramatical, String numero)
    {
        Util.conectarComFicheiroMotorDeInferencia();
        String str = classeGramatical+"("+numero+", "+classeGramatical+"("+termo+")) --> ["+termo+"].";         
        Query consulta = new Query("inserir_facto('"+str+"').");
        consulta.hasSolution();       
    }
    
    public static void salvarNovaClasseGramatical(String classeGramatical)
    {
        Util.conectarComFicheiroMotorDeInferencia();

        String strDiscontiguous = ":- discontiguous "+classeGramatical+"/3.";         
        Query consultaDiscontiguous = new Query("inserir_facto('"+strDiscontiguous+"').");
        consultaDiscontiguous.hasSolution();       
        
        String strClasseGramatical = "classeGramatical --> ["+classeGramatical+"].";         
        Query consultaClasseGramatical = new Query("inserir_facto('"+strClasseGramatical+"').");
        consultaClasseGramatical.hasSolution();       
    }

    public static void salvarNovaPergunta_e_Resposta(String descPergunta, String descResposta)
    {
        Util.conectarComFicheiroMotorDeInferencia();
        
        String str = "pergunta(\\'"+descResposta+"\\') --> [\\'"+descPergunta+"\\'].";       
        Query consulta = new Query("inserir_facto('"+str+"').");
        consulta.hasSolution();       
    }
    

}

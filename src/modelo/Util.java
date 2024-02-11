/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.JOptionPane;
import modelo.Pergunta;
import modelo.ResultadoModelo;
import org.jpl7.Query;
import org.jpl7.Term;

/**
 *
 * @author Ademaro
 */
public class Util {

    public  static void conectarComFicheiroBaseDeConhecimento()
    {
        Query consultarFicheiro = new Query("consult('base de conhecimento.pl')");     
        consultarFicheiro.hasSolution();    
    }
    
    public  static void conectarComFicheiroMotorDeInferencia()
    {
        Query consultarFicheiro = new Query("consult('motor de inferencia.pl')");     
        consultarFicheiro.hasSolution();    
    }
    
    public static List<String> listaTermosDesconhecidos(String[] palavras)
    {
        List<String> listaTermosDesconhecidos = new ArrayList<>();
        for(String termo : palavras)
        {
            List<String> listaClassesGramaticais = retornarTodasClassesGramaticais();
            boolean flag = false;
            for(String classeGramatical : listaClassesGramaticais)
            {
                Query consulta =  Util.retornarQueryDaClasseGramatical(classeGramatical, termo);//new Query(classeGramatical+"(A, ["+termo+"], []).");
                if(consulta.hasSolution() == true)
                {
                    flag = true;
                    break;
                }
            }
            if(flag == false)
                listaTermosDesconhecidos.add(termo);
        }
        for(String termo : listaTermosDesconhecidos) System.out.println(termo);
        return listaTermosDesconhecidos;
    }
    
    public static Pergunta procurarPergunta(String frase)
    {
        conectarComFicheiroBaseDeConhecimento();
        
        Query  consulta = new Query("pergunta(Resposta, ['"+frase+"'], [])");
        
        if(consulta.hasMoreSolutions())
        {
            Map<String, Term> mapa = consulta.nextSolution();
            
            String resposta = mapa.get("Resposta").toString().replace("'", "");
            return new Pergunta(frase, resposta);
        }
        return null;
    }
    
    public static void main(String[] args)
    {
        procurarPergunta("bom dia ?");
    }
    
    public static List<String> retornarTodasClassesGramaticais()
    {
        conectarComFicheiroBaseDeConhecimento();
        
        Query  consulta = new Query("classeGramatical(Resultado, [])");
        consulta.hasMoreSolutions();

        List<String> classesGramaticais = new ArrayList<>();
        do
        {
            Map<String, Term> mapa = consulta.nextSolution();

            String aux = mapa.get("Resultado").toString();
            aux = aux.replace("'[|]'(", "");
            aux = aux.replace(", '[]')", "");
            
            classesGramaticais.add(aux);
        }
        while (consulta.hasMoreSolutions());
        
        return classesGramaticais;   
    }
    
    public static String formatarRespostaComAsClassesGramaticiais(ArrayList<ResultadoModelo> respostas)
    {
        String str = "";

        str += "\n******************************\n";  
        str += "      Análise Morfológica     \n";
        str += "******************************\n";      
        for(ResultadoModelo res : respostas)
        {
           str += "\n" + res.getClasseGramatical() + " : ";
            for(String palavra : res.getPalavras())
            {
                str += palavra + " ; ";
            }
        }
        str += "\n\n\n******************************\n";
        //str += "******************************\n\n";        
        return str;
    }

    public static String formatarRespostaComSujeito_e_Predicado(String sujeito, String predicado)
    {
            String str = "";
            str += "\n******************************\n";  
            str += "      Análise Sintática     \n";
            str += "******************************\n\n";  
            str += "Sujeito : "+sujeito +" \n";
            str += "Predicado : "+predicado +"\n\n";
            return str;
    }
    
    public static String formatarPergunta_e_Resposta(Pergunta pergunta)
    {
            String str = "";
            str += "\n******************************\n";  
            str += "           Questão             \n";
            str += "******************************\n\n";  
            str += "Questão : "+pergunta.getDescPergunta() +" \n";
            str += "Resposta : "+pergunta.getDescResposta() +"\n\n";
            return str;
    }
    
    public static Query retornarQueryDaClasseGramatical(String classeGram, String palavra)
    {
        Query classeGramatical;
        if(classeGram.equalsIgnoreCase("artigo") || classeGram.equalsIgnoreCase("substantivo"))
            classeGramatical= new Query(classeGram+"(_, _, A, ["+palavra+"], []).");                
        else if(classeGram.equalsIgnoreCase("verbo"))
            classeGramatical= new Query(classeGram+"(_, A, ["+palavra+"], []).");
        else
            classeGramatical= new Query(classeGram+"(A, ["+palavra+"], []).");
        return classeGramatical;
    }
}

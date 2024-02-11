/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Ademaro
 */
public class ResultadoModelo {
    
    private String classeGramatical;
    private ArrayList<String> palavras;

    public ResultadoModelo() {
        palavras = new ArrayList<>();
    }

    public void inserirPalavra(String palavra)
    {
        if(palavras == null)
            palavras = new ArrayList<>();
        
        if(!palavras.contains(palavra))
            palavras.add(palavra);
    }
    
    public String getClasseGramatical() {
        return classeGramatical;
    }

    public void setClasseGramatical(String classeGramatical) {
        this.classeGramatical = classeGramatical;
    }

    
    public ArrayList<String> getPalavras() {
        return palavras;
    }
}

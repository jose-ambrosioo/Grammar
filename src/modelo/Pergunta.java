/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Ademaro
 */
public class Pergunta {
    
    private String descPergunta, descResposta;

    public Pergunta(String descPergunta, String descResposta) {
        this.descPergunta = descPergunta;
        this.descResposta = descResposta;
    }

    public Pergunta() {
    }

    public String getDescPergunta() {
        return descPergunta;
    }

    public void setDescPergunta(String descPergunta) {
        this.descPergunta = descPergunta;
    }

    public String getDescResposta() {
        return descResposta;
    }

    public void setDescResposta(String descResposta) {
        this.descResposta = descResposta;
    }
    
    
    
}

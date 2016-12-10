/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author guilherme
 */
public enum Genero {
    M("Masculino"),
    F("Feminino");
    
    private String nomeFormatado;
    Genero(String nomeFormatado) {
        this.nomeFormatado = nomeFormatado;
    }
    
    public String toString() {
        return nomeFormatado;
    }
}

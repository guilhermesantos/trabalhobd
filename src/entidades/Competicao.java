/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import annotations.Coluna;

/**
 *
 * @author guilherme
 */
public class Competicao {
    private int identificacao;
    private String diaEHora;
    int numeroDeFase;
    private String observacoesGerais;
    private String local;
    
    //update
    public Competicao(int identificacao, String diaEHora, int numeroDeFase, String observacoesGerais, String local){
        this.identificacao = identificacao;
        this.diaEHora = diaEHora;
        this.numeroDeFase = numeroDeFase;
        this.observacoesGerais = observacoesGerais;
        this.local = local;
    }
    
    @Override
    public String toString(){
        return "Competicao: "+ "NUMERO_DE_IDENTIFICACAO = " +identificacao + ", DIA_E_HORA = " + diaEHora 
                + ", NUMERO_DE_FASE = " + numeroDeFase + ", OBSERVACOES_GERAIS = " + observacoesGerais + ", LOCAL = " + local;
                
    }
    
    public int getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(int identificacao) {
        this.identificacao = identificacao;
    }
    
    @Coluna(nome="Identificação", posicao=0)
    public String getIdentificacaoString() {
        return Integer.toString(identificacao);
    }
    
    @Coluna(nome="Data e Hora", posicao=1)
    public String getDiaEHora() {
        return diaEHora;
    }

    public void setDiaEHora(String diaEHora) {
        this.diaEHora = diaEHora;
    }

    public int getNumeroDeFase() {
        return numeroDeFase;
    }

    public void setNumeroDeFase(int numeroDeFase) {
        this.numeroDeFase = numeroDeFase;
    }

    @Coluna(nome="Número de Fase", posicao=2)
    public String getNumeroDeFaseString() {
        return Integer.toString(identificacao);
    }
    
    @Coluna(nome="Obs. Gerais", posicao=3)
    public String getObservacoesGerais() {
        return observacoesGerais;
    }

    public void setObservacoesGerais(String observacoesGerais) {
        this.observacoesGerais = observacoesGerais;
    }

    @Coluna(nome="Local", posicao=4)
    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}

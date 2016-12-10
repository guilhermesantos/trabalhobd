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
public class Atleta {
    private int identificacao;
    private String passaporte;
    private String nome;
    private Genero sexo;
    private int qtdPartidas;

    public Atleta(int identificacao, String passaporte, String nome, Genero sexo, int qtdPartidas) {
        this.identificacao = identificacao;
        this.passaporte = passaporte;
        this.nome = nome;
        this.sexo = sexo;
        this.qtdPartidas = qtdPartidas;
    }

    public int getIdentificacao() {
        return identificacao;
    }
    
    @Coluna(nome="Identificação", posicao=0)
    public String getIdentificacaoString() {
        return Integer.toString(identificacao);
    }

    public void setIdentificacao(int identificacao) {
        this.identificacao = identificacao;

    }
    @Coluna(nome="Passaporte", posicao=1)
    public String getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
    }

    @Coluna(nome="Nome", posicao=2)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Genero getSexo() {
        return sexo;
    }
    
    @Coluna(nome="Gênero", posicao=3)
    public String getGeneroString() {
        return sexo.toString();
    }

    public void setSexo(Genero sexo) {
        this.sexo = sexo;
    }
    
    public int getQtdPartidas() {
        return qtdPartidas;
    }
    
    @Coluna(nome="No. de jogos", posicao=4)
    public String getQtdPartidasString() {
        return Integer.toString(qtdPartidas);
    }

    public void setQtdPartidas(int qtdPartidas) {
        this.qtdPartidas = qtdPartidas;
    }
    
    @Override
    public String toString() {
        return "Atleta: identificacao " + identificacao + " No. de jogos: " + qtdPartidas;
    }
}

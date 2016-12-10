/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import entidades.Competicao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import database.ConexaoBD;

/**
 *
 * @author 8910347
 */
public class GestorCompeticao {
    
    public static List<Competicao> listarCompeticoes(){
        Statement stmt;
        List<Competicao> competicoes = new ArrayList<>();
        try {
            stmt = ConexaoBD.getInstance().getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM COMPETICAO");
            
            while (rs.next()) {
                int identificacao = rs.getInt("NUMERO_DE_IDENTIFICACAO");
                String diaEHora = rs.getString("DIA_E_HORA");
                int numeroDeFase = rs.getInt("NUMERO_DE_FASE");
                String observacoesGerais = rs.getString("OBSERVACOES_GERAIS");
                String local = rs.getString("LOCAL");

                Competicao comp = new Competicao(identificacao, diaEHora,numeroDeFase,observacoesGerais,local);
                competicoes.add(comp);
            }
            

            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorCompeticao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return competicoes;
    }
    
    private static void alterarCompeticao(String updateCommand){
        PreparedStatement pstmt;
        try {
            pstmt = ConexaoBD.getInstance().getConnection().prepareStatement(updateCommand);
            pstmt.executeUpdate();
            System.out.println("Alteração Efetuada");
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
    
    public static void inserirCompeticao(Competicao c){
        String observ;
        if("".equals(c.getObservacoesGerais())){
            observ = "NULL";
        }else{
            observ = "'"+c.getObservacoesGerais()+"'";
        }
        String data = "TO_DATE('"+c.getDiaEHora()+"', 'DD-MM-YYYY HH24:MI:SS')";
        
        String comandoSQL = "INSERT INTO COMPETICAO VALUES (" + c.getIdentificacao() + "," + data + "," + c.getNumeroDeFase() 
                + "," + observ + ", '" + c.getLocal() + "')";
        alterarCompeticao(comandoSQL);
        System.out.println(comandoSQL);
    }
    
    public static void atualizarCompeticao(Competicao c,int identificacao){
        String observ;
        if("".equals(c.getObservacoesGerais())){
            observ = "NULL";
        }else{
            observ = "'"+c.getObservacoesGerais()+"'";
        }
        String data = "TO_DATE('"+c.getDiaEHora()+"', 'DD-MM-YYYY HH24:MI:SS')";
        
        String comandoSQL = "UPDATE COMPETICAO SET " + "NUMERO_DE_IDENTIFICACAO = " +c.getIdentificacao() + ", DIA_E_HORA = " + data
                + ", NUMERO_DE_FASE = " + c.getNumeroDeFase() + ", OBSERVACOES_GERAIS = " + observ + ", LOCAL = '" + c.getLocal() + "'"
                +" WHERE NUMERO_DE_IDENTIFICACAO = " +identificacao;
        alterarCompeticao(comandoSQL);
        System.out.println(comandoSQL);
    }
    
    public static void removerCompeticao(int identificacao){
        String comandoSQL = "DELETE FROM COMPETICAO WHERE NUMERO_DE_IDENTIFICACAO = " + identificacao;
        alterarCompeticao(comandoSQL);
    }
}

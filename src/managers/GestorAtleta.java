/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entidades.Atleta;
import database.ConexaoBD;
import entidades.Genero;

/**
 *
 * @author 8910347
 */
public class GestorAtleta {

    public static List<Atleta> runQuery(){
        Statement stmt;
        List<Atleta> atletas = new ArrayList<>();
        try {
            stmt = ConexaoBD.getInstance().getConnection().createStatement();
            String query = "SELECT A.CODIGO_DE_IDENTIFICACAO, A.NOME, A.NUMERO_DO_PASSAPORTE, A.SEXO, COUNT(*) AS NRO_TOTAL_JOGOS FROM JOGA J JOIN PARTICIPA P ON P.EQUIPE = J.EQUIPE JOIN ATLETA A ON A.CODIGO_DE_IDENTIFICACAO = P.ATLETA\n" +
"GROUP BY A.CODIGO_DE_IDENTIFICACAO, A.NOME, A.NUMERO_DO_PASSAPORTE, A.SEXO\n" + "HAVING COUNT(*) >= 3";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int identificacao = rs.getInt("CODIGO_DE_IDENTIFICACAO");
                String passaporte = rs.getString("NUMERO_DO_PASSAPORTE");
                String nome = rs.getString("NOME");
                
                String genero = rs.getString("SEXO");
                Genero sexo;
                if(genero.equals("M")){
                    sexo = Genero.M;
                }else{
                    sexo = Genero.F;
                }
                
                int qtdPartidas = rs.getInt("NRO_TOTAL_JOGOS");
                
                Atleta atl = new Atleta(identificacao, passaporte, nome, sexo, qtdPartidas);
                atletas.add(atl);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorAtleta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return atletas;
    }
}

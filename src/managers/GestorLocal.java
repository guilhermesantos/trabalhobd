/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

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
public class GestorLocal {
    
    public static List<String> listarLocal(){
        Statement stmt;
        List<String> nomeLocais = new ArrayList<>();
        
        try {
            stmt = ConexaoBD.getInstance().getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM LOCAL");
            while (rs.next()) {
                nomeLocais.add(rs.getString("NOME"));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nomeLocais;
    }
}

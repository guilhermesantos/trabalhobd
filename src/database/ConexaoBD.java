/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 8910347
 */
public class ConexaoBD {
    public static ConexaoBD getInstance() {
        return ConexaoBDSingletonHolder.conexao;
    }
    private Connection connection;
        
    public boolean conectarBanco(String usuario, String senha, String host, String porta, String SID){
        boolean flag = true;
        String connectionString = "jdbc:oracle:thin:@"+host+":"+porta+":"+SID;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = 
                //DriverManager.getConnection("jdbc:oracle:thin:@192.168.183.15:1521:orcl",usuario, senha);
                    DriverManager.getConnection(connectionString, usuario, senha);
        } catch (SQLException|ClassNotFoundException ex) {
            flag = false;
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    
    public Connection getConnection(){
        return connection;
    }
        
    public void fechaConexao(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
    private static class ConexaoBDSingletonHolder {
        private static final ConexaoBD conexao = new ConexaoBD();
    }
}
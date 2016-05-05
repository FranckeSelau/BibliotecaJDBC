
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 631510072
 */
public class ProjetoBanco1 {
    public static void main(String[] args){
        try {
            Class.forName("org.postgresql.Driver"); // habilitando o driver
            
            String url = "jdbc:postgresql://localhost:5432/lp2_consultorio"; //fazendo a conexão com o banco
            Connection conexao = DriverManager.getConnection(url, "postgres", "123456");
            if (conexao != null) {
                System.out.println("Conectado com sucesso!!!");
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjetoBanco1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

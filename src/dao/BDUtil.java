package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDUtil {
    private final static String HOST = "localhost";
    private final static String PORT = "5432";
    private final static String BD = "postgres";
    private final static String URL = "jdbc:postgresql://"+HOST+":"+PORT+"/"+BD; // caminho do JDBC
    private final static String USUARIO = "postgres";
    private final static String SENHA = "123456";
    
    public static Connection getConnection(){
        Connection conexao = null;
        try {
            Class.forName("org.postgresql.Driver"); // habilitando o driver
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA); //fazendo a conexão com o banco
            
        } catch (ClassNotFoundException ex) {
            System.err.println("Erro de Sistema - Classe do Driver Nao Encontrada!");
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema na conexão do banco de dados");
            throw new RuntimeException(ex);
        }
        return(conexao);
    }    
}

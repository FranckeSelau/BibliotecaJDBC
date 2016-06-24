package dao.impl_BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDUtil {
    private final static String HOST = "localhost";
    private final static String PORT = "3307";
    private final static String BD = "biblioteca";
    //jdbc:mysql://localhost:3306/biblioteca?zeroDateTimeBehavior=convertToNull [root on Default schema]
    private final static String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+BD+"?zeroDateTimeBehavior=convertToNull"; // caminho do JDBC
    private final static String USUARIO = "root";
    private final static String SENHA = "usbw";
    
    public static Connection getConnection(){
        Connection conexao = null;
        try {
            Class.forName("org.postgresql.Driver"); // habilitando o driver
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA); //fazendo a conexão com o banco
            if (conexao != null) {
            }
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

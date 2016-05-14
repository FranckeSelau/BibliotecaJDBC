
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Console;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 631510072
 */
public class Sistema {

   public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver"); // habilitando o driver

            String url = "jdbc:postgresql://localhost:5432/postgres"; //fazendo a conexão com o banco
            Connection conexao = DriverManager.getConnection(url, "postgres", "123456");
            if (conexao != null) {
                System.out.println("Banco conectado com sucesso!!!");
            }

            /*
            //Removendo todas os clientes no banco
            String sqlDelete = "DELETE FROM CLIENTE";
            PreparedStatement comandoDelete = conexao.prepareStatement(sqlDelete);
            comandoDelete.execute();*/
 /*
            
            //Inserindo um cliente no banco solicitando os dados
            String sqlInserir1 = "INSERT INTO CLIENTE (NOME,TELEFONE) VALUES (?,?)";
            PreparedStatement comandoInserir1 = conexao.prepareStatement(sqlInserir1);
            String nome1 = Console.scanString("Nome: ");
            String telefone1 = Console.scanString("Telefone: ");
            comandoInserir1.setString(1, nome1);
            comandoInserir1.setString(2, telefone1);
            int linhasAtualizadas1 = comandoInserir1.executeUpdate();            
            if(linhasAtualizadas1 > 0)
                System.out.println("Cliente cadastrado com sucesso!");
        
            /*
            //Inserindo outro cliente no banco solicitando os dados
            String sqlInserir2 = "INSERT INTO CLIENTE (NOME,TELEFONE) VALUES (?,?)";
            PreparedStatement comandoInserir2 = conexao.prepareStatement(sqlInserir2);
            String nome2 = Console.scanString("Nome: ");
            String telefone2 = Console.scanString("Telefone: ");
            comandoInserir2.setString(1, nome2);
            comandoInserir2.setString(2, telefone2);
            int linhasAtualizadas2 = comandoInserir2.executeUpdate();            
            if(linhasAtualizadas2 > 0)
                System.out.println("Pessoa cadastrada com sucesso!");
             */
            //Consultando todos os clientes no banco
            String sqlConsulta1 = "select * from cliente";
            PreparedStatement comandoConsulta1 = conexao.prepareStatement(sqlConsulta1);
            ResultSet resultado1 = comandoConsulta1.executeQuery();
            while (resultado1.next()) {
                System.out.println(resultado1.getString("nome")
                        + " - " + resultado1.getString("telefone"));
            }
            /*
            //Solicita um nome e deleta todas as pessoas no banco que contenham esse nome
            System.out.println("-------");
            String sqlDelete2 = "delete from cliente where nome like ?";
            PreparedStatement comandoDelete2 = conexao.prepareStatement(sqlDelete2);
            String nomeDelete = Console.scanString("Nome a Excluir: ");
            comandoDelete2.setString(1, "%"+nomeDelete+"%");
            
            System.err.println(nomeDelete+" deletado com sucesso!!!");
             */

            //Solicita um nome e altera todas as pessoas no banco que contenham esse nome
            System.out.println("-------");
            String sqlUpdate = "update cliente set nome = ? where nome like ?";
            PreparedStatement comandoUpdate = conexao.prepareStatement(sqlUpdate);
            String nomeUpdate = Console.scanString("Nome novo: ");
            comandoUpdate.setString(1, nomeUpdate);
            String nomeUpdatevelho = Console.scanString("Nome a Editar: ");
            comandoUpdate.setString(2, "%" + nomeUpdatevelho + "%");
            System.out.println(nomeUpdatevelho+" alterado para "+nomeUpdate+" com sucesso!!");
            comandoUpdate.executeUpdate();
            /*
            String updateTableSQL = "UPDATE DBUSER SET USERNAME = ? WHERE USER_ID = ?";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL);
            preparedStatement.setString(1, "mkyong_new_value");
            preparedStatement.setInt(2, 1001);
            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            /*
            //Solicita um nome e consulta todas as pessoas no banco que contenham esse nome
            System.out.println("-------");
            String sqlDelete2 = "select * from cliente where nome like ?";
            PreparedStatement comandoConsulta2 = conexao.prepareStatement(sqlDelete2);
            String nomeConsultar = Console.scanString("Nome a consultar: ");
            comandoConsulta2.setString(1, "%"+nomeConsultar+"%");
            ResultSet resultado2 = comandoConsulta2.executeQuery();
            while(resultado2.next()){
            System.out.println(resultado2.getString("nome")+
            " - "+resultado2.getString("telefone"));
            }*/
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

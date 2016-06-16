package dao.impl_BD;

import dao.RetiradaDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Retirada;
import negocio.LivroNegocio;
import negocio.ClienteNegocio;
import negocio.NegocioException;
import view.UIUtil;


public class RetiradaDaoBd extends DaoBd<Retirada> implements RetiradaDao {
    
    private LivroNegocio livroNegocio = new LivroNegocio();
    private ClienteNegocio clienteNegocio = new ClienteNegocio();

    @Override
    public void salvar(Retirada retirada) {
        int id = 0;
        try {
            String sql = "select * from view_livros_disponiveis where isbn = ? and diferenca > 0";
            conectarObtendoId(sql);
            comando.setString(1, retirada.getLivro().getIsbn());
            ResultSet resultado = comando.executeQuery();
            
            if(resultado.next()){
                throw new Exception("Este livro consta na nossa lista de livros emprestados, não pode ser emprestado novamente.");
            }
            int matricula = retirada.getCliente().getMatricula();
            sql = "select * from view_livros_alocados where matricula = ? and alocados >= 1";
            conectarObtendoId(sql);
            comando.setInt(1, matricula);
            resultado = comando.executeQuery();
            
            if(resultado.next()){
                throw new Exception("O cliente já possuí um livro emprestado, não pode retirar mais.");
            }
            
            sql = "INSERT INTO retirada (retirada, devolvido, entrega, matricula, isbn, livroDevolvido)"
                    + "VALUES (?,?,?,?,?,?)";
            conectarObtendoId(sql);
            java.sql.Date dataSqlRetirada = new java.sql.Date(retirada.getRetirada().getTime());
            comando.setDate(1, dataSqlRetirada);
            java.sql.Date dataSqldevolvido = new java.sql.Date(retirada.getDevolvido().getTime());
            comando.setDate(2, dataSqldevolvido);
            java.sql.Date dataSqlEntrega = new java.sql.Date(retirada.getEntrega().getTime());
            comando.setDate(3, dataSqlEntrega);
            comando.setInt(4, retirada.getCliente().getMatricula());
            comando.setString(5, retirada.getLivro().getIsbn());
            comando.setString(6, null);
            comando.executeUpdate();
            //Obtém o resultSet para pegar a matricula
            resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta a matricula para o objeto
                id = resultado.getInt(1);
                retirada.setId(id);
            }
            else{
                System.err.println("Erro de Sistema - Nao gerou Id conforme esperado!");
                throw new BDException("Nao gerou a Id conforme esperado!");
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar retirada no Banco de Dados!");
            throw new RuntimeException(ex);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(Retirada retirada) {
        try {
            String sql = "DELETE FROM retirada WHERE id = ?";
            conectar(sql);
            comando.setInt(1, retirada.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar retirada no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void atualizar(Retirada retirada) {
        try {
            String sql = "UPDATE retirada SET retirada=?, devolvido=?, entrega=?, matricula=?, isbn=?, livroDevolvido=?"
                    + "WHERE id=?";
            
            conectar(sql);
            //Trabalhando com data: lembrando dataUtil -> dataSql
            java.sql.Date dataSqlRetirada = new java.sql.Date(retirada.getRetirada().getTime());
            comando.setDate(1, dataSqlRetirada);
            java.sql.Date dataSqldevolvido = new java.sql.Date(retirada.getDevolvido().getTime());
            comando.setDate(2, dataSqldevolvido);
            java.sql.Date dataSqlEntrega = new java.sql.Date(retirada.getEntrega().getTime());
            comando.setDate(3, dataSqlEntrega);
            comando.setInt(4, retirada.getCliente().getMatricula());
            comando.setString(5, retirada.getLivro().getIsbn());
            comando.setBoolean(6, retirada.getLivroDevolvido());
            comando.setInt(7, retirada.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar esta retirada no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Retirada> listar() {
        List<Retirada> listaRetiradas = new ArrayList<>();

        String sql = "SELECT * FROM retirada";

        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                //Trabalhando com data: lembrando dataSql -> dataUtil
                java.sql.Date dataSqlRetirada = resultado.getDate("retirada");
                java.util.Date dataUtilRetirada = new java.util.Date(dataSqlRetirada.getTime());
                java.sql.Date dataSqlDevolvido = resultado.getDate("devolvido");
                java.util.Date dataUtilDevolvido = new java.util.Date(dataSqlDevolvido.getTime());
                java.sql.Date dataSqlEntrega = resultado.getDate("entrega");
                java.util.Date dataUtilEntrega = new java.util.Date(dataSqlEntrega.getTime());
                int matricula = resultado.getInt("matricula");
                String isbn = resultado.getString("isbn");
                Boolean livroDevolvido = resultado.getBoolean("livroDevolvido");
                
                Retirada ret;
                try {
                    ret = new Retirada(id, dataUtilRetirada, dataUtilDevolvido, dataUtilEntrega, clienteNegocio.procurarMatricula(matricula), livroNegocio.procurarPorIsbn(isbn), livroDevolvido);
                    listaRetiradas.add(ret);
                } catch (NegocioException ex) {
                    UIUtil.mostrarErro(ex.getMessage());
                }                
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os retiradas do Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
        return (listaRetiradas);
    }
    
    

    @Override
    public Retirada procurarPorId(int id) {
        String sql = "SELECT * FROM retirada WHERE id = ?";
        
        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                //Trabalhando com data: lembrando dataSql -> dataUtil
                java.sql.Date dataSqlRetirada = resultado.getDate("retirada");
                java.util.Date dataUtilRetirada = new java.util.Date(dataSqlRetirada.getTime());
                java.sql.Date dataSqlDevolvido = resultado.getDate("devolvido");
                java.util.Date dataUtilDevolvido = new java.util.Date(dataSqlDevolvido.getTime());
                java.sql.Date dataSqlEntrega = resultado.getDate("entrega");
                java.util.Date dataUtilEntrega = new java.util.Date(dataSqlEntrega.getTime());
                int matricula = resultado.getInt("matricula");
                String isbn = resultado.getString("isbn");
                Boolean livroDevolvido = resultado.getBoolean("livroDevolvido");
                
                Retirada ret;
                
                   ret = new Retirada(id, dataUtilRetirada, dataUtilDevolvido, dataUtilEntrega, clienteNegocio.procurarMatricula(matricula), livroNegocio.procurarPorIsbn(isbn), livroDevolvido);
                   
                return ret;
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o retirada pelo id do Banco de Dados!");
            throw new RuntimeException(ex);
        } catch (NegocioException ex) {
            Logger.getLogger(RetiradaDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public List<Retirada> procurarPorNomeLista(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

package dao.impl_BD;

import dao.LivroDao;
import model.Livro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDaoBd extends DaoBd<Livro> implements LivroDao {

    @Override
    public void salvar(Livro livro) {
        try {
            String sql = "INSERT INTO livro (isbn, nome, autor, editora, ano)"
                    + "VALUES (?,?,?,?,?)";
            conectarObtendoMatricula(sql);
            comando.setInt(1, livro.getIsbn());
            comando.setString(2, livro.getNome());
            comando.setString(3, livro.getAutor());            
            comando.setString(4, livro.getEditora());
            //Trabalhando com data: lembrando dataUtil -> dataSql
            java.sql.Date dataSql = new java.sql.Date(livro.getAno().getTime());
            comando.setDate(5, dataSql);
            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar cliente no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(Livro livro) {
        try {
            String sql = "DELETE FROM livro WHERE ibsn = ?";
            conectar(sql);
            comando.setInt(1, livro.getIsbn());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar livro no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void atualizar(Livro livro) {
        try {
            String sql = "UPDATE livro SET nome=?, autor=?, editora=?, ano=? "
                    + "WHERE isbn=?";
            
            conectar(sql);
            comando.setString(1, livro.getNome());
            comando.setString(2, livro.getAutor());            
            comando.setString(3, livro.getEditora());
            //Trabalhando com data: lembrando dataUtil -> dataSql
            java.sql.Date dataSql = new java.sql.Date(livro.getAno().getTime());
            comando.setDate(4, dataSql);
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar este livro no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Livro> listar() {
        List<Livro> listaLivros = new ArrayList<>();

        String sql = "SELECT * FROM livro";

        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int isbn = resultado.getInt("isbn");
                String nome = resultado.getString("nome");
                String autor = resultado.getString("autor");
                String editora = resultado.getString("editora");
                //Trabalhando com data: lembrando dataSql -> dataUtil
                java.sql.Date dataSql = resultado.getDate("ano");
                java.util.Date dataUtil = new java.util.Date(dataSql.getTime());
                
                Livro liv = new Livro(isbn, nome, autor, editora, dataUtil);

                listaLivros.add(liv);
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os livros do Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
        return (listaLivros);
    }
    
    @Override
    public List<Cliente> procurarPorNomeLista(String nome) {
        List<Cliente> listaCliente = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE nome LIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int matriculaX = resultado.getInt("matricula");
                String nomeX = resultado.getString("nome");
                String telefoneX = resultado.getString("telefone");

                Cliente c = new Cliente(matriculaX, nomeX, telefoneX);

                listaCliente.add(c);
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os clientes pelo nome do Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
        return (listaCliente);
    }

    @Override
    public Cliente procurarPorMatricula(int matricula) {
        String sql = "SELECT * FROM cliente WHERE matricula = ?";

        try {
            conectar(sql);
            comando.setInt(1, matricula);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String nome = resultado.getString("nome");
                String telefone = resultado.getString("telefone");
                
                Cliente cli = new Cliente(matricula, nome, telefone);

                return cli;

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o paciente pelo id do Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }
}

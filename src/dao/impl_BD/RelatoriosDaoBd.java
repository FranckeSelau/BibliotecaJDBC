/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl_BD;

import dao.RelatorioDao;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Livro;
import model.Relatorio;
import model.ViewClientesMaisAtrasos;
import model.ViewClientesMaisEmprestimos;
import model.ViewDisponiveis;
import model.ViewMaisEmprestados;

/**
 *
 * @author saulovieira
 */
public class RelatoriosDaoBd extends DaoBd<Relatorio> implements RelatorioDao{
    
    public List<ViewMaisEmprestados> getLivrosMaisEmprestados(){
        List<ViewMaisEmprestados> lista = new ArrayList<ViewMaisEmprestados>();
        String sql = "SELECT * FROM view_mais_emprestados order by livro desc";
        try {
            conectar(sql);
           
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int qtd = resultado.getInt("livro");
                String livro = resultado.getString("isbn");
                
                LivroDaoBd livroBd = new LivroDaoBd();
                Livro l = livroBd.procurarPorIsbn(livro);
                ViewMaisEmprestados v = new ViewMaisEmprestados(qtd, l);

                lista.add(v);
            }

        } catch (Exception ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os livros pelo nome do Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
        return lista;
    }    
    
    public List<ViewDisponiveis> getLivrosDisponiveis(){
        List<ViewDisponiveis> lista = new ArrayList<ViewDisponiveis>();
        String sql = "SELECT * FROM view_livros_disponiveis where diferenca = 0 order by nome asc";
        try {
            conectar(sql);
           
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int dif = resultado.getInt("diferenca");
                String livro = resultado.getString("isbn");
                
                LivroDaoBd livroBd = new LivroDaoBd();
                Livro l = livroBd.procurarPorIsbn(livro);
                ViewDisponiveis v = new ViewDisponiveis(dif, l);

                lista.add(v);
            }

        } catch (Exception ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os livros pelo nome do Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
        return lista;
    } 
    
    public List<ViewClientesMaisEmprestimos> getClientesComMaisEmprestimos(){
        List<ViewClientesMaisEmprestimos> lista = new ArrayList<ViewClientesMaisEmprestimos>();
        String sql = "SELECT * FROM view_clientes_mais_emprestimos order by qtd_emprestimos desc";
        try {
            conectar(sql);
           
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int qtd = resultado.getInt("qtd_emprestimos");
                int matricula = resultado.getInt("matricula");
                
                ClienteDaoBd clienteBD = new ClienteDaoBd();
                Cliente c = clienteBD.procurarPorId(matricula);
                ViewClientesMaisEmprestimos v = new ViewClientesMaisEmprestimos(qtd, c);

                lista.add(v);
            }

        } catch (Exception ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os livros pelo nome do Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
        return lista;
    } 
    
    public List<ViewClientesMaisAtrasos> getClientesComMaisAtrrasos(){
        List<ViewClientesMaisAtrasos> lista = new ArrayList<ViewClientesMaisAtrasos>();
        String sql = "SELECT * FROM view_clientes_mais_atrasos order by total_atrasos desc";
        try {
            conectar(sql);
           
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int qtd = resultado.getInt("total_atrasos");
                int matricula = resultado.getInt("matricula");
                
                ClienteDaoBd clienteBD = new ClienteDaoBd();
                Cliente c = clienteBD.procurarPorId(matricula);
                ViewClientesMaisAtrasos v = new ViewClientesMaisAtrasos(qtd, c);

                lista.add(v);
            }

        } catch (Exception ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os livros pelo nome do Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
        return lista;
    } 

    @Override
    public void salvar(Relatorio dominio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Relatorio objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Relatorio objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Relatorio> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Relatorio procurarPorId(int matricula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}

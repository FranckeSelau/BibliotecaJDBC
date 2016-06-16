/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl_BD;

import dao.DevolucaoDao;
import static dao.impl_BD.DaoBd.comando;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Devolucao;
import model.Retirada;
import negocio.ClienteNegocio;
import negocio.NegocioException;
import view.UIUtil;

/**
 *
 * @author saulovieira
 */
public class DevolucaoDaoBd  extends DaoBd<Devolucao> implements DevolucaoDao{
    
    private ClienteNegocio clienteNegocio = new ClienteNegocio();
    
    public void salvar(Retirada retirada) throws Exception {
        int id = 0;
        try {
            String sql = "select * from view_livros_disponiveis where isbn = ? and diferenca = 0";
            conectarObtendoId(sql);
            comando.setString(1, retirada.getLivro().getIsbn());
            ResultSet resultado = comando.executeQuery();
            
            if(resultado.next()){
                throw new Exception("Este livro consta na nossa lista de livros disponíveis, não pode ser devolvido.");
            }
            
            sql = "select * from devolucao where retirada = ?";
            conectarObtendoId(sql);
            comando.setInt(1, retirada.getId());
            resultado = comando.executeQuery();
            if(resultado.next()){
                throw new Exception("Este livro consta como devolvido.");
            }
            
            sql = "INSERT INTO devolucao (retirada, devolvido)"
                    + "VALUES (?,?)";
            conectarObtendoId(sql);
            
            comando.setInt(1, retirada.getId());
            java.sql.Date dataSqldevolvido = new java.sql.Date(System.currentTimeMillis());
            comando.setDate(2, dataSqldevolvido);
           
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
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void salvar(Devolucao dominio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Devolucao objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Devolucao objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Devolucao> listar() {
        List<Devolucao> lista = new ArrayList<>();

        String sql = "SELECT * FROM devolucao";

        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            RetiradaDaoBd retiradaDao = new RetiradaDaoBd();
            while (resultado.next()) {
                int id = resultado.getInt("id");
                //Trabalhando com data: lembrando dataSql -> dataUtil
                int idRetirada = resultado.getInt("retirada");
                Retirada retirada = retiradaDao.procurarPorId(idRetirada);
                java.sql.Date dataSqlDevolvido = resultado.getDate("devolvido");
                java.util.Date dataUtilDevolvido = new java.util.Date(dataSqlDevolvido.getTime());
                
                
                try {
                    Devolucao dev = new Devolucao(id, retirada, dataUtilDevolvido);
                    lista.add(dev);
                } catch (Exception ex) {
                    UIUtil.mostrarErro(ex.getMessage());
                }                
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os retiradas do Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
        return (lista);
    }

    @Override
    public Devolucao procurarPorId(int matricula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
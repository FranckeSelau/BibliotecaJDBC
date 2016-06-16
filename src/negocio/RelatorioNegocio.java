/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dao.RelatorioDao;
import dao.impl_BD.RelatoriosDaoBd;
import java.util.List;
import model.ViewClientesMaisAtrasos;
import model.ViewClientesMaisEmprestimos;
import model.ViewDisponiveis;
import model.ViewMaisEmprestados;

/**
 *
 * @author saulovieira
 */
public class RelatorioNegocio {
    
    private RelatoriosDaoBd relatorioBd;
    
    public RelatorioNegocio(){
        relatorioBd = new RelatoriosDaoBd();
    }
    
    public List<ViewMaisEmprestados> getLivrosMaisEmprestados(){
        return this.relatorioBd.getLivrosMaisEmprestados();
    }
    
    public List<ViewDisponiveis> getLivrosDisponiveis(){
        return this.relatorioBd.getLivrosDisponiveis();
    } 
    
    public List<ViewClientesMaisEmprestimos> getClientesComMaisEmprestimos(){
        return this.relatorioBd.getClientesComMaisEmprestimos();
    } 
    
    public List<ViewClientesMaisAtrasos> getClientesComMaisAtrrasos(){
        return this.relatorioBd.getClientesComMaisAtrrasos();
    } 
    
    
}

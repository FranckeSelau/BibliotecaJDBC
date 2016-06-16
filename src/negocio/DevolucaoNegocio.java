/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dao.DevolucaoDao;
import dao.impl_BD.DevolucaoDaoBd;
import java.util.List;
import model.Devolucao;
import model.Retirada;

/**
 *
 * @author saulovieira
 */
public class DevolucaoNegocio {
    
    private DevolucaoDaoBd devolucaoDao;
    
    public DevolucaoNegocio(){
        devolucaoDao = new DevolucaoDaoBd();
    }
    
    public void salvar(Retirada r) throws Exception{
        devolucaoDao.salvar(r);
    }
    
    public List<Devolucao> listar()
    {
        return devolucaoDao.listar();
    }
}

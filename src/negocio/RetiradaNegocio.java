package negocio;

import dao.RetiradaDao;
import dao.impl_BD.RetiradaDaoBd;
import model.Retirada;
import java.util.List;

public class RetiradaNegocio {

    private RetiradaDao retiradaDao;

    public RetiradaNegocio() {
        retiradaDao = new RetiradaDaoBd();
    }

    public void salvar(Retirada r) throws NegocioException {
        this.validarCamposObrigatorios(r);
        retiradaDao.salvar(r);
    }

    public List<Retirada> listar() {
        return (retiradaDao.listar());
    }

    public void deletar(Retirada retirada) throws NegocioException {
        if (retirada == null || retirada.getCliente() == null) {
            throw new NegocioException("Cliente nao existe!");
        }
        retiradaDao.deletar(retirada);
    }

    public void atualizar(Retirada retirada) throws NegocioException {
        if (retirada == null || retirada.getCliente() == null) {
            throw new NegocioException("Cliente nao existe!");
        }
        this.validarCamposObrigatorios(retirada);
        retiradaDao.atualizar(retirada);
    }

        public Retirada procurarPorId(int id) throws NegocioException {
        if (id == 0) {
            throw new NegocioException("Campo id nao informado");
        }
        Retirada retirada = retiradaDao.procurarPorId(id);
        if (retirada == null) {
            throw new NegocioException("Registro nao encontrado");
        }
        return (retirada);
    }
        
    private void validarCamposObrigatorios(Retirada r) throws NegocioException {
        if (r.getCliente() == null) {
            throw new NegocioException("Campo Cliente nao informado");
        }
        
        
        
    }
}

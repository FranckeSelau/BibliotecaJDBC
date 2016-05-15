package negocio;


import dao.ClienteDao;
import dao.impl_BD.ClienteDaoBd;
import dominio.Cliente;
import java.util.List;

public class ClienteNegocio {

    private ClienteDao clienteDao;

    public ClienteNegocio() {
        clienteDao = new ClienteDaoBd();
    }

    public void salvar(Cliente c) throws NegocioException {
        this.validarCamposObrigatorios(c);
        //this.validarRgExistente(c);
        clienteDao.salvar(c);
    }

    public List<Cliente> listar() {
        return (clienteDao.listar());
    }

    public void deletar(Cliente cliente) throws NegocioException {
        if (cliente == null || cliente.getNome() == null) {
            throw new NegocioException("Cliente nao existe!");
        }
        clienteDao.deletar(cliente);
    }

    public void atualizar(Cliente cliente, String nome) throws NegocioException {
        if (cliente == null || cliente.getNome() == null) {
            throw new NegocioException("Cliente nao existe!");
        }
        this.validarCamposObrigatorios(cliente);
        clienteDao.atualizar(cliente, nome);
    }

        public Cliente procurarNome1(String nome) throws NegocioException {
        if (nome == null || nome.isEmpty()) {
            throw new NegocioException("Campo RG nao informado");
        }
        Cliente cliente = clienteDao.procurarPorNome(nome);
        if (cliente == null) {
            throw new NegocioException("Paciente nao encontrado");
        }
        return (cliente);
    }
    
    public List<Cliente> procurarNome(String nome) throws NegocioException {
        if (nome == null || nome.isEmpty()) {
            throw new NegocioException("Campo nome nao informado");
        }
        return(clienteDao.procurarPorNomeLista(nome));
    }
    /*
    public boolean clienteExiste(String nome) {
        Cliente cliente = Dao.procurarPornome(nome);
        return (cliente != null);
    }*/

    private void validarCamposObrigatorios(Cliente c) throws NegocioException {
        if (c.getTelefone() == null || c.getTelefone().isEmpty()) {
            throw new NegocioException("Campo Telefone nao informado");
        }

        if (c.getNome() == null || c.getNome().isEmpty()) {
            throw new NegocioException("Campo nome nao informado");
        }
    }
/*
    private void validarRgExistente(Paciente p) throws NegocioException {
        if (pacienteExiste(p.getRg())) {
            throw new NegocioException("RG ja existente");
        }
    }*/

}

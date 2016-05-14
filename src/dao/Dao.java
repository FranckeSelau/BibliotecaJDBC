package dao;

import java.util.List;

/**
 *
 * @author 
 */

//A ideia da interface Dao é que padronizar todos os métodos do CRUD da aplicação.
public interface Dao<T> {
    public void salvar(T dominio);
    public void deletar(T cliente);
    public void atualizar(T cliente);
    public List<T> listar();
    public T procurarPorNome(String Nome);
}

package dao;

import java.util.List;

/**
 *
 * @author
 */

//A ideia da interface Dao é de padronizar todos os métodos do CRUD da aplicação.
public interface Dao<T> {
    public void salvar(T dominio);
    public void deletar(T objeto);
    public void atualizar(T objeto);
    public List<T> listar();
    public T procurarPorMatricula(int matricula);
}

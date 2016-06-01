
package dao;

import java.util.List;
import model.Livro;

//Além dos métodos do Crud padronizado na interface Dao, procurarPorNomeLista e procurarPorIsbn são obrigatórios.
public interface LivroDao extends Dao<Livro>{
    public Livro procurarPorIsbn(String isbn);
    public List<Livro> procurarPorNomeListaLivro(String nome); 
}


package dao;

import java.util.List;
import model.Livro;

//Além dos métodos do Crud padronizado na interface Dao, procurarPorNomeLista é obrigatório.
public interface LivroDao extends Dao<Livro>{
    public List<Livro> procurarPorNomeListaLivro(String nome); 
}

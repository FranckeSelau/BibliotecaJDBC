
package dao;

import java.util.List;
import model.Cliente;

/**
 *
 * @author 
 */
//Além dos métodos do Crud padronizado na interface Dao, procurarPorNomeLista é obrigatório.
public interface ClienteDao extends Dao<Cliente>{
    public List<Cliente> procurarPorNomeLista(String nome); 
}

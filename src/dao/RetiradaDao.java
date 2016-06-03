
package dao;

import java.util.List;
import model.Retirada;

//Além dos métodos do Crud padronizado na interface Dao, procurarPorNomeLista é obrigatório.
public interface RetiradaDao extends Dao<Retirada>{
    public List<Retirada> procurarPorNomeLista(String nome); 
}

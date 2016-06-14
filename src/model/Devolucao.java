/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author saulovieira
 */
public class Devolucao {
    private int id;

    private Retirada retirada;
    private Date devolvido;
    
    public Devolucao(int id, Retirada retirada, Date devolvido){
        this.id = id;
        this.retirada = retirada;
        this.devolvido = devolvido;
        
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Retirada getRetirada() {
        return retirada;
    }

    public void setRetirada(Retirada retirada) {
        this.retirada = retirada;
    }

    

    public Date getDevolvido() {
        return devolvido;
    }

    public void setDevolvido(Date devolvido) {
        this.devolvido = devolvido;
    }

}

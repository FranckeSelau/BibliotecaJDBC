/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author saulovieira
 */
public class ViewMaisEmprestados {
    private int qtd;
    private Livro livro;
    
    public ViewMaisEmprestados(int qtd, Livro l){
        this.qtd = qtd;
        this.livro = l;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }    
}

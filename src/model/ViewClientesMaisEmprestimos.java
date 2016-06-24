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
public class ViewClientesMaisEmprestimos {

    private int qtd;
    private Cliente cliente;

    public ViewClientesMaisEmprestimos(int qtd, Cliente c) {
        this.qtd = qtd;
        this.cliente = c;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

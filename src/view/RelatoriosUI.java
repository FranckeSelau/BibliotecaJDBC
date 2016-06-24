/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.InputMismatchException;
import java.util.List;
import model.ViewClientesMaisAtrasos;
import model.ViewClientesMaisEmprestimos;
import model.ViewDisponiveis;
import model.ViewMaisEmprestados;
import negocio.RelatorioNegocio;
import util.Console;
import util.DateUtil;
import view.menu.RelatoriosMenu;

/**
 *
 * @author Francke
 */
public class RelatoriosUI {   
    
    private RelatorioNegocio relatorioNegocio;
    
    public RelatoriosUI(){
        this.relatorioNegocio = new RelatorioNegocio();
    }

    /**
     * Executa as opções do Menu Livros.
     *
     */
    public void executar() {
        int opcao = 0;
        do {
            System.out.println(RelatoriosMenu.getOpcoes());
            try {
                opcao = Console.scanInt("Digite sua opção: ");
            } catch (InputMismatchException e) {
                opcao = -1;
            }

            switch (opcao) {
                case RelatoriosMenu.OP_RELATORIO_LIVROS_DISP:
                    livrosDisponiveis();
                    break;
                case RelatoriosMenu.OP_RELATORIO_LIVROS_RET:
                    livrosMaisRetirados();
                    break;
                case RelatoriosMenu.OP_RELATORIO_CLIENTES_RET:
                    clientesMaisRetiraram();
                    break;
                case RelatoriosMenu.OP_RELATORIO_CLIENTES_ATR:
                    clientesMaisAtrasaram();
                    break;
                case RelatoriosMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.err.println("ERRO! Digite uma opção válida!!");

            }
        } while (opcao != RelatoriosMenu.OP_VOLTAR);
    }

    public void livrosDisponiveis() {
        this.mostrarLivros(this.relatorioNegocio.getLivrosDisponiveis());
    }

    public void livrosMaisRetirados() {
        this.mostrarLivrosRetirados(this.relatorioNegocio.getLivrosMaisEmprestados());
    }

    public void clientesMaisRetiraram() {
        this.mostrarClientes(this.relatorioNegocio.getClientesComMaisEmprestimos());
    }
    
    public void clientesMaisAtrasaram() {
        this.mostrarClientesAtrasos(this.relatorioNegocio.getClientesComMaisAtrrasos());
    }

    /**
     * Mostra novo livro.
     *
     * imprime os livros formatados em Strings
     */
    public void mostrarLivros(List<ViewDisponiveis> listaLivrosDisponiveis) {
        System.out.println("--------------------------------------\n");
        System.out.println(String.format("%-10s", "ISBN") + "\t"
                + String.format("%-40s", "|NOME") + "\t"
                + String.format("%-20s", "|AUTOR") + "\t"
                + String.format("%-20s", "|EDITORA") + "\t"
                + String.format("%-5s", "|ANO"));
        for (ViewDisponiveis livro : listaLivrosDisponiveis) {
            System.out.println(String.format("%-10s", livro.getLivro().getIsbn()) + "\t"
                    + String.format("%-40s", "|" + livro.getLivro().getNome()) + "\t"
                    + String.format("%-20s", "|" + livro.getLivro().getAutor()) + "\t"
                    + String.format("%-20s", "|" + livro.getLivro().getEditora()) + "\t"
                    + String.format("%-5s", "|" + DateUtil.yearToString(livro.getLivro().getAno()))); // converte ano data em String
        }
    }

    /**
     * Mostra novo livro.
     *
     * imprime os livros formatados em Strings
     */
    public void mostrarLivrosRetirados(List<ViewMaisEmprestados> listaLivrosDisponiveis) {
        System.out.println("--------------------------------------\n");
        System.out.println(String.format("%-10s", "ISBN") + "\t"
                + String.format("%-40s", "|NOME") + "\t"
                + String.format("%-20s", "|AUTOR") + "\t"
                + String.format("%-20s", "|EDITORA") + "\t"
                + String.format("%-20s", "|RETIRADAS") + "\t"
                + String.format("%-5s", "|ANO"));
        for (ViewMaisEmprestados livro : listaLivrosDisponiveis) {
            System.out.println(String.format("%-10s", livro.getLivro().getIsbn()) + "\t"
                    + String.format("%-40s", "|" + livro.getLivro().getNome()) + "\t"
                    + String.format("%-20s", "|" + livro.getLivro().getAutor()) + "\t"
                    + String.format("%-20s", "|" + livro.getLivro().getEditora()) + "\t"
                    + String.format("%-20s", "|" + livro.getQtd()) + "\t"
                    + String.format("%-5s", "|" + DateUtil.yearToString(livro.getLivro().getAno()))); // converte ano data em String
        }
    }
    
    public void mostrarClientes(List<ViewClientesMaisEmprestimos> clientes) {
        System.out.println("--------------------------------------\n");
        System.out.println(String.format("%-10s", "MATRÍCULA") + "\t"
                + String.format("%-20s", "|NOME") + "\t"
                + String.format("%-20s", "|RETIRADAS") + "\t"
                
                + String.format("%-20s", "|TELEFONE"));
        for (ViewClientesMaisEmprestimos cliente : clientes) {
            System.out.println(String.format("%-10s", cliente.getCliente().getMatricula()) + "\t"
                    + String.format("%-20s", "|" + cliente.getCliente().getNome()) + "\t"
                    + String.format("%-20s", "|" + cliente.getQtd()) + "\t"
                  
                    + String.format("%-20s", "|" + cliente.getCliente().getTelefone()));
        }
    }    
    
    public void mostrarClientesAtrasos(List<ViewClientesMaisAtrasos> clientes) {
        System.out.println("--------------------------------------\n");
        System.out.println(String.format("%-10s", "MATRÍCULA") + "\t"
                + String.format("%-20s", "|NOME") + "\t"
               
                + String.format("%-20s", "|ATRASOS") + "\t"
                + String.format("%-20s", "|TELEFONE"));
        for (ViewClientesMaisAtrasos cliente : clientes) {
            System.out.println(String.format("%-10s", cliente.getCliente().getMatricula()) + "\t"
                    + String.format("%-20s", "|" + cliente.getCliente().getNome()) + "\t"                  
                    + String.format("%-20s", "|" + cliente.getQtd()) + "\t"
                    + String.format("%-20s", "|" + cliente.getCliente().getTelefone()));
        }
    }
}


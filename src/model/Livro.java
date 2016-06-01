package model;


import java.util.Date;

/**
 * Classe para objetos do tipo livro, onde serão criados os livros da
 * Biblioteca.
 *
 * @author Francke
 * @since JDK 1.0
 */
public class Livro {

    private static int CODIGO_GERADO = 1;
    private String nome, autor, editora;
    private int isbn;
    private Date ano;
    private int retiradas;

    /**
     * Construtor para inicializar livro
     *
     * @param isbn identifica o ISBN de um livro.
     * @param nome identifica o nome de um livro.
     * @param autor identifica o autor de um livro.
     * @param editora identifica a editora de um livro.
     * @param ano identifica o ano de publicação de um livro.
     *
     */
    public Livro(int isbn, String nome, String autor, String editora, Date ano) {
        this.nome = nome;
        this.autor = autor;
        this.editora = editora;
        this.isbn = isbn;
        this.ano = ano;
    }

    /**
     * Retorna código do menu
     *
     * @return um código Estático para o menu de opções
     */
    public static int getCODIGO_GERADO() {
        return CODIGO_GERADO;
    }

    /**
     * Retorna o nome
     *
     * @return nome de um livro
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o autor
     *
     * @return o autor de um livro
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Retorna a editora
     *
     * @return a editora de um livro
     */
    public String getEditora() {
        return editora;
    }

    /**
     * Retorna a matrícula
     *
     * @return matrícula de uma pessoa
     */
    public int getIsbn() {
        return isbn;
    }

    /**
     * Retorna o ISBN
     *
     * @return o ISBN de um livro
     */
    public Date getAno() {
        return ano;
    }

    /**
     * Retorna adição ao código gerado
     *
     * @return o incremento do código gerado para o menu
     */
    private int generateCodigo() {
        return (CODIGO_GERADO++);
    }

    public int getRetiradas() {
        return retiradas;
    }

    public void setRetiradas(int retiradas) {
        this.retiradas = retiradas;
    }
    
    
}

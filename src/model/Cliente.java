package model;

import java.util.Objects;

/**
 * Classe para objetos do tipo cliente, onde serão criados os clientes da
 * Biblioteca.
 *
 * @author Francke
 * @since JDK 1.0
 */
public class Cliente {

    private String nome, telefone;
    private int matricula, retiradas, atrasos;

    /**
     * Construtor para inicializar cliente
     *
     * @param nome identifica o nome de uma pessoa.
     * @param telefone identifica telefone de uma pessoa.
     *
     */
    public Cliente(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }
    
    /**
     * Construtor para inicializar cliente
     *
     * @param matricula identifica a matrícula de uma pessoa.
     * @param nome identifica o nome de uma pessoa.
     * @param telefone identifica telefone de uma pessoa.
     *
     */
    public Cliente(int matricula, String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }
    
    /**
     * Retorna a matrícula
     *
     * @return matrícula de uma pessoa
     */
    public int getMatricula() {
        return matricula;
    }
    
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    /**
     * Retorna o nome
     *
     * @return nome de uma pessoa
     */
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o telefone
     *
     * @param telefone
     */
    public void setTelefone(String telefone) {
       this.telefone = telefone;
    }
    
    public String getTelefone() {
        return telefone;
    }

    public int getRetiradas() {
        return retiradas;
    }

    public void setRetiradas(int retiradas) {
        this.retiradas = retiradas;
    }

    public int getAtrasos() {
        return atrasos;
    }

    public void setAtrasos(int atrasos) {
        this.atrasos = atrasos;
    }    

    /**
     * Faz o controle de matrículas para não inserir uma nova repetida.
     *
     * @return verdadeiro para uma nova matrícula única, ou falso para matrícula
     * já existente.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.matricula);
        return hash;
    }

    /**
     * converte o objeto pessoa em String para mostrar suas informações
     * corretamente.
     *
     * @return a matricula, o nome e o telefone de uma pessoa já formatado em
     * String.
     */
    @Override
    public String toString() {
        return matricula + " - " + nome + ", " + telefone;
    }
}

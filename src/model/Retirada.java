package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe para objetos do tipo retirada de livros, onde são instanciados os eventos de retiradas
 * Implementa FrameWork CompareTo
 * @author Francke
 * @since JDK 1.0
 */
public class Retirada implements Comparable<Retirada> {

    private int id;
    private Date retirada;
    private Date devolvido;
    private Date entrega;
    private Cliente cliente;
    private Livro livro;
    private Boolean livroDevolvido = false;

    /**
     * Construtor para a data de entrega de um livo, automaticamente 7 dias
     *
     * Ajusta autometicamente uma semana.
     */
    public Retirada() {
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        this.setEntrega(new Date(System.currentTimeMillis() + (7 * DAY_IN_MS)));

    }

    /**
     * Data de entrega
     *
     * @return a entrega de um livro.
     */
    public Date getEntrega() {
        return entrega;
    }

    /**
     * Formata data de entrega
     *
     * @return a entrega de um livro com data formatada "dd/MM/yyyy".
     */
    public String getEntregaFormatada() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(entrega);
    }

    /**
     * Informa Entrega (com base no parâmetro construtor)
     *
     * @param entrega recebida do parâmentro contrutor.
     */
    public void setEntrega(Date entrega) {
        this.entrega = entrega;
    }

    /**
     * Recebe cliente que retira um livro
     *
     * @return cliente que retirou um livro
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Informa cliente que retirou um livro
     *
     * @param cliente que retira um livro.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Recebe livro que foi retirado
     *
     * @return livro que foi retirado.
     */
    public Livro getLivro() {
        return livro;
    }

    /**
     * Informa livro a ser retirado
     *
     * @param livro recebe novo livro retirado.
     */
    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    /**
     * Recebe data de retirada
     *
     * @return retirada, data da retirada.
     */
    public Date getRetirada() {
        return retirada;
    }

    /**
     * recebe data de devolução no mesmo livro, caso ele ainda tenha sido
     * devolvido, não poderá ser retirado
     *
     * @return devolvido, data.
     */
    public Date getDevolvido() {
        return devolvido;
    }

    /**
     * Informa caso o livro a ser retirado já tenha sido devolvido anteriormente
     *
     * @param devolvido, a data de devolução anterior ao próximo empréstimo.
     */
    public void setDevolvido(Date devolvido) {
        this.devolvido = devolvido;
    }

    /**
     * Formata data de devolução anterior ao próximo empréstimo
     *
     * @return devolvido. data formatada "dd/MM/yyyy".
     */
    public String getDevolvidoFormatada() {
        if (devolvido != null) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            return df.format(devolvido);
        } else {
            return "";
        }
    }

    /**
     * Formata data de retirada anterior ao próximo empréstimo
     *
     * @return retirada. data formatada "dd/MM/yyyy".
     */
    public String getRetiradaFormatada() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(retirada);
    }
    
    /**
     * Recebe data da Retirada atual
     *
     * @param retirada de livro.
     */
    public void setRetirada(Date retirada) {
        this.retirada = retirada;
    }

    /**
     * Retorna status de devolução do livro a ser emprestado
     *
     * @return livroDevolvido, verdadeiro ou falso.
     */
    public Boolean getLivroDevolvido() {
        return livroDevolvido;
    }

    /**
     * Recebe status de devolução do livro a ser emprestado
     *
     * @param livroDevolvido verdadeiro ou falso.
     */
    public void setLivroDevolvido(Boolean livroDevolvido) {
        this.livroDevolvido = livroDevolvido;
    }

    /**
     * Retorna cód id da retirada
     *
     * @return id, retirada de livro.
     */
    public int getId() {
        return id;
    }

    /**
     * Recebe cód id da retirada
     *
     * @param id, retirada de livro.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Compara se um livro ja foi retirado antes de realizar nova retirada.
     *
     * @return verdadeiro ou falso para o livro a ser retidado.
     */
    @Override
    public int compareTo(Retirada o) {
        return (this.getEntrega().compareTo(o.getEntrega()));
    }
    
    /**
     * Retorna horário atual.
     *
     * @return horario
     */
    public Object getHorario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

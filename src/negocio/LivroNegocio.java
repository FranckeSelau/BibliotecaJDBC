package negocio;

import dao.LivroDao;
import dao.impl_BD.LivroDaoBd;
import java.util.List;
import model.Livro;

public class LivroNegocio {

    private LivroDao livroDao;

    public LivroNegocio() {
        livroDao = new LivroDaoBd();
    }

    public void salvar(Livro l) throws NegocioException {
        this.validarCamposObrigatorios(l);
        this.validarIsbnExistente(l);
        livroDao.salvar(l);
    }

    public List<Livro> listar() {
        return (livroDao.listar());
    }

    public void deletar(Livro livro) throws NegocioException {
        if (livro == null || livro.getNome() == null) {
            throw new NegocioException("Livro nao existe!");
        }
        livroDao.deletar(livro);
    }

    public void atualizar(Livro livro) throws NegocioException {
        if (livro == null || livro.getNome() == null) {
            throw new NegocioException("Livro nao existe!");
        }
        this.validarCamposObrigatorios(livro);
        livroDao.atualizar(livro);
    } 
    
    public Livro procurarPorIsbn(String isbn) throws NegocioException {
        if (isbn == null || isbn.isEmpty()) {
            throw new NegocioException("Campo ISBN nao informado");
        }
        Livro livro = livroDao.procurarPorIsbn(isbn);
        if (livro == null) {
            throw new NegocioException("Livro nao encontrado");
        }
        return (livro);
    }
    
    public List<Livro> procurarNome(String nome) throws NegocioException {
        if (nome == null || nome.isEmpty()) {
            throw new NegocioException("Campo nome nao informado");
        }
        return(livroDao.procurarPorNomeListaLivro(nome));
    }
    
    public boolean livroExiste(String isbn) {
        Livro livro = livroDao.procurarPorIsbn(isbn);
        return (livro != null);
    }
    
    private void validarCamposObrigatorios(Livro l) throws NegocioException {
        if (l.getIsbn() == null || l.getIsbn().isEmpty()) {
            throw new NegocioException("Campo ISBN nao informado");
        }

        if (l.getNome() == null || l.getNome().isEmpty()) {
            throw new NegocioException("Campo nome nao informado");
        }
        
        if (l.getAutor() == null || l.getAutor().isEmpty()) {
            throw new NegocioException("Campo Autor nao informado");
        }

        if (l.getEditora() == null || l.getEditora().isEmpty()) {
            throw new NegocioException("Campo Editora nao informado");
        }        
    }
    
    private void validarIsbnExistente(Livro l) throws NegocioException {
        if (livroExiste(l.getIsbn())) {
            throw new NegocioException("ISBN ja existente");
        }
    }
}

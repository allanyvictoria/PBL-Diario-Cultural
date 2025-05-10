package controller;

import model.Livro;
import view.Listagem;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe responsável por realizar buscas no acervo de livros com base em diferentes critérios.
 */
public class BuscaLivro {

    /**
     * Busca livros pelo título (ou parte dele), ignorando maiúsculas e minúsculas.
     *
     * @param titulo Título ou parte do título do livro.
     */
    public static void titulo(String titulo) {
        List<Livro> resultados = Acervo.getLivroList().stream()
                .filter(l -> l.getNome() != null &&
                        l.getNome().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
        Listagem.listaLivros(resultados);
    }

    /**
     * Busca livros pelo nome do autor (ou parte dele), ignorando maiúsculas e minúsculas.
     *
     * @param autor Nome ou parte do nome do autor.
     */
    public static void autor(String autor) {
        List<Livro> resultados = Acervo.getLivroList().stream()
                .filter(l -> l.getAutor() != null &&
                        l.getAutor().toLowerCase().contains(autor.toLowerCase()))
                .collect(Collectors.toList());
        Listagem.listaLivros(resultados);
    }

    /**
     * Busca livros pelo gênero, ignorando maiúsculas e minúsculas.
     *
     * @param genero Gênero a ser buscado.
     */
    public static void buscaGenero(String genero) {
        List<Livro> resultados = Acervo.getLivroList().stream()
                .filter(l -> l.getGenero() != null &&
                        l.getGenero().toLowerCase().contains(genero.toLowerCase()))
                .collect(Collectors.toList());
        Listagem.listaLivros(resultados);
    }

    /**
     * Busca livros publicados em um ano específico.
     *
     * @param ano Ano de publicação.
     */
    public static void ano(int ano) {
        List<Livro> resultados = Acervo.getLivroList().stream()
                .filter(l -> l.getAno() == ano)
                .collect(Collectors.toList());
        Listagem.listaLivros(resultados);
    }

    /**
     * Busca livros pelo ISBN, desconsiderando caracteres não numéricos.
     *
     * @param isbn Código ISBN (pode conter traços ou espaços).
     */
    public static void isbn(String isbn) {
        List<Livro> resultados = Acervo.getLivroList().stream()
                .filter(l -> l.getIsbn() != null &&
                        l.getIsbn().replaceAll("\\D", "").contains(
                                isbn.replaceAll("\\D", "")))
                .collect(Collectors.toList());
        Listagem.listaLivros(resultados);
    }
}

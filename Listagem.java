package view;

import model.Filme;
import model.Livro;
import model.Serie;
import model.Temporada;
import java.util.List;

/**
 * Classe responsável por exibir a listagem de livros, filmes e séries cadastrados.
 * Cada tipo de item (livro, filme, série) tem sua própria implementação de exibição,
 * incluindo detalhes como título, gênero, ano de lançamento e status.
 */
public class Listagem {

    /**
     * Método que exibe a listagem de livros cadastrados.
     * Exibe informações como nome, autor, gênero, ano de lançamento, editora, ISBN,
     * e status (se foi lido ou não), além de avaliação e review, se disponíveis.
     *
     * @param lista Lista de livros a ser exibida.
     */
    public static void listaLivros(List<Livro> lista) {
        System.out.println();
        System.out.println("=".repeat(50));
        if (lista.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            System.out.println("=".repeat(50));
            return;
        }
        for (Livro livro : lista) {
            System.out.println("Livro " + livro.getNome());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Gênero: " + livro.getGenero());
            System.out.println("Ano de lançamento: " + livro.getAno());
            System.out.println("Editora: " + livro.getEditora());
            System.out.println("ISBN: " + livro.getIsbn());
            if (livro.getLeitura() != null) {
                System.out.println("Status: lido");
                System.out.println("Data de leitura: " + livro.getLeitura());
                System.out.println("Review: " + livro.getReview());
                System.out.println("Avaliação: " + livro.getAvaliacao());
            } else {
                System.out.println("Status: Não lido");
            }
            System.out.println("=".repeat(50));
        }
        System.out.println();
    }

    /**
     * Método que exibe a listagem de filmes cadastrados.
     * Exibe informações como nome, título original, gênero, ano de lançamento, duração,
     * elenco, direção, roteiro, e status (se foi assistido ou não), além de avaliação e review, se disponíveis.
     *
     * @param lista Lista de filmes a ser exibida.
     */
    public static void listaFilmes(List<Filme> lista) {
        System.out.println();
        System.out.println("=".repeat(50));
        if (lista.isEmpty()) {
            System.out.println("Nenhum filme cadastrado.");
            System.out.println("=".repeat(50));
            return;
        }
        for (Filme filme : lista) {
            System.out.println("Filme " + filme.getNome());
            System.out.println("Titulo Original: " + filme.getTituloOriginal());
            System.out.println("Gênero: " + filme.getGenero());
            System.out.println("Ano de lançamento: " + filme.getAno());
            System.out.println("Duração: " + filme.getDuracao());
            System.out.println("Elenco: ");
            for (String elenco : filme.getElenco()) {
                System.out.println(elenco);
            }
            System.out.println("Direção: ");
            for (String direcao : filme.getDirecao()) {
                System.out.println(direcao);
            }
            System.out.println("Roteiro: ");
            for (String roteiro : filme.getRoteiro()) {
                System.out.println(roteiro);
            }
            if (filme.getDataAssistiu() != null) {
                System.out.println("Status: Visto");
                System.out.println("Data de visualização: " + filme.getDataAssistiu());
                System.out.println("Onde assistiu: " + filme.getOndeAssistir());
                System.out.println("Review: " + filme.getReview());
                System.out.println("Avaliação: " + filme.getAvaliacao());
            } else {
                System.out.println("Status: Não visto");
            }
            System.out.println("=".repeat(50));
        }
        System.out.println();
    }

    /**
     * Método que exibe a listagem de séries cadastradas.
     * Exibe informações como nome, título original, gênero, ano de encerramento, elenco,
     * avaliação, onde assistiu, e detalhes de cada temporada (ano de lançamento, quantidade de episódios, review).
     *
     * @param lista Lista de séries a ser exibida.
     */
    public static void listaSeries(List<Serie> lista) {
        System.out.println();
        System.out.println("=".repeat(50));
        if (lista.isEmpty()) {
            System.out.println("Nenhuma série cadastrada.");
            System.out.println("=".repeat(50));
            return;
        }
        for (Serie serie : lista) {
            System.out.println("Série " + serie.getNome());
            System.out.println("Título Original: " + serie.getTituloOriginal());
            System.out.println("Gênero: " + serie.getGenero());
            System.out.println("Ano de encerramento: " + serie.getFim());
            System.out.println("Elenco: ");
            for (String elenco : serie.getElenco()) {
                System.out.println(elenco);
            }
            System.out.println("Avaliação: " + serie.getAvaliacao());
            System.out.println("Onde assistiu: " + serie.getOndeAssistiu());
            for (Temporada temporada : serie.getTemporadas()) {
                System.out.println("-".repeat(50));
                int c = 1;
                System.out.println("Temporada " + c);
                System.out.println("Ano de lançamento: " + temporada.getAno());
                System.out.println("Quantidade: " + temporada.getQuantidade());
                System.out.println("Review: " + temporada.getReview());
                System.out.println("-".repeat(50));
            }
            System.out.println("=".repeat(50));
        }
        System.out.println();
    }
}

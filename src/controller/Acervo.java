package controller;

import model.Filme;
import model.Livro;
import model.Serie;
import view.Listagem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Classe responsável por armazenar e gerenciar as listas de livros, filmes e séries cadastradas no sistema.
 * Permite adicionar itens, recuperar as listas, listar e ordenar por avaliação.
 */
public class Acervo {
    private static List<Livro> livroList = new ArrayList<>();
    private static List<Filme> filmeList = new ArrayList<>();
    private static List<Serie> serieList = new ArrayList<>();

    /**
     * Retorna a lista de livros cadastrados.
     * @return Lista de livros.
     */
    public static List<Livro> getLivroList() {
        return livroList;
    }

    /**
     * Retorna a lista de filmes cadastrados.
     * @return Lista de filmes.
     */
    public static List<Filme> getFilmeList() {
        return filmeList;
    }

    /**
     * Retorna a lista de séries cadastradas.
     * @return Lista de séries.
     */
    public static List<Serie> getSerieList() {
        return serieList;
    }

    /**
     * Adiciona um livro ao acervo.
     * @param livro Livro a ser adicionado.
     */
    public static void adicionarLivro(Livro livro) {
        livroList.add(livro);
    }

    /**
     * Adiciona um filme ao acervo.
     * @param filme Filme a ser adicionado.
     */
    public static void adicionarFilme(Filme filme) {
        filmeList.add(filme);
    }

    /**
     * Adiciona uma série ao acervo.
     * @param serie Série a ser adicionada.
     */
    public static void adicionarSerie(Serie serie) {
        serieList.add(serie);
    }

    /**
     * Lista todos os livros cadastrados no sistema.
     */
    public static void listarLivros() {
        Listagem.listaLivros(livroList);
    }

    /**
     * Lista todos os filmes cadastrados no sistema.
     */
    public static void listarFilmes() {
        Listagem.listaFilmes(filmeList);
    }

    /**
     * Lista todas as séries cadastradas no sistema.
     */
    public static void listarSeries() {
        Listagem.listaSeries(serieList);
    }

    /**
     * Ordena os livros por avaliação, em ordem crescente ou decrescente.
     * @param decrescente Se verdadeiro, ordena em ordem decrescente.
     */
    public static void listarLivrosAvaliacao(boolean decrescente) {
        if (decrescente) {
            livroList.sort(Comparator.comparing(Livro::getAvaliacao).reversed());
        } else {
            livroList.sort(Comparator.comparing(Livro::getAvaliacao));
        }
    }

    /**
     * Ordena os filmes por avaliação, em ordem crescente ou decrescente.
     * @param decrescente Se verdadeiro, ordena em ordem decrescente.
     */
    public static void listarFilmesAvaliacao(boolean decrescente) {
        if (decrescente) {
            filmeList.sort(Comparator.comparing(Filme::getAvaliacao).reversed());
        } else {
            filmeList.sort(Comparator.comparing(Filme::getAvaliacao));
        }
    }

    /**
     * Ordena as séries por avaliação, em ordem crescente ou decrescente.
     * @param decrescente Se verdadeiro, ordena em ordem decrescente.
     */
    public static void listarSeriesAvaliacao(boolean decrescente) {
        if (decrescente) {
            serieList.sort(Comparator.comparing(Serie::getAvaliacao).reversed());
        } else {
            serieList.sort(Comparator.comparing(Serie::getAvaliacao));
        }
    }
}

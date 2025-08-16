package com.example.diarioculturaljavafx.service;

import com.example.diarioculturaljavafx.model.Filme;
import com.example.diarioculturaljavafx.model.Livro;
import com.example.diarioculturaljavafx.model.Serie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Classe utilitária que mantém as coleções (acervo) de livros, filmes e séries
 * cadastrados na aplicação.
 *
 * <p>Oferece métodos para adicionar itens, recuperar listas inteiras e ordenar
 * cada coleção por avaliação.</p>
 */
public class Acervo {

    /** Lista estática que armazena todos os livros cadastrados. */
    private static final List<Livro> livroList = new ArrayList<>();

    /** Lista estática que armazena todos os filmes cadastrados. */
    private static final List<Filme> filmeList = new ArrayList<>();

    /** Lista estática que armazena todas as séries cadastradas. */
    private static final List<Serie> serieList = new ArrayList<>();

    // -----------------------------------------------------------
    // Métodos de acesso (Getters)
    // -----------------------------------------------------------

    /**
     * Obtém a lista completa de livros.
     *
     * @return {@link List} de {@link Livro}
     */
    public static List<Livro> getLivroList() {
        return livroList;
    }

    /**
     * Obtém a lista completa de filmes.
     *
     * @return {@link List} de {@link Filme}
     */
    public static List<Filme> getFilmeList() {
        return filmeList;
    }

    /**
     * Obtém a lista completa de séries.
     *
     * @return {@link List} de {@link Serie}
     */
    public static List<Serie> getSerieList() {
        return serieList;
    }

    // -----------------------------------------------------------
    // Métodos de adição
    // -----------------------------------------------------------

    /**
     * Adiciona um livro ao acervo.
     *
     * @param livro instância de {@link Livro} a ser adicionada
     */
    public static void adicionarLivro(Livro livro) {
        livroList.add(livro);
    }

    /**
     * Adiciona um filme ao acervo.
     *
     * @param filme instância de {@link Filme} a ser adicionada
     */
    public static void adicionarFilme(Filme filme) {
        filmeList.add(filme);
    }

    /**
     * Adiciona uma série ao acervo.
     *
     * @param serie instância de {@link Serie} a ser adicionada
     */
    public static void adicionarSerie(Serie serie) {
        serieList.add(serie);
    }

    // -----------------------------------------------------------
    // Métodos de ordenação por avaliação
    // -----------------------------------------------------------

    /**
     * Ordena a lista de livros pela avaliação.
     *
     * @param decrescente {@code true} para ordem decrescente; {@code false} para crescente
     */
    public static void ordenarLivrosPorAvaliacao(boolean decrescente) {
        livroList.sort(decrescente
                ? Comparator.comparing(Livro::getAvaliacao).reversed()
                : Comparator.comparing(Livro::getAvaliacao));
    }

    /**
     * Ordena a lista de filmes pela avaliação.
     *
     * @param decrescente {@code true} para ordem decrescente; {@code false} para crescente
     */
    public static void ordenarFilmesPorAvaliacao(boolean decrescente) {
        filmeList.sort(decrescente
                ? Comparator.comparing(Filme::getAvaliacao).reversed()
                : Comparator.comparing(Filme::getAvaliacao));
    }

    /**
     * Ordena a lista de séries pela avaliação.
     *
     * @param decrescente {@code true} para ordem decrescente; {@code false} para crescente
     */
    public static void ordenarSeriesPorAvaliacao(boolean decrescente) {
        serieList.sort(decrescente
                ? Comparator.comparing(Serie::getAvaliacao).reversed()
                : Comparator.comparing(Serie::getAvaliacao));
    }
}

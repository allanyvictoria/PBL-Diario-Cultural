package com.example.diarioculturaljavafx.testes.controller;

import com.example.diarioculturaljavafx.model.*;
import com.example.diarioculturaljavafx.service.Acervo;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AcervoTest {

    @Before
    public void setUp() {
        Acervo.getLivroList().clear();
        Acervo.getFilmeList().clear();
        Acervo.getSerieList().clear();
    }

    @Test
    public void testAdicionarLivro() {
        Livro livro = new Livro(
                "Livro X", 9, "Fantasia", 2020, "Muito bom",
                "Autor X", "Editora Y", "123456789", true,
                LocalDate.of(2021, 5, 10)
        );
        Acervo.adicionarLivro(livro);
        List<Livro> livros = Acervo.getLivroList();
        assertEquals(1, livros.size());
        assertEquals("Livro X", livros.get(0).getNome());
    }

    @Test
    public void testAdicionarFilme() {
        Filme filme = new Filme(
                "Filme X", 8, "Ação", 2021, "Adorei o filme",
                120,
                new ArrayList<>(Arrays.asList("Ator 1", "Ator 2")),
                "Netflix", "Original Title",
                new ArrayList<>(List.of("Roteirista A")),
                new ArrayList<>(List.of("Diretor B")),
                LocalDate.of(2022, 3, 20)
        );
        Acervo.adicionarFilme(filme);
        List<Filme> filmes = Acervo.getFilmeList();
        assertEquals(1, filmes.size());
        assertEquals("Filme X", filmes.get(0).getNome());
    }

    @Test
    public void testAdicionarSerie() {
        Serie serie = new Serie(
                "Série Y", "Comédia", 7, 2019, "Boa série",
                new ArrayList<>(List.of(new Temporada(2019, 10, 7, "Primeira Temp"))),
                2021,
                "Original Title Y",
                "HBO",
                new ArrayList<>(List.of("Ator A", "Ator B"))
        );
        Acervo.adicionarSerie(serie);
        List<Serie> series = Acervo.getSerieList();
        assertEquals(1, series.size());
        assertEquals("Série Y", series.get(0).getNome());
    }

    @Test
    public void testOrdenarLivrosPorAvaliacao() {
        Livro l1 = new Livro("Livro A", 6, "Ficção", 2018, "Ok", "Autor A", "Editora A", "111", true, LocalDate.now());
        Livro l2 = new Livro("Livro B", 9, "Suspense", 2020, "Excelente", "Autor B", "Editora B", "222", false, LocalDate.now());
        Acervo.adicionarLivro(l1);
        Acervo.adicionarLivro(l2);

        Acervo.ordenarLivrosPorAvaliacao(true);
        assertEquals("Livro B", Acervo.getLivroList().get(0).getNome());

        Acervo.ordenarLivrosPorAvaliacao(false);
        assertEquals("Livro A", Acervo.getLivroList().get(0).getNome());
    }

    @Test
    public void testOrdenarFilmesPorAvaliacao() {
        Filme f1 = new Filme("Filme A", 5, "Ação", 2021, "Fraco", 100,
                List.of("Ator A"), "HBO", "Titulo A", List.of("Roteiro A"), List.of("Diretor A"), LocalDate.now());
        Filme f2 = new Filme("Filme B", 9, "Ação", 2022, "Top", 120,
                List.of("Ator B"), "Netflix", "Titulo B", List.of("Roteiro B"), List.of("Diretor B"), LocalDate.now());
        Acervo.adicionarFilme(f1);
        Acervo.adicionarFilme(f2);

        Acervo.ordenarFilmesPorAvaliacao(true);
        assertEquals("Filme B", Acervo.getFilmeList().get(0).getNome());

        Acervo.ordenarFilmesPorAvaliacao(false);
        assertEquals("Filme A", Acervo.getFilmeList().get(0).getNome());
    }

    @Test
    public void testOrdenarSeriesPorAvaliacao() {
        Serie s1 = new Serie("Série A", "Drama", 7, 2019, "Boa",
                List.of(new Temporada(2019, 10, 7, "Temp 1")),
                2021, "Orig A", "Globoplay", List.of("Ator X"));
        Serie s2 = new Serie("Série B", "Drama", 10, 2020, "Ótima",
                List.of(new Temporada(2020, 8, 9, "Temp 2")),
                2022, "Orig B", "Netflix", List.of("Ator Y"));

        Acervo.adicionarSerie(s1);
        Acervo.adicionarSerie(s2);

        Acervo.ordenarSeriesPorAvaliacao(true);
        assertEquals("Série B", Acervo.getSerieList().get(0).getNome());

        Acervo.ordenarSeriesPorAvaliacao(false);
        assertEquals("Série A", Acervo.getSerieList().get(0).getNome());
    }
}

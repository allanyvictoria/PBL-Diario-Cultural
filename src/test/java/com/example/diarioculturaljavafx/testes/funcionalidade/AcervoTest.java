package com.example.diarioculturaljavafx.testes.funcionalidade;

import com.example.diarioculturaljavafx.model.Filme;
import com.example.diarioculturaljavafx.model.Livro;
import com.example.diarioculturaljavafx.model.Serie;
import com.example.diarioculturaljavafx.model.Temporada;
import com.example.diarioculturaljavafx.service.Acervo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AcervoTest {

    @BeforeEach
    public void limparAcervo() {
        Acervo.getLivroList().clear();
        Acervo.getFilmeList().clear();
        Acervo.getSerieList().clear();
    }

    @Test
    public void testAdicionarLivro() {
        Livro livro = new Livro(
                "O Senhor dos Anéis", 5, "Fantasia", 1954, "Excelente livro",
                "J.R.R. Tolkien", "HarperCollins", "9780261103573",
                true, LocalDate.now()
        );

        Acervo.adicionarLivro(livro);

        assertEquals(1, Acervo.getLivroList().size());
        assertTrue(Acervo.getLivroList().contains(livro));
    }

    @Test
    public void testAdicionarFilme() {
        Filme filme = new Filme(
                "Vingadores: Ultimato", 5, "Ação", 2019, "Melhor filme de ação",
                181, List.of("Robert Downey Jr.", "Chris Evans"),
                "Disney+", "Avengers: Endgame",
                List.of("Roteiro A", "Roteiro B"), List.of("Diretor A", "Diretor B"),
                LocalDate.now()
        );

        Acervo.adicionarFilme(filme);

        assertEquals(1, Acervo.getFilmeList().size());
        assertTrue(Acervo.getFilmeList().contains(filme));
    }

    @Test
    public void testAdicionarSerie() {
        Temporada temporada = new Temporada(2020, 13, 5, "bom");

        Serie serie = new Serie(
                "Breaking Bad", "Crime", 5, 2008, "Melhor série de todos os tempos",
                List.of(temporada), 2013, "Breaking Bad", "Netflix",
                List.of("Bryan Cranston", "Aaron Paul")
        );

        Acervo.adicionarSerie(serie);

        assertEquals(1, Acervo.getSerieList().size());
        assertTrue(Acervo.getSerieList().contains(serie));
    }
}

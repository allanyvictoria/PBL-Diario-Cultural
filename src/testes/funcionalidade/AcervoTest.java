package testes.funcionalidade;

import controller.Acervo;
import model.Filme;
import model.Livro;
import model.Serie;
import model.Temporada;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AcervoTest {

    @Test
    public void testAdicionarLivro() {
        // Adiciona um livro ao Acervo
        Livro livro = new Livro("O Senhor dos Anéis", 5, "Fantasia",
                1954, "Excelente livro", "J.R.R. Tolkien",
                "HarperCollins", "9780261103573", true, LocalDate.now());
        Acervo.adicionarLivro(livro);

        // Verifica se o número de livros na lista aumentou
        assertEquals(1, Acervo.getLivroList().size());
        assertTrue(Acervo.getLivroList().contains(livro));
    }

    @Test
    public void testAdicionarFilme() {
        // Adiciona um filme ao Acervo
        Filme filme = new Filme("Vingadores: Ultimato", 5,
                "Ação", 2019, "Melhor filme de ação", 181,
                List.of("Robert Downey Jr.", "Chris Evans"), "Disney+",
                "Avengers: Endgame", List.of("Roteiro A", "Roteiro B"), List.of("Diretor A",
                "Diretor B"), LocalDate.now());
        Acervo.adicionarFilme(filme);

        // Verifica se o número de filmes na lista aumentou
        assertEquals(1, Acervo.getFilmeList().size());
        assertTrue(Acervo.getFilmeList().contains(filme));
    }

    @Test
    public void testAdicionarSerie() {
        // Adiciona uma série ao Acervo
        Serie serie = new Serie("Breaking Bad", "Crime", 5, 2008,
                "Melhor série de todos os tempos", List.of(new Temporada(2020,
                13, 5, "bom")), 2013, "Breaking Bad",
                "Netflix", List.of("Bryan Cranston", "Aaron Paul"));
        Acervo.adicionarSerie(serie);

        // Verifica se o número de séries na lista aumentou
        assertEquals(1, Acervo.getSerieList().size());
        assertTrue(Acervo.getSerieList().contains(serie));
    }
}

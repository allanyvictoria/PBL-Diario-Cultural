package testes.integracao;

import controller.Acervo;
import controller.VerificaCadastro;
import model.Livro;
import model.Filme;
import model.Serie;
import org.junit.*;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.*;

public class VerificaCadastroIntegracaoTest {

    @Before
    public void setUp() {
        // Limpa as listas do Acervo antes de cada teste
        Acervo.getLivroList().clear();
        Acervo.getFilmeList().clear();
        Acervo.getSerieList().clear();
    }

    @After
    public void tearDown() {
        // Limpa as listas do Acervo depois de cada teste
        Acervo.getLivroList().clear();
        Acervo.getFilmeList().clear();
        Acervo.getSerieList().clear();
    }

    @Test
    public void testCadastroLivro() {
        assertEquals(0, Acervo.getLivroList().size());

        VerificaCadastro.verificaCadastro(
                "Dom Casmurro",
                5,
                "Romance",
                1899,
                "Clássico brasileiro.",
                "Machado de Assis",
                "Editora X",
                "123-4567890123",
                true,
                LocalDate.of(2023, 3, 10)
        );

        assertEquals(1, Acervo.getLivroList().size());
        Livro livro = Acervo.getLivroList().get(0);
        assertEquals("Dom Casmurro", livro.getNome());
        assertEquals(5, livro.getAvaliacao());
    }

    @Test
    public void testCadastroFilme() {
        assertEquals(0, Acervo.getFilmeList().size());

        VerificaCadastro.verificaCadastro(
                "Vingadores: Ultimato",
                5,
                "Ação",
                2019,
                "Final épico.",
                181,
                Arrays.asList("Robert Downey Jr.", "Chris Evans", "Scarlett Johansson"),
                "Disney+",
                "Avengers: Endgame",
                Arrays.asList("Roteiro 1", "Roteiro 2"),
                Arrays.asList("Direção 1", "Direção 2"),
                LocalDate.of(2021, 1, 1)
        );

        assertEquals(1, Acervo.getFilmeList().size());
        Filme filme = Acervo.getFilmeList().get(0);
        assertEquals("Vingadores: Ultimato", filme.getNome());
        assertEquals(5, filme.getAvaliacao());
    }

    @Test
    public void testCadastroSerie() {
        assertEquals(0, Acervo.getSerieList().size());

        VerificaCadastro.verificaCadastro(
                "Stranger Things",
                "Terror",
                4,
                2016,
                "Mistério sobrenatural.",
                Arrays.asList(),
                3,
                "Stranger Things",
                "Netflix",
                Arrays.asList("Millie Bobby Brown", "Winona Ryder")
        );

        assertEquals(1, Acervo.getSerieList().size());
        Serie serie = Acervo.getSerieList().get(0);
        assertEquals("Stranger Things", serie.getNome());
        assertEquals(4, serie.getAvaliacao());
    }
}

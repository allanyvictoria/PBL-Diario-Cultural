package testes.funcionalidade;

import controller.Acervo;
import controller.VerificaCadastro;
import model.Filme;
import org.junit.*;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ListagemFilmesTest {

    @Before
    public void setUp() {
        // Limpa a lista de filmes antes de cada teste
        Acervo.getFilmeList().clear();
    }

    @After
    public void tearDown() {
        // Limpa a lista de filmes depois de cada teste
        Acervo.getFilmeList().clear();
    }

    @Test
    public void testListarFilmes() {
        // Adicionando filmes para testar a listagem
        VerificaCadastro.verificaCadastro(
                "Vingadores: Ultimato",
                5,
                "Ação",
                2019,
                "Final épico.",
                181,
                Arrays.asList("Robert Downey Jr.", "Chris Evans"),
                "Disney+",
                "Avengers: Endgame",
                Arrays.asList("Roteiro 1", "Roteiro 2"),
                Arrays.asList("Direção 1", "Direção 2"),
                LocalDate.of(2021, 1, 1)
        );

        VerificaCadastro.verificaCadastro(
                "Pantera Negra",
                4,
                "Ação",
                2018,
                "Herói africano.",
                134,
                Arrays.asList("Chadwick Boseman", "Michael B. Jordan"),
                "Disney+",
                "Black Panther",
                Arrays.asList("Roteiro 1", "Roteiro 2"),
                Arrays.asList("Direção 1", "Direção 2"),
                LocalDate.of(2020, 3, 15)
        );

        // Verifica se a lista de filmes tem o tamanho correto
        assertEquals(2, Acervo.getFilmeList().size());

        // Verifica se o nome do primeiro filme na lista é "Vingadores: Ultimato"
        Filme primeiroFilme = Acervo.getFilmeList().get(0);
        assertEquals("Vingadores: Ultimato", primeiroFilme.getNome());

        // Testando a listagem de filmes por avaliação decrescente
        Acervo.listarFilmesAvaliacao(true); // Ordenação decrescente
        assertEquals("Vingadores: Ultimato", Acervo.getFilmeList().get(0).getNome());
        assertEquals("Pantera Negra", Acervo.getFilmeList().get(1).getNome());

        // Testando a listagem de filmes por avaliação crescente
        Acervo.listarFilmesAvaliacao(false); // Ordenação crescente
        assertEquals("Pantera Negra", Acervo.getFilmeList().get(0).getNome());
        assertEquals("Vingadores: Ultimato", Acervo.getFilmeList().get(1).getNome());
    }
}

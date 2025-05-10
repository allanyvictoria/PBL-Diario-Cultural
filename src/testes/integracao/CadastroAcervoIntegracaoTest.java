package testes.integracao;

import controller.Acervo;
import model.Cadastro;
import model.Livro;
import org.junit.*;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class CadastroAcervoIntegracaoTest {

    @Before
    public void setUp() {
        Acervo.getLivroList().clear(); // Limpa antes de cada teste
    }

    @After
    public void tearDown() {
        Acervo.getLivroList().clear(); // Limpa depois também, pra garantir
    }

    @Test
    public void testCadastroLivroAdicionaNoAcervo() {
        assertEquals(0, Acervo.getLivroList().size());

        Cadastro.cadastro(
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
}

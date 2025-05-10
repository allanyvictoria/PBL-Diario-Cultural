package testes.model;

import model.Livro;
import org.junit.*;
import static org.junit.Assert.*;

import java.time.LocalDate;

public class LivroTest {

    private Livro livro;

    @Before
    public void setUp() {
        livro = new Livro(
                "O Senhor dos Anéis",
                10,
                "Fantasia",
                1954,
                "Uma obra-prima da literatura fantástica.",
                "J.R.R. Tolkien",
                "HarperCollins",
                "978-0-261-10236-9",
                true,
                LocalDate.of(2020, 6, 15)
        );
    }

    @Test
    public void testGetters() {
        assertEquals("O Senhor dos Anéis", livro.getNome());
        assertEquals(10, livro.getAvaliacao());
        assertEquals("Fantasia", livro.getGenero());
        assertEquals(1954, livro.getAno());
        assertEquals("Uma obra-prima da literatura fantástica.", livro.getReview());
        assertEquals("J.R.R. Tolkien", livro.getAutor());
        assertEquals("HarperCollins", livro.getEditora());
        assertEquals("978-0-261-10236-9", livro.getIsbn());
        assertTrue(livro.isPossui());
        assertEquals(LocalDate.of(2020, 6, 15), livro.getLeitura());
    }

}


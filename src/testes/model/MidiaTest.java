package testes.model;

import model.Midia;
import org.junit.*;
import static org.junit.Assert.*;

public class MidiaTest {

    private Midia midia;

    @Before
    public void setUp() {
        midia = new Midia(
                "Nome Genérico",
                7,
                "Comédia",
                2015,
                "Review interessante e equilibrada."
        );
    }

    @Test
    public void testGetNome() {
        assertEquals("Nome Genérico", midia.getNome());
    }

    @Test
    public void testGetAvaliacao() {
        assertEquals(7, midia.getAvaliacao());
    }

    @Test
    public void testGetGenero() {
        assertEquals("Comédia", midia.getGenero());
    }

    @Test
    public void testGetAno() {
        assertEquals(2015, midia.getAno());
    }

    @Test
    public void testGetReview() {
        assertEquals("Review interessante e equilibrada.", midia.getReview());
    }
}


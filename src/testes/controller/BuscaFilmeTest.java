package testes.controller;

import controller.Acervo;
import controller.BuscaFilme;
import model.Filme;
import org.junit.*;
import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.*;

public class BuscaFilmeTest {

    private final ByteArrayOutputStream saidaCapturada = new ByteArrayOutputStream();
    private final PrintStream saidaOriginal = System.out;

    Filme filme1, filme2, filme3;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(saidaCapturada));

        Acervo.getFilmeList().clear();

        filme1 = new Filme("Inception", 9, "Ficção", 2010, "Ótimo",
                148, Arrays.asList("Leonardo DiCaprio", "Tom Hardy"), "Netflix",
                "Inception", Arrays.asList("Christopher Nolan"),
                Arrays.asList("Christopher Nolan"), LocalDate.of(2020, 6, 10));

        filme2 = new Filme("Interestelar", 10, "Ficção", 2014, "Maravilhoso",
                169, Arrays.asList("Matthew McConaughey", "Anne Hathaway"), "HBO Max",
                "Interstellar", Arrays.asList("Jonathan Nolan"),
                Arrays.asList("Christopher Nolan"), LocalDate.of(2021, 8, 15));

        filme3 = new Filme("Clube da Luta", 8, "Drama", 1999, "Impactante",
                139, Arrays.asList("Brad Pitt", "Edward Norton"), "Prime Video",
                "Fight Club", Arrays.asList("Jim Uhls"),
                Arrays.asList("David Fincher"), LocalDate.of(2019, 10, 5));

        Acervo.adicionarFilme(filme1);
        Acervo.adicionarFilme(filme2);
        Acervo.adicionarFilme(filme3);
    }

    @After
    public void tearDown() {
        System.setOut(saidaOriginal); // restaura saída padrão
    }

    private String getSaida() {
        return saidaCapturada.toString().trim();
    }

    @Test
    public void testBuscaPorTitulo() {
        BuscaFilme.titulo("Inception");
        String saida = getSaida();
        assertTrue(saida.contains("Inception"));
        assertFalse(saida.contains("Clube da Luta"));
    }

    @Test
    public void testBuscaPorDiretor() {
        BuscaFilme.buscarPorDiretor("Christopher Nolan");
        String saida = getSaida();
        assertTrue(saida.contains("Inception"));
        assertTrue(saida.contains("Interestelar"));
        assertFalse(saida.contains("Clube da Luta"));
    }

    @Test
    public void testBuscaPorAtor() {
        BuscaFilme.buscarPorAtor("Brad Pitt");
        String saida = getSaida();
        assertTrue(saida.contains("Clube da Luta"));
        assertFalse(saida.contains("Inception"));
    }

    @Test
    public void testBuscaPorGenero() {
        BuscaFilme.buscaGenero("Ficção");
        String saida = getSaida();
        assertTrue(saida.contains("Inception"));
        assertTrue(saida.contains("Interestelar"));
        assertFalse(saida.contains("Clube da Luta"));
    }

    @Test
    public void testBuscaPorAno() {
        BuscaFilme.ano(2014);
        String saida = getSaida();
        assertTrue(saida.contains("Interestelar"));
        assertFalse(saida.contains("Inception"));
    }
}

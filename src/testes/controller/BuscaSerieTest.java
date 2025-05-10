package testes.controller;

import controller.Acervo;
import controller.BuscaSerie;
import model.Serie;
import model.Temporada;
import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class BuscaSerieTest {

    private final ByteArrayOutputStream saidaCapturada = new ByteArrayOutputStream();
    private final PrintStream saidaOriginal = System.out;

    Serie serie1, serie2, serie3;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(saidaCapturada));

        Acervo.getSerieList().clear();

        serie1 = new Serie("Breaking Bad", "Crime", 10, 2008, "Brilhante",
                Collections.singletonList(new Temporada(1, 7, 5, "bom")), 2013,
                "Breaking Bad", "Netflix", Arrays.asList("Bryan Cranston", "Aaron Paul"));

        serie2 = new Serie("Dark", "Ficção", 9, 2017, "Intrigante",
                Collections.singletonList(new Temporada(1, 10, 5, "bom")), 2020,
                "Dark", "Netflix", Arrays.asList("Louis Hofmann", "Lisa Vicari"));

        serie3 = new Serie("Friends", "Comédia", 8, 1994, "Clássica",
                Collections.singletonList(new Temporada(1, 24, 5, "bom")), 2004,
                "Friends", "HBO Max", Arrays.asList("Jennifer Aniston", "Courteney Cox"));

        Acervo.adicionarSerie(serie1);
        Acervo.adicionarSerie(serie2);
        Acervo.adicionarSerie(serie3);
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
        BuscaSerie.titulo("Dark");
        String saida = getSaida();
        assertTrue(saida.contains("Dark"));
        assertFalse(saida.contains("Friends"));
    }

    @Test
    public void testBuscaPorGenero() {
        BuscaSerie.buscaGenero("Ficção");
        String saida = getSaida();
        assertTrue(saida.contains("Dark"));
        assertFalse(saida.contains("Friends"));
    }

    @Test
    public void testBuscaPorAtor() {
        BuscaSerie.buscarPorAtor("Aaron Paul");
        String saida = getSaida();
        assertTrue(saida.contains("Breaking Bad"));
        assertFalse(saida.contains("Dark"));
    }

    @Test
    public void testBuscaPorAno() {
        BuscaSerie.ano(1994);
        String saida = getSaida();
        assertTrue(saida.contains("Friends"));
        assertFalse(saida.contains("Dark"));
    }
}

package testes.model;

import model.Serie;
import model.Temporada;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;

public class SerieTest {

    private Serie serie;

    @Before
    public void setUp() {
        List<Temporada> temporadas = new ArrayList<>();
        temporadas.add(new Temporada(2020, 10, 8, "Primeira temporada muito boa"));
        List<String> elenco = new ArrayList<>(Arrays.asList("Ator A", "Ator B"));

        serie = new Serie(
                "Nome da Série",
                "Drama",
                9,
                2020,
                "Review geral da série",
                temporadas,
                2022,
                "Original Title",
                "Netflix",
                elenco
        );
    }

    @Test
    public void testAdicionarAtor() {
        serie.adicionarAtor("Ator C");
        List<String> elenco = serie.getElenco();
        assertTrue(elenco.contains("Ator C"));
        assertEquals(3, elenco.size());
    }

    @Test
    public void testAdicionarTemporada() {
        Temporada novaTemp = new Temporada(2021, 8, 9, "Segunda temporada ainda melhor");
        serie.adicionarTemporada(novaTemp);
        List<Temporada> temps = serie.getTemps();
        assertEquals(2, temps.size());
        assertEquals(2021, temps.get(1).getAno());
    }

    @Test
    public void testGetters() {
        assertEquals("Netflix", serie.getOndeAssistiu());
        assertEquals("Original Title", serie.getTituloOriginal());
        assertEquals(2022, serie.getFim());
        assertEquals("Drama", serie.getGenero());
        assertEquals(2020, serie.getAno());
        assertEquals(9, serie.getAvaliacao());
        assertEquals("Review geral da série", serie.getReview());
    }
}

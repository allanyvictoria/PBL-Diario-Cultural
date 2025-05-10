package testes.controller;

import controller.Acervo;
import controller.VerificaCadastro;
import model.*;
import org.junit.*;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class VerificaCadastroTest {

    private final InputStream entradaOriginal = System.in;
    private final PrintStream saidaOriginal = System.out;
    private final ByteArrayOutputStream saidaCapturada = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(saidaCapturada));
        Acervo.getLivroList().clear();
        Acervo.getFilmeList().clear();
        Acervo.getSerieList().clear();
    }

    @After
    public void tearDown() {
        System.setIn(entradaOriginal);
        System.setOut(saidaOriginal);
    }

    private void simulaEntrada(String dados) {
        ByteArrayInputStream entradaSimulada = new ByteArrayInputStream(dados.getBytes());
        System.setIn(entradaSimulada);
    }

    private String getSaida() {
        return saidaCapturada.toString().trim();
    }

    // ----------- TESTES PARA LIVRO -----------

    @Test
    public void testLivroIsbnInvalidoCorrigeDepois() {
        simulaEntrada("1234567890\n"); // ISBN válido após erro

        VerificaCadastro.verificaCadastro(
                "Livro Teste", 4, "Aventura", 2000, "Bom",
                "Autor A", "Editora B", "123", true, LocalDate.now());

        String saida = getSaida();
        assertTrue(saida.contains("ISBN deve ter 10 ou 13 dígitos"));
        assertEquals(1, Acervo.getLivroList().size());
        assertEquals("Livro Teste", Acervo.getLivroList().get(0).getNome());
    }

    @Test
    public void testLivroAvaliacaoInvalidaCorrigeDepois() {
        simulaEntrada("5\n"); // Corrige avaliação para 5

        VerificaCadastro.verificaCadastro(
                "Livro Nota", 0, "Drama", 2001, "Excelente",
                "Autor B", "Editora X", "1234567890", false, LocalDate.now());

        String saida = getSaida();
        assertTrue(saida.contains("Avalie entre 0 e 5"));
        assertEquals(1, Acervo.getLivroList().size());
    }

    @Test
    public void testLivroSemLeituraNaoValidaNada() {
        VerificaCadastro.verificaCadastro(
                "Sem Leitura", 0, "Ficção", 2020, "Não lido",
                "Autor Z", "Editora Z", "1234567890", false, null);

        assertEquals(1, Acervo.getLivroList().size());
    }

    // ----------- TESTES PARA FILME -----------

    @Test
    public void testFilmeComAvaliacaoInvalidaCorrigeDepois() {
        simulaEntrada("4\n");

        VerificaCadastro.verificaCadastro(
                "Filme Teste", 6, "Suspense", 2010, "Tenso",
                120, Arrays.asList("Ator A", "Ator B"),
                "Netflix", "Original Title",
                Arrays.asList("Roteirista A"), Arrays.asList("Diretor B"),
                LocalDate.now());

        String saida = getSaida();
        assertTrue(saida.contains("Avalie entre 0 e 5"));
        assertEquals(1, Acervo.getFilmeList().size());
    }

    @Test
    public void testFilmeSemDataNaoValidaNota() {
        VerificaCadastro.verificaCadastro(
                "Filme Sem Data", 7, "Ação", 2015, "Explosivo",
                100, Arrays.asList("Ator X"),
                "HBO Max", "Explosive Title",
                Arrays.asList("Roteirista Z"), Arrays.asList("Diretor Y"),
                null); // Não tem data assistiu

        // Mesmo nota fora do intervalo, cadastro é aceito
        assertEquals(1, Acervo.getFilmeList().size());
        assertEquals("Filme Sem Data", Acervo.getFilmeList().get(0).getNome());
    }

    // ----------- TESTES PARA SÉRIE -----------

    @Test
    public void testSerieCadastroSimples() {
        Temporada temp1 = new Temporada(1, 10,4, "legal");

        VerificaCadastro.verificaCadastro(
                "Série Top", "Drama", 5, 2021, "Muito boa",
                Collections.singletonList(temp1),
                2023, "Original Title", "Amazon",
                Arrays.asList("Ator Y", "Ator Z"));

        assertEquals(1, Acervo.getSerieList().size());
        assertEquals("Série Top", Acervo.getSerieList().get(0).getNome());
    }
}

package com.example.diarioculturaljavafx.testes.model;

import com.example.diarioculturaljavafx.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PersistenciaAcervoTest {

    private static final String TEST_LIVROS_FILE = "test_livros.json";
    private static final String TEST_FILMES_FILE = "test_filmes.json";
    private static final String TEST_SERIES_FILE = "test_series.json";

    private List<Livro> livrosTeste;
    private List<Filme> filmesTeste;
    private List<Serie> seriesTeste;

    @Before
    public void setUp() {
        // Cria dados de teste para Livro
        livrosTeste = new ArrayList<>();
        livrosTeste.add(new Livro(
                "Dom Casmurro",
                5,
                "Romance",
                1899,
                "Clássico da literatura brasileira",
                "Machado de Assis",
                "Editora Garnier",
                "978-85-7232-000-0",
                true,
                LocalDate.of(2023, 5, 10)
        ));

        // Cria dados de teste para Filme
        filmesTeste = new ArrayList<>();
        filmesTeste.add(new Filme(
                "O Poderoso Chefão",
                5,
                "Drama",
                1972,
                "Um dos melhores filmes de todos os tempos",
                175,
                List.of("Marlon Brando", "Al Pacino", "James Caan"),
                "Netflix",
                "The Godfather",
                List.of("Mario Puzo", "Francis Ford Coppola"),
                List.of("Francis Ford Coppola"),
                LocalDate.of(2023, 4, 15)
        ));

        // Cria dados de teste para Série
        seriesTeste = new ArrayList<>();
        seriesTeste.add(new Serie(
                "Breaking Bad",
                "Drama",
                5,
                2008,
                "Série incrível sobre a transformação de um homem comum",
                new ArrayList<>(),
                2013,
                "Breaking Bad",
                "Netflix",
                List.of("Bryan Cranston", "Aaron Paul", "Anna Gunn")
        ));

        // Adiciona temporadas à série
        seriesTeste.get(0).adicionarTemporada(new Temporada(2008, 7, 5, "Excelente primeira temporada"));
    }

    @After
    public void tearDown() {
        // Limpa arquivos de teste após cada teste
        deleteTestFiles();
    }

    @Test
    public void testSalvarECarregarLivros() {
        // Salva a lista de livros
        PersistenciaAcervo.salvarListaJson(livrosTeste, TEST_LIVROS_FILE);

        // Carrega a lista de livros
        List<Livro> livrosCarregados = PersistenciaAcervo.carregarListaJson(TEST_LIVROS_FILE, Livro.class);

        // Verifica se os dados foram corretamente salvos e carregados
        assertEquals(1, livrosCarregados.size());
        assertEquals("Dom Casmurro", livrosCarregados.get(0).getNome());
        assertEquals("Machado de Assis", livrosCarregados.get(0).getAutor());
        assertEquals(LocalDate.of(2023, 5, 10), livrosCarregados.get(0).getLeitura());
    }

    @Test
    public void testSalvarECarregarFilmes() {
        // Salva a lista de filmes
        PersistenciaAcervo.salvarListaJson(filmesTeste, TEST_FILMES_FILE);

        // Carrega a lista de filmes
        List<Filme> filmesCarregados = PersistenciaAcervo.carregarListaJson(TEST_FILMES_FILE, Filme.class);

        // Verifica os dados
        assertEquals(1, filmesCarregados.size());
        assertEquals("O Poderoso Chefão", filmesCarregados.get(0).getNome());
        assertEquals(175, filmesCarregados.get(0).getDuracao());
        assertEquals("The Godfather", filmesCarregados.get(0).getTituloOriginal());
        assertEquals(LocalDate.of(2023, 4, 15), filmesCarregados.get(0).getDataAssistiu());
    }

    @Test
    public void testSalvarECarregarSeries() {
        // Salva a lista de séries
        PersistenciaAcervo.salvarListaJson(seriesTeste, TEST_SERIES_FILE);

        // Carrega a lista de séries
        List<Serie> seriesCarregadas = PersistenciaAcervo.carregarListaJson(TEST_SERIES_FILE, Serie.class);

        // Verifica os dados
        assertEquals(1, seriesCarregadas.size());
        assertEquals("Breaking Bad", seriesCarregadas.get(0).getNome());
        assertEquals(2013, seriesCarregadas.get(0).getFim());
        assertEquals(1, seriesCarregadas.get(0).getTemporadas().size());
        assertEquals(2008, seriesCarregadas.get(0).getTemporadas().get(0).getAno());
    }

    @Test
    public void testArquivoNaoExiste() {
        // Tenta carregar de um arquivo que não existe
        List<Livro> resultado = PersistenciaAcervo.carregarListaJson("arquivo_inexistente.json", Livro.class);

        // Deve retornar uma lista vazia
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testArquivoVazio() throws IOException {
        // Cria um arquivo vazio
        Files.write(Paths.get(TEST_LIVROS_FILE), "".getBytes());

        // Tenta carregar o arquivo vazio
        List<Livro> resultado = PersistenciaAcervo.carregarListaJson(TEST_LIVROS_FILE, Livro.class);

        // Deve retornar uma lista vazia
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testJsonInvalido() throws IOException {
        // Cria um arquivo com JSON inválido
        Files.write(Paths.get(TEST_LIVROS_FILE), "{isso não é um json válido}".getBytes());

        // Tenta carregar o arquivo
        List<Livro> resultado = PersistenciaAcervo.carregarListaJson(TEST_LIVROS_FILE, Livro.class);

        // Deve retornar uma lista vazia
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testLocalDateSerializacao() {
        // Testa a serialização de LocalDate usando um livro
        LocalDate dataEspecifica = LocalDate.of(2023, 6, 20);
        Livro livroComData = new Livro(
                "1984",
                5,
                "Ficção Distópica",
                1949,
                "Clássico atemporal",
                "George Orwell",
                "Companhia Editora Nacional",
                "978-85-221-0000-0",
                true,
                dataEspecifica
        );
        livrosTeste.add(livroComData);

        // Salva e carrega
        PersistenciaAcervo.salvarListaJson(livrosTeste, TEST_LIVROS_FILE);
        List<Livro> livrosCarregados = PersistenciaAcervo.carregarListaJson(TEST_LIVROS_FILE, Livro.class);

        // Verifica se a data foi corretamente serializada/desserializada
        assertEquals(dataEspecifica, livrosCarregados.get(1).getLeitura());
    }

    private static void deleteTestFiles() {
        // Deleta todos os arquivos de teste
        new File(TEST_LIVROS_FILE).delete();
        new File(TEST_FILMES_FILE).delete();
        new File(TEST_SERIES_FILE).delete();
    }
}
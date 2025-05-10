package testes.views;

import model.Filme;
import model.Livro;
import model.Serie;
import model.Temporada;
import view.Listagem;
import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ListagemTest {
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @Before
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testListaLivrosVazio() {
        Listagem.listaLivros(Collections.emptyList());
        String output = outContent.toString();
        assertTrue(output.contains("Nenhum livro cadastrado."));
    }

    @Test
    public void testListaLivrosComDados() {
        Livro livro = new Livro(
                "Livro Teste",         // nome
                4,                     // avaliacao
                "Ficção",              // genero
                2020,                  // ano
                "Muito bom",           // review
                "Autor Teste",         // autor
                "Editora X",           // editora
                "123456789",           // isbn
                true,                  // possui
                LocalDate.of(2024, 1, 1) // leitura
        );

        Listagem.listaLivros(List.of(livro));
        String output = outContent.toString();
        assertTrue(output.contains("Livro Livro Teste"));
        assertTrue(output.contains("Autor: Autor Teste"));
        assertTrue(output.contains("Editora: Editora X"));
        assertTrue(output.contains("Status: lido"));
        assertTrue(output.contains("Data de leitura: 2024-01-01"));
        assertTrue(output.contains("Review: Muito bom"));
        assertTrue(output.contains("Avaliação: 4"));
    }


    @Test
    public void testListaFilmesVazio() {
        Listagem.listaFilmes(Collections.emptyList());
        String output = outContent.toString();
        assertTrue(output.contains("Nenhum filme cadastrado."));
    }

    @Test
    public void testListaFilmesComDados() {
        Filme filme = new Filme(
                "Filme Teste",       // nome
                5,                   // avaliacao
                "Drama",             // genero
                2022,                // ano
                "Gostei bastante",   // review
                120,                 // duracao
                List.of("Ator A", "Ator B"),        // elenco
                "Netflix",           // ondeAssistir
                "Original Title",    // titulo
                List.of("Roteirista A"),            // roteiro
                List.of("Diretor A"),               // direcao
                LocalDate.of(2023, 12, 12)          // dataAssistiu
        );

        Listagem.listaFilmes(List.of(filme));
        String output = outContent.toString();
        assertTrue(output.contains("Filme Filme Teste"));
        assertTrue(output.contains("Titulo Original: Original Title"));
        assertTrue(output.contains("Gênero: Drama"));
        assertTrue(output.contains("Ano de lançamento: 2022"));
        assertTrue(output.contains("Duraço: 120"));
        assertTrue(output.contains("Ator A"));
        assertTrue(output.contains("Diretor A"));
        assertTrue(output.contains("Roteirista A"));
        assertTrue(output.contains("Status: Visto"));
        assertTrue(output.contains("Data de visualização: 2023-12-12"));
        assertTrue(output.contains("Onde assistiu: Netflix"));
        assertTrue(output.contains("Review: Gostei bastante"));
        assertTrue(output.contains("Avaliação: 5"));
    }


        @Test
    public void testListaSeriesVazio() {
        Listagem.listaSeries(Collections.emptyList());
        String output = outContent.toString();
        assertTrue(output.contains("Nenhuma série cadastrada."));
    }

    @Test
    public void testListaSeriesComDados() {
        // Criando uma Temporada
        Temporada temporada = new Temporada(2021, 10, 8, "Muito boa temporada");

        // Criando uma Serie com a Temporada
        Serie serie = new Serie(
                "Série Teste",
                "Suspense",
                5,
                2019,
                "Review da série",
                new ArrayList<>(List.of(temporada)), // Usando ArrayList para permitir modificações
                2023,
                "Título Original Série",
                "HBO Max",
                new ArrayList<>(List.of("Ator A", "Ator B")) // Lista mutável
        );

        // Chama o metodo de listagem
        Listagem.listaSeries(List.of(serie));

        // Captura a saída
        String output = outContent.toString();

        // Verificando se as informações estão presentes na saída
        assertTrue(output.contains("Série Série Teste"));
        assertTrue(output.contains("Título Original: Título Original Série"));
        assertTrue(output.contains("Ano de encerramento: 2023"));
        assertTrue(output.contains("Temporada 1"));
        assertTrue(output.contains("Ano de lançamento: 2021"));
        assertTrue(output.contains("Quantidade: 10"));
        assertTrue(output.contains("Review: Muito boa temporada"));
    }
}

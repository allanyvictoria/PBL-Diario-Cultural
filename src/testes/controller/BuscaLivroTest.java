package testes.controller;
import controller.Acervo;
import controller.BuscaLivro;
import model.Livro;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BuscaLivroTest {
    private static final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(output));

        // Limpa e adiciona livros ao acervo
        Acervo.getLivroList().clear();

        Livro livro1 = new Livro("Dom Quixote", 5, "Romance", 1605, "Excelente",
                "Miguel de Cervantes", "Planeta", "978-1234567890", true,
                LocalDate.of(2023, 1, 1));

        Livro livro2 = new Livro("A Revolução dos Bichos", 4, "Fábula", 1945, "Crítico e inteligente",
                "George Orwell", "Companhia das Letras", "978-9876543210", false, null);

        Acervo.getLivroList().add(livro1);
        Acervo.getLivroList().add(livro2);

        output.reset();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testBuscaPorTitulo() {
        BuscaLivro.titulo("dom quixote");
        String saida = output.toString();
        assertTrue(saida.contains("Dom Quixote"));
        assertTrue(saida.contains("Miguel de Cervantes"));
    }

    @Test
    void testBuscaPorAutor() {
        BuscaLivro.autor("george orwell");
        String saida = output.toString();
        assertTrue(saida.contains("A Revolução dos Bichos"));
        assertTrue(saida.contains("George Orwell"));
    }

    @Test
    void testBuscaPorGenero() {
        BuscaLivro.buscaGenero("fábula");
        String saida = output.toString();
        assertTrue(saida.contains("A Revolução dos Bichos"));
        assertTrue(saida.contains("Fábula"));
    }

    @Test
    void testBuscaPorAno() {
        BuscaLivro.ano(1605);
        String saida = output.toString();
        assertTrue(saida.contains("Dom Quixote"));
        assertTrue(saida.contains("1605"));
    }

    @Test
    void testBuscaPorISBN() {
        BuscaLivro.isbn("9781234567890");
        String saida = output.toString();
        assertTrue(saida.contains("Dom Quixote"));
        assertTrue(saida.contains("978-1234567890"));
    }
}

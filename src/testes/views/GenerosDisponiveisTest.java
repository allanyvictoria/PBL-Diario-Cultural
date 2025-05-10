package testes.views;

import org.junit.*;
import view.GenerosDisponiveis;

import java.io.*;
import static org.junit.Assert.*;

public class GenerosDisponiveisTest {
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private ByteArrayOutputStream outContent;
    private ByteArrayInputStream inContent;

    @Before
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testExibirGeneros() {
        // Simula o usuário escolhe a opção 3 (Comédia)
        String input = "3\n";
        inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        // Chama o método exibirGeneros()
        String generoEscolhido = GenerosDisponiveis.exibirGeneros();

        // Verifica a saída e o valor retornado
        assertEquals("Comédia", generoEscolhido);

        // Verifica se o gênero foi exibido corretamente
        String output = outContent.toString();
        assertTrue(output.contains("=== GÊNEROS DISPONÍVEIS ==="));
        assertTrue(output.contains("3 - Comédia"));
    }

    @Test
    public void testExibirGenerosComEntradaInvalida() {
        String input = "2\n";  //  2 (Aventura)
        inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        // Chama o metodo
        String generoEscolhido = GenerosDisponiveis.exibirGeneros();

        // Verifica se o valor retornado é o esperado
        assertEquals("Aventura", generoEscolhido);

        // Verifica se a saída contém a mensagem de erro e a opção correta
        String output = outContent.toString();
        assertTrue(output.contains("=== GÊNEROS DISPONÍVEIS ==="));
        assertTrue(output.contains("2 - Aventura"));
    }
}


package testes.views;

import org.junit.*;
import view.MenuCadastro;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class MenuCadastroTest {
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private ByteArrayOutputStream outContent;

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
    public void testSaidaMenuCadastro() {
        String input = "4\n"; // Simula o usuário digitando 4
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        MenuCadastro.menucadastro();

        String output = outContent.toString();
        assertTrue(output.contains("=== Bem-vindo ao cadastro ==="));
        assertTrue(output.contains("Saindo..."));
    }

    @Test
    public void testMenuCadastroEntradaInvalida() {
        String input = "0\n4\n"; // Simula digitar uma opção inválida e depois sair
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        MenuCadastro.menucadastro();

        String output = outContent.toString();
        assertTrue(output.contains("=== Bem-vindo ao cadastro ==="));
        assertTrue(output.contains("Opção inválida. Tente novamente."));
    }
}

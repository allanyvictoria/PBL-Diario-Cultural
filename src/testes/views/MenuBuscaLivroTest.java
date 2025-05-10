package testes.views;

import org.junit.*;
import view.MenuBuscaLivro;

import java.io.*;

import static org.junit.Assert.*;

public class MenuBuscaLivroTest {
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
    public void testMenuBuscaLivroFinalizar() {
        // Simula a entrada de um usuário que escolhe a opção 6 (Sair)
        String input = "6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        MenuBuscaLivro.menuBusca();

        String output = outContent.toString();

        // Verifica se a saída contém a mensagem de "Saindo..."
        assertTrue(output.contains("Saindo..."));
    }

}

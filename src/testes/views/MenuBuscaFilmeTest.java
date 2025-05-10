package testes.views;


import org.junit.*;
import view.MenuBuscaFilme;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class MenuBuscaFilmeTest {
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
    public void testSaida() {
        String input = "6\n"; // Simula o usuário escolhendo sair
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        MenuBuscaFilme.menuFilme();

        String output = outContent.toString();
        assertTrue(output.contains("=== BUSCAR FILME POR ==="));
        assertTrue(output.contains("Saindo..."));
    }

}

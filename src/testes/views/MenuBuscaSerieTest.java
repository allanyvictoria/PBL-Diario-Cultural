package testes.views;

import org.junit.*;
import view.MenuBuscaSerie;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class MenuBuscaSerieTest {
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
        String input = "5\n"; // Simula o usuário escolhendo sair
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        MenuBuscaSerie.menuSerie();

        String output = outContent.toString();
        assertTrue(output.contains("=== BUSCAR SÉRIE POR ==="));
        assertTrue(output.contains("Saindo..."));
    }

}

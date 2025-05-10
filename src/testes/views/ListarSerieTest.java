package testes.views;

import org.junit.*;
import view.ListarSerie;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class ListarSerieTest {
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
    public void testOpcaoInvalidaESair() {
        // Simula o usuário digitando sair (3)
        String input = "3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ListarSerie.listar();

        String output = outContent.toString();
        assertTrue(output.contains("Saindo..."));
    }
}

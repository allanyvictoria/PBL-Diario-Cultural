package testes.views;

import org.junit.*;
import view.Listar;
import view.VerificaInteiro;

import java.io.*;
import static org.junit.Assert.assertTrue;

public class ListarTest {
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
        String input = "4\n"; // Simula o usuário escolhendo sair
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Listar.listar();

        String output = outContent.toString();
        assertTrue(output.contains("LISTAR"));
        assertTrue(output.contains("Saindo..."));
    }

    @Test
    public void testMenuBusca() {
        System.setIn(new ByteArrayInputStream("0\n".getBytes()));
        VerificaInteiro.verificaInteiro("Digite um número: "); // 1ª chamada

        System.setIn(new ByteArrayInputStream("4\n".getBytes()));
        // 2ª chamada
        Listar.listar();

        String output = outContent.toString();
        assertTrue(output.contains("LISTAR"));
        assertTrue(output.contains("Saindo..."));
    }
}


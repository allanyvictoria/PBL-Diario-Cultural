package testes.views;

import org.junit.*;
import view.MenuBusca;
import view.VerificaInteiro;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class MenuBuscaTest {
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
    public void testSaidaMenu() {
        String input = "4\n"; // Simula o usuário digitando 4 e pressionando Enter
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        MenuBusca.menuBusca();

        String output = outContent.toString();
        assertTrue(output.contains("BUSCA"));
        assertTrue(output.contains("Saindo..."));
    }
    @Test
    public void testMenuBusca() {
        System.setIn(new ByteArrayInputStream("abc\n".getBytes()));
        VerificaInteiro.verificaInteiro("Digite um número: "); // 1ª chamada

        System.setIn(new ByteArrayInputStream("4\n".getBytes()));
        // 2ª chamada (esperando funcionar com nova entrada)

        MenuBusca.menuBusca();

        String output = outContent.toString();
        assertTrue(output.contains("BUSCA"));
        assertTrue(output.contains("Saindo..."));
    }
}

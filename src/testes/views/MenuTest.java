package testes.views;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

import view.Menu;

public class MenuTest {

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

        Menu menu = new Menu();
        menu.menu();

        String output = outContent.toString();
        assertTrue(output.contains("Saindo..."));
    }

    @Test
    public void testInvalidaMenu() {
        // Simula entrada: 0 (inválida), depois 4 (sair)
        String input = "0\n4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Executa o menu
        Menu menu = new Menu();
        menu.menu();

        // Captura a saída como string
        String output = outContent.toString();

        // Verificações
        assertTrue(output.contains("Opção inválida. Tente novamente."));
    }
}
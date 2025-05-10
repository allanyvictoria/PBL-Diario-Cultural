package testes.views;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;
import java.time.LocalDate;

import view.CadastroView;

public class CadastroViewTest {

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
    public void testVerificaDataValida() {
        // Simula o usuário digitando a data corretamente
        String input = "2024-04-22\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        LocalDate resultado = CadastroView.verificaData("Digite a data");

        assertEquals(LocalDate.of(2024, 4, 22), resultado);
        String output = outContent.toString();
        assertTrue(output.contains("Digite a data (AAAA-MM-DD): "));
    }

    @Test
    public void testVerificaDataInvalidaDepoisValida() {
        // Simula entrada inválida primeiro, depois válida
        String input = "abcd\n2024-04-22\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        LocalDate resultado = CadastroView.verificaData("Digite a data");

        assertEquals(LocalDate.of(2024, 4, 22), resultado);
        String output = outContent.toString();
        assertTrue(output.contains("Formato inválido! Tente novamente."));
    }
}

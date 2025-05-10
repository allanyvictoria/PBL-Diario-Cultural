package testes.views;

import org.junit.*;
import view.VerificaInteiro;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class VerificaInteiroTest {
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
    public void testEntradaValida() {
        String input = "5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        int resultado = VerificaInteiro.verificaInteiro("Digite um número: ");
        assertEquals(5, resultado);
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Digite um número:"));
    }

    @Test
    public void testNumeroNegativo() {
        String input = "-3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        int resultado = VerificaInteiro.verificaInteiro("Digite um número: ");
        assertEquals(-1, resultado);
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Não é permitido número negativo."));
    }

    @Test
    public void testEntradaInvalidaENumeroValidoDepois() {
        System.setIn(new ByteArrayInputStream("abc\n".getBytes()));
        VerificaInteiro.verificaInteiro("Digite um número: "); // 1ª chamada

        System.setIn(new ByteArrayInputStream("10\n".getBytes()));
        // 2ª chamada (esperando funcionar com nova entrada)

        int resultado = VerificaInteiro.verificaInteiro("Digite um número: ");
        assertEquals(10, resultado);
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Digite um número válido."));
    }

    @Test
    public void testFimDeEntrada() {
        // Simula o encerramento da entrada (por exemplo, Ctrl+D)
        System.setIn(new ByteArrayInputStream(new byte[0]));

        int resultado = VerificaInteiro.verificaInteiro("Digite um número: ");
        assertEquals(-1, resultado);
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Entrada encerrada - Saindo automaticamente"));
    }
}

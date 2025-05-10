package testes.integracao;
import org.junit.*;
import view.Listar;

import static org.junit.Assert.*;

import java.io.*;

public class ListarIntegracaoTest {

    private final InputStream entradaOriginal = System.in;
    private final PrintStream saidaOriginal = System.out;

    private ByteArrayInputStream entradaSimulada;
    private ByteArrayOutputStream saidaCapturada;

    @Before
    public void configurarStreams() {
        saidaCapturada = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saidaCapturada));
    }

    private void fornecerEntrada(String dados) {
        entradaSimulada = new ByteArrayInputStream(dados.getBytes());
        System.setIn(entradaSimulada);
    }

    private String obterSaida() {
        return saidaCapturada.toString();
    }

    @After
    public void restaurarStreams() {
        System.setIn(entradaOriginal);
        System.setOut(saidaOriginal);
    }

    @Test
    public void testEntradaEncerradaAutomaticamente() {
        fornecerEntrada(""); // Simula EOF (sem entrada)

        Listar.listar();

        String saida = obterSaida();
        assertTrue(saida.contains("[Entrada encerrada - Saindo automaticamente]"));
    }
}

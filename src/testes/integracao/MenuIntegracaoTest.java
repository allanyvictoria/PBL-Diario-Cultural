package testes.integracao;

import org.junit.*;
import view.Menu;

import static org.junit.Assert.*;
import java.io.*;

public class MenuIntegracaoTest {

    private final InputStream entradaOriginal = System.in;
    private final PrintStream saidaOriginal = System.out;

    private ByteArrayInputStream entradaSimulada;
    private ByteArrayOutputStream saidaCapturada;

    // Antes de cada teste: redireciona a saída para capturar o que o programa imprime
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

    // Após cada teste: restaurar os streams originais
    @After
    public void restaurarStreams() {
        System.setIn(entradaOriginal);
        System.setOut(saidaOriginal);
    }

    @Test
    public void testMenuBuscaChamadoPeloMenuPrincipal() {
        // Simulando entrada do usuário:
        // 2 -> abrir menu de busca
        // 4 -> sair do menu de busca
        // 4 -> sair do menu principal
        fornecerEntrada("2\n4\n4\n");

        Menu menu = new Menu();
        menu.menu();

        String saida = obterSaida();

        // Verificações básicas
        assertTrue(saida.contains("=== Bem-vindo ao Diário Cultural ==="));
        assertTrue(saida.contains("=== BUSCA ==="));
    }
}

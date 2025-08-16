package com.example.diarioculturaljavafx.testes.controller;

import com.example.diarioculturaljavafx.controller.VerificaCadastroController;
import com.example.diarioculturaljavafx.service.Acervo;
import com.example.diarioculturaljavafx.model.Livro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CadastroLivroControllerTest {

    @BeforeEach
    public void limparLista() {
        Acervo.getLivroList().clear();
    }

    @Test
    public void testCadastroLivroValidoComLeitura() {
        VerificaCadastroController.cadastrarLivro(
                "1984",
                5,
                "Distopia",
                1949,
                "Crítico e atemporal",
                "George Orwell",
                "Companhia das Letras",
                "9788535914849",
                true,
                LocalDate.of(2023, 10, 10)
        );

        assertEquals(1, Acervo.getLivroList().size());
        Livro l = Acervo.getLivroList().getFirst();
        assertEquals("1984", l.getNome());
        assertEquals("George Orwell", l.getAutor());
        assertEquals(5, l.getAvaliacao());
        assertEquals(LocalDate.of(2023, 10, 10), l.getLeitura());
    }

    @Test
    public void testCadastroLivroNaoLido() {
        VerificaCadastroController.cadastrarLivro(
                "A Revolução dos Bichos",
                -1,
                "Fábula política",
                1945,
                "Não lido",
                "George Orwell",
                "Companhia das Letras",
                "9788535909555",
                false,
                null
        );

        assertEquals(1, Acervo.getLivroList().size());
        Livro l = Acervo.getLivroList().getFirst();
        assertEquals("A Revolução dos Bichos", l.getNome());
        assertNull(l.getLeitura());
        assertEquals(-1, l.getAvaliacao());
    }

    @Test
    public void testCadastroComISBNInvalido() {
        // Esperado: o método deve entrar em loop ou exibir erro se o ISBN for inválido
        // Como o método mostra um Alert e tenta pedir novo ISBN, não é possível testar diretamente aqui sem UI
        // Então esse teste é mais ilustrativo — o ideal seria refatorar a lógica de validação separadamente.
        assertFalse(chamaValidacaoInterna("123"));  // ISBN com menos de 10 dígitos
        assertTrue(chamaValidacaoInterna("1234567890"));  // ISBN com 10 dígitos
        assertTrue(chamaValidacaoInterna("9788535914849"));  // ISBN com 13 dígitos
    }

    // Simula chamada interna à validação
    private boolean chamaValidacaoInterna(String isbn) {
        try {
            var method = VerificaCadastroController.class.getDeclaredMethod("isbnValido", String.class);
            method.setAccessible(true);
            return (boolean) method.invoke(null, isbn);
        } catch (Exception e) {
            fail("Erro ao testar método privado: " + e.getMessage());
            return false;
        }
    }
}

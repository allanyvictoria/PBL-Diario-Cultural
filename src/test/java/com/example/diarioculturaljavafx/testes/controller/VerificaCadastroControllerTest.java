package com.example.diarioculturaljavafx.testes.controller;

import com.example.diarioculturaljavafx.controller.VerificaCadastroController;
import com.example.diarioculturaljavafx.model.Temporada;
import com.example.diarioculturaljavafx.service.Acervo;
import javafx.application.Platform;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class VerificaCadastroControllerTest {

    @BeforeClass
    public static void initJavaFX() {
        // Só inicia o JavaFX se ainda não estiver iniciado
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {
                // nada a fazer aqui, só inicializar
            });
        }
    }

    @Before
    public void setUp() {
        Acervo.getLivroList().clear();
        Acervo.getFilmeList().clear();
        Acervo.getSerieList().clear();
    }

    @Test
    public void testCadastroLivroValido() {
        VerificaCadastroController.cadastrarLivro(
                "Livro Válido", 4, "Aventura", 2000, "Bom",
                "Autor A", "Editora B", "1234567890", true, LocalDate.now());

        assertEquals(1, Acervo.getLivroList().size());
        assertEquals("Livro Válido", Acervo.getLivroList().get(0).getNome());
    }

    @Test
    public void testCadastroLivroSemLeituraNaoVerificaNota() {
        VerificaCadastroController.cadastrarLivro(
                "Livro Sem Leitura", 0, "Ficção", 2020, "Ok",
                "Autor Z", "Editora Z", "1234567890", false, null);

        assertEquals(1, Acervo.getLivroList().size());
        assertEquals("Livro Sem Leitura", Acervo.getLivroList().get(0).getNome());
    }

    @Test
    public void testCadastroFilmeSemDataAceitaNotaQualquer() {
        VerificaCadastroController.cadastrarFilme(
                "Filme Sem Data", 7, "Ação", 2015, "Legal",
                120, Arrays.asList("Ator 1"), "HBO", "Titulo Original",
                Arrays.asList("Roteiro"), Arrays.asList("Diretor"), null);

        assertEquals(1, Acervo.getFilmeList().size());
        assertEquals("Filme Sem Data", Acervo.getFilmeList().get(0).getNome());
    }

    @Test
    public void testCadastroSerieSimples() {
        Temporada t = new Temporada(1, 10, 5, "Boa");

        VerificaCadastroController.cadastrarSerie(
                "Série Nova", "Comédia", 5, 2021, "Divertida",
                Collections.singletonList(t), 2023, "Título Original", "Prime", Arrays.asList("Ator A"));

        assertEquals(1, Acervo.getSerieList().size());
        assertEquals("Série Nova", Acervo.getSerieList().get(0).getNome());
    }
}

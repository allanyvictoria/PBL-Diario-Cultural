package com.example.diarioculturaljavafx.testes.funcionalidade;

import com.example.diarioculturaljavafx.controller.VerificaCadastroController;
import com.example.diarioculturaljavafx.model.Filme;
import com.example.diarioculturaljavafx.service.Acervo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ListagemFilmesTest {

    @Before
    public void setUp() {
        Acervo.getFilmeList().clear();
    }

    @After
    public void tearDown() {
        Acervo.getFilmeList().clear();
    }

    @Test
    public void testListarFilmes() {
        VerificaCadastroController.cadastrarFilme(
                "Vingadores: Ultimato",
                5,
                "Ação",
                2019,
                "Final épico.",
                181,
                Arrays.asList("Robert Downey Jr.", "Chris Evans"),
                "Disney+",
                "Avengers: Endgame",
                Arrays.asList("Roteiro 1", "Roteiro 2"),
                Arrays.asList("Direção 1", "Direção 2"),
                LocalDate.of(2021, 1, 1)
        );

        VerificaCadastroController.cadastrarFilme(
                "Pantera Negra",
                4,
                "Ação",
                2018,
                "Herói africano.",
                134,
                Arrays.asList("Chadwick Boseman", "Michael B. Jordan"),
                "Disney+",
                "Black Panther",
                Arrays.asList("Roteiro 1", "Roteiro 2"),
                Arrays.asList("Direção 1", "Direção 2"),
                LocalDate.of(2020, 3, 15)
        );

        assertEquals(2, Acervo.getFilmeList().size());

        Filme primeiroFilme = Acervo.getFilmeList().get(0);
        assertEquals("Vingadores: Ultimato", primeiroFilme.getNome());

        Acervo.ordenarFilmesPorAvaliacao(true); // decrescente
        assertEquals("Vingadores: Ultimato", Acervo.getFilmeList().get(0).getNome());

        Acervo.ordenarFilmesPorAvaliacao(false); // crescente
        assertEquals("Pantera Negra", Acervo.getFilmeList().get(0).getNome());
    }
}

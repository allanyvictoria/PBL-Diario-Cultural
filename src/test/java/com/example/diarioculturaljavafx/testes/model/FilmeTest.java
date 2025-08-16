package com.example.diarioculturaljavafx.testes.model;

import com.example.diarioculturaljavafx.model.Filme;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FilmeTest {

    private Filme filme;

    @Before
    public void setUp() {
        List<String> elenco = new ArrayList<>(Arrays.asList("Ator A", "Ator B"));
        List<String> roteiro = new ArrayList<>(Arrays.asList("Roteirista 1"));
        List<String> direcao = new ArrayList<>(Arrays.asList("Diretor 1"));

        filme = new Filme(
                "Nome do Filme",
                8,
                "Ação",
                2021,
                "Filme cheio de ação e efeitos especiais.",
                120,
                elenco,
                "Amazon Prime Video",
                "Original Movie Title",
                roteiro,
                direcao,
                LocalDate.of(2023, 3, 15)
        );
    }

    @Test
    public void testGetters() {
        assertEquals("Nome do Filme", filme.getNome());
        assertEquals(8, filme.getAvaliacao());
        assertEquals("Ação", filme.getGenero());
        assertEquals(2021, filme.getAno());
        assertEquals("Filme cheio de ação e efeitos especiais.", filme.getReview());
        assertEquals(120, filme.getDuracao());
        assertEquals("Amazon Prime Video", filme.getOndeAssistir());
        assertEquals("Original Movie Title", filme.getTituloOriginal());
        assertEquals(LocalDate.of(2023, 3, 15), filme.getDataAssistiu());
        assertEquals(Arrays.asList("Ator A", "Ator B"), filme.getElenco());
        assertEquals(Arrays.asList("Roteirista 1"), filme.getRoteiro());
        assertEquals(Arrays.asList("Diretor 1"), filme.getDirecao());
    }

}

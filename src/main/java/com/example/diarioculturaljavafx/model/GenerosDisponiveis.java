package com.example.diarioculturaljavafx.model;

import java.util.Arrays;
import java.util.List;

/**
 * Classe utilitária para fornecer os gêneros disponíveis.
 */
public class GenerosDisponiveis {

    /**
     * Retorna a lista de gêneros disponíveis.
     *
     * @return Lista de gêneros como Strings.
     */
    public static List<String> listaGeneros() {
        return Arrays.asList(
                "Ação", "Aventura", "Comédia", "Drama",
                "Terror", "Romance", "Fantasia", "Ficção Científica",
                "Suspense", "Animação", "Outro"
        );
    }
}

package com.example.diarioculturaljavafx.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Classe que representa um filme, estendendo a classe Midia.
 */
public class Filme extends Midia {
    private final int duracao;                     // Duração do filme em minutos
    private final List<String> elenco;             // Lista de atores e atrizes
    private String ondeAssistir;                   // Plataforma onde o filme foi assistido
    private final String titulo;                   // Título original do filme
    private final List<String> roteiro;            // Lista de roteiristas
    private final List<String> direcao;            // Lista de diretores
    private final LocalDate dataAssistiu;          // Data em que o filme foi assistido

    /**
     * Construtor da classe Filme.
     *
     * @param nome Nome do filme.
     * @param avaliacao Avaliação do filme (de 0 a 5).
     * @param genero Gênero do filme.
     * @param ano Ano de lançamento.
     * @param review Comentário ou crítica.
     * @param duracao Duração do filme em minutos.
     * @param elenco Lista com os nomes do elenco.
     * @param ondeAssistir Plataforma onde o filme foi assistido.
     * @param titulo Título original do filme.
     * @param roteiro Lista de roteiristas.
     * @param direcao Lista de diretores.
     * @param dataAssistiu Data em que o filme foi assistido (pode ser null).
     */
    public Filme(String nome, int avaliacao, String genero, int ano, String review, int duracao,
                 List<String> elenco, String ondeAssistir, String titulo, List<String> roteiro,
                 List<String> direcao, LocalDate dataAssistiu) {
        super(nome, avaliacao, genero, ano, review);
        this.duracao = duracao;
        this.elenco = elenco;
        this.ondeAssistir = ondeAssistir;
        this.titulo = titulo;
        this.roteiro = roteiro;
        this.direcao = direcao;
        this.dataAssistiu = dataAssistiu;
    }

    /**
     * Retorna a data em que o filme foi assistido.
     *
     * @return Data de visualização do filme (pode ser null).
     */
    public LocalDate getDataAssistiu() {
        return dataAssistiu;
    }

    /**
     * Retorna a duração do filme.
     *
     * @return Duração em minutos.
     */
    public int getDuracao() {
        return duracao;
    }

    /**
     * Retorna a lista do elenco do filme.
     *
     * @return Lista de atores/atrizes.
     */
    public List<String> getElenco() {
        return elenco;
    }

    /**
     * Retorna a plataforma onde o filme foi assistido.
     *
     * @return Nome da plataforma.
     */
    public String getOndeAssistir() {
        return ondeAssistir;
    }

    /**
     * Retorna a lista de roteiristas do filme.
     *
     * @return Lista de roteiristas.
     */
    public List<String> getRoteiro() {
        return roteiro;
    }

    /**
     * Retorna a lista de diretores do filme.
     *
     * @return Lista de diretores.
     */
    public List<String> getDirecao() {
        return direcao;
    }

    /**
     * Retorna o título original do filme.
     *
     * @return Título original.
     */
    public String getTituloOriginal() {
        return titulo;
    }
}

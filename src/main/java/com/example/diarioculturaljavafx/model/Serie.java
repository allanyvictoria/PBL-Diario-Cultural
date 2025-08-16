package com.example.diarioculturaljavafx.model;

import java.util.List;

/**
 * Classe que representa uma série de mídia.
 * Contém informações sobre o elenco, temporadas, ano de fim, título original e onde foi assistida.
 */
public class Serie extends Midia {
    private List <Temporada> temps; // Lista de temporadas da série
    private final List <String> elenco; // Lista de atores/atrizes do elenco
    private int fim; // Ano de fim da série
    private final String tituloOriginal; // Título original da série
    private final String ondeAssistiu; // Onde a série foi assistida

    /**
     * Construtor da classe Serie.
     *
     * @param nome O nome da série.
     * @param genero O gênero da série.
     * @param avaliacao A avaliação da série (de 0 a 5).
     * @param ano O ano de lançamento da série.
     * @param review O review geral da série.
     * @param temps A lista de temporadas da série.
     * @param fim O ano de encerramento da série.
     * @param tituloOriginal O título original da série.
     * @param ondeAssistiu Onde a série foi assistida.
     * @param elenco A lista de atores e atrizes do elenco.
     */
    public Serie(String nome, String genero, int avaliacao, int ano, String review,
                 List<Temporada> temps, int fim, String tituloOriginal, String ondeAssistiu, List <String> elenco) {
        super(nome, avaliacao, genero, ano, review);
        this.elenco = elenco;
        this.temps = temps;
        this.fim = fim;
        this.tituloOriginal = tituloOriginal;
        this.ondeAssistiu = ondeAssistiu;
    }

    /**
     * Adiciona um ator ao elenco da série, se ainda não estiver presente.
     *
     * @param ator O nome do ator a ser adicionado ao elenco.
     */
    public void adicionarAtor(String ator) {
        if (!elenco.contains(ator)) {
            elenco.add(ator); // Só pode adicionar por aqui
        }
    }

    /**
     * Adiciona uma temporada à lista de temporadas da série.
     *
     * @param temporada A temporada a ser adicionada.
     */
    public void adicionarTemporada(Temporada temporada) {
        temps.add(temporada); // Só pode adicionar por aqui
    }

    /**
     * Retorna uma cópia imutável da lista de atores/atrizes do elenco da série.
     *
     * @return A lista imutável do elenco da série.
     */
    public List<String> getElenco() {
        return List.copyOf(elenco); // Retorna cópia imutável
    }

    /**
     * Retorna uma cópia imutável da lista de temporadas da série.
     *
     * @return A lista imutável das temporadas da série.
     */
    public List <Temporada> getTemps() {
        return List.copyOf(temps);
    }

    /**
     * Retorna o ano de encerramento da série.
     *
     * @return O ano de encerramento da série.
     */
    public int getFim() {
        return fim;
    }

    /**
     * Retorna o título original da série.
     *
     * @return O título original da série.
     */
    public String getTituloOriginal() {
        return tituloOriginal;
    }

    /**
     * Retorna o local onde a série foi assistida.
     *
     * @return O local onde a série foi assistida.
     */
    public String getOndeAssistiu() {
        return ondeAssistiu;
    }

    /**
     * Retorna uma cópia imutável da lista de temporadas da série.
     *
     * @return A lista imutável das temporadas da série.
     */
    public List<Temporada> getTemporadas() {
        return List.copyOf(temps);
    }
}

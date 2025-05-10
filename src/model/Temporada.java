package model;

/**
 * Classe que representa uma temporada de uma série.
 * Contém informações sobre o ano de lançamento, número de episódios,
 * avaliação da temporada e um review geral.
 */
public class Temporada {
    private final int ano; // Ano de lançamento da temporada
    private int quantidade; // Quantidade de episódios da temporada
    private int avaliacao; // Avaliação da temporada (de 0 a 5)
    private String review; // Review da temporada

    /**
     * Construtor da classe Temporada.
     *
     * @param ano O ano de lançamento da temporada.
     * @param quantidade A quantidade de episódios da temporada.
     * @param avaliacao A avaliação da temporada (de 0 a 5).
     * @param review O review da temporada.
     */
    public Temporada(int ano, int quantidade, int avaliacao, String review) {
        this.ano = ano;
        this.quantidade = quantidade;
        this.avaliacao = avaliacao;
        this.review = review;
    }

    /**
     * Retorna o review da temporada.
     *
     * @return O review da temporada.
     */
    public String getReview() {
        return review;
    }

    /**
     * Retorna o ano de lançamento da temporada.
     *
     * @return O ano da temporada.
     */
    public int getAno() {
        return ano;
    }

    /**
     * Retorna a quantidade de episódios da temporada.
     *
     * @return A quantidade de episódios.
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Retorna a avaliação da temporada.
     *
     * @return A avaliação (de 0 a 5) da temporada.
     */
    public int getAvaliacao() {
        return avaliacao;
    }
}

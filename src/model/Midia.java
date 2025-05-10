package model;

/**
 * Classe base que representa uma mídia genérica.
 * Pode ser estendida para representar livros, filmes, séries, etc.
 */
public class Midia {
    private String nome;       // Nome da mídia
    private int avaliacao;     // Avaliação (de 0 a 5)
    private String genero;     // Gênero da mídia
    private int ano;           // Ano de lançamento
    private String review;     // Review ou comentário geral

    /**
     * Construtor da classe Midia.
     *
     * @param nome Nome da mídia.
     * @param avaliacao Avaliação da mídia (0 a 5).
     * @param genero Gênero da mídia.
     * @param ano Ano de lançamento da mídia.
     * @param review Comentário ou review da mídia.
     */
    public Midia(String nome, int avaliacao, String genero, int ano, String review) {
        this.nome = nome;
        this.avaliacao = avaliacao;
        this.genero = genero;
        this.ano = ano;
        this.review = review;
    }

    /**
     * Retorna o nome da mídia.
     *
     * @return Nome da mídia.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a avaliação da mídia.
     *
     * @return Avaliação da mídia.
     */
    public int getAvaliacao() {
        return avaliacao;
    }

    /**
     * Retorna o gênero da mídia.
     *
     * @return Gênero da mídia.
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Retorna o ano de lançamento da mídia.
     *
     * @return Ano de lançamento da mídia.
     */
    public int getAno() {
        return ano;
    }

    /**
     * Retorna o review da mídia.
     *
     * @return Comentário ou review da mídia.
     */
    public String getReview() {
        return review;
    }
}

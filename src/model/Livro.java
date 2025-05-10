package model;

import java.time.LocalDate;

/**
 * Classe que representa um livro, estendendo a classe Midia.
 */
public class Livro extends Midia {
    private final String autor;      // Autor do livro
    private String editora;          // Editora do livro
    private String genero;           // Gênero literário
    private String isbn;             // Código ISBN do livro
    private boolean possui;          // Indica se o usuário possui o livro
    private LocalDate leitura;       // Data de leitura (se o livro foi lido)

    /**
     * Construtor da classe Livro.
     *
     * @param nome Nome do livro.
     * @param avaliacao Avaliação do livro (de 0 a 5).
     * @param genero Gênero literário.
     * @param ano Ano de lançamento do livro.
     * @param review Comentário ou review sobre o livro.
     * @param autor Autor do livro.
     * @param editora Editora responsável.
     * @param isbn Código ISBN.
     * @param possui Indica se o usuário possui o livro.
     * @param leitura Data de leitura (pode ser null se não lido).
     */
    public Livro(String nome, int avaliacao, String genero, int ano, String review, String autor,
                 String editora, String isbn, boolean possui, LocalDate leitura) {
        super(nome, avaliacao, genero, ano, review);
        this.genero = genero;
        this.autor = autor;
        this.editora = editora;
        this.isbn = isbn;
        this.possui = possui;
        this.leitura = leitura;
    }

    /**
     * Retorna o autor do livro.
     *
     * @return Autor do livro.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Retorna a editora do livro.
     *
     * @return Editora do livro.
     */
    public String getEditora() {
        return editora;
    }

    /**
     * Retorna o código ISBN do livro.
     *
     * @return ISBN do livro.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Verifica se o usuário possui o livro.
     *
     * @return true se o usuário possui, false caso contrário.
     */
    public boolean isPossui() {
        return possui;
    }

    /**
     * Retorna a data de leitura do livro.
     *
     * @return Data da leitura (pode ser null).
     */
    public LocalDate getLeitura() {
        return leitura;
    }
}

package model;

import controller.Acervo;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe utilitária responsável por cadastrar mídias (livros, filmes e séries) no acervo.
 */
public class Cadastro {

    /**
     * Cadastra um livro no acervo.
     *
     * @param nome Nome do livro.
     * @param avaliacao Avaliação do livro.
     * @param genero Gênero do livro.
     * @param ano Ano de publicação.
     * @param review Comentário ou crítica.
     * @param autor Autor do livro.
     * @param editora Editora responsável.
     * @param isbn Código ISBN do livro.
     * @param possui Indica se o livro está na posse do usuário.
     * @param leitura Data da leitura (pode ser null).
     */
    public static void cadastro(String nome, int avaliacao, String genero, int ano, String review,
                                String autor, String editora, String isbn, boolean possui, LocalDate leitura) {
        Acervo.adicionarLivro(new Livro(nome, avaliacao, genero, ano, review, autor, editora, isbn, possui, leitura));
    }

    /**
     * Cadastra um filme no acervo.
     *
     * @param nome Nome do filme.
     * @param avaliacao Avaliação do filme.
     * @param genero Gênero do filme.
     * @param ano Ano de lançamento.
     * @param review Comentário ou crítica.
     * @param duracao Duração em minutos.
     * @param elenco Lista de atores/atrizes.
     * @param ondeAssistir Plataforma onde o filme foi assistido.
     * @param titulo Título original do filme.
     * @param roteiro Lista de roteiristas.
     * @param direcao Lista de diretores.
     * @param dataAssistiu Data em que o filme foi assistido.
     */
    public static void cadastro(String nome, int avaliacao, String genero, int ano, String review, int duracao,
                                List<String> elenco, String ondeAssistir, String titulo, List<String> roteiro,
                                List<String> direcao, LocalDate dataAssistiu) {
        Acervo.adicionarFilme(new Filme(nome, avaliacao, genero, ano, review, duracao, elenco, ondeAssistir,
                titulo, roteiro, direcao, dataAssistiu));
    }

    /**
     * Cadastra uma série no acervo.
     *
     * @param nome Nome da série.
     * @param genero Gênero da série.
     * @param avaliacao Avaliação geral da série.
     * @param ano Ano de estreia.
     * @param review Comentário ou crítica.
     * @param temps Lista de temporadas.
     * @param fim Ano em que a série terminou ou 0 caso ainda esteja em exibição.
     * @param tituloOriginal Título original da série.
     * @param ondeAssistiu Plataforma onde a série foi assistida.
     * @param elenco Lista de atores/atrizes.
     */
    public static void cadastro(String nome, String genero, int avaliacao, int ano, String review,
                                List<Temporada> temps, int fim, String tituloOriginal, String ondeAssistiu,
                                List<String> elenco) {
        Acervo.adicionarSerie(new Serie(nome, genero, avaliacao, ano, review, temps, fim,
                tituloOriginal, ondeAssistiu, elenco));
    }
}

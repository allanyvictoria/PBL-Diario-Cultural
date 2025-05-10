package controller;

import model.*;
import view.CadastroView;
import view.VerificaInteiro;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe responsável por verificar e validar dados antes do cadastro de mídias no sistema.
 */
public class VerificaCadastro {

    /**
     * Verifica e cadastra um livro após validar os dados fornecidos.
     *
     * @param nome Nome do livro.
     * @param avaliacao Avaliação atribuída ao livro.
     * @param genero Gênero literário.
     * @param ano Ano de publicação.
     * @param review Comentário ou opinião.
     * @param autor Nome do autor.
     * @param editora Nome da editora.
     * @param isbn Código ISBN do livro (deve ter 10 ou 13 dígitos).
     * @param possui Indica se o livro está na posse do usuário.
     * @param leitura Data em que a leitura foi realizada.
     */
    public static void verificaCadastro(String nome, int avaliacao, String genero, int ano, String review,
                                        String autor, String editora, String isbn,
                                        boolean possui, LocalDate leitura) {
        if (leitura != null) {
            while (avaliacao < 1 || avaliacao > 5) {
                avaliacao = VerificaInteiro.verificaInteiro("Avalie entre 0 e 5: ");
            }
        }

        while (!isbnValido(isbn)) {
            System.out.println("ISBN deve ter 10 ou 13 dígitos");
            isbn = CadastroView.numisbn();
        }

        Cadastro.cadastro(nome, avaliacao, genero, ano, review,
                autor, editora, isbn, possui, leitura);
    }

    /**
     * Verifica e cadastra um filme após validar os dados fornecidos.
     *
     * @param nome Nome do filme.
     * @param avaliacao Avaliação atribuída ao filme.
     * @param genero Gênero do filme.
     * @param ano Ano de lançamento.
     * @param review Comentário ou opinião.
     * @param duracao Duração do filme em minutos.
     * @param elenco Lista com os nomes dos atores/atrizes.
     * @param ondeAssistir Plataforma onde foi assistido.
     * @param titulo Título original do filme.
     * @param roteiro Lista de roteiristas.
     * @param direcao Lista de diretores.
     * @param dataAssistiu Data em que o filme foi assistido.
     */
    public static void verificaCadastro(String nome, int avaliacao, String genero, int ano, String review, int duracao,
                                        List<String> elenco, String ondeAssistir, String titulo, List <String> roteiro,
                                        List <String> direcao, LocalDate dataAssistiu) {
        if (dataAssistiu != null) {
            while (avaliacao < 1 || avaliacao > 5) {
                avaliacao = VerificaInteiro.verificaInteiro("Avalie entre 0 e 5: ");
            }
        }
        Cadastro.cadastro(nome, avaliacao, genero, ano, review, duracao,
                elenco, ondeAssistir, titulo, roteiro, direcao, dataAssistiu);
    }

    /**
     * Verifica e cadastra uma série.
     *
     * @param nome Nome da série.
     * @param genero Gênero da série.
     * @param avaliacao Avaliação atribuída à série.
     * @param ano Ano de início da série.
     * @param review Comentário ou opinião.
     * @param temps Lista de temporadas.
     * @param fim Ano de encerramento (ou 0 se ainda estiver em exibição).
     * @param tituloOriginal Título original da série.
     * @param ondeAssistiu Plataforma onde foi assistida.
     * @param elenco Lista com os nomes dos atores/atrizes.
     */
    public static void verificaCadastro(String nome, String genero, int avaliacao, int ano, String review,
                                        List<Temporada> temps, int fim, String tituloOriginal,
                                        String ondeAssistiu, List <String> elenco) {

        Cadastro.cadastro(nome, genero, avaliacao, ano, review,
                temps, fim, tituloOriginal, ondeAssistiu, elenco);
    }

    /**
     * Verifica se o código ISBN informado é válido (10 ou 13 dígitos numéricos).
     *
     * @param isbn Código ISBN a ser validado.
     * @return true se for válido, false caso contrário.
     */
    private static boolean isbnValido(String isbn) {
        if (isbn == null) return false;
        String limpo = isbn.replaceAll("\\D", "");
        return limpo.matches("^\\d{10}$|^\\d{13}$");
    }
}

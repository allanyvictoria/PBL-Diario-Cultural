package com.example.diarioculturaljavafx.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import com.example.diarioculturaljavafx.model.Cadastro;
import com.example.diarioculturaljavafx.model.Temporada;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Classe responsável por validar e encaminhar cadastros de livros, filmes e séries.
 * Realiza verificações como formato do ISBN e faixa de avaliação válida.
 */
public class VerificaCadastroController {

    /**
     * Realiza o cadastro de um livro após verificar validade da avaliação e do ISBN.
     *
     * @param nome      título do livro
     * @param avaliacao nota de 1 a 5 (obrigatória se leitura for informada)
     * @param genero    gênero textual
     * @param ano       ano de publicação
     * @param review    texto de opinião
     * @param autor     nome do autor
     * @param editora   nome da editora
     * @param isbn      número ISBN (validado)
     * @param possui    se o usuário possui o livro
     * @param leitura   data em que o livro foi lido (pode ser nula)
     */
    public static void cadastrarLivro(String nome, int avaliacao, String genero, int ano,
                                      String review,
                                      String autor, String editora, String isbn,
                                      boolean possui, LocalDate leitura) {
        if (leitura != null && (avaliacao < 1 || avaliacao > 5)) {
            mostrarAlertaErro("Avaliação inválida", "A avaliação deve estar entre 1 e 5.");
            return;
        }

        while (!isbnValido(isbn)) {
            mostrarAlertaErro("ISBN inválido", "O ISBN deve conter 10 ou 13 dígitos.");
            isbn = solicitarISBN();
        }

        Cadastro.cadastro(nome, avaliacao, genero, ano, review,
                autor, editora, isbn, possui, leitura);
    }

    /**
     * Realiza o cadastro de um filme, verificando a nota se a data de exibição for informada.
     *
     * @param nome          título do filme
     * @param avaliacao     nota de 1 a 5 (obrigatória se dataAssistiu for informada)
     * @param genero        gênero do filme
     * @param ano           ano de lançamento
     * @param review        comentário sobre o filme
     * @param duracao       duração em minutos
     * @param elenco        lista de atores principais
     * @param ondeAssistir  plataforma ou local de exibição
     * @param titulo        título original (se houver)
     * @param roteiro       roteiristas
     * @param direcao       diretores
     * @param dataAssistiu  data em que foi assistido (pode ser nula)
     */
    public static void cadastrarFilme(String nome, int avaliacao, String genero, int ano,
                                      String review, int duracao,
                                      List<String> elenco, String ondeAssistir, String titulo,
                                      List<String> roteiro, List<String> direcao, LocalDate dataAssistiu) {
        if (dataAssistiu != null && (avaliacao < 1 || avaliacao > 5)) {
            mostrarAlertaErro("Avaliação inválida", "A avaliação deve estar entre 1 e 5.");
            return;
        }

        Cadastro.cadastro(nome, avaliacao, genero, ano, review,
                duracao, elenco, ondeAssistir, titulo, roteiro, direcao, dataAssistiu);
    }

    /**
     * Realiza o cadastro de uma série.
     *
     * @param nome            título da série
     * @param genero          gênero da série
     * @param avaliacao       nota de 1 a 5
     * @param ano             ano de lançamento
     * @param review          comentário pessoal
     * @param temps           lista de temporadas da série
     * @param fim             ano de encerramento (0 se não finalizada)
     * @param tituloOriginal  nome original (caso diferente)
     * @param ondeAssistiu    plataforma onde foi assistida
     * @param elenco          principais atores
     */
    public static void cadastrarSerie(String nome, String genero, int avaliacao,
                                      int ano, String review,
                                      List<Temporada> temps, int fim, String tituloOriginal,
                                      String ondeAssistiu, List<String> elenco) {
        Cadastro.cadastro(nome, genero, avaliacao, ano, review,
                temps, fim, tituloOriginal, ondeAssistiu, elenco);
    }

    /**
     * Verifica se o ISBN informado é válido (10 ou 13 dígitos).
     *
     * @param isbn texto do ISBN
     * @return true se for válido, false caso contrário
     */
    private static boolean isbnValido(String isbn) {
        if (isbn == null) return false;
        String limpo = isbn.replaceAll("\\D", "");
        return limpo.matches("^\\d{10}$|^\\d{13}$");
    }

    /**
     * Abre uma janela de diálogo solicitando um novo ISBN ao usuário.
     *
     * @return ISBN informado ou string vazia se cancelado
     */
    private static String solicitarISBN() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("ISBN inválido");
        dialog.setHeaderText("Informe um ISBN com 10 ou 13 dígitos:");
        Optional<String> result = dialog.showAndWait();
        return result.orElse("");
    }

    /**
     * Exibe uma janela de erro com título e mensagem personalizada.
     *
     * @param titulo   título do alerta
     * @param mensagem conteúdo da mensagem de erro
     */
    private static void mostrarAlertaErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}

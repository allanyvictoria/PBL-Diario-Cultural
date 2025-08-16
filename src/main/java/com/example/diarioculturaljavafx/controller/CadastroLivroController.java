package com.example.diarioculturaljavafx.controller;

import com.example.diarioculturaljavafx.model.PersistenciaAcervo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.diarioculturaljavafx.model.GenerosDisponiveis;

import java.time.LocalDate;

/**
 * Controlador responsável pelo cadastro de livros no sistema.
 * Permite preencher e validar dados como título, autor, gênero, avaliação e leitura.
 */
public class CadastroLivroController {

    // Campos principais do formulário
    @FXML private TextField campoNome;
    @FXML private TextField campoAutor;
    @FXML private TextField campoEditora;
    @FXML private TextField campoAno;
    @FXML private TextField campoISBN;
    @FXML private ComboBox<String> comboGenero;

    // Campos relacionados à posse e leitura
    @FXML private CheckBox checkPossui;
    @FXML private CheckBox checkLido;
    @FXML private TextField campoAvaliacao;
    @FXML private TextField campoReview;
    @FXML private DatePicker campoDataLeitura;

    /**
     * Inicializa os elementos da interface após o carregamento da tela.
     * Configura o ComboBox de gêneros e ativa/desativa campos de leitura conforme o checkbox.
     */
    @FXML
    public void initialize() {
        comboGenero.getItems().addAll(GenerosDisponiveis.listaGeneros());

        campoAvaliacao.setDisable(true);
        campoReview.setDisable(true);
        campoDataLeitura.setDisable(true);

        checkLido.setOnAction(e -> {
            boolean lido = checkLido.isSelected();
            campoAvaliacao.setDisable(!lido);
            campoReview.setDisable(!lido);
            campoDataLeitura.setDisable(!lido);
        });
    }

    /**
     * Manipula o botão de cadastro.
     * Realiza validações, coleta os dados preenchidos, cadastra o livro e salva no acervo.
     */
    @FXML
    public void handleCadastrar() {
        try {
            String nome     = campoNome.getText();
            String autor    = campoAutor.getText();
            String editora  = campoEditora.getText();
            String isbn     = campoISBN.getText();
            String genero   = comboGenero.getValue();

            if (nome.isBlank() || autor.isBlank() || genero == null || campoAno.getText().isBlank()) {
                mostrarAlertaErro("Campos obrigatórios", "Preencha todos os campos obrigatórios.");
                return;
            }

            int ano;
            try {
                ano = Integer.parseInt(campoAno.getText());
            } catch (NumberFormatException e) {
                mostrarAlertaErro("Ano inválido", "O ano deve ser um número inteiro.");
                return;
            }

            boolean possui = checkPossui.isSelected();

            if (checkLido.isSelected()) {
                if (campoAvaliacao.getText().isBlank()) {
                    mostrarAlertaErro("Campo obrigatório", "Informe a avaliação.");
                    return;
                }

                int avaliacao;
                try {
                    avaliacao = Integer.parseInt(campoAvaliacao.getText());
                    if (avaliacao < 1 || avaliacao > 5) {
                        mostrarAlertaErro("Avaliação inválida", "A avaliação deve estar entre 1 e 5.");
                        return;
                    }
                } catch (NumberFormatException e) {
                    mostrarAlertaErro("Avaliação inválida", "A avaliação deve ser um número inteiro.");
                    return;
                }

                String review = campoReview.getText();
                LocalDate dataLeitura = campoDataLeitura.getValue();
                if (dataLeitura == null) {
                    mostrarAlertaErro("Data inválida", "Informe a data da leitura.");
                    return;
                }

                VerificaCadastroController.cadastrarLivro(
                        nome, avaliacao, genero, ano, review,
                        autor, editora, isbn, possui, dataLeitura
                );
            } else {
                VerificaCadastroController.cadastrarLivro(
                        nome, -1, genero, ano, "Não lido",
                        autor, editora, isbn, possui, null
                );
            }

            PersistenciaAcervo.salvarTudo();
            mostrarAlertaInfo();
            limparCampos();

        } catch (Exception e) {
            mostrarAlertaErro("Erro inesperado", "Verifique os campos preenchidos.");
            e.printStackTrace(); // ajuda na depuração
        }
    }

    /**
     * Limpa todos os campos do formulário e desativa os campos relacionados à leitura.
     */
    private void limparCampos() {
        campoNome.clear();
        campoAutor.clear();
        campoEditora.clear();
        campoAno.clear();
        campoISBN.clear();
        comboGenero.setValue(null);
        checkPossui.setSelected(false);
        checkLido.setSelected(false);
        campoAvaliacao.clear();
        campoReview.clear();
        campoDataLeitura.setValue(null);

        campoAvaliacao.setDisable(true);
        campoReview.setDisable(true);
        campoDataLeitura.setDisable(true);
    }

    /**
     * Exibe um alerta de erro com título e mensagem personalizados.
     *
     * @param titulo   Título do alerta
     * @param mensagem Mensagem de erro
     */
    private void mostrarAlertaErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    /**
     * Exibe um alerta de sucesso após o cadastro ser realizado.
     */
    private void mostrarAlertaInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText("Cadastro realizado");
        alert.setContentText("Livro cadastrado com sucesso!");
        alert.showAndWait();
    }

    /**
     * Retorna à tela inicial da aplicação.
     *
     * @param event Evento disparado pelo botão "Voltar"
     */
    @FXML
    public void voltar(ActionEvent event) {
        TrocarTela trocar = new TrocarTela();
        trocar.trocarTela("tela-inicial.fxml", event);
    }
}

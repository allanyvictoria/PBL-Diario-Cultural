package com.example.diarioculturaljavafx.controller;

import com.example.diarioculturaljavafx.model.GenerosDisponiveis;
import com.example.diarioculturaljavafx.model.PersistenciaAcervo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador responsável pelo cadastro de filmes no sistema.
 * Permite ao usuário preencher informações detalhadas sobre um filme,
 * como título, gênero, elenco, avaliação e status de visualização.
 */
public class CadastroFilmeController {

    // Campos de entrada de dados da interface
    @FXML private TextField campoNome;
    @FXML private ComboBox<String> comboGenero;
    @FXML private TextField campoAno;
    @FXML private TextArea  campoDiretores;
    @FXML private TextArea  campoRoteiristas;
    @FXML private TextArea  campoAtores;
    @FXML private TextField campoOndeAssistir;
    @FXML private TextField campoTituloOriginal;
    @FXML private TextField campoDuracao;

    // Campos relacionados à visualização
    @FXML private CheckBox   checkVisto;
    @FXML private TextField  campoAvaliacao;
    @FXML private TextField  campoReview;
    @FXML private DatePicker campoDataView;

    /**
     * Inicializa a tela de cadastro configurando os campos e eventos.
     * Desabilita os campos de avaliação até que o checkbox "Visto" seja marcado.
     */
    @FXML
    public void initialize() {
        comboGenero.getItems().addAll(GenerosDisponiveis.listaGeneros());

        campoAvaliacao.setDisable(true);
        campoReview.setDisable(true);
        campoDataView.setDisable(true);

        checkVisto.setOnAction(e -> {
            boolean visto = checkVisto.isSelected();
            campoAvaliacao.setDisable(!visto);
            campoReview.setDisable(!visto);
            campoDataView.setDisable(!visto);
        });
    }

    /**
     * Manipula o evento de clique no botão "Cadastrar".
     * Valida os dados, organiza as listas e chama o método de cadastro.
     */
    @FXML
    public void handleCadastrar() {
        try {
            if (comboGenero.getValue() == null) {
                mostrarErro("Selecione um gênero antes de cadastrar a série.");
                return;
            }

            String nome           = campoNome.getText();
            String genero         = comboGenero.getValue();
            int    ano            = Integer.parseInt(campoAno.getText());
            String tituloOriginal = campoTituloOriginal.getText();
            String ondeAssistir   = campoOndeAssistir.getText();
            int    duracao        = Integer.parseInt(campoDuracao.getText());

            List<String> diretores   = separarLista(campoDiretores.getText());
            List<String> roteiristas = separarLista(campoRoteiristas.getText());
            List<String> atores      = separarLista(campoAtores.getText());

            if (checkVisto.isSelected()) {
                int       avaliacao = Integer.parseInt(campoAvaliacao.getText());
                String    review    = campoReview.getText();
                LocalDate dataView  = campoDataView.getValue();

                VerificaCadastroController.cadastrarFilme(
                        nome, avaliacao, genero, ano, review,
                        duracao, atores, ondeAssistir, tituloOriginal,
                        roteiristas, diretores, dataView
                );
            } else {
                VerificaCadastroController.cadastrarFilme(
                        nome, -1, genero, ano, "Não assistido",
                        duracao, atores, ondeAssistir, tituloOriginal,
                        roteiristas, diretores, null
                );
            }

            PersistenciaAcervo.salvarTudo();
            mostrarInfo();
            limparCampos();

        } catch (Exception e) {
            mostrarErro("Verifique os campos preenchidos.");
        }
    }

    /* ---------------------- Métodos utilitários privados ---------------------- */

    /**
     * Separa uma string com itens separados por vírgula em uma lista de strings.
     *
     * @param texto Texto inserido pelo usuário
     * @return Lista de strings, com espaços removidos e elementos não vazios
     */
    private List<String> separarLista(String texto) {
        return Arrays.stream(texto.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    /**
     * Limpa todos os campos do formulário de cadastro.
     */
    private void limparCampos() {
        campoNome.clear(); campoAno.clear(); campoDiretores.clear();
        campoRoteiristas.clear(); campoAtores.clear(); campoOndeAssistir.clear();
        campoTituloOriginal.clear(); campoDuracao.clear(); campoAvaliacao.clear();
        campoReview.clear(); campoDataView.setValue(null); checkVisto.setSelected(false);
    }

    /**
     * Exibe uma mensagem de erro em um alerta do tipo ERROR.
     *
     * @param msg Mensagem a ser exibida no alerta
     */
    private void mostrarErro(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        a.setHeaderText("Erro no cadastro"); a.showAndWait();
    }

    /**
     * Exibe uma mensagem informando o sucesso do cadastro.
     */
    private void mostrarInfo() {
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Filme cadastrado com sucesso!", ButtonType.OK);
        a.setHeaderText("Cadastro realizado"); a.showAndWait();
    }

    /**
     * Retorna à tela inicial da aplicação.
     *
     * @param event Evento de clique no botão "Voltar"
     */
    @FXML
    public void voltar(ActionEvent event) {
        TrocarTela trocar = new TrocarTela();
        trocar.trocarTela("tela-inicial.fxml", event);
    }
}

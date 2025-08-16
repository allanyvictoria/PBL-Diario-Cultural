package com.example.diarioculturaljavafx.controller;

import com.example.diarioculturaljavafx.model.GenerosDisponiveis;
import com.example.diarioculturaljavafx.model.PersistenciaAcervo;
import com.example.diarioculturaljavafx.model.Temporada;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador responsável pelo cadastro de séries no sistema.
 * Permite o preenchimento de dados básicos da série e o registro das temporadas individualmente.
 */
public class CadastroSerieController {

    // Campos principais do cadastro da série
    @FXML private TextField campoNome;
    @FXML private ComboBox<String> comboGenero;
    @FXML private TextField campoAno;
    @FXML private TextField campoTituloOriginal;
    @FXML private TextField campoOndeAssistir;
    @FXML private TextField campoAnoFinal;
    @FXML private TextArea  campoAtores;
    @FXML private TextField campoReviewGeral;

    // Tabela e colunas para exibir temporadas cadastradas
    @FXML private TableView<Temporada> tabelaTemporadas;
    @FXML private TableColumn<Temporada,Integer> colAno;
    @FXML private TableColumn<Temporada,Integer> colEpisodios;
    @FXML private TableColumn<Temporada,Integer> colAvaliacao;
    @FXML private TableColumn<Temporada,String>  colReview;

    // Campos para cadastrar uma nova temporada
    @FXML private TextField campoAnoTemp;
    @FXML private TextField campoEpisodiosTemp;
    @FXML private TextField campoAvaliacaoTemp;
    @FXML private TextField campoReviewTemp;

    private final ObservableList<Temporada> temporadas = FXCollections.observableArrayList();

    /**
     * Inicializa os elementos da tela, incluindo ComboBox de gêneros e
     * mapeamento das colunas da tabela de temporadas.
     */
    @FXML
    public void initialize() {
        comboGenero.getItems().addAll(GenerosDisponiveis.listaGeneros());

        colAno      .setCellValueFactory(new PropertyValueFactory<>("ano"));
        colEpisodios.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colAvaliacao.setCellValueFactory(new PropertyValueFactory<>("avaliacao"));
        colReview   .setCellValueFactory(new PropertyValueFactory<>("review"));

        tabelaTemporadas.setItems(temporadas);
    }

    /**
     * Adiciona uma temporada à tabela com os dados informados pelo usuário.
     * Realiza validações básicas antes da inserção.
     */
    @FXML
    public void handleAdicionarTemporada() {
        try {
            int    ano       = Integer.parseInt(campoAnoTemp.getText());
            int    episodios = Integer.parseInt(campoEpisodiosTemp.getText());
            int    avaliacao = Integer.parseInt(campoAvaliacaoTemp.getText());
            String review    = campoReviewTemp.getText();

            if (avaliacao < 1 || avaliacao > 5) {
                mostrarErro("Avaliação deve estar entre 1 e 5.");
                return;
            }

            temporadas.add(new Temporada(ano, episodios, avaliacao, review));

            campoAnoTemp.clear(); campoEpisodiosTemp.clear();
            campoAvaliacaoTemp.clear(); campoReviewTemp.clear();

        } catch (NumberFormatException e) {
            mostrarErro("Ano, episódios e avaliação devem ser numéricos.");
        }
    }

    /**
     * Realiza o cadastro da série com todas as informações preenchidas,
     * incluindo temporadas, e salva os dados no acervo.
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
            String ondeAssistiu   = campoOndeAssistir.getText();
            int    anoFinal       = Integer.parseInt(campoAnoFinal.getText());
            String reviewGeral    = campoReviewGeral.getText();
            List<String> atores   = separarLista(campoAtores.getText());

            List<Temporada> listaTemp = new ArrayList<>(temporadas);
            int avaliacao = listaTemp.isEmpty() ? 0 :
                    listaTemp.stream().mapToInt(Temporada::getAvaliacao).sum() / listaTemp.size();

            VerificaCadastroController.cadastrarSerie(
                    nome, genero, avaliacao, ano, reviewGeral,
                    listaTemp, anoFinal, tituloOriginal, ondeAssistiu, atores
            );

            PersistenciaAcervo.salvarTudo();
            mostrarInfo();
            limparCampos();

        } catch (Exception e) {
            mostrarErro("Verifique os campos preenchidos.");
        }
    }

    /**
     * Separa uma string de itens por vírgula e transforma em lista de strings.
     *
     * @param texto Texto com vírgulas
     * @return Lista de itens separados e sem espaços em branco
     */
    private List<String> separarLista(String texto) {
        return Arrays.stream(texto.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    /**
     * Limpa todos os campos da interface e a tabela de temporadas.
     */
    private void limparCampos() {
        campoNome.clear(); campoAno.clear(); campoTituloOriginal.clear();
        campoOndeAssistir.clear(); campoAnoFinal.clear(); campoAtores.clear();
        campoReviewGeral.clear(); temporadas.clear();
    }

    /**
     * Exibe uma caixa de alerta para erro, com uma mensagem personalizada.
     *
     * @param msg Mensagem a ser exibida
     */
    private void mostrarErro(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        a.setHeaderText("Erro no cadastro"); a.showAndWait();
    }

    /**
     * Exibe uma caixa de alerta informando o sucesso do cadastro.
     */
    private void mostrarInfo() {
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Série cadastrada com sucesso!", ButtonType.OK);
        a.setHeaderText("Cadastro realizado"); a.showAndWait();
    }

    /**
     * Retorna para a tela inicial da aplicação.
     *
     * @param event Evento acionado ao clicar em "Voltar"
     */
    @FXML
    public void voltar(ActionEvent event) {
        TrocarTela trocar = new TrocarTela();
        trocar.trocarTela("tela-inicial.fxml", event);
    }
}

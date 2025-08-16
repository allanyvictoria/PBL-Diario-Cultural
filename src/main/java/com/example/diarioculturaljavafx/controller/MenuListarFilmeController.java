package com.example.diarioculturaljavafx.controller;

import com.example.diarioculturaljavafx.model.Filme;
import com.example.diarioculturaljavafx.model.GenerosDisponiveis;
import com.example.diarioculturaljavafx.service.Acervo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller responsável por gerenciar as ações do menu de listagem de filmes
 * na aplicação Diário Cultural.
 *
 * <p>Cada método anotado com {@link FXML} é acionado por algum componente
 * definido no FXML de origem e filtra ou ordena a coleção mantida em
 * {@link Acervo}. Após o processamento, a listagem é exibida pela cena
 * {@code listagem.fxml} através do {@link #mostrarResultados(List, ActionEvent)}.</p>
 *
 * @author SeuNome
 * @since 1.0
 */
public class MenuListarFilmeController {

    /**
     * Carrega a tela de listagem e exibe os resultados fornecidos.
     *
     * @param resultados lista de {@link Filme} a ser exibida
     * @param event      evento que disparou a ação, utilizado para obter a
     *                   {@link Stage} atual e trocar a cena
     */
    private void mostrarResultados(List<Filme> resultados, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/diarioculturaljavafx/fxml/listagem.fxml"));

            // Passa os dados e título para ListagemController
            ListagemController.setDados(resultados);

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ordena os filmes pela melhor avaliação (decrescente) e exibe o resultado.
     *
     * @param event evento disparado pelo botão "Melhores"
     */
    @FXML
    private void melhores(ActionEvent event) {
        Acervo.ordenarFilmesPorAvaliacao(true);
        List<Filme> resultados = Acervo.getFilmeList();
        mostrarResultados(resultados, event);
    }

    /**
     * Ordena os filmes pela pior avaliação (crescente) e exibe o resultado.
     *
     * @param event evento disparado pelo botão "Piores"
     */
    @FXML
    private void piores(ActionEvent event) {
        Acervo.ordenarFilmesPorAvaliacao(false);
        List<Filme> resultados = Acervo.getFilmeList();
        mostrarResultados(resultados, event);
    }

    /**
     * Exibe um diálogo para selecionar gênero e filtra os filmes de acordo.
     *
     * @param event evento disparado pelo botão "Filtrar por Gênero"
     */
    @FXML
    public void filtroGenero(ActionEvent event) {
        List<String> opcoes = GenerosDisponiveis.listaGeneros();
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Drama", opcoes);
        dialog.setTitle("Buscar por Gênero");
        dialog.setHeaderText("Escolha um gênero:");

        dialog.showAndWait().ifPresent(genero -> {
            List<Filme> resultados = Acervo.getFilmeList().stream()
                    .filter(l -> l.getGenero() != null && l.getGenero().equalsIgnoreCase(genero))
                    .collect(Collectors.toList());
            mostrarResultados(resultados, event);
        });
    }

    /**
     * Exibe um diálogo para inserir o ano de lançamento e filtra os filmes daquele ano.
     *
     * @param event evento disparado pelo botão "Filtrar por Ano"
     */
    @FXML
    public void filtroAno(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar por Ano");
        dialog.setHeaderText("Digite o ano de lançamento:");
        dialog.setContentText("Ano:");

        dialog.showAndWait().ifPresent(input -> {
            try {
                int ano = Integer.parseInt(input.trim());
                List<Filme> resultados = Acervo.getFilmeList().stream()
                        .filter(l -> l.getAno() == ano)
                        .collect(Collectors.toList());
                mostrarResultados(resultados, event);
            } catch (NumberFormatException e) {
                System.out.println("Ano inválido.");
            }
        });
    }

    /**
     * Exibe todos os filmes sem aplicar filtros ou ordenação.
     *
     * @param event evento disparado pelo botão "Todos"
     */
    @FXML
    private void todos(ActionEvent event) {
        List<Filme> resultados = Acervo.getFilmeList();
        mostrarResultados(resultados, event);
    }

    /**
     * Retorna à tela inicial da aplicação.
     *
     * @param event evento disparado pelo botão "Voltar"
     */
    @FXML
    private void voltar(ActionEvent event) {
        TrocarTela trocar = new TrocarTela();
        trocar.trocarTela("tela-inicial.fxml", event);
    }
}

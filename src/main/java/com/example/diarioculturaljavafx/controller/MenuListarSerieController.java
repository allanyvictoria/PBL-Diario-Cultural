package com.example.diarioculturaljavafx.controller;

import com.example.diarioculturaljavafx.model.GenerosDisponiveis;
import com.example.diarioculturaljavafx.model.Serie;
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
 * Controller responsável por lidar com a tela de listagem de séries na aplicação.
 *
 * Permite ordenar, filtrar e exibir todas as séries cadastradas no acervo.
 */
public class MenuListarSerieController {

    /**
     * Troca para a tela de listagem e exibe as séries fornecidas.
     *
     * @param resultados lista de séries a serem exibidas
     * @param event evento acionado, usado para recuperar a janela atual
     */
    private void mostrarResultados(List<Serie> resultados, ActionEvent event) {
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
     * Ordena as séries da melhor para a pior avaliação e exibe.
     *
     * @param event evento disparado pelo botão "Melhores"
     */
    @FXML
    private void melhores(ActionEvent event) {
        Acervo.ordenarSeriesPorAvaliacao(true);
        List<Serie> resultados = Acervo.getSerieList();
        mostrarResultados(resultados, event);
    }

    /**
     * Ordena as séries da pior para a melhor avaliação e exibe.
     *
     * @param event evento disparado pelo botão "Piores"
     */
    @FXML
    private void piores(ActionEvent event) {
        Acervo.ordenarSeriesPorAvaliacao(false);
        List<Serie> resultados = Acervo.getSerieList();
        mostrarResultados(resultados, event);
    }

    /**
     * Filtra as séries com base no gênero escolhido em um diálogo.
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
            List<Serie> resultados = Acervo.getSerieList().stream()
                    .filter(l -> l.getGenero() != null && l.getGenero().equalsIgnoreCase(genero))
                    .collect(Collectors.toList());
            mostrarResultados(resultados, event);
        });
    }

    /**
     * Filtra as séries com base no ano de lançamento informado.
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
                List<Serie> resultados = Acervo.getSerieList().stream()
                        .filter(l -> l.getAno() == ano)
                        .collect(Collectors.toList());
                mostrarResultados(resultados, event);
            } catch (NumberFormatException e) {
                System.out.println("Ano inválido.");
            }
        });
    }

    /**
     * Exibe todas as séries cadastradas.
     *
     * @param event evento disparado pelo botão "Todos"
     */
    @FXML
    private void todos(ActionEvent event) {
        List<Serie> resultados = Acervo.getSerieList();
        mostrarResultados(resultados, event);
    }

    /**
     * Retorna para a tela inicial da aplicação.
     *
     * @param event evento disparado pelo botão "Voltar"
     */
    @FXML
    private void voltar(ActionEvent event) {
        TrocarTela trocar = new TrocarTela();
        trocar.trocarTela("tela-inicial.fxml", event);
    }
}

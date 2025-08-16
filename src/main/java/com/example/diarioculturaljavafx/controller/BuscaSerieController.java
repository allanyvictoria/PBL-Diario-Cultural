package com.example.diarioculturaljavafx.controller;

import com.example.diarioculturaljavafx.model.Filme;
import com.example.diarioculturaljavafx.model.Serie;
import com.example.diarioculturaljavafx.service.Acervo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Controlador responsável pelas funcionalidades de busca de séries,
 * permitindo pesquisar por título, gênero, ano ou ator.
 */
public class BuscaSerieController extends TrocarTela implements Initializable {

    /**
     * Inicializa o controlador após o carregamento do FXML.
     *
     * @param location  localização usada para resolver caminhos relativos ao objeto raiz
     * @param resources recursos utilizados para localizar valores específicos de idioma
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Nenhuma ação necessária na inicialização
    }

    /**
     * Exibe os resultados da busca na tela de listagem.
     *
     * @param resultados Lista de séries resultantes da busca
     * @param event      Evento que acionou a troca de tela
     */
    private void mostrarResultados(List<Serie> resultados, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/diarioculturaljavafx/fxml/listagem.fxml"));

            // Passa os dados antes de carregar a tela
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
     * Abre um diálogo para o usuário digitar o título da série e realiza a busca correspondente.
     *
     * @param event Evento do botão de busca por título
     */
    @FXML
    public void buscarTitulo(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar por Título");
        dialog.setHeaderText("Digite o título do livro:");
        dialog.setContentText("Título:");

        dialog.showAndWait().ifPresent(titulo -> {
            List<Serie> resultados = Acervo.getSerieList().stream()
                    .filter(l -> l.getNome() != null && l.getNome().toLowerCase().contains(titulo.toLowerCase()))
                    .collect(Collectors.toList());
            mostrarResultados(resultados, event);
        });
    }

    /**
     * Exibe um diálogo com opções de gênero e realiza a busca pela série correspondente.
     *
     * @param event Evento do botão de busca por gênero
     */
    @FXML
    public void buscarGenero(ActionEvent event) {
        List<String> opcoes = List.of("Drama", "Romance", "Terror", "Fantasia", "Comédia", "Ação", "Ficção", "Outros");
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
     * Abre um diálogo para o usuário digitar o ano de lançamento e realiza a busca de séries.
     *
     * @param event Evento do botão de busca por ano
     */
    @FXML
    public void buscarAno(ActionEvent event) {
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
     * Abre um diálogo para o usuário digitar o nome de um ator e realiza a busca de séries que contenham esse ator.
     *
     * @param event Evento do botão de busca por ator
     */
    @FXML
    public void buscarAtor(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar por Título");
        dialog.setHeaderText("Digite o título do livro:");
        dialog.setContentText("Título:");

        dialog.showAndWait().ifPresent(atorBuscado -> {
            List<Serie> resultados = Acervo.getSerieList().stream()
                    .filter(f -> f.getElenco() != null &&
                            f.getElenco().stream()
                                    .anyMatch(ator ->
                                            ator != null &&
                                                    ator.toLowerCase().contains(atorBuscado.toLowerCase())
                                    ))
                    .collect(Collectors.toList());
            mostrarResultados(resultados, event);
        });
    }

    /**
     * Retorna à tela anterior do menu de busca.
     *
     * @param event Evento do botão "Voltar"
     */
    @FXML
    public void voltar(ActionEvent event) {
        trocarTela("menu-busca.fxml", event);
    }
}

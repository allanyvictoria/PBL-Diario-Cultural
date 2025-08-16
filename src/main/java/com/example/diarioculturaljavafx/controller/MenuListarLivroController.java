package com.example.diarioculturaljavafx.controller;

import com.example.diarioculturaljavafx.model.GenerosDisponiveis;
import com.example.diarioculturaljavafx.model.Livro;
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
 * Controller responsável por manipular o menu de listagem de livros
 * na aplicação Diário Cultural.
 * <p>
 * Os métodos anotados com @FXML respondem aos botões da interface.
 * Eles filtram ou ordenam os livros do Acervo e mostram os resultados na tela de listagem.
 */
public class MenuListarLivroController {

    /**
     * Carrega a tela de listagem com os livros passados.
     *
     * @param resultados lista de livros a ser exibida
     * @param event evento disparado para identificar a janela atual
     */
    private void mostrarResultados(List<Livro> resultados, ActionEvent event) {
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
     * Ordena os livros da melhor para a pior avaliação.
     */
    @FXML
    private void melhores(ActionEvent event) {
        Acervo.ordenarLivrosPorAvaliacao(true);
        List<Livro> resultados = Acervo.getLivroList();
        mostrarResultados(resultados, event);
    }

    /**
     * Ordena os livros da pior para a melhor avaliação.
     */
    @FXML
    private void piores(ActionEvent event) {
        Acervo.ordenarLivrosPorAvaliacao(false);
        List<Livro> resultados = Acervo.getLivroList();
        mostrarResultados(resultados, event);
    }

    /**
     * Filtra os livros pelo gênero escolhido em um diálogo.
     */
    @FXML
    public void filtroGenero(ActionEvent event) {
        List<String> opcoes = GenerosDisponiveis.listaGeneros();
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Drama", opcoes);
        dialog.setTitle("Buscar por Gênero");
        dialog.setHeaderText("Escolha um gênero:");

        dialog.showAndWait().ifPresent(genero -> {
            List<Livro> resultados = Acervo.getLivroList().stream()
                    .filter(l -> l.getGenero() != null && l.getGenero().equalsIgnoreCase(genero))
                    .collect(Collectors.toList());
            mostrarResultados(resultados, event);
        });
    }

    /**
     * Filtra os livros pelo ano informado em um diálogo.
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
                List<Livro> resultados = Acervo.getLivroList().stream()
                        .filter(l -> l.getAno() == ano)
                        .collect(Collectors.toList());
                mostrarResultados(resultados, event);
            } catch (NumberFormatException e) {
                System.out.println("Ano inválido.");
            }
        });
    }

    /**
     * Mostra todos os livros cadastrados no acervo.
     */
    @FXML
    private void todos(ActionEvent event) {
        List<Livro> resultados = Acervo.getLivroList();
        mostrarResultados(resultados, event);
    }

    /**
     * Volta para a tela inicial da aplicação.
     */
    @FXML
    private void voltar(ActionEvent event) {
        TrocarTela trocar = new TrocarTela();
        trocar.trocarTela("tela-inicial.fxml", event);
    }
}

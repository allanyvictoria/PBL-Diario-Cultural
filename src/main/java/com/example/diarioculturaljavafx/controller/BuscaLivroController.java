package com.example.diarioculturaljavafx.controller;

import com.example.diarioculturaljavafx.service.Acervo;
import com.example.diarioculturaljavafx.model.Livro;
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
 * Controlador responsável pelas funcionalidades de busca de livros
 * com base em critérios como título, autor, gênero, ano de lançamento e ISBN.
 */
public class BuscaLivroController extends TrocarTela implements Initializable {

    /**
     * Método chamado após o carregamento do FXML.
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
     * @param resultados Lista de livros resultantes da busca
     * @param event      Evento que acionou a troca de tela
     */
    private void mostrarResultados(List<Livro> resultados, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/diarioculturaljavafx/fxml/listagem.fxml"));

            // Passa os dados antes de carregar a tela
            ListagemController.setDados(resultados);

            Parent root = loader.load();  // só carrega depois de setar dados

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Abre um diálogo para o usuário digitar o título e realiza a busca de livros com base nele.
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
            List<Livro> resultados = Acervo.getLivroList().stream()
                    .filter(l -> l.getNome() != null && l.getNome().toLowerCase().contains(titulo.toLowerCase()))
                    .collect(Collectors.toList());
            mostrarResultados(resultados, event);
        });
    }

    /**
     * Abre um diálogo para o usuário digitar o nome do autor e realiza a busca.
     *
     * @param event Evento do botão de busca por autor
     */
    @FXML
    public void buscarAutor(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar por Autor");
        dialog.setHeaderText("Digite o nome do autor:");
        dialog.setContentText("Autor:");

        dialog.showAndWait().ifPresent(autor -> {
            List<Livro> resultados = Acervo.getLivroList().stream()
                    .filter(l -> l.getAutor() != null && l.getAutor().toLowerCase().contains(autor.toLowerCase()))
                    .collect(Collectors.toList());
            mostrarResultados(resultados, event);
        });
    }

    /**
     * Exibe um menu de opções para o usuário escolher o gênero e realiza a busca.
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
            List<Livro> resultados = Acervo.getLivroList().stream()
                    .filter(l -> l.getGenero() != null && l.getGenero().equalsIgnoreCase(genero))
                    .collect(Collectors.toList());
            mostrarResultados(resultados, event);
        });
    }

    /**
     * Abre um diálogo para o usuário digitar o ano e realiza a busca dos livros lançados naquele ano.
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
     * Abre um diálogo para o usuário digitar o ISBN e realiza a busca de livros com base nele.
     *
     * @param event Evento do botão de busca por ISBN
     */
    @FXML
    public void buscarISBN(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar por ISBN");
        dialog.setHeaderText("Digite o ISBN do livro:");
        dialog.setContentText("ISBN:");

        dialog.showAndWait().ifPresent(isbn -> {
            String isbnLimpo = isbn.replaceAll("\\D", "");
            List<Livro> resultados = Acervo.getLivroList().stream()
                    .filter(l -> l.getIsbn() != null &&
                            l.getIsbn().replaceAll("\\D", "").contains(isbnLimpo))
                    .collect(Collectors.toList());
            mostrarResultados(resultados, event);
        });
    }

    /**
     * Volta para o menu de busca principal.
     *
     * @param event Evento do botão "Voltar"
     */
    @FXML
    public void voltar(ActionEvent event) {
        trocarTela("menu-busca.fxml", event);
    }
}

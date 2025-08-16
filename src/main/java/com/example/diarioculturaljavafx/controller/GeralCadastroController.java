package com.example.diarioculturaljavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controlador responsável por gerenciar a navegação a partir do menu de cadastro geral.
 * Permite o redirecionamento para as telas de cadastro de livros, filmes e séries.
 */
public class GeralCadastroController {

    TrocarTela trocar = new TrocarTela();

    /**
     * Navega para a tela de cadastro de livros.
     *
     * @param event Evento de clique no botão correspondente
     */
    @FXML
    public void irParaLivros(ActionEvent event) {
        trocar.trocarTela("cadastro-livro.fxml", event);
    }

    /**
     * Navega para a tela de cadastro de filmes.
     *
     * @param event Evento de clique no botão correspondente
     */
    @FXML
    public void irParaFilmes(ActionEvent event) {
        trocar.trocarTela("cadastro-filme.fxml", event);
    }

    /**
     * Navega para a tela de cadastro de séries.
     *
     * @param event Evento de clique no botão correspondente
     */
    @FXML
    public void irParaSeries(ActionEvent event) {
        trocar.trocarTela("cadastro-serie.fxml", event);
    }

    /**
     * Retorna para a tela inicial da aplicação.
     *
     * @param event Evento de clique no botão "Voltar"
     */
    @FXML
    public void voltar(ActionEvent event) {
        TrocarTela trocar = new TrocarTela();
        trocar.trocarTela("tela-inicial.fxml", event);
    }
}

package com.example.diarioculturaljavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controlador responsável por gerenciar a navegação a partir do menu de listas gerais.
 * Permite o redirecionamento para as telas de listagem de livros, filmes e séries.
 */
public class GeralListasController {

    TrocarTela trocar = new TrocarTela();

    /**
     * Navega para o menu de listagem de livros.
     *
     * @param event Evento acionado ao clicar no botão de livros
     */
    @FXML
    public void irParaLivros(ActionEvent event) {
        trocar.trocarTela("menu-listar-livros.fxml", event);
    }

    /**
     * Navega para o menu de listagem de filmes.
     *
     * @param event Evento acionado ao clicar no botão de filmes
     */
    @FXML
    public void irParaFilmes(ActionEvent event) {
        trocar.trocarTela("menu-listar-filmes.fxml", event);
    }

    /**
     * Navega para o menu de listagem de séries.
     *
     * @param event Evento acionado ao clicar no botão de séries
     */
    @FXML
    public void irParaSeries(ActionEvent event) {
        trocar.trocarTela("menu-listar-series.fxml", event);
    }
}

package com.example.diarioculturaljavafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controlador responsável pelo menu de busca do Diário Cultural.
 * Permite navegar para as telas de busca por livros, filmes e séries,
 * ou retornar à tela inicial.
 */
public class MenuBuscaController {

    /** Botão para acessar a tela de busca de livros */
    @FXML
    private Button btnLivro;

    /** Botão para acessar a tela de busca de filmes */
    @FXML
    private Button btnFilme;

    /** Botão para acessar a tela de busca de séries */
    @FXML
    private Button btnSerie;

    /** Botão para retornar à tela inicial */
    @FXML
    private Button btnSair;

    /** Instância auxiliar para troca de telas */
    TrocarTela troca = new TrocarTela();

    /**
     * Inicializa os eventos dos botões após o carregamento do FXML.
     * Cada botão redireciona o usuário para uma tela específica.
     */
    @FXML
    private void initialize() {
        btnLivro.setOnAction(e -> troca.trocarTela("busca-livro.fxml", e));
        btnFilme.setOnAction(e -> troca.trocarTela("busca-filme.fxml", e));
        btnSerie.setOnAction(e -> troca.trocarTela("busca-serie.fxml", e));
        btnSair.setOnAction(e -> troca.trocarTela("tela-inicial.fxml", e));
    }
}

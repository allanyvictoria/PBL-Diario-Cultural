package com.example.diarioculturaljavafx.controller;

import com.example.diarioculturaljavafx.model.Livro;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;

import java.util.List;

public class ResultadoListagemController {

    @FXML
    private TextArea textAreaSaida;

    @FXML
    private Label labelTitulo;

    private static List<Livro> dados;
    private static String titulo;

    public static void setDados(List<Livro> lista, String tituloRecebido) {
        dados = lista;
        titulo = tituloRecebido;
    }

    @FXML
    public void initialize() {
        labelTitulo.setText(titulo);
        StringBuilder sb = new StringBuilder();
        if (dados.isEmpty()) {
            sb.append("Nenhum item encontrado.");
        } else {
            for (Livro livro : dados) {
                sb.append("Livro: ").append(livro.getNome()).append("\n");
                sb.append("Autor: ").append(livro.getAutor()).append("\n");
                sb.append("Gênero: ").append(livro.getGenero()).append("\n");
                sb.append("Ano: ").append(livro.getAno()).append("\n");
                sb.append("Avaliação: ").append(livro.getAvaliacao()).append("\n");
                sb.append("-".repeat(50)).append("\n");
            }
        }
        textAreaSaida.setText(sb.toString());
    }

    @FXML
    private void voltar(ActionEvent event) {
        new TrocarTela().trocarTela("menu-listar-livros.fxml", event);
    }
}

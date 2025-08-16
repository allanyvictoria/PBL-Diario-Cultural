package com.example.diarioculturaljavafx.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

/**
 * Classe utilitária responsável por trocar a cena (tela) exibida em um {@link Stage}
 * da aplicação JavaFX.
 *
 * <p>A troca de tela preserva o tamanho padrão definido (900 × 600) e,
 * em caso de falha ao carregar o arquivo FXML, exibe um diálogo de erro.</p>
 */
public class TrocarTela {

    /**
     * Troca a cena atual do {@link Stage} que disparou o {@link ActionEvent}
     * pelo arquivo FXML informado.
     *
     * @param caminhoFXML nome do arquivo FXML (dentro de
     *        <code>resources/com/example/diarioculturaljavafx/fxml/</code>)
     * @param event       evento usado para recuperar o {@link Stage} ativo
     */
    public void trocarTela(String caminhoFXML, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/example/diarioculturaljavafx/fxml/" + caminhoFXML));
            Parent novaTela = loader.load();

            // Recupera o stage atual
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            double largura = stage.getWidth();
            double altura = stage.getHeight();

            // Cria nova cena com o mesmo tamanho
            Scene novaCena = new Scene(novaTela, largura, altura);

            // Define a nova cena
            stage.setScene(novaCena);
            stage.setWidth(largura);
            stage.setHeight(altura);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace(); // Mostra o erro completo no console
            mostrarErro("Erro ao carregar a tela: " + e.getMessage());
        }
    }

    /**
     * Exibe um diálogo de erro com a mensagem fornecida.
     *
     * @param mensagem texto detalhando o problema ocorrido
     */
    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Falha ao carregar a tela");
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}

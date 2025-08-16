package com.example.diarioculturaljavafx.controller;

import com.example.diarioculturaljavafx.model.PersistenciaAcervo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller da tela inicial da aplicação Diário Cultural.
 *
 * <p>Essa classe herda {@link TrocarTela} para utilizar o método {@code trocarTela}
 * e implementa {@link Initializable} para executar ações de inicialização.</p>
 *
 * <ul>
 *   <li>No método {@link #initialize(URL, ResourceBundle)} carrega todos os dados
 *       persistidos do acervo quando a aplicação é iniciada.</li>
 *   <li>Os métodos anotados com {@link FXML} são handlers para os botões de
 *       navegação: cadastro, listagem e buscas.</li>
 * </ul>
 */
public class TelaInicialController extends TrocarTela implements Initializable {

    /**
     * Carrega todos os dados salvos do acervo logo após a interface ser montada.
     *
     * @param location  URL do recurso raiz (não utilizado).
     * @param resources ResourceBundle para localização (não utilizado).
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PersistenciaAcervo.carregarTudo();
    }

    /**
     * Abre a tela de cadastro de mídias.
     *
     * @param event evento disparado pelo botão “Cadastro”.
     */
    @FXML
    public void cadastro(ActionEvent event) {
        trocarTela("geral-cadastro.fxml", event);
    }

    /**
     * Abre a tela com as listas de mídias cadastradas.
     *
     * @param event evento disparado pelo botão “Listagem”.
     */
    @FXML
    public void listagem(ActionEvent event) {
        trocarTela("geral-listas.fxml", event);
    }

    /**
     * Abre o menu de buscas personalizadas.
     *
     * @param event evento disparado pelo botão “Buscas”.
     */
    @FXML
    public void buscas(ActionEvent event) {
        trocarTela("menu-busca.fxml", event);
    }
}

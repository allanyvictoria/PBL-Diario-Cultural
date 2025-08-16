package com.example.diarioculturaljavafx.testes.controller;


import com.example.diarioculturaljavafx.controller.TrocarTela;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class TrocarTelaTest {

    @Test
    void testClasseExisteEMetodoPresente() {
        TrocarTela trocarTela = new TrocarTela();
        assertNotNull(trocarTela);

        // Verifica se o método "trocarTela" existe com os parâmetros corretos
        try {
            Method metodo = TrocarTela.class.getMethod("trocarTela", String.class, javafx.event.ActionEvent.class);
            assertNotNull(metodo);
        } catch (NoSuchMethodException e) {
            fail("Método 'trocarTela(String, ActionEvent)' não encontrado.");
        }
    }

}

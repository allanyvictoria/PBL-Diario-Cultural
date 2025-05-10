package testes.model;

import model.Temporada;
import org.junit.*;
import static org.junit.Assert.*;

public class TemporadaTest {

    @Test
    public void testTemporadaConstructorAndGetters() {
        // Arrange
        int ano = 2022;
        int quantidade = 12;
        int avaliacao = 9;
        String review = "Excelente temporada com ótima produção.";

        // Act
        Temporada temporada = new Temporada(ano, quantidade, avaliacao, review);

        // Assert
        assertEquals(ano, temporada.getAno());
        assertEquals(quantidade, temporada.getQuantidade());
        assertEquals(avaliacao, temporada.getAvaliacao());
        assertEquals(review, temporada.getReview());
    }
}

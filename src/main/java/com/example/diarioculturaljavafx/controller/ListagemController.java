package com.example.diarioculturaljavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import com.example.diarioculturaljavafx.model.*;

import java.util.List;

/**
 * Controlador responsável por exibir os detalhes de itens buscados (livros, filmes ou séries)
 * em uma interface de navegação paginada.
 */
public class ListagemController {

    @FXML
    public TextArea textAreaSaida;

    TrocarTela trocar = new TrocarTela();

    /** Lista de dados genérica vinda de outros controladores */
    public static List<?> dadosExternos;

    /** Título opcional a ser exibido no topo da listagem */
    public static String titulo;

    /** Índice do item atualmente sendo exibido */
    private int indiceAtual = 0;

    // Listas específicas convertidas a partir de dadosExternos
    private List<Livro> livros;
    private List<Filme> filmes;
    private List<Serie> series;

    /**
     * Define a lista de dados externos que será usada na exibição.
     *
     * @param lista Lista de objetos (Livro, Filme ou Serie)
     */
    public static void setDados(List<?> lista) {
        dadosExternos = lista;
    }

    /**
     * Inicializa a tela de listagem exibindo o primeiro item da lista, se houver.
     */
    @FXML
    public void initialize() {
        textAreaSaida.clear();

        if (dadosExternos == null || dadosExternos.isEmpty()) {
            textAreaSaida.setText("Nenhum item encontrado.");
            return;
        }

        Object primeiro = dadosExternos.get(0);

        // Define a lista do tipo correto com base no primeiro elemento
        if (primeiro instanceof Livro) {
            livros = (List<Livro>) dadosExternos;
        } else if (primeiro instanceof Filme) {
            filmes = (List<Filme>) dadosExternos;
        } else if (primeiro instanceof Serie) {
            series = (List<Serie>) dadosExternos;
        }

        indiceAtual = 0;
        atualizarExibicaoAtual();
    }

    /**
     * Atualiza o conteúdo exibido no TextArea com base no item atual da lista.
     */
    private void atualizarExibicaoAtual() {
        StringBuilder sb = new StringBuilder();

        if (titulo != null) {
            sb.append("== ").append(titulo).append(" ==\n\n");
        }

        if (livros != null) {
            Livro livro = livros.get(indiceAtual);
            sb.append(montarTextoLivro(livro, indiceAtual + 1, livros.size()));
        } else if (filmes != null) {
            Filme filme = filmes.get(indiceAtual);
            sb.append(montarTextoFilme(filme, indiceAtual + 1, filmes.size()));
        } else if (series != null) {
            Serie serie = series.get(indiceAtual);
            sb.append(montarTextoSerie(serie, indiceAtual + 1, series.size()));
        } else {
            sb.append("Nenhum item encontrado.");
        }

        textAreaSaida.setText(sb.toString());
    }

    /**
     * Gera a representação textual de um livro.
     */
    private String montarTextoLivro(Livro livro, int atual, int total) {
        StringBuilder sb = new StringBuilder();
        sb.append("📖 Livro ").append(atual).append(" de ").append(total).append("\n");
        sb.append("• Nome: ").append(livro.getNome()).append("\n");
        sb.append("• Autor: ").append(livro.getAutor()).append("\n");
        sb.append("• Gênero: ").append(livro.getGenero()).append("\n");
        sb.append("• Ano de lançamento: ").append(livro.getAno()).append("\n");
        sb.append("• Editora: ").append(livro.getEditora()).append("\n");
        sb.append("• ISBN: ").append(livro.getIsbn()).append("\n");

        if (livro.getLeitura() != null) {
            sb.append("• Status: ✅ Lido\n");
            sb.append("• Data de leitura: ").append(livro.getLeitura()).append("\n");
            sb.append("• Review: ").append(livro.getReview()).append("\n");
            sb.append("• Avaliação: ⭐ ").append(livro.getAvaliacao()).append("/5\n");
        } else {
            sb.append("• Status: ⏳ Não lido\n");
        }
        return sb.toString();
    }

    /**
     * Gera a representação textual de um filme.
     */
    private String montarTextoFilme(Filme filme, int atual, int total) {
        StringBuilder sb = new StringBuilder();
        sb.append("🎬 Filme ").append(atual).append(" de ").append(total).append("\n");
        sb.append("• Nome: ").append(filme.getNome()).append("\n");
        sb.append("• Título Original: ").append(filme.getTituloOriginal()).append("\n");
        sb.append("• Gênero: ").append(filme.getGenero()).append("\n");
        sb.append("• Ano de lançamento: ").append(filme.getAno()).append("\n");
        sb.append("• Duração: ").append(filme.getDuracao()).append("\n");

        sb.append("• Elenco:\n");
        for (String e : filme.getElenco()) {
            sb.append("   - ").append(e).append("\n");
        }

        sb.append("• Direção:\n");
        for (String d : filme.getDirecao()) {
            sb.append("   - ").append(d).append("\n");
        }

        sb.append("• Roteiro:\n");
        for (String r : filme.getRoteiro()) {
            sb.append("   - ").append(r).append("\n");
        }

        if (filme.getDataAssistiu() != null) {
            sb.append("• Status: ✅ Visto\n");
            sb.append("• Data de visualização: ").append(filme.getDataAssistiu()).append("\n");
            sb.append("• Onde assistiu: ").append(filme.getOndeAssistir()).append("\n");
            sb.append("• Review: ").append(filme.getReview()).append("\n");
            sb.append("• Avaliação: ⭐ ").append(filme.getAvaliacao()).append("/5\n");
        } else {
            sb.append("• Status: ⏳ Não visto\n");
        }

        return sb.toString();
    }

    /**
     * Gera a representação textual de uma série.
     */
    private String montarTextoSerie(Serie serie, int atual, int total) {
        StringBuilder sb = new StringBuilder();
        sb.append("📺 Série ").append(atual).append(" de ").append(total).append("\n");
        sb.append("• Nome: ").append(serie.getNome()).append("\n");
        sb.append("• Título Original: ").append(serie.getTituloOriginal()).append("\n");
        sb.append("• Gênero: ").append(serie.getGenero()).append("\n");
        sb.append("• Ano de encerramento: ").append(serie.getFim()).append("\n");
        sb.append("• Avaliação: ⭐ ").append(serie.getAvaliacao()).append("/5\n");
        sb.append("• Onde assistiu: ").append(serie.getOndeAssistiu()).append("\n");

        sb.append("• Elenco:\n");
        for (String e : serie.getElenco()) {
            sb.append("   - ").append(e).append("\n");
        }

        int c = 1;
        for (Temporada t : serie.getTemporadas()) {
            sb.append("\n🔸 Temporada ").append(c++).append("\n");
            sb.append("   - Ano: ").append(t.getAno()).append("\n");
            sb.append("   - Quantidade de episódios: ").append(t.getQuantidade()).append("\n");
            sb.append("   - Review: ").append(t.getReview()).append("\n");
        }

        return sb.toString();
    }

    /**
     * Exibe o próximo item da lista, se houver.
     */
    @FXML
    private void proximo() {
        if (livros != null && indiceAtual < livros.size() - 1) {
            indiceAtual++;
            atualizarExibicaoAtual();
        } else if (filmes != null && indiceAtual < filmes.size() - 1) {
            indiceAtual++;
            atualizarExibicaoAtual();
        } else if (series != null && indiceAtual < series.size() - 1) {
            indiceAtual++;
            atualizarExibicaoAtual();
        }
    }

    /**
     * Exibe o item anterior da lista, se houver.
     */
    @FXML
    private void anterior() {
        if (indiceAtual > 0) {
            indiceAtual--;
            atualizarExibicaoAtual();
        }
    }

    /**
     * Retorna à tela inicial.
     *
     * @param event Evento acionado ao clicar no botão "Voltar"
     */
    @FXML
    private void retornar(ActionEvent event) {
        trocar.trocarTela("tela-inicial.fxml", event);
    }
}

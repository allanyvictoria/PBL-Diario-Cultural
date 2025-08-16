package com.example.diarioculturaljavafx.model;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.example.diarioculturaljavafx.service.Acervo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsável por persistir e restaurar o acervo (livros, filmes e séries)
 * em arquivos JSON usando a biblioteca <a href="https://github.com/google/gson">Gson</a>.
 *
 * <p>As constantes {@link #ARQUIVO_LIVROS}, {@link #ARQUIVO_FILMES} e
 * {@link #ARQUIVO_SERIES} indicam o caminho padrão dos arquivos de saída.
 * O {@code Gson} usado contém um {@link LocalDateAdapter} para tratar
 * corretamente campos {@link LocalDate}.</p>
 */
public class PersistenciaAcervo {

    /** Instância única de {@code Gson} configurada com impressão “bonita” e suporte a {@link LocalDate}. */
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    /** Caminho do arquivo JSON de livros. */
    public static final String ARQUIVO_LIVROS = "src/main/resources/livros.json";

    /** Caminho do arquivo JSON de filmes. */
    public static final String ARQUIVO_FILMES = "src/main/resources/filmes.json";

    /** Caminho do arquivo JSON de séries. */
    public static final String ARQUIVO_SERIES = "src/main/resources/series.json";

    /* ======================================================================
       Operações de salvamento / carregamento globais
       ====================================================================== */

    /** Salva as listas de livros, filmes e séries nos respectivos arquivos JSON. */
    public static void salvarTudo() {
        salvarListaJson(Acervo.getLivroList(),  ARQUIVO_LIVROS);
        salvarListaJson(Acervo.getFilmeList(),  ARQUIVO_FILMES);
        salvarListaJson(Acervo.getSerieList(),  ARQUIVO_SERIES);
    }

    /**
     * Limpa as listas em memória e recarrega o acervo completo
     * a partir dos arquivos JSON. Arquivos inexistentes ou vazios
     * geram listas novas sem lançar exceção.
     */
    public static void carregarTudo() {
        Acervo.getLivroList().clear();
        Acervo.getLivroList().addAll(carregarListaJson(ARQUIVO_LIVROS, Livro.class));

        Acervo.getFilmeList().clear();
        Acervo.getFilmeList().addAll(carregarListaJson(ARQUIVO_FILMES, Filme.class));

        Acervo.getSerieList().clear();
        Acervo.getSerieList().addAll(carregarListaJson(ARQUIVO_SERIES, Serie.class));
    }

    /* ======================================================================
       Métodos utilitários genéricos
       ====================================================================== */

    /**
     * Serializa uma lista em formato JSON e grava no disco.
     *
     * @param lista   lista de objetos a ser salva
     * @param arquivo caminho (relativo ou absoluto) do arquivo de destino
     * @param <T>     tipo dos elementos da lista
     */
    public static <T> void salvarListaJson(List<T> lista, String arquivo) {
        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar JSON em \"" + arquivo + "\": " + e.getMessage());
        }
    }

    /**
     * Lê um arquivo JSON e o converte em uma lista de objetos do tipo indicado.
     * Se ocorrer erro de leitura ou parse, uma lista vazia é retornada.
     *
     * @param arquivo caminho do arquivo JSON
     * @param tipo    classe do elemento da lista
     * @param <T>     tipo genérico dos elementos
     * @return lista carregada ou {@link ArrayList} vazio em caso de falha
     */
    public static <T> List<T> carregarListaJson(String arquivo, Class<T> tipo) {
        File file = new File(arquivo);
        if (!file.exists() || file.length() == 0) {
            System.out.println("ℹ️ Arquivo \"" + arquivo + "\" vazio ou inexistente. Criando lista vazia.");
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(arquivo)) {
            Type tipoLista = TypeToken.getParameterized(List.class, tipo).getType();
            List<T> lista = gson.fromJson(reader, tipoLista);

            if (lista == null) {
                System.err.println("⚠️ Estrutura JSON inválida em \"" + arquivo + "\". Retornando lista vazia.");
                return new ArrayList<>();
            }
            return lista;

        } catch (JsonSyntaxException e) {
            System.err.println("❌ JSON mal‑formatado em \"" + arquivo + "\": " + e.getMessage());
            return new ArrayList<>();
        } catch (IOException e) {
            System.err.println("❌ Erro de leitura em \"" + arquivo + "\": " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

/* ========================================================================== */
/* Adaptador LocalDate                                                         */
/* ========================================================================== */

/**
 * Adaptador Gson para serializar e desserializar {@link LocalDate} no padrão ISO‑8601 (yyyy‑MM‑dd).
 */
class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    /** Formatter ISO‑8601 usado na conversão. */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    /** {@inheritDoc} */
    @Override
    public JsonElement serialize(LocalDate date, Type type, JsonSerializationContext context) {
        return new JsonPrimitive(date.format(FORMATTER));
    }

    /** {@inheritDoc} */
    @Override
    public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDate.parse(json.getAsString(), FORMATTER);
    }
}

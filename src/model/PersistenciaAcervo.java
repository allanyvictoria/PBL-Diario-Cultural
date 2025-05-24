package model;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import controller.Acervo;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela persistência dos dados do acervo (livros, filmes, séries)
 * em arquivos JSON. Utiliza a biblioteca Gson para serialização e desserialização.
 */
public class PersistenciaAcervo {

    /** Instância de Gson com adaptador para LocalDate e impressão formatada. */
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    /** Nome do arquivo JSON onde os livros serão salvos. */
    public static final String ARQUIVO_LIVROS = "livros.json";

    /** Nome do arquivo JSON onde os filmes serão salvos. */
    public static final String ARQUIVO_FILMES = "filmes.json";

    /** Nome do arquivo JSON onde as séries serão salvas. */
    public static final String ARQUIVO_SERIES = "series.json";

    /**
     * Salva todas as listas do acervo (livros, filmes, séries) em seus respectivos arquivos JSON.
     */
    public static void salvarTudo() {
        salvarListaJson(Acervo.getLivroList(), ARQUIVO_LIVROS);
        salvarListaJson(Acervo.getFilmeList(), ARQUIVO_FILMES);
        salvarListaJson(Acervo.getSerieList(), ARQUIVO_SERIES);
    }

    /**
     * Carrega todas as listas do acervo (livros, filmes, séries) a partir dos arquivos JSON.
     * Caso os arquivos estejam vazios ou inválidos, cria listas vazias.
     */
    public static void carregarTudo() {
        Acervo.getLivroList().clear();
        Acervo.getLivroList().addAll(carregarListaJson(ARQUIVO_LIVROS, Livro.class));

        Acervo.getFilmeList().clear();
        Acervo.getFilmeList().addAll(carregarListaJson(ARQUIVO_FILMES, Filme.class));

        Acervo.getSerieList().clear();
        Acervo.getSerieList().addAll(carregarListaJson(ARQUIVO_SERIES, Serie.class));
    }

    /**
     * Salva uma lista de objetos genéricos em um arquivo JSON.
     *
     * @param lista   Lista de objetos a serem salvos.
     * @param arquivo Nome do arquivo de destino.
     * @param <T>     Tipo dos objetos da lista.
     */
    public static <T> void salvarListaJson(List<T> lista, String arquivo) {
        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar JSON: " + e.getMessage());
        }
    }

    /**
     * Carrega uma lista de objetos genéricos a partir de um arquivo JSON.
     *
     * @param arquivo Nome do arquivo a ser lido.
     * @param tipo    Classe dos objetos contidos na lista.
     * @param <T>     Tipo dos objetos da lista.
     * @return Lista de objetos carregados do JSON ou uma nova lista vazia em caso de erro.
     */
    public static <T> List<T> carregarListaJson(String arquivo, Class<T> tipo) {
        File file = new File(arquivo);
        if (!file.exists() || file.length() == 0) {
            System.out.println("ℹ️ Arquivo " + arquivo + " vazio ou não encontrado. Criando nova lista.");
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(arquivo)) {
            Type tipoLista = TypeToken.getParameterized(List.class, tipo).getType();
            List<T> lista = gson.fromJson(reader, tipoLista);

            if (lista == null) {
                System.err.println("⚠️ Estrutura inválida em " + arquivo + ". Criando lista vazia.");
                return new ArrayList<>();
            }

            return lista;

        } catch (JsonSyntaxException e) {
            System.err.println("❌ JSON malformatado em " + arquivo + ": " + e.getMessage());
            return new ArrayList<>();
        } catch (IOException e) {
            System.err.println("❌ Erro de leitura em " + arquivo + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

/**
 * Adaptador personalizado para serialização e desserialização de objetos {@link LocalDate}
 * no formato ISO (ex: "2023-12-31"), utilizado pelo Gson.
 */
class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    /** Formatter padrão ISO para datas locais. */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Serializa um {@link LocalDate} para uma {@link JsonPrimitive} no formato ISO.
     *
     * @param date    Data a ser serializada.
     * @param type    Tipo do objeto.
     * @param context Contexto de serialização.
     * @return Elemento JSON representando a data como string.
     */
    @Override
    public JsonElement serialize(LocalDate date, Type type, JsonSerializationContext context) {
        return new JsonPrimitive(date.format(FORMATTER));
    }

    /**
     * Desserializa uma string em formato ISO para um {@link LocalDate}.
     *
     * @param json    Elemento JSON contendo a data.
     * @param type    Tipo esperado.
     * @param context Contexto de desserialização.
     * @return Objeto {@link LocalDate} correspondente à string.
     * @throws JsonParseException se a string estiver em formato inválido.
     */
    @Override
    public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDate.parse(json.getAsString(), FORMATTER);
    }
}

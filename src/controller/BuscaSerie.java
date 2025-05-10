package controller;

import model.Serie;
import view.Listagem;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe responsável por realizar buscas de séries cadastradas com base em diferentes critérios.
 */
public class BuscaSerie {

    /**
     * Busca séries pelo título (ou parte dele), ignorando maiúsculas e minúsculas.
     *
     * @param titulo Título ou fragmento do nome da série a ser buscada.
     */
    public static void titulo(String titulo) {
        List<Serie> resultados = Acervo.getSerieList().stream()
                .filter(s -> s.getNome() != null &&
                        s.getNome().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
        Listagem.listaSeries(resultados);
    }

    /**
     * Busca séries por gênero, considerando correspondências parciais e ignorando maiúsculas e minúsculas.
     *
     * @param genero Gênero a ser buscado.
     */
    public static void buscaGenero(String genero) {
        List<Serie> resultados = Acervo.getSerieList().stream()
                .filter(s -> s.getGenero() != null &&
                        s.getGenero().toLowerCase().contains(genero.toLowerCase()))
                .collect(Collectors.toList());
        Listagem.listaSeries(resultados);
    }

    /**
     * Busca séries pelo nome de um ator ou atriz presente no elenco.
     *
     * @param atorBuscado Nome (ou parte do nome) do ator ou atriz a ser buscado.
     */
    public static void buscarPorAtor(String atorBuscado) {
        List<Serie> resultados = Acervo.getSerieList().stream()
                .filter(s -> s.getElenco() != null &&  // Verifica se a lista de elenco não é nula
                        s.getElenco().stream()  // Transforma a lista de elenco em Stream
                                .anyMatch(ator ->      // Verifica se algum ator do filme atende à condição
                                        ator != null &&    // Protege contra ator nulo
                                                ator.toLowerCase().contains(atorBuscado.toLowerCase())
                                ))
                .collect(Collectors.toList());

        Listagem.listaSeries(resultados);
    }

    /**
     * Busca séries lançadas em um ano específico.
     *
     * @param ano Ano de início da série.
     */
    public static void ano(int ano) {
        List<Serie> resultados = Acervo.getSerieList().stream()
                .filter(s -> s.getAno() == ano)
                .collect(Collectors.toList());
        Listagem.listaSeries(resultados);
    }
}

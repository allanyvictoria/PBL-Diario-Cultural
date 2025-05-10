package controller;

import model.Filme;
import view.Listagem;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe responsável por realizar buscas no acervo de filmes com base em diferentes critérios.
 */
public class BuscaFilme {

    /**
     * Busca filmes pelo título (ou parte dele), ignorando maiúsculas e minúsculas.
     *
     * @param titulo Título ou parte do título do filme.
     */
    public static void titulo(String titulo) {
        List<Filme> resultados = Acervo.getFilmeList().stream()
                .filter(f -> f.getNome() != null &&
                        f.getNome().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
        Listagem.listaFilmes(resultados);
    }

    /**
     * Busca filmes pelo nome do diretor (ou parte dele), ignorando maiúsculas e minúsculas.
     *
     * @param diretorBuscado Nome ou parte do nome do diretor.
     */
    public static void buscarPorDiretor(String diretorBuscado) {
        List<Filme> resultados = Acervo.getFilmeList().stream()
                .filter(f -> f.getDirecao() != null &&
                        f.getDirecao().stream()
                                .anyMatch(diretor ->
                                        diretor != null &&
                                                diretor.toLowerCase().contains(diretorBuscado.toLowerCase())
                                ))
                .collect(Collectors.toList());

        Listagem.listaFilmes(resultados);
    }

    /**
     * Busca filmes por nome de ator (ou parte dele), ignorando maiúsculas e minúsculas.
     *
     * @param atorBuscado Nome ou parte do nome do ator.
     */
    public static void buscarPorAtor(String atorBuscado) {
        List<Filme> resultados = Acervo.getFilmeList().stream()
                .filter(f -> f.getElenco() != null &&
                        f.getElenco().stream()
                                .anyMatch(ator ->
                                        ator != null &&
                                                ator.toLowerCase().contains(atorBuscado.toLowerCase())
                                ))
                .collect(Collectors.toList());

        Listagem.listaFilmes(resultados);
    }

    /**
     * Busca filmes pelo gênero, ignorando maiúsculas e minúsculas.
     *
     * @param genero Gênero a ser buscado.
     */
    public static void buscaGenero(String genero) {
        List<Filme> resultados = Acervo.getFilmeList().stream()
                .filter(f -> f.getGenero() != null &&
                        f.getGenero().toLowerCase().contains(genero.toLowerCase()))
                .collect(Collectors.toList());
        Listagem.listaFilmes(resultados);
    }

    /**
     * Busca filmes lançados em um ano específico.
     *
     * @param ano Ano de lançamento.
     */
    public static void ano(int ano) {
        List<Filme> resultados = Acervo.getFilmeList().stream()
                .filter(f -> f.getAno() == ano)
                .collect(Collectors.toList());
        Listagem.listaFilmes(resultados);
    }
}

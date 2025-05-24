package testes.integracao;

import controller.Acervo;
import model.*;
import model.PersistenciaAcervo;
import org.junit.*;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class PersistenciaAcervoIntegrationTest {

    @Before
    public void setUp() {
        // Limpa as listas antes de cada teste
        Acervo.getLivroList().clear();
        Acervo.getFilmeList().clear();
        Acervo.getSerieList().clear();
    }

    @After
    public void tearDown() {
        // Limpa os arquivos de teste após cada teste
        new File(PersistenciaAcervo.ARQUIVO_LIVROS).delete();
        new File(PersistenciaAcervo.ARQUIVO_FILMES).delete();
        new File(PersistenciaAcervo.ARQUIVO_SERIES).delete();
    }

    @Test
    public void testIntegracaoCompleta_AcervoEPersistencia() {
        // 1. Cria e adiciona itens ao Acervo
        Livro livro = new Livro("Dom Casmurro", 5, "Romance", 1899,
                "Clássico brasileiro", "Machado de Assis", "Editora Garnier",
                "978-0000000000", true, LocalDate.now());

        Filme filme = new Filme("O Poderoso Chefão", 5, "Drama", 1972,
                "Clássico do cinema", 175, List.of("Marlon Brando"), "Netflix",
                "The Godfather", List.of("Mario Puzo"), List.of("Francis Ford Coppola"),
                LocalDate.now());

        Serie serie = new Serie("Breaking Bad", "Drama", 5, 2008, "Série incrível",
                List.of(new Temporada(2008, 7, 5, "Ótima temporada")), 2013,
                "Breaking Bad", "Netflix", List.of("Bryan Cranston"));

        Acervo.adicionarLivro(livro);
        Acervo.adicionarFilme(filme);
        Acervo.adicionarSerie(serie);

        // 2. Persiste os dados
        PersistenciaAcervo.salvarTudo();

        // 3. Limpa o Acervo
        Acervo.getLivroList().clear();
        Acervo.getFilmeList().clear();
        Acervo.getSerieList().clear();

        // 4. Carrega os dados persistidos
        PersistenciaAcervo.carregarTudo();

        // 5. Verifica a integridade dos dados
        // Verifica Livros
        List<Livro> livrosCarregados = Acervo.getLivroList();
        assertEquals(1, livrosCarregados.size());
        assertEquals("Dom Casmurro", livrosCarregados.get(0).getNome());
        assertEquals("Machado de Assis", livrosCarregados.get(0).getAutor());

        // Verifica Filmes
        List<Filme> filmesCarregados = Acervo.getFilmeList();
        assertEquals(1, filmesCarregados.size());
        assertEquals("O Poderoso Chefão", filmesCarregados.get(0).getNome());
        assertEquals(175, filmesCarregados.get(0).getDuracao());

        // Verifica Séries
        List<Serie> seriesCarregadas = Acervo.getSerieList();
        assertEquals(1, seriesCarregadas.size());
        assertEquals("Breaking Bad", seriesCarregadas.get(0).getNome());
        assertEquals(1, seriesCarregadas.get(0).getTemporadas().size());
    }

    @Test
    public void testIntegracao_OrdenacaoAvaliacao() {
        // Adiciona livros com avaliações diferentes
        Acervo.adicionarLivro(new Livro("Livro 1", 3, "Genero", 2000, "Review",
                "Autor", "Editora", "ISBN", true, null));
        Acervo.adicionarLivro(new Livro("Livro 2", 5, "Genero", 2000, "Review",
                "Autor", "Editora", "ISBN", true, null));

        // Persiste e carrega
        PersistenciaAcervo.salvarTudo();
        Acervo.getLivroList().clear();
        PersistenciaAcervo.carregarTudo();

        // Testa ordenação
        Acervo.listarLivrosAvaliacao(true); // Ordena decrescente
        assertEquals(5, Acervo.getLivroList().get(0).getAvaliacao());
        assertEquals(3, Acervo.getLivroList().get(1).getAvaliacao());
    }

    @Test
    public void testIntegracao_ListasVazias() {
        // Persiste listas vazias
        PersistenciaAcervo.salvarTudo();

        // Adiciona itens e depois carrega (deveria limpar)
        Acervo.adicionarLivro(new Livro("Temp", 1, "G", 1, "R", "A", "E", "I", false, null));
        PersistenciaAcervo.carregarTudo();

        assertTrue(Acervo.getLivroList().isEmpty());
        assertTrue(Acervo.getFilmeList().isEmpty());
        assertTrue(Acervo.getSerieList().isEmpty());
    }
}
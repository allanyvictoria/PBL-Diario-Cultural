package view;

import controller.Acervo;
import controller.BuscaFilme;
import java.util.Scanner;

/**
 * Classe responsável por exibir o menu de listagem de filmes, onde o usuário pode
 * escolher entre listar os filmes mais ou menos avaliados, com a opção de aplicar filtros adicionais.
 */
public class ListarFilme {

    /**
     * Metodo que exibe o menu de listagem de filmes e permite que o usuário escolha entre
     * visualizar os filmes mais ou menos avaliadas.
     * O loop continua até que o usuário escolha a opção de sair ou uma entrada inválida seja fornecida.
     */
    public static void listar() {

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("=== LISTAGEM DE FILMES ===");
            System.out.println("1 - Melhores avaliados");
            System.out.println("2 - Piores avaliados");
            System.out.println("3 - Sair");

            System.out.print("\nEscolha uma opção: ");
            System.out.println();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1 ->{
                    Acervo.listarFilmesAvaliacao(true);
                    filtro();
                }
                case 2 ->{
                    Acervo.listarFilmesAvaliacao(false);
                    filtro();
                }
                case 3 -> {
                    System.out.println("Saindo...");
                    loop = false;
                }
                case -1 -> {
                    System.out.println("\n[Entrada encerrada - Saindo automaticamente]");
                    loop = false;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    /**
     * Metodo que exibe o menu de filtros adicionais, permitindo que o usuário filtre os filmes
     * por gênero ou ano de lançamento, ou escolha não aplicar filtro algum.
     * O loop continua até que uma opção válida seja escolhida.
     */
    public static void filtro() {
        boolean loop = true;

        while (loop) {
            System.out.println("=== FILTRAR POR ===");
            System.out.println("1 - Gênero");
            System.out.println("2 - Ano de lançamento");
            System.out.println("3 - Sem filtro");

            int opcao = VerificaInteiro.verificaInteiro("\nEscolha uma opção: ");

            switch (opcao) {
                case 1 -> {
                    String genero = GenerosDisponiveis.exibirGeneros();
                    BuscaFilme.buscaGenero(genero);
                    loop = false;
                }
                case 2 -> {
                    int ano = VerificaInteiro.verificaInteiro("Digite o ano de lançamento " +
                            "que deseja buscar: ");
                    BuscaFilme.ano(ano);
                    loop = false;
                }
                case 3 -> {
                    Acervo.listarFilmes();
                    loop = false;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}

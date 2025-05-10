package view;

import controller.Acervo;
import controller.BuscaSerie;
import java.util.Scanner;

/**
 * Classe responsável por exibir o menu de listagem de séries, onde o usuário pode
 * escolher entre listar as séries mais ou menos avaliadas, com a opção de aplicar filtros adicionais.
 */
public class ListarSerie {

    /**
     * Método que exibe o menu de listagem de séries e permite que o usuário escolha entre
     * visualizar as séries mais ou menos avaliadas.
     * O loop continua até que o usuário escolha a opção de sair ou uma entrada inválida seja fornecida.
     */
    public static void listar() {

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            // Exibe as opções para o usuário
            System.out.println("=== LISTAGEM DE SÉRIES ===");
            System.out.println("1 - Melhores avaliados");
            System.out.println("2 - Piores avaliados");
            System.out.println("3 - Sair");

            System.out.print("\nEscolha uma opção: ");
            System.out.println();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            // Processa a opção escolhida pelo usuário
            switch (opcao) {
                case 1 -> {
                    // Exibe as séries mais bem avaliadas e chama a função de filtro
                    Acervo.listarSeriesAvaliacao(true);
                    filtro();
                }
                case 2 -> {
                    // Exibe as séries menos avaliadas e chama a função de filtro
                    Acervo.listarSeriesAvaliacao(false);
                    filtro();
                }
                case 3 -> {
                    // Encerra o loop e sai
                    System.out.println("Saindo...");
                    loop = false;
                }
                case -1 -> {
                    // Simula uma saída se o usuário encerrasse a entrada
                    System.out.println("\n[Entrada encerrada - Saindo automaticamente]");
                    loop = false;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");  // Opção inválida
            }
        }
    }

    /**
     * Método que exibe um menu para aplicar filtros adicionais nas séries listadas.
     * O usuário pode filtrar por gênero, ano de lançamento ou escolher não aplicar filtro.
     */
    public static void filtro() {
        boolean loop = true;

        while (loop) {
            // Exibe as opções de filtro para o usuário
            System.out.println("=== FILTRAR POR ===");
            System.out.println("1 - Gênero");
            System.out.println("2 - Ano de lançamento");
            System.out.println("3 - Sem filtro");

            // Solicita que o usuário escolha uma opção de filtro
            int opcao = VerificaInteiro.verificaInteiro("\nEscolha uma opção: ");

            // Processa a opção de filtro escolhida pelo usuário
            switch (opcao) {
                case 1 -> {
                    // Exibe os gêneros disponíveis e realiza a busca por gênero
                    String genero = GenerosDisponiveis.exibirGeneros();
                    BuscaSerie.buscaGenero(genero);
                    loop = false;
                }
                case 2 -> {
                    // Solicita o ano de lançamento e realiza a busca por ano
                    int ano = VerificaInteiro.verificaInteiro("Digite o ano de" +
                            " lançamento que deseja buscar: ");
                    BuscaSerie.ano(ano);
                    loop = false;
                }
                case 3 -> {
                    // Exibe todas as séries sem aplicar filtros
                    Acervo.listarSeries();
                    loop = false;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");  // Opção inválida
            }
        }
    }
}

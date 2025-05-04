package view;

import controller.BuscaSerie;
import java.util.Scanner;

/**
 * Classe responsável por exibir o menu para buscar uma série por diferentes critérios.
 * O usuário pode escolher entre buscar por título, gênero, ano de lançamento ou ator/atriz.
 * O menu também oferece a opção de sair.
 */

public class MenuBuscaSerie {

    /**
     * Metodo que exibe o menu de busca e lida com as opções de busca de séries.
     * O loop continua até que o usuário escolha a opção de saída ou uma entrada inválida.
     */

    public static void menuSerie() {
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("=== BUSCAR SÉRIE POR ===");
            System.out.println("1 - Titulo");
            System.out.println("2 - Gênero");
            System.out.println("3 - Ano de lançamento");
            System.out.println("4 - Ator/Atriz");
            System.out.println("5 - Sair");

            int opcao = VerificaInteiro.verificaInteiro("\nEscolha uma opção: ");

            switch (opcao) {
                case 1 -> {
                    System.out.println("Digite o titulo que deseja buscar:\n" +
                            "Obs: Atente-se a erros de escrita ");
                    String titulo = scanner.nextLine();
                    BuscaSerie.titulo(titulo);
                }

                case 2 -> {
                    String genero = GenerosDisponiveis.exibirGeneros();
                    BuscaSerie.buscaGenero(genero);
                }

                case 3 -> {
                    int ano = VerificaInteiro.verificaInteiro("Digite o ano de lançamento " +
                            "que deseja buscar: ");
                    BuscaSerie.ano(ano);
                }

                case 4 -> {

                    System.out.println("Digite o ator/atriz que deseja buscar:\n" +
                            "Obs: Atente-se a erros de escrita ");
                    String elenco = scanner.nextLine();
                    BuscaSerie.buscarPorAtor(elenco);
                }

                case 5 -> {
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
}

package view;

import controller.BuscaFilme;

/**
 * Classe responsável por exibir o menu para buscar um filme por diferentes critérios.
 * O usuário pode escolher entre buscar por título, diretor, gênero, ano de lançamento ou ator/atriz.
 * O menu também oferece a opção de sair.
 */

public class MenuBuscaFilme {

    /**
     * Metodo que exibe o menu de busca e lida com as opções de busca de filmes.
     * O loop continua até que o usuário escolha a opção de saída ou uma entrada inválida.
     */

    public static void menuFilme() {

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("=== BUSCAR FILME POR ===");
            System.out.println("1 - Titulo");
            System.out.println("2 - Diretor");
            System.out.println("3 - Gênero");
            System.out.println("4 - Ano de lançamento");
            System.out.println("5 - Ator/Atriz");
            System.out.println("6 - Sair");

            int opcao = VerificaInteiro.verificaInteiro("\nEscolha uma opção: ");

            switch (opcao) {
                case 1 -> {
                    System.out.println("Digite o titulo que deseja buscar:\n" +
                            "Obs: Atente-se a erros de escrita ");
                    String titulo = scanner.nextLine();
                    BuscaFilme.titulo(titulo);
                }

                case 2 -> {
                    System.out.println("Digite o diretor que deseja buscar:\n" +
                            "Obs: Atente-se a erros de escrita ");
                    String diretor = scanner.nextLine();
                    BuscaFilme.buscarPorDiretor(diretor);
                }

                case 3 -> {
                    String genero = GenerosDisponiveis.exibirGeneros();
                    BuscaFilme.buscaGenero(genero);
                }

                case 4 -> {
                    int ano = VerificaInteiro.verificaInteiro("Digite o ano " +
                            "de lançamento que deseja buscar: ");
                    BuscaFilme.ano(ano);
                }

                case 5 -> {
                    System.out.println("Digite o ator/atriz que deseja buscar:\n" +
                            "Obs: Atente-se a erros de escrita ");
                    String elenco = scanner.nextLine();
                    BuscaFilme.buscarPorAtor(elenco);
                }


                case 6 -> {
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

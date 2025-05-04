package view;

import controller.BuscaLivro;

/**
 * Classe responsável por exibir o menu para buscar um livro por diferentes critérios.
 * O usuário pode escolher entre buscar por título, autor, gênero, ano de lançamento ou ISBN.
 * O menu também oferece a opção de sair.
 */
public class MenuBuscaLivro {

    /**
     * Método que exibe o menu de busca de livros e lida com as opções de busca por título, autor,
     * gênero, ano de lançamento ou ISBN.
     * O loop continua até que o usuário escolha a opção de saída ou forneça uma entrada inválida.
     */
    public static void menuBusca() {

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("=== BUSCAR LIVRO POR ===");
            System.out.println("1 - Titulo");
            System.out.println("2 - Autor");
            System.out.println("3 - Gênero");
            System.out.println("4 - Ano de lançamento");
            System.out.println("5 - ISBN");
            System.out.println("6 - Sair");

            int opcao = VerificaInteiro.verificaInteiro("\nEscolha uma opção: ");

            switch (opcao) {
                case 1 -> {
                    System.out.println("Digite o titulo que deseja buscar:\n" +
                            "Obs: Atente-se a erros de escrita ");
                    String titulo = scanner.nextLine();
                    BuscaLivro.titulo(titulo);
                }

                case 2 -> {
                    System.out.println("Digite o autor que deseja buscar:\n" +
                            "Obs: Atente-se a erros de escrita ");
                    String autor = scanner.nextLine();
                    BuscaLivro.autor(autor);
                }

                case 3 -> {
                    String genero = GenerosDisponiveis.exibirGeneros();
                    BuscaLivro.buscaGenero(genero);
                }

                case 4 -> {
                    int ano = VerificaInteiro.verificaInteiro("Digite o ano " +
                            "de lançamento que deseja buscar: ");
                    BuscaLivro.ano(ano);
                }

                case 5 -> {
                    System.out.println("Digite o ISBN que deseja buscar:\n" +
                            "Obs: Atente-se a erros de escrita ");
                    String isbn = scanner.nextLine();
                    BuscaLivro.isbn(isbn);
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

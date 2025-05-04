package view;

/**
 * Classe responsável por exibir o menu de busca, onde o usuário pode escolher entre buscar
 * livros, filmes, séries ou sair do sistema.
 * O menu continua sendo exibido até que o usuário escolha a opção de saída ou uma entrada inválida seja fornecida.
 */

public class MenuBusca {

    /**
     * Metodo que exibe o menu de busca e lida com as opções selecionadas pelo usuário.
     * O loop continua até que o usuário escolha a opção de sair ou uma entrada inválida.
     */

    public static void menuBusca() {
        boolean loop = true;

        while (loop) {
            System.out.println("=== BUSCA ===");
            System.out.println("1 - Buscar Livro");
            System.out.println("2 - Buscar Filme");
            System.out.println("3 - Buscar Série");
            System.out.println("4 - Sair");

            int opcao = VerificaInteiro.verificaInteiro("\nEscolha uma opção: ");

            switch (opcao) {
                case 1 -> MenuBuscaLivro.menuBusca();

                case 2 -> MenuBuscaFilme.menuFilme();

                case 3 -> MenuBuscaSerie.menuSerie();

                case 4 -> {
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

package view;

/**
 * Classe responsável por exibir o menu de listagem geral, onde o usuário pode escolher
 * entre listar livros, filmes, séries ou sair do sistema.
 * O menu continua sendo exibido até que o usuário escolha a opção de saída ou uma entrada inválida seja fornecida.
 */

public class Listar {
    /**
     * Metodo que exibe o menu de listagem e lida com as opções selecionadas pelo usuário.
     * O loop continua até que o usuário escolha a opção de sair ou uma entrada inválida.
     */
    public static void listar() {

        boolean loop = true;

        while (loop) {
            System.out.println("=== LISTAR ===");
            System.out.println("1 - Livros");
            System.out.println("2 - Filmes");
            System.out.println("3 - Séries");
            System.out.println("4 - Sair");

            int opcao = VerificaInteiro.verificaInteiro("\nEscolha uma opção: ");

            switch (opcao) {
                case 1 ->ListarLivro.listar();
                case 2 ->ListarFilme.listar();
                case 3 ->ListarSerie.listar();
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

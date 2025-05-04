package view;

/**
 * Classe responsável por exibir o menu de cadastro e permitir ao usuário
 * escolher a opção de cadastrar Livro, Filme ou Série.
 * O usuário pode sair do menu através da opção de saída ou se ocorrer uma entrada inválida.
 */

public class MenuCadastro {

    /**
     * Metodo responsável por exibir o menu e gerenciar as opções de cadastro.
     * O loop continuará até que o usuário escolha a opção de saída ou uma entrada inválida seja fornecida.
     */

    public static void menucadastro() {

        boolean loop = true;

        while (loop) {
            System.out.println("=== Bem-vindo ao cadastro ===");
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Cadastrar Filme");
            System.out.println("3 - Cadastrar Série");
            System.out.println("4 - Sair");

            int opcao = VerificaInteiro.verificaInteiro("\nEscolha uma opção: ");

            switch (opcao) {
                case 1 -> {
                    CadastroView.cadastrarLivro();
                    System.out.println("\nLivro cadastrado com sucesso!\n");
                }

                case 2 -> {
                    CadastroView.cadastrarFilme();
                    System.out.println("Filme cadastrado com sucesso!");
                }

                case 3 -> {
                    CadastroView.cadastrarSerie();
                    System.out.println("Série cadastrada com sucesso!");
                }


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

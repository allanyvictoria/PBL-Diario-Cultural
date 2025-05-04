package view;

/**
 * Classe responsável por exibir o menu principal do sistema, com opções para cadastrar, buscar, listar
 * ou sair do Diário Cultural.
 * O menu continua sendo exibido até que o usuário escolha a opção de saída ou uma entrada inválida seja fornecida.
 */

public class Menu {

    /**
     * Metodo que exibe o menu principal do Diário Cultural e lida com as opções selecionadas pelo usuário.
     * O loop continua até que o usuário escolha a opção de saída ou uma entrada inválida.
     */
    public void menu(){

        boolean loop = true;

        while (loop) {
            System.out.println("=== Bem-vindo ao Diário Cultural ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Buscar");
            System.out.println("3 - Listar");
            System.out.println("4 - Sair");

            int opcao = VerificaInteiro.verificaInteiro("\nEscolha uma opção: ");

            switch (opcao) {
                case 1 -> MenuCadastro.menucadastro();
                case 2 -> MenuBusca.menuBusca();
                case 3 -> Listar.listar();
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

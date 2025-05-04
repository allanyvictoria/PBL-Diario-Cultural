package view;

/**
 * Classe responsável por exibir uma lista de gêneros disponíveis e permitir ao usuário escolher um.
 */

public class GenerosDisponiveis {

    /**
     * Metodo que exibe os gêneros disponíveis e retorna o gênero escolhido pelo usuário.
     * O metodo assegura que o usuário escolhe uma opção válida, entre os gêneros apresentados.
     *
     * @return O gênero escolhido pelo usuário, ou null se a entrada for encerrada.
     */

    public static String exibirGeneros() {

        String[] generos = {"Ação", "Aventura", "Comédia", "Drama",
                "Terror", "Romance", "Fantasia", "Ficção Científica",
                "Suspense", "Animação", "Outro"
        };

        int opcao = 0;
        boolean valido = false;

        while (!valido) {
            System.out.println("=== GÊNEROS DISPONÍVEIS ===");
            for (int i = 0; i < generos.length; i++) {
                System.out.println((i + 1) + " - " + generos[i]);
            }

            opcao = VerificaInteiro.verificaInteiro("Escolha um gênero: ");

            if (opcao >= 1 && opcao <= generos.length) {
                valido = true;
            } else if (opcao == -1) {
                    System.out.println("\n[Entrada encerrada - Saindo automaticamente]");
                    return null;
            } else {
                System.out.println("Opção inválida. Tente novamente.\n");
            }
        }


        // Retorna o gênero escolhido, ajustado pela indexação (1-based para 0-based)
        return generos[opcao - 1];
    }
}

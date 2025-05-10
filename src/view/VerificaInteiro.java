package view;

import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * Classe responsável por validar a entrada de um número inteiro.
 * Caso o usuário insira um valor inválido, o método solicita novamente a entrada.
 */
public class VerificaInteiro {

    /**
     * Método que solicita a entrada de um número inteiro e verifica se o valor fornecido é válido.
     * Caso o número seja negativo ou inválido, ele pede ao usuário para tentar novamente.
     * Em caso de fim de entrada (como um "Ctrl+D"), retorna -1 para indicar o encerramento.
     *
     * @param mensagem A mensagem a ser exibida para o usuário ao solicitar a entrada do número.
     * @return O número inteiro fornecido pelo usuário, ou -1 se a entrada for encerrada.
     */
    public static int verificaInteiro(String mensagem) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(mensagem);
        try {
            String linha = scanner.nextLine();
            int numero = Integer.parseInt(linha);

            if (numero < 0) {
                System.out.println("Não é permitido número negativo.");
                return verificaInteiro(mensagem); // tenta de novo
            }

            return numero;
        } catch (NoSuchElementException e) {
            // Fim da entrada: simula que o usuário escolheu "-1" (sair)
            System.out.println("\n[Entrada encerrada - Saindo automaticamente]");
            return -1;
        } catch (NumberFormatException e) {
            System.out.println("Digite um número válido.");
            return verificaInteiro(mensagem); // tenta de novo
        }
    }
}

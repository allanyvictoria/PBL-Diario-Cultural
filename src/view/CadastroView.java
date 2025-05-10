package view;

import controller.VerificaCadastro;
import model.Temporada;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável pelas interações de cadastro de livros, filmes e séries.
 * Permite ao usuário cadastrar informações detalhadas sobre livros, filmes e séries, incluindo gênero, avaliação, review e outros dados específicos.
 */
public class CadastroView {
    // Criando o scanner para ler entradas do usuário
    static Scanner scanner = new Scanner(System.in);

    /**
     * Lê o ISBN de um livro.
     *
     * @return O ISBN do livro como uma string.
     */
    public static String numisbn() {
        return scanner.nextLine();
    }

    /**
     * Verifica se a data inserida está no formato correto (AAAA-MM-DD).
     *
     * @param mensagem Mensagem que será exibida antes da entrada de data.
     * @return A data inserida, no formato LocalDate.
     */
    public static LocalDate verificaData(String mensagem) {
        Scanner sc = new Scanner(System.in);
        LocalDate data = null;
        boolean dataValida = false;

        // Loop até que o usuário insira uma data válida
        while (!dataValida) {
            System.out.print(mensagem + " (AAAA-MM-DD): ");
            String entrada = sc.nextLine();

            try {
                // Tenta converter a entrada para uma data
                data = LocalDate.parse(entrada);
                dataValida = true; // se chegou aqui, a data é válida
            } catch (DateTimeParseException e) {
                // Se o formato for inválido, exibe uma mensagem de erro e pede para tentar novamente
                System.out.println("Formato inválido! Tente novamente.");
            }
        }
        return data;
    }

    /**
     * Realiza o cadastro de um livro.
     * Solicita ao usuário informações como nome, gênero, autor, editora, ISBN, se foi lido ou não,
     * e se lido, uma avaliação, review e data de leitura.
     */
    public static void cadastrarLivro() {
        System.out.println("\n== Cadastro de Livro == ");
        int lido = 0;
        System.out.println("1-Lido\n2-Não lido");

        // Verificação se a opção de status do livro é válida
        while (lido != 1 && lido != 2) {
            lido = VerificaInteiro.verificaInteiro("Escolha uma opção: ");
            if (lido != 1 && lido != 2) System.out.println("Inválido! Tente novamente.");
        }

        // Solicitação do nome do livro
        System.out.println("Nome:");
        String nome = scanner.nextLine();

        // Solicitação do gênero do livro através do método 'exibirGeneros'
        String genero = GenerosDisponiveis.exibirGeneros();

        // Solicitação do ano de lançamento do livro
        int ano = VerificaInteiro.verificaInteiro("Ano de lançamento:");

        // Solicitação do autor e editora do livro
        System.out.println("Autor: ");
        String autor = scanner.nextLine();

        System.out.println("Editora: ");
        String editora = scanner.nextLine();

        // Solicitação do ISBN do livro
        System.out.println("ISBN: ");
        String isbn = numisbn();

        int resposta = 0;
        System.out.println("1- Possui\n2-Não Possui ");

        // Verificação se a opção de ter ou não o livro é válida
        while (resposta != 1 && resposta != 2) {
            resposta = VerificaInteiro.verificaInteiro("Escolha uma opção: ");
            if (resposta != 1 && resposta != 2) System.out.println("Inválido! Tente novamente.");
        }
        boolean possui;
        possui = resposta == 1; // Verifica se o usuário possui o livro

        // Se o livro foi lido, solicita a avaliação, review e data de leitura
        if (lido == 1) {
            int avaliacao = VerificaInteiro.verificaInteiro("Avalie entre 0 e 5: ");
            System.out.println("Review: ");
            String review = scanner.nextLine();
            LocalDate dataLeitura = verificaData("Data da leitura");

            // Chama o método de cadastro passando todos os dados
            VerificaCadastro.verificaCadastro(nome, avaliacao, genero,
                    ano, review, autor, editora, isbn, possui, dataLeitura);
        } else {
            // Caso o livro não tenha sido lido, cadastra com a avaliação padrão (-1)
            VerificaCadastro.verificaCadastro(nome, -1, genero,
                    ano, "Não lido", autor, editora, isbn, possui, null);
        }
    }

    /**
     * Realiza o cadastro de um filme.
     * Solicita ao usuário informações como nome, gênero, ano de lançamento, elenco (diretores, roteiristas, atores),
     * se foi visto ou não, e se visto, uma avaliação, review e data de visualização.
     */
    public static void cadastrarFilme() {
        System.out.println("\n\n== Cadastro de Filme ==\n ");
        int visto = 0;
        System.out.println("1-Visto\n2-Não visto");

        // Verificação se a opção de status do filme é válida
        while (visto != 1 && visto != 2) {
            visto = VerificaInteiro.verificaInteiro("Escolha uma opção: ");
            if (visto != 1 && visto != 2) System.out.println("Inválido! Tente novamente.");
        }

        // Solicitação do nome do filme
        System.out.println("\nNome: ");
        String nome = scanner.nextLine();

        // Solicitação do gênero do filme através do método 'exibirGeneros'
        String genero = GenerosDisponiveis.exibirGeneros();

        // Solicitação do ano de lançamento do filme
        int ano = VerificaInteiro.verificaInteiro("Ano de lançamento:");

        // Solicitação do número de diretores, roteiristas e atores
        int qntDiretor = VerificaInteiro.verificaInteiro("Quantidade de diretores que deseja cadastrar: ");
        List<String> diretores = new ArrayList<>();

        // Cadastro dos diretores
        for (int i = 0; i < qntDiretor; i++){
            System.out.println("Diretor " + (i + 1) + ": ");
            diretores.add(scanner.nextLine());
        }

        // Solicitação do número de roteiristas
        int qntRoteiro = VerificaInteiro.verificaInteiro("Quantidade de roteiristas que deseja cadastrar: ");
        List<String> roteiristas = new ArrayList<>();

        // Cadastro dos roteiristas
        for (int i = 0; i < qntRoteiro; i++){
            System.out.println("Roteirista " + (i + 1) + ": ");
            roteiristas.add(scanner.nextLine());
        }

        // Solicitação do número de atores
        int qntElenco = VerificaInteiro.verificaInteiro("Quantidade de atores/atrizes que deseja cadastrar: ");
        List<String> atores = new ArrayList<>();

        // Cadastro dos atores
        for (int i = 0; i < qntElenco; i++){
            System.out.println("Ator/atriz " + (i + 1) + ": ");
            atores.add(scanner.nextLine());
        }

        // Solicitação de onde o filme pode ser assistido
        System.out.println("Onde assistir: ");
        String ondeAssistiu = scanner.nextLine();

        // Solicitação do título original do filme
        System.out.println("Titulo Original: ");
        String tituloOriginal = scanner.nextLine();

        // Solicitação da duração do filme
        int duracao = VerificaInteiro.verificaInteiro("Duração do filme (Em minutos): ");

        // Se o filme foi visto, solicita a avaliação, review e data de visualização
        if (visto == 1) {
            int avaliacao = VerificaInteiro.verificaInteiro("Avalie entre 0 e 5: ");
            System.out.println("Review: ");
            String review = scanner.nextLine();
            LocalDate dataView = verificaData("Data em que assistiu");

            // Chama o método de cadastro passando todos os dados
            VerificaCadastro.verificaCadastro( nome, avaliacao, genero, ano, review, duracao,
                    atores, ondeAssistiu, tituloOriginal, roteiristas, diretores, dataView);
        } else {
            // Caso o filme não tenha sido visto, cadastra com a avaliação padrão (-1)
            VerificaCadastro.verificaCadastro( nome, -1, genero, ano, "Não assistido", duracao,
                    atores, ondeAssistiu, tituloOriginal, roteiristas, diretores, null);
        }
    }

    /**
     * Realiza o cadastro de uma série.
     * Solicita ao usuário informações como nome, gênero, ano de lançamento, elenco (atores),
     * review geral, e detalhes sobre as temporadas (ano, quantidade de episódios, avaliação, review).
     */
    public static void cadastrarSerie() {
        System.out.println("\n\n== Cadastro de Serie ==\n ");
        // Solicitação do nome da série
        System.out.println("Nome: ");
        String nome = scanner.nextLine();

        // Solicitação do gênero da série
        String genero = GenerosDisponiveis.exibirGeneros();

        // Solicitação do ano de lançamento da série
        int ano = VerificaInteiro.verificaInteiro("Ano de lançamento:");

        // Solicitação do título original da série
        System.out.println("Titulo Original: ");
        String tituloOriginal = scanner.nextLine();

        // Solicitação de onde a série pode ser assistida
        System.out.println("Onde assistir: ");
        String ondeAssistiu = scanner.nextLine();

        // Solicitação do ano de encerramento da série
        int anoFinal = VerificaInteiro.verificaInteiro("Ano de encerramento(Ano que última " +
                "temporada disponível foi lançada): ");

        // Solicitação do elenco da série (atores)
        int qntElenco = VerificaInteiro.verificaInteiro("Quantidade de atores/atrizes" +
                " que deseja cadastrar: ");
        List<String> atores = new ArrayList<>();

        // Cadastro dos atores
        for (int i = 0; i < qntElenco; i++){
            System.out.println("Ator/atriz " + (i + 1) + ": ");
            atores.add(scanner.nextLine());
        }

        // Solicitação do review geral da série
        System.out.println("Review geral: ");
        String review = scanner.nextLine();

        // Cálculo da avaliação total da série, baseada nas temporadas
        int totalAvaliacao = 0;

        // Solicitação da quantidade de temporadas
        int qntTemp = VerificaInteiro.verificaInteiro("Quantidade de temporadas" +
                " que deseja cadastrar: ");

        List<Temporada> temp = new ArrayList<>();
        for (int i = 0; i < qntTemp; i++){
            System.out.println("======Cadastro temporada " + (i + 1) + "====== ");
            int anoTemp = VerificaInteiro.verificaInteiro("Ano da temporada: ");
            int quantidade = VerificaInteiro.verificaInteiro("Quantidade de episódios: ");
            int avaliacao = VerificaInteiro.verificaInteiro("Avalie entre 0 e 5: ");
            while (avaliacao < 1 || avaliacao > 5) {
                avaliacao = VerificaInteiro.verificaInteiro("Avalie entre 0 e 5: ");
            }
            System.out.println("Review da temporada: ");
            String reviewTemp = scanner.nextLine();

            Temporada t = new Temporada(anoTemp, quantidade, avaliacao, reviewTemp);
            temp.add(t);
            totalAvaliacao += avaliacao;
        }
        int avaliacao;
        if (qntTemp > 0) {
            avaliacao = totalAvaliacao / qntTemp;
        } else {
            // Se não houver temporadas,a avaliação é 0
            avaliacao = 0;
        }

        // Chama o metodo de cadastro da série
        VerificaCadastro.verificaCadastro(nome, genero, avaliacao, ano, review,
                temp, anoFinal, tituloOriginal, ondeAssistiu, atores);
    }
}

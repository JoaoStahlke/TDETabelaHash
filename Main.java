import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int tamanhoTabela = 10007;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha a função hash:");
        System.out.println("1 - Tabela Hash com Soma de Caracteres");
        System.out.println("2 - Tabela Hash com Soma ASCII dos Caracteres");
        int escolha = scanner.nextInt();
        scanner.close();

        TabelaHash<String> tabelaHash;

        if (escolha == 1) {
            tabelaHash = new TabelaHashSomaCaracteres(tamanhoTabela);
        } else if (escolha == 2) {
            tabelaHash = new TabelaHashSomaASCII(tamanhoTabela);
        } else {
            System.out.println("Opção inválida.");
            return;
        }

        String caminhoArquivo = "female_names.txt";

        try {
            tabelaHash.carregarDados(caminhoArquivo);
            
            tabelaHash.mostrarRelatorio();

        } catch (IOException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }
}

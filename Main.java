import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Define o tamanho da tabela (o ideal é ajustar conforme a necessidade)
        int tamanhoTabela = 10007; // Usar um número primo como tamanho da tabela para minimizar colisões

        // Permite que o usuário escolha qual função hash deseja utilizar
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

        // Define o caminho para o arquivo CSV
        String caminhoArquivo = "female_names.txt";

        try {
            // Carrega os dados do arquivo e insere na tabela hash
            tabelaHash.carregarDados(caminhoArquivo);

            // Mostra o estado final da tabela
            tabelaHash.mostrarRelatorio();

        } catch (IOException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }
}

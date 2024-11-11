import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TabelaHashSomaASCII extends TabelaHash<String> {

    private int numColisoes;
    private Map<Integer, Integer> colisoesPorIndice;

    public TabelaHashSomaASCII(int tamanho) {
        super(tamanho);
        this.numColisoes = 0;
        this.colisoesPorIndice = new HashMap<>();
    }

    @Override
    public void inserir(String chave, String valor) {
        int indice = calcularIndice(chave);

        // Tratamento de colisões com busca linear
        while (tabela[indice] != null) {
            numColisoes++;
            colisoesPorIndice.put(indice, colisoesPorIndice.getOrDefault(indice, 0) + 1);
            indice = (indice + 1) % tamanho; // Incrementa o índice com busca linear
        }

        tabela[indice] = valor;
    }

    @Override
    public String buscar(String chave) {
        int indice = calcularIndice(chave);

        while (tabela[indice] != null) {
            if (tabela[indice].equals(chave)) {
                return (String) tabela[indice];
            }
            indice = (indice + 1) % tamanho;
        }

        return null; // Retorna null se não encontrar a chave
    }

    @Override
    protected int calcularIndice(String chave) {
        int soma = 0;
        for (char c : chave.toCharArray()) {
            soma += (int) c; // Soma os valores ASCII de todos os caracteres
        }
        return soma % tamanho; // Retorna o índice para a chave
    }

    @Override
    public void remover(String chave) {
        // Opcional: Implementar caso necessário
    }

    public void carregarDados(String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            long inicioInsercao = System.nanoTime();

            while ((linha = br.readLine()) != null) {
                inserir(linha, linha);
            }

            long fimInsercao = System.nanoTime();
            double tempoInsercaoSegundos = (fimInsercao - inicioInsercao) / 1_000_000_000.0;

            // Exibir resultados de inserção
            System.out.println("Número total de colisões: " + numColisoes);
            System.out.println("Tempo total de inserção: " + String.format("%.3f", tempoInsercaoSegundos) + " segundos");

            // Tempo para buscar o conjunto de nomes
            String[] nomesParaBuscar = {"Abby", "Kaylyn", "Manon", "Zorah", "Vi"};
            long inicioBuscaConjunto = System.nanoTime();

            for (String nome : nomesParaBuscar) {
                buscar(nome);
            }

            long fimBuscaConjunto = System.nanoTime();
            double tempoBuscaTotalSegundos = (fimBuscaConjunto - inicioBuscaConjunto) / 1_000_000_000.0;

            System.out.println("Tempo de busca total para o conjunto de nomes (\"Abby, Kaylyn, Manon, Zorah, Vi\"): " + String.format("%.6f", tempoBuscaTotalSegundos) + " segundos");

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public void mostrarRelatorio() {
        System.out.println("Cada índice possui uma chave (devido ao tratamento de colisões por busca linear).");

        // Relatório de clusterização
        System.out.println("Relatório de clusterização (índices com colisões):");
        for (Map.Entry<Integer, Integer> entrada : colisoesPorIndice.entrySet()) {
            System.out.println("Índice " + entrada.getKey() + ": " + entrada.getValue() + " colisões");
        }
    }
}

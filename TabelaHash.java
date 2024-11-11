import java.io.IOException;

public abstract class TabelaHash<T> {

    protected int tamanho;
    protected Object[] tabela;

    // Construtor para inicializar a tabela com um tamanho específico
    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new Object[tamanho];
    }

    // Método abstrato para inserir um elemento na tabela
    public abstract void inserir(String chave, T valor);

    // Método abstrato para buscar um elemento na tabela
    public abstract T buscar(String chave);

    // Método para calcular o índice baseado na chave, será implementado nas subclasses
    protected abstract int calcularIndice(String chave);

    // Método opcional para remover um elemento da tabela (pode ser implementado nas subclasses)
    public abstract void remover(String chave);

    // Método para carregar dados de um arquivo (abstrato para obrigar implementação nas subclasses)
    public abstract void carregarDados(String caminhoArquivo) throws IOException;

    // Método para exibir o relatório da tabela (abstrato para obrigar implementação nas subclasses)
    public abstract void mostrarRelatorio();
}

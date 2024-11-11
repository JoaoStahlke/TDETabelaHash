import java.io.IOException;

public abstract class TabelaHash<T> {

    protected int tamanho;
    protected Object[] tabela;

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new Object[tamanho];
    }

    public abstract void inserir(String chave, T valor);

    public abstract T buscar(String chave);

    protected abstract int calcularIndice(String chave);

    public abstract void carregarDados(String caminhoArquivo) throws IOException;

    public abstract void mostrarRelatorio();
}

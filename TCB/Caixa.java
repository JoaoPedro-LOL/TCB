import java.io.Serializable;

public class Caixa implements Serializable {
    private static final long serialVersionUID = 1L;
    private int idMatriz;
    private int linha;
    private int coluna;
    private String nome;
    private String informacao;

    public Caixa(int idMatriz, int linha, int coluna, String nome, String informacao) {
        this.idMatriz = idMatriz;
        this.linha = linha;
        this.coluna = coluna;
        this.nome = nome == null ? "" : nome;
        this.informacao = informacao == null ? "" : informacao;
    }

    public int getIdMatriz() { return idMatriz; }
    public int getLinha() { return linha; }
    public int getColuna() { return coluna; }
    public String getNome() { return nome; }
    public String getInformacao() { return informacao; }
    public void setNome(String nome) { this.nome = nome == null ? "" : nome; }
    public void setInformacao(String informacao) { this.informacao = informacao == null ? "" : informacao; }
}

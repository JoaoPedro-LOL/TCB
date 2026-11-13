import java.io.Serializable;

public class Matriz implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int linhas;
    private int colunas;

    public Matriz(int id, int linhas, int colunas) {
        this.id = id;
        this.linhas = linhas;
        this.colunas = colunas;
    }

    public int getId() { return id; }
    public int getLinhas() { return linhas; }
    public int getColunas() { return colunas; }
    public void setLinhas(int linhas) { this.linhas = linhas; }
    public void setColunas(int colunas) { this.colunas = colunas; }
}

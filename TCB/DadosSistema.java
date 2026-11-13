import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DadosSistema implements Serializable {
    private static final long serialVersionUID = 1L;
    private Matriz matriz;
    private String senhaHash;
    private Map<String, Caixa> caixas;

    public DadosSistema() {
        matriz = new Matriz(1, 30, 30);
        senhaHash = Seguranca.sha256("1234");
        caixas = new HashMap<>();
    }

    public Matriz getMatriz() { return matriz; }
    public void setMatriz(Matriz matriz) { this.matriz = matriz; }
    public String getSenhaHash() { return senhaHash; }
    public void setSenhaHash(String senhaHash) { this.senhaHash = senhaHash; }
    public Map<String, Caixa> getCaixas() { return caixas; }
}

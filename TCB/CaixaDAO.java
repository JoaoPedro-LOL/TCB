import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CaixaDAO {
    private String chave(int linha, int coluna) {
        return linha + "-" + coluna;
    }

    public Caixa consultar(int idMatriz, int linha, int coluna) {
        Caixa caixa = BancoArquivo.obterDados().getCaixas().get(chave(linha, coluna));
        if (caixa == null) return new Caixa(idMatriz, linha, coluna, "", "");
        return caixa;
    }

    public void salvar(Caixa caixa) {
        BancoArquivo.obterDados().getCaixas().put(chave(caixa.getLinha(), caixa.getColuna()), caixa);
        BancoArquivo.salvar();
    }

    public void apagar(int idMatriz, int linha, int coluna) {
        BancoArquivo.obterDados().getCaixas().remove(chave(linha, coluna));
        BancoArquivo.salvar();
    }

    public List<Caixa> pesquisar(int idMatriz, String texto) {
        List<Caixa> encontrados = new ArrayList<>();
        String busca = texto.toLowerCase();
        for (Caixa caixa : BancoArquivo.obterDados().getCaixas().values()) {
            if (caixa.getNome().toLowerCase().contains(busca)
                    || caixa.getInformacao().toLowerCase().contains(busca)) {
                encontrados.add(caixa);
            }
        }
        return encontrados;
    }
}

import java.io.*;

public class BancoArquivo {
    private static final String ARQUIVO = "dados_sistema.dat";
    private static DadosSistema dados;

    public static synchronized void inicializar() {
        File arquivo = new File(ARQUIVO);
        if (arquivo.exists()) {
            carregar();
        } else {
            dados = new DadosSistema();
            salvar();
        }
    }

    public static synchronized DadosSistema obterDados() {
        if (dados == null) carregar();
        return dados;
    }

    public static synchronized void salvar() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            out.writeObject(dados);
        } catch (IOException e) {
            JOptionPaneUtil.erro("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    private static synchronized void carregar() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            dados = (DadosSistema) in.readObject();
        } catch (Exception e) {
            JOptionPaneUtil.erro("Não foi possível carregar os dados. " + e.getMessage());
            dados = new DadosSistema();
            salvar();
        }
    }
}

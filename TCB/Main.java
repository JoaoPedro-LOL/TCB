import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        BancoArquivo.inicializar();
        SwingUtilities.invokeLater(() -> new TelaInicial().setVisible(true));
    }
}

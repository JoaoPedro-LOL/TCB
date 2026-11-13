import javax.swing.JOptionPane;

public class JOptionPaneUtil {
    public static void erro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}

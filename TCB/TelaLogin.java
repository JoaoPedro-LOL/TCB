import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {
    public TelaLogin() {
        setTitle("Login - Manutenção");
        setSize(320, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel(new GridLayout(3, 1, 8, 8));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
        JPasswordField senha = new JPasswordField();
        JButton entrar = new JButton("Entrar");
        painel.add(new JLabel("Senha de manutenção:"));
        painel.add(senha);
        painel.add(entrar);
        add(painel);

        entrar.addActionListener(e -> {
            if (new PerfilDAO().validarSenha(new String(senha.getPassword()))) {
                dispose();
                new TelaMatriz(true).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Senha incorreta.");
            }
        });
    }
}

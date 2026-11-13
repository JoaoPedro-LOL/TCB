import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JFrame {
    public TelaInicial() {
        setTitle("Sistema de Armazenamento em Matriz de Caixas");
        setSize(420, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel painel = new JPanel(new GridLayout(3, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        painel.add(new JLabel("Sistema de Armazenamento", SwingConstants.CENTER));
        JButton manutencao = new JButton("Manutenção");
        JButton visitante = new JButton("Visitante");
        painel.add(manutencao);
        painel.add(visitante);
        add(painel);

        manutencao.addActionListener(e -> new TelaLogin().setVisible(true));
        visitante.addActionListener(e -> new TelaMatriz(false).setVisible(true));
    }
}

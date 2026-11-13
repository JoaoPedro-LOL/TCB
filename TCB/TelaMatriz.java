import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TelaMatriz extends JFrame {
    private final boolean manutencao;
    private final MatrizDAO matrizDAO = new MatrizDAO();
    private final CaixaDAO caixaDAO = new CaixaDAO();
    private Matriz matriz;
    private final Set<String> encontrados = new HashSet<>();
    private JPanel grade;

    public TelaMatriz(boolean manutencao) {
        this.manutencao = manutencao;
        setTitle(manutencao ? "Matriz - Manutenção" : "Matriz - Visitante");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        montarTela();
    }

    private void montarTela() {
        JPanel topo = new JPanel(new FlowLayout());
        JTextField pesquisa = new JTextField(18);
        JButton pesquisar = new JButton("Pesquisar");
        JButton limpar = new JButton("Limpar pesquisa");
        topo.add(new JLabel("Texto:"));
        topo.add(pesquisa);
        topo.add(pesquisar);
        topo.add(limpar);

        if (manutencao) {
            JButton tamanho = new JButton("Alterar linhas/colunas");
            JButton senha = new JButton("Alterar senha");
            topo.add(tamanho);
            topo.add(senha);
            tamanho.addActionListener(e -> alterarTamanho());
            senha.addActionListener(e -> alterarSenha());
        }

        grade = new JPanel();
        add(topo, BorderLayout.NORTH);
        add(new JScrollPane(grade), BorderLayout.CENTER);
        pesquisar.addActionListener(e -> pesquisar(pesquisa.getText()));
        limpar.addActionListener(e -> {
            encontrados.clear();
            atualizarGrade();
        });
        atualizarGrade();
    }

    private String chave(int linha, int coluna) {
        return linha + "-" + coluna;
    }

    private void atualizarGrade() {
        try {
            matriz = matrizDAO.obter();
            grade.removeAll();
            grade.setLayout(new GridLayout(matriz.getLinhas(), matriz.getColunas(), 2, 2));
            for (int l = 1; l <= matriz.getLinhas(); l++) {
                for (int c = 1; c <= matriz.getColunas(); c++) {
                    final int linha = l, coluna = c;
                    JButton botao = new JButton(l + "," + c);
                    botao.setMargin(new Insets(2, 2, 2, 2));
                    botao.setBackground(encontrados.contains(chave(l, c)) ? Color.YELLOW : Color.LIGHT_GRAY);
                    botao.setOpaque(true);
                    botao.addActionListener(e -> abrirCaixa(linha, coluna));
                    grade.add(botao);
                }
            }
            grade.revalidate();
            grade.repaint();
        } catch (Exception e) {
            JOptionPaneUtil.erro(e.getMessage());
        }
    }

    private void abrirCaixa(int linha, int coluna) {
        try {
            Caixa caixa = caixaDAO.consultar(1, linha, coluna);
            if (!manutencao) {
                JOptionPane.showMessageDialog(this, "Posição: " + linha + ", " + coluna
                        + "\nNome: " + caixa.getNome() + "\nInformação: " + caixa.getInformacao(),
                        "Detalhes da caixa", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            JTextField nome = new JTextField(caixa.getNome(), 25);
            JTextArea informacao = new JTextArea(caixa.getInformacao(), 8, 25);
            JPanel painel = new JPanel(new BorderLayout(5, 5));
            JPanel campos = new JPanel(new GridLayout(2, 1, 5, 5));
            campos.add(new JLabel("Nome (máximo 100 caracteres):"));
            campos.add(nome);
            painel.add(campos, BorderLayout.NORTH);
            painel.add(new JScrollPane(informacao), BorderLayout.CENTER);

            Object[] opcoes = {"Salvar", "Apagar", "Cancelar"};
            int escolha = JOptionPane.showOptionDialog(this, painel, "Caixa " + linha + "," + coluna,
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

            if (escolha == 0) {
                if (nome.getText().length() > 100) {
                    JOptionPane.showMessageDialog(this, "Nome máximo: 100 caracteres.");
                    return;
                }
                caixa.setNome(nome.getText());
                caixa.setInformacao(informacao.getText());
                caixaDAO.salvar(caixa);
            } else if (escolha == 1) {
                caixaDAO.apagar(1, linha, coluna);
            }
            atualizarGrade();
        } catch (Exception e) {
            JOptionPaneUtil.erro(e.getMessage());
        }
    }

    private void pesquisar(String texto) {
        encontrados.clear();
        if (texto != null && !texto.trim().isEmpty()) {
            List<Caixa> lista = caixaDAO.pesquisar(1, texto.trim());
            for (Caixa caixa : lista) encontrados.add(chave(caixa.getLinha(), caixa.getColuna()));
            JOptionPane.showMessageDialog(this, lista.size() + " caixa(s) encontrada(s).");
        }
        atualizarGrade();
    }

    private void alterarTamanho() {
        JTextField linhas = new JTextField(String.valueOf(matriz.getLinhas()));
        JTextField colunas = new JTextField(String.valueOf(matriz.getColunas()));
        JPanel painel = new JPanel(new GridLayout(2, 2, 5, 5));
        painel.add(new JLabel("Linhas (1-100):"));
        painel.add(linhas);
        painel.add(new JLabel("Colunas (1-100):"));
        painel.add(colunas);

        if (JOptionPane.showConfirmDialog(this, painel, "Tamanho da matriz",
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            try {
                matrizDAO.atualizarTamanho(Integer.parseInt(linhas.getText()),
                        Integer.parseInt(colunas.getText()));
                atualizarGrade();
            } catch (Exception e) {
                JOptionPaneUtil.erro(e.getMessage());
            }
        }
    }

    private void alterarSenha() {
        JPasswordField senha1 = new JPasswordField();
        JPasswordField senha2 = new JPasswordField();
        JPanel painel = new JPanel(new GridLayout(2, 2, 5, 5));
        painel.add(new JLabel("Nova senha:"));
        painel.add(senha1);
        painel.add(new JLabel("Confirmar senha:"));
        painel.add(senha2);

        if (JOptionPane.showConfirmDialog(this, painel, "Alterar senha",
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            String s1 = new String(senha1.getPassword());
            String s2 = new String(senha2.getPassword());
            if (!s1.equals(s2)) {
                JOptionPane.showMessageDialog(this, "As senhas não coincidem.");
                return;
            }
            try {
                new PerfilDAO().alterarSenha(s1);
                JOptionPane.showMessageDialog(this, "Senha alterada com sucesso.");
            } catch (Exception e) {
                JOptionPaneUtil.erro(e.getMessage());
            }
        }
    }
}

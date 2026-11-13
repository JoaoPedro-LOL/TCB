public class MatrizDAO {
    public void criarMatrizInicial() {
        // A matriz 30x30 é criada ao iniciar BancoArquivo pela primeira vez.
    }

    public Matriz obter() {
        return BancoArquivo.obterDados().getMatriz();
    }

    public void atualizarTamanho(int linhas, int colunas) {
        if (linhas < 1 || linhas > 100 || colunas < 1 || colunas > 100)
            throw new IllegalArgumentException("Linhas e colunas devem estar entre 1 e 100.");
        Matriz matriz = obter();
        matriz.setLinhas(linhas);
        matriz.setColunas(colunas);
        BancoArquivo.salvar();
    }
}

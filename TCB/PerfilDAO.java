public class PerfilDAO {
    public void criarPerfilInicial() {
        // O perfil inicial é criado automaticamente em DadosSistema.
    }

    public boolean validarSenha(String senha) {
        return BancoArquivo.obterDados().getSenhaHash().equals(Seguranca.sha256(senha));
    }

    public void alterarSenha(String senha) {
        if (senha == null || senha.length() < 4)
            throw new IllegalArgumentException("A senha deve possuir pelo menos 4 caracteres.");
        BancoArquivo.obterDados().setSenhaHash(Seguranca.sha256(senha));
        BancoArquivo.salvar();
    }
}

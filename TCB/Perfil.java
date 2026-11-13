public class Perfil {
    private int id;
    private String nome;
    private String senhaHash;
    private int idMatriz;

    public Perfil(int id, String nome, String senhaHash, int idMatriz) {
        this.id = id;
        this.nome = nome;
        this.senhaHash = senhaHash;
        this.idMatriz = idMatriz;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getSenhaHash() { return senhaHash; }
    public int getIdMatriz() { return idMatriz; }
}

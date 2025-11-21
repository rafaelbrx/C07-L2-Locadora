package locadora.classes;

public class Categoria {

    private int id_categoria;
    private String nome;
    private String descricao;

    public Categoria(){}

    //Construtor INSERT (sem id)
    public Categoria(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    //Construtor SELECT (com id)
    public Categoria(int id_categoria, String nome, String descricao) {
        this.id_categoria = id_categoria;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId_categoria() { return id_categoria; }
    public void setId_categoria(int id_categoria) { this.id_categoria = id_categoria; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
        return "ID Categoria: " + id_categoria +
                "\nNome: " + nome +
                "\nDescricao: " + descricao;
    }
}

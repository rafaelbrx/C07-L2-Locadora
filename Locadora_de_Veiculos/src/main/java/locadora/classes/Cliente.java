package locadora.classes;

public class Cliente {
    private int id_cliente;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    public Cliente(){}

    //Construtor - INSERT (sem id)
    public Cliente(String nome, String cpf, String telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

    //Construtor - SELECT (com id)
    public Cliente(int id_cliente, String nome, String cpf, String telefone, String email) {
        this.id_cliente = id_cliente;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf;}

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getId() { return id_cliente; }
    public void setId(int idCliente) { this.id_cliente = idCliente; }

    @Override
    public String toString() {
        return  "ID: " + id_cliente +
                "\nNome: " + nome +
                "\nCPF: " + cpf +
                "\nTelefone: " + telefone +
                "\nEmail: " + email;
    }

}

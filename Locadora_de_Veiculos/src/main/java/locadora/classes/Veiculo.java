package locadora.classes;

public class Veiculo {

    private int id_veiculo;
    private String modelo;
    private String placa;
    private int ano_fabricacao;
    private boolean disponibilidade;

    public Veiculo(){}

    //Construtor - INSERT (sem id)
    public Veiculo(String modelo, String placa, int ano_fabricacao, boolean disponibilidade) {
        this.modelo = modelo;
        this.placa = placa;
        this.ano_fabricacao = ano_fabricacao;
        this.disponibilidade = disponibilidade;
    }

    //Construtor - SELECT (com id)
    public Veiculo(int id_veiculo, String modelo, String placa, int ano_fabricacao, boolean disponibilidade) {
        this.id_veiculo = id_veiculo;
        this.modelo = modelo;
        this.placa = placa;
        this.ano_fabricacao = ano_fabricacao;
        this.disponibilidade = disponibilidade;
    }

    public int getId_veiculo() {return id_veiculo;}
    public void setId_veiculo(int id_veiculo) {this.id_veiculo = id_veiculo;}

    public String getModelo() {return modelo;}
    public void setModelo(String modelo) {this.modelo = modelo;}

    public String getPlaca() {return placa;}
    public void setPlaca(String placa) {this.placa = placa;}

    public int getAno_fabricacao() {return ano_fabricacao;}

    public void setAno_fabricacao(int ano_fabricacao) {this.ano_fabricacao = ano_fabricacao;}

    public boolean isDisponibilidade() {return disponibilidade;}
    public void setDisponibilidade(boolean disponibilidade) {this.disponibilidade = disponibilidade;}

    @Override
    public String toString() {
        return  "ID Veículo: " + id_veiculo +
                "\nModelo: " + modelo +
                "\nPlaca: " + placa +
                "\nAno Fabricação: " + ano_fabricacao +
                "\nDisponível: " + (disponibilidade ? "Sim" : "Não");
    }

}

package locadora.classes;

public class Reserva {

    private int id_reserva;
    private String data_inicio;
    private String data_fim;
    private int duracao_reserva;
    private int idR_cliente;
    private int idR_veiculo;

    public Reserva() {}

    // Construtor INSERT (sem id)
    public Reserva(String data_inicio, String data_fim, int duracao_reserva, int idR_cliente, int idR_veiculo) {
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.duracao_reserva = duracao_reserva;
        this.idR_cliente = idR_cliente;
        this.idR_veiculo = idR_veiculo;
    }

    // Construtor SELECT (com id)
    public Reserva(int id_reserva, String data_inicio, String data_fim, int duracao_reserva, int idR_cliente, int idR_veiculo) {
        this.id_reserva = id_reserva;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.duracao_reserva = duracao_reserva;
        this.idR_cliente = idR_cliente;
        this.idR_veiculo = idR_veiculo;
    }

    public int getId_reserva() { return id_reserva; }
    public void setId_reserva(int id_reserva) { this.id_reserva = id_reserva; }

    public String getData_inicio() { return data_inicio; }
    public void setData_inicio(String data_inicio) { this.data_inicio = data_inicio; }

    public String getData_fim() { return data_fim; }
    public void setData_fim(String data_fim) { this.data_fim = data_fim; }

    public int getDuracao_reserva() { return duracao_reserva; }
    public void setDuracao_reserva(int duracao_reserva) { this.duracao_reserva = duracao_reserva; }

    public int getIdR_cliente() { return idR_cliente; }
    public void setIdR_cliente(int idR_cliente) { this.idR_cliente = idR_cliente; }

    public int getIdR_veiculo() { return idR_veiculo; }
    public void setIdR_veiculo(int idR_veiculo) { this.idR_veiculo = idR_veiculo; }

    @Override
    public String toString() {
        return  "ID Reserva: " + id_reserva +
                "\nID Cliente: " + idR_cliente +
                "\nID Veículo: " + idR_veiculo +
                "\nData Início: " + data_inicio +
                "\nData Fim: " + data_fim +
                "\nDuração: " + duracao_reserva + " dias";
    }
}

package locadora.classes;

public class Pagamento {
    private int id_pagamento;
    private double valor;
    private String forma_pagamento;
    private boolean status;
    private int idP_reserva;

    // Construtor para INSERT (sem id)
    public Pagamento(double valor, String forma_pagamento, boolean status, int idP_reserva) {
        this.valor = valor;
        this.forma_pagamento = forma_pagamento;
        this.status = status;
        this.idP_reserva = idP_reserva;
    }

    // Construtor para SELECT (com id)
    public Pagamento(int id_pagamento, double valor, String forma_pagamento, boolean status, int idP_reserva) {
        this.id_pagamento = id_pagamento;
        this.valor = valor;
        this.forma_pagamento = forma_pagamento;
        this.status = status;
        this.idP_reserva = idP_reserva;
    }

    public Pagamento() {}

    public int getId_pagamento() { return id_pagamento; }
    public void setId_pagamento(int id_pagamento) { this.id_pagamento = id_pagamento; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public String getForma_pagamento() { return forma_pagamento; }
    public void setForma_pagamento(String forma_pagamento) { this.forma_pagamento = forma_pagamento; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public int getIdP_reserva() { return idP_reserva; }
    public void setIdP_reserva(int idP_reserva) { this.idP_reserva = idP_reserva; }

    @Override
    public String toString() {
        return "ID Pagamento: " + id_pagamento +
                "\nValor: R$ " + valor +
                "\nForma de Pagamento: " + forma_pagamento +
                "\nID da Reserva: " + idP_reserva +
                "\nStatus: " + status;
    }
}
package com.example.BancTransfer.transferDTO;

public class TransferInputDTO {

    private Integer idSender;

    private Integer idReceiver;
    private Double amount;

    private Valuta valuta;

    public Valuta getValuta() {
        return valuta;
    }

    public void setValuta(Valuta valuta) {
        this.valuta = valuta;
    }

    public Integer getIdSender() {
        return idSender;
    }

    public void setIdSender(Integer idSender) {
        this.idSender = idSender;
    }

    public Integer getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(Integer idReceiver) {
        this.idReceiver = idReceiver;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransferInputDTO{" +
                "idSender=" + idSender +
                ", idReceiver=" + idReceiver +
                ", amount=" + amount +
                '}';
    }
}

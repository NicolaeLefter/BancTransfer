package com.example.BancTransfer.entity;

import jakarta.persistence.*;

@Entity
public class ContBancar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cont")

    private Integer idCont;
    @Column(name = "currency")

    private String currency;
    @Column(name = "balance")
    private Double balance;
    @Column(name = "id_person")
    private Integer idPerson;

    public Integer getIdCont() {
        return idCont;
    }

    public void setIdCont(Integer idCont) {
        this.idCont = idCont;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    @Override
    public String toString() {
        return "ContBancar{" +
                "idCont=" + idCont +
                ", currency='" + currency + '\'' +
                ", balance=" + balance +
                ", idPerson=" + idPerson +
                '}';
    }
}

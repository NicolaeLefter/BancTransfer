package com.example.BancTransfer.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "cont_bancar")
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

    public ContBancar(){

    }

    public ContBancar(Integer idCont){
        this.idCont = idCont;
    }

    public ContBancar(Integer  idCont, String currency){
        this.idCont = idCont;
        this.currency = currency;
    }

    public ContBancar(Integer idCont, String currency, Double balance, Integer idPerson) {
        this.idCont = idCont;
        this.currency = currency;
        this.balance = balance;
        this.idPerson = idPerson;
    }

    public ContBancar(String currency, Double balance, Integer idPerson) {

        this.currency = currency;
        this.balance = balance;
        this.idPerson = idPerson;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContBancar that)) return false;
        return Objects.equals(idCont, that.idCont) && Objects.equals(currency, that.currency) && Objects.equals(balance, that.balance) && Objects.equals(idPerson, that.idPerson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCont, currency, balance, idPerson);
    }
}

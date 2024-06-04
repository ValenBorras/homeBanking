package com.dh.poo.Cuenta_src;

public class Cuenta {
    private int idCuenta;
    private int idUsuario;
    private String cbu;
    private String alias;
    private double balance;
    private String tipo;

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String idCBU) {
        this.cbu = idCBU;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double saldo) {
        this.balance = saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}

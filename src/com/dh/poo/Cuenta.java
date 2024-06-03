package com.dh.poo;

public class Cuenta {
    private int idCuenta;
    private int idCBU;
    private String alias;
    private double saldo;
    private String tipo;
    private Usuario titular;

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public int getIdCBU() {
        return idCBU;
    }

    public void setIdCBU(int idCBU) {
        this.idCBU = idCBU;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Usuario getTitular() {
        return titular;
    }

    public void setTitular(Usuario titular) {
        this.titular = titular;
    }

    public Cuenta(int idCuenta, int idCBU, String alias, double saldo, String tipo, Usuario titular) {
        this.idCuenta = idCuenta;
        this.idCBU = idCBU;
        this.alias = alias;
        this.saldo = saldo;
        this.tipo = tipo;
        this.titular = titular;
    }
}

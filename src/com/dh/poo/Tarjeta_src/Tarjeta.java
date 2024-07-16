package com.dh.poo.Tarjeta_src;

public class Tarjeta {
    private int idTarjeta;
    private String numeroTarjeta;
    private int cvv;
    private double disponible;
    private int idCuentaADebitar;
    private int idUsuario;

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public double getDisponible() {
        return disponible;
    }

    public void setDisponible(double disponible) {
        this.disponible = disponible;
    }

    public int getIdCuentaADebitar() {
        return idCuentaADebitar;
    }

    public void setIdCuentaADebitar(int idCuentaADebitar) {
        this.idCuentaADebitar = idCuentaADebitar;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}

package com.dh.poo;

import java.sql.Timestamp;
import java.time.LocalDate;

public abstract class Tarjeta {
    private int tarjetaID;
    private long numeroTarjeta;
    private int cvv;
    private LocalDate fechaVencimiento;
    private int idUsuario;

    public void pagarConTarjeta(double monto){};

    public int getTarjetaID() {
        return tarjetaID;
    }

    public void setTarjetaID(int tarjetaID) {
        this.tarjetaID = tarjetaID;
    }

    public long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}

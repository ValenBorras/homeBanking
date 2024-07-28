package com.dh.poo.MovimientoTarjeta_src;


import java.time.LocalDate;

public class MovimientoTarjeta {
    private int idMovimientoTarjeta;
    private long numeroTarjeta;
    private LocalDate fecha;
    private double monto;

    public int getIdMovimientoTarjeta() {
        return idMovimientoTarjeta;
    }

    public void setIdMovimientoTarjeta(int idMovimientoTarjeta) {
        this.idMovimientoTarjeta = idMovimientoTarjeta;
    }

    public long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}

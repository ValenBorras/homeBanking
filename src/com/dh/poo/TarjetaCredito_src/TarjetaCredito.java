package com.dh.poo.TarjetaCredito_src;

import com.dh.poo.Tarjeta_src.Tarjeta;

import java.sql.Timestamp;

public class TarjetaCredito extends Tarjeta {
    private double aPagar;
    private Timestamp fechaCierre;

    public double getSaldoAPagar() {
        return aPagar;
    }

    public void setSaldoAPagar(double saldoAPagar) {
        this.aPagar = saldoAPagar;
    }
}

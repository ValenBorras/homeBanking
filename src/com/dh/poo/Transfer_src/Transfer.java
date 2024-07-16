package com.dh.poo.Transfer_src;

import java.sql.Timestamp;

public class Transfer {

    private int idTransfer;
    private String aliasDesde;
    private String aliasHasta;
    private Timestamp fecha;
    private double monto;

    public int getIdTransfer() {
        return idTransfer;
    }

    public void setIdTransfer(int idTransfer) {
        this.idTransfer = idTransfer;
    }

    public String getAliasDesde() {
        return aliasDesde;
    }

    public void setAliasDesde(String aliasDesde) {
        this.aliasDesde = aliasDesde;
    }

    public String getAliasHasta() {
        return aliasHasta;
    }

    public void setAliasHasta(String aliasHasta) {
        this.aliasHasta = aliasHasta;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}

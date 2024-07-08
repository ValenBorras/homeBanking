package com.dh.poo.Transferencia_src;

public class Transferencia {

    private int idTransfer;
    private int idCDesde;
    private int idCHasta;
    private int fecha;
    private int monto;

    public int getIdTransfer() {
        return idTransfer;
    }

    public void setIdTransfer(int idTransfer) {
        this.idTransfer = idTransfer;
    }

    public int getIdCDesde() {
        return idCDesde;
    }

    public void setIdCDesde(int idCDesde) {
        this.idCDesde = idCDesde;
    }

    public int getIdCHasta() {
        return idCHasta;
    }

    public void setIdCHasta(int idCHasta) {
        this.idCHasta = idCHasta;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }
}

package com.dh.poo.TarjetaDebito_src;

import com.dh.poo.Cuenta_src.Cuenta;
import com.dh.poo.Cuenta_src.CuentaService;
import com.dh.poo.ServiceException;
import com.dh.poo.Tarjeta;
import com.dh.poo.Transfer_src.Transfer;
import com.dh.poo.Transfer_src.TransferService;
import front.PanelManager;

import javax.swing.*;

public class TarjetaDebito extends Tarjeta {
    private int idCuenta;

    public void pagarConTarjeta(double monto) {
        CuentaService cuentaService = new CuentaService();
        TransferService transferService = new TransferService();
        Cuenta cuenta;
        PanelManager panelManager = new PanelManager();

        try {
            cuenta = cuentaService.read(this.idCuenta);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        if(monto < cuenta.getBalance()){
            try {
                cuentaService.debitar(cuenta, monto);
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
            Transfer transferencia = new Transfer();
            transferencia.setAliasDesde(cuenta.getAlias());
            transferencia.setAliasHasta("tiendaX");
            transferencia.setMonto(monto);

            try {
                transferService.create(transferencia);
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }

        }else{
            JOptionPane.showMessageDialog(panelManager.getFrame(), "Saldo insuficiente en cuenta", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }



}

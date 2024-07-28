package com.dh.poo.TarjetaCredito_src;

import com.dh.poo.Cuenta_src.Cuenta;
import com.dh.poo.Cuenta_src.CuentaService;
import com.dh.poo.MovimientoTarjeta_src.MovimientoTarjeta;
import com.dh.poo.MovimientoTarjeta_src.MovimientoTarjetaService;
import com.dh.poo.ServiceException;
import com.dh.poo.Tarjeta;
import front.PanelManager;

import javax.swing.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class TarjetaCredito extends Tarjeta {
    private double disponible;
    private double aPagar;
    private LocalDate fechaCierre;

    public void pagarConTarjeta(double monto){
        PanelManager panelManager = new PanelManager();
        TarjetaCreditoService creditoService = new TarjetaCreditoService();
        MovimientoTarjetaService movimientoService = new MovimientoTarjetaService();
        if(this.disponible < monto){
            JOptionPane.showMessageDialog(panelManager.getFrame(), "Ese monto sobrepasa su limite de credito.", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            this.setDisponible(this.disponible - monto);
            this.setaPagar(this.aPagar + monto);
            try {
                creditoService.update(this);
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }

            MovimientoTarjeta movimiento = new MovimientoTarjeta();
            movimiento.setNumeroTarjeta(this.getNumeroTarjeta());
            movimiento.setMonto(monto);

            try {
                movimientoService.create(movimiento);
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void cobrarTarjeta() {
        PanelManager panelManager = new PanelManager();
        CuentaService cuentaService = new CuentaService();
        TarjetaCreditoService creditoService = new TarjetaCreditoService();
        List<Cuenta> cuentas;
        Cuenta cuentaACobrar = new Cuenta();

        try {
            cuentas = cuentaService.readAllFromUserID(this.getIdUsuario());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        double max;
        max = 0;

        for(Cuenta cuenta : cuentas){
            if(cuenta.getBalance() > max ){
                max = cuenta.getBalance();
                cuentaACobrar = cuenta;
            }
        }

        if(this.aPagar > cuentaACobrar.getBalance()){
            JOptionPane.showMessageDialog(panelManager.getFrame(), "No hay dinero suficiente para pagar la tarjeta en ninguna de las cuentas del usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            try {
                cuentaService.debitar(cuentaACobrar,this.aPagar );
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }

            this.disponible += this.aPagar;
            this.aPagar = 0;

            try {
                creditoService.update(this);
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }

            JOptionPane.showMessageDialog(panelManager.getFrame(), "Se ha cobrado la tarjeta con exito.", "Exito", JOptionPane.PLAIN_MESSAGE);
        }

    }
    public double getDisponible() {
        return disponible;
    }

    public void setDisponible(double disponible) {
        this.disponible = disponible;
    }

    public double getaPagar() {
        return aPagar;
    }

    public void setaPagar(double aPagar) {
        this.aPagar = aPagar;
    }

    public LocalDate getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDate fechaCierre) {
        this.fechaCierre = fechaCierre;
    }
    }

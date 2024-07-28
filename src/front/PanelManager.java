package front;

import javax.swing.*;

import com.dh.poo.Cuenta_src.Cuenta;
import com.dh.poo.Tarjeta;
import com.dh.poo.User_src.Usuario;

import java.awt.*;
import java.util.List;

public class PanelManager {
    private JFrame frame;
    private PanelUsers panelUsers;
    private PanelCrearUsuario panelCrearUsuario;
    private PortalUsuario portalUsuario;
    private PanelCrearCuenta panelCrearCuenta;
    private PanelUpdateCuenta panelUpdateCuenta;
    private PanelUpdateUsuario panelUpdateUsuario;
    private PanelAdmin panelAdmin;
    private PanelTransferir panelTransferir;
    private PanelDepositar panelDepositar;
    private PanelTarjetas panelTarjetas;
    private PanelCuentas panelCuentas;
    private PanelCrearTarjeta panelCrearTarjeta;
    private PanelCrearTarjetaDebito panelCrearTarjetaDebito;
    private PanelPagarConTarjeta panelPagarConTarjeta;
    private PanelResumenMovimientos panelResumenMovimientos;
    private PanelReporteMes panelReporteMes;

    public PanelManager(){}

    public void armarManager(){

        frame = new JFrame();
        //frame.setBounds(100,100,500,500);

        panelUsers = new PanelUsers(this);
        panelCrearUsuario = new PanelCrearUsuario(this);
        panelCrearUsuario.armarPanelCrearUsuario();
        portalUsuario = new PortalUsuario(this);
        panelCrearCuenta = new PanelCrearCuenta(this);
        panelUpdateCuenta = new PanelUpdateCuenta(this);
        panelUpdateUsuario = new PanelUpdateUsuario(this);
        panelAdmin = new PanelAdmin(this);
        panelTransferir = new PanelTransferir(this);
        panelDepositar = new PanelDepositar(this);
        panelTarjetas = new PanelTarjetas(this);
        panelCuentas = new PanelCuentas(this);
        panelCrearTarjeta = new PanelCrearTarjeta(this);
        panelCrearTarjetaDebito = new PanelCrearTarjetaDebito(this);
        panelPagarConTarjeta = new PanelPagarConTarjeta(this);
        panelResumenMovimientos = new PanelResumenMovimientos(this);
        panelReporteMes = new PanelReporteMes(this);

    }
    public void showFrame() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public void mostrarSalir() {
        int response = JOptionPane.showConfirmDialog(frame,"Esta seguro?");
        if( response == JOptionPane.OK_OPTION){
            System.exit(0);

        }
    }
    public void mostrarPanelUsers(){
        panelUsers.armarPanelUsers();
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelUsers);
        frame.getContentPane().validate();
        frame.pack();
        frame.getContentPane().repaint();
    }
    public void mostrarPanelCrearUsuario(){
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelCrearUsuario);
        frame.getContentPane().validate();
        frame.pack();
        frame.getContentPane().repaint();
    }
    public void mostrarPanelUpdateUsuario(Usuario usuario){
        panelUpdateUsuario.armarPanelUpdateUsuario(usuario);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelUpdateUsuario);
        frame.getContentPane().validate();
        frame.pack();
        frame.getContentPane().repaint();
    }
    public void mostrarPortalUsuario(int id, boolean admin){
        portalUsuario.armarPortalUsuario(id,admin);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(portalUsuario);
        frame.getContentPane().validate();
        frame.pack();
        frame.getContentPane().repaint();
    }
    public void mostrarPanelCrearCuenta(int id){
        panelCrearCuenta.armarPanelCrearCuenta(id);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelCrearCuenta);
        frame.getContentPane().validate();
        frame.pack();
        frame.getContentPane().repaint();
    }
    public void mostrarPanelUpdateCuenta(Cuenta cuenta){
        panelUpdateCuenta.armarPanelUpdateCuenta(cuenta);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelUpdateCuenta);
        frame.getContentPane().validate();
        frame.pack();
        frame.getContentPane().repaint();
    }
    public void mostrarPanelAdmin(){
        panelAdmin.armarPanelAdmin();
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelAdmin);
        frame.getContentPane().validate();
        frame.pack();
        frame.getContentPane().repaint();
    }
    public void mostrarPanelTransferir(Usuario usuario){
        panelTransferir.armarPanelTransferir(usuario);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelTransferir);
        frame.getContentPane().validate();
        frame.pack();
        frame.getContentPane().repaint();
    }
    public void mostrarPanelDepositar(Usuario usuario){
        panelDepositar.armarPanelDepositar(usuario);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelDepositar);
        frame.getContentPane().validate();
        frame.pack();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelTarjetas(Usuario usuario, Boolean admin){
        panelTarjetas.armarPanelTarjetas(usuario, admin);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelTarjetas);
        frame.getContentPane().validate();
        JScrollPane scrollPane = new JScrollPane(panelTarjetas, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollPane);
        frame.pack();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelCuentas(Usuario usuario, Boolean admin){
        panelCuentas.armarPanelCuentas(usuario, admin);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelCuentas);
        frame.getContentPane().validate();
        JScrollPane scrollPane = new JScrollPane(panelCuentas, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(400, 500));
        frame.add(scrollPane);
        frame.pack();
        frame.getContentPane().repaint();
    }
    public void mostrarPanelCrearTarjeta(Usuario usuario){
        panelCrearTarjeta.armarPanelCrearTarjeta(usuario);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelCrearTarjeta);
        frame.getContentPane().validate();
        frame.pack();
        frame.getContentPane().repaint();
    }
    public void mostrarPanelCrearTarjetaDebito(Usuario usuario){
        panelCrearTarjetaDebito.armarPanelCrearTarjetaDebito(usuario);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelCrearTarjetaDebito);
        frame.getContentPane().validate();
        frame.pack();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelPagarConTarjeta(Usuario usuario){
        panelPagarConTarjeta.armarPanelPagarConTarjeta(usuario);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelPagarConTarjeta);
        frame.getContentPane().validate();
        frame.pack();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelResumenMovimientos(Usuario usuario, Boolean admin){
        panelResumenMovimientos.armarPanelResumenMovimientos(usuario, admin);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelResumenMovimientos);
        frame.getContentPane().validate();
        JScrollPane scrollPane = new JScrollPane(panelResumenMovimientos, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(400, 500));
        frame.add(scrollPane);
        frame.pack();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelReporteMes(Tarjeta tarjeta, int mes, Usuario usuario, Boolean admin){
        panelReporteMes.armarPanelReporteMes(tarjeta, mes, usuario, admin);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelReporteMes);
        frame.getContentPane().validate();
        JScrollPane scrollPane = new JScrollPane(panelReporteMes, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(400, 500));
        frame.add(scrollPane);
        frame.pack();
        frame.getContentPane().repaint();
    }


    public JFrame getFrame() {
        return frame;
    }
}
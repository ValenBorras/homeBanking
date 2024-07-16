package front;

import javax.swing.*;

import com.dh.poo.Cuenta_src.Cuenta;
import com.dh.poo.User_src.Usuario;
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

    }
    public void showFrame() {
        frame.setVisible(true); frame.pack(); ;
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



    public JFrame getFrame() {
        return frame;
    }
}
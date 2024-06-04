package front;

import javax.swing.*;

import com.dh.poo.User_src.Usuario;
import java.util.List;

public class PanelManager {
    private JFrame frame;
    private PanelUsers panelUsers;
    private PanelCrearUsuario panelCrearUsuario;
    private PortalUsuario portalUsuario;
    private PanelCrearCuenta panelCrearCuenta;

    public PanelManager(){}

    public void armarManager(){

        frame = new JFrame();
        frame.setBounds(100,100,500,500);

        panelUsers = new PanelUsers(this);

        panelCrearUsuario = new PanelCrearUsuario(this);
        panelCrearUsuario.armarPanelCrearUsuario();

        portalUsuario = new PortalUsuario(this);

        panelCrearCuenta = new PanelCrearCuenta(this);

    }
    public void showFrame() {
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
        frame.getContentPane().repaint();
    }

    public void mostrarPanelCrearUsuario(){
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelCrearUsuario);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPortalUsuario(int id){
        portalUsuario.armarPortalUsuario(id);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(portalUsuario);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelCrearCuenta(int id){
        panelCrearCuenta.armarPanelCrearCuenta(id);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelCrearCuenta);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public JFrame getFrame() {
        return frame;
    }
}
package front;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class PanelManager {
    private JFrame frame;
    private PanelUsers panelUsers;
    private PanelCrearUsuario panelCrearUsuario;
    private PortalUsuario portalUsuario;

    public PanelManager(){}

    public void armarManager(){
        frame = new JFrame();
        frame.setBounds(100,100,500,500);

        panelUsers = new PanelUsers(this);
        panelUsers.armarPanelUsers();

        panelCrearUsuario = new PanelCrearUsuario(this);
        panelCrearUsuario.armarPanelCrearUsuario();

        portalUsuario = new PortalUsuario(this);
        portalUsuario.armarPortalUsuario();

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

    public void mostrarPortalUsuario(){
        frame.getContentPane().removeAll();
        frame.getContentPane().add(portalUsuario);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
        portalUsuario.updateUI();
    }

    public JFrame getFrame() {
        return frame;
    }
}
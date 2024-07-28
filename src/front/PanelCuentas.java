package front;

import com.dh.poo.Cuenta_src.Cuenta;
import com.dh.poo.Cuenta_src.CuentaService;
import com.dh.poo.ServiceException;
import com.dh.poo.User_src.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.SwingConstants.WEST;

public class PanelCuentas extends JPanel {
    private PanelManager panelManager;
    private JButton boton;
    private JLabel label;
    private List<Cuenta> cuentas = new ArrayList<>();


    public PanelCuentas(PanelManager m){this.panelManager = m;}


    public PanelManager getPanelManager() {
        return panelManager;
    }

    public void armarPanelCuentas(Usuario usuario, Boolean admin){
        CuentaService cuentaService = new CuentaService();

        this.removeAll();
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5,5,  5);


        try{
            cuentas = cuentaService.readAllFromUserID(usuario.getIdUsuario());
        }catch(ServiceException e){
            e.printStackTrace();
        }

        label = new JLabel("Cuentas:");
        constraints.gridx = 0;
        constraints.gridy++;
        add(label, constraints);

        if(cuentas.isEmpty()){
            label = new JLabel("El usuario no tiene cuentas");
            constraints.gridx = 0;
            constraints.gridy++;
            add(label, constraints);
        }

        for(Cuenta cuenta:cuentas){
            label = new JLabel("<html><b> CBU:</b></html>");
            constraints.gridx = 0;
            constraints.gridy++;
            add(label,constraints);

            label = new JLabel(String.valueOf(cuenta.getCbu()));
            constraints.gridx = 1;
            add(label,constraints);

            label = new JLabel("<html><b> Alias:</b></html>");
            constraints.gridx = 0;
            constraints.gridy++;
            add(label,constraints);

            label = new JLabel(cuenta.getAlias());
            constraints.gridx = 1;
            add(label,constraints);

            label = new JLabel("<html><b> Balance:</b></html>");
            constraints.gridx = 0;
            constraints.gridy++;
            add(label,constraints);

            label = new JLabel(String.valueOf(cuenta.getBalance()));
            constraints.gridx = 1;
            add(label,constraints);

            label = new JLabel("<html><b> Tipo:</b></html>");
            constraints.gridx = 0;
            constraints.gridy++;
            add(label,constraints);

            label = new JLabel(cuenta.getTipo());
            constraints.gridx = 1;
            add(label,constraints);

            if(admin){
                //Eliminar Cuenta
                boton = new JButton("Eliminar Cuenta");
                constraints.gridx = 0;
                constraints.gridy++;
                boton.setForeground(Color.red);
                add(boton, constraints);
                boton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try{
                            cuentaService.delete(cuenta.getIdCuenta());

                        }catch(ServiceException er){
                            er.printStackTrace();
                        }
                        panelManager.mostrarPanelUsers();
                    }
                });
                //Editar Cuenta
                boton = new JButton("Editar Cuenta");
                constraints.gridx = 1;
                boton.setForeground(Color.blue);
                add(boton, constraints);
                boton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panelManager.mostrarPanelUpdateCuenta(cuenta);
                    }

                });

            }
            label = new JLabel("------------");
            constraints.gridx = 0;
            constraints.gridy++;
            add(label,constraints);
        }

        //Boton volver
        JButton backButton = new JButton("Volver");
        constraints.gridx = 0;
        constraints.gridy++;
        backButton.setForeground(Color.blue);
        add(backButton, constraints);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPortalUsuario(usuario.getIdUsuario(), admin);
            }
        });

        if(admin) {
            //Boton crearCuenta
            JButton botonCrearCuenta = new JButton("Crear Cuenta");
            constraints.gridx = 1;
            botonCrearCuenta.setForeground(Color.blue);
            add(botonCrearCuenta, constraints);

            botonCrearCuenta.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panelManager.mostrarPanelCrearCuenta(usuario.getIdUsuario());
                }
            });
        }


    }

}
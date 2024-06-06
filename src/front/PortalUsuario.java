package front;

import com.dh.poo.Cuenta_src.Cuenta;
import com.dh.poo.Cuenta_src.CuentaService;
import com.dh.poo.ServiceException;
import com.dh.poo.User_src.UsuarioService;
import com.dh.poo.User_src.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PortalUsuario extends JPanel{
    private PanelManager panelManager;
    private JLabel label;
    private JButton button;
    private Usuario user;
    private List<Cuenta> cuentas;
    public PortalUsuario(PanelManager m){this.panelManager = m;}

    public void armarPortalUsuario(int id){
        UsuarioService userService = new UsuarioService();
        CuentaService cuentaService = new CuentaService();
        this.removeAll();

        try{
            user = userService.read(id);
        }catch(ServiceException e){
            e.printStackTrace();
        }

        try{
            cuentas = cuentaService.readAllFromUserID(id);
        }catch(ServiceException e){
            e.printStackTrace();
        }

        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        label = new JLabel("<html><b> Portal de " + user.getNombre() + "</b></html>");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(label, constraints);

        label = new JLabel("Cuentas:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(label, constraints);

        if(cuentas.isEmpty()){
            label = new JLabel("El usuario no tiene cuentas");
            constraints.gridx = 0;
            constraints.gridy = 2;
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

            //Eliminar Cuenta
            button = new JButton("Eliminar Cuenta");
            constraints.gridx = 0;
            constraints.gridy++;
            add(button, constraints);
            button.addActionListener(new ActionListener() {
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
            button = new JButton("Editar Cuenta");
            constraints.gridx = 1;
            add(button, constraints);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panelManager.mostrarPanelUpdateCuenta(cuenta);
                }

            });

            label = new JLabel("------------");
            constraints.gridx = 0;
            constraints.gridy++;
            add(label,constraints);
        }

        //Boton volver
        JButton backButton = new JButton("Volver");
        constraints.gridx = 0;
        constraints.gridy++;
        add(backButton, constraints);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelUsers();
            }
        });

        //Boton crearCuenta
        JButton botonCrearCuenta = new JButton("Crear Cuenta");
        constraints.gridx = 1;
        add(botonCrearCuenta, constraints);

        botonCrearCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelCrearCuenta(user.getIdUsuario());
            }
        });

        JButton deleteButton = new JButton("Eliminar usuario");
        constraints.gridx = 0;
        constraints.gridy++;
        add(deleteButton, constraints);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    userService.delete(user.getIdUsuario());
                    panelManager.mostrarPanelUsers();
                }catch(ServiceException er){
                    er.printStackTrace();
                }
            }
        });

        JButton updateButton = new JButton("Editar Usuario");
        constraints.gridx = 1;
        add(updateButton, constraints);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelUpdateUsuario(user);
            }
        });

    }

}

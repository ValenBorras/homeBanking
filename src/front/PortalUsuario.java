package front;

import com.dh.poo.Cuenta_src.Cuenta;
import com.dh.poo.Cuenta_src.CuentaService;
import com.dh.poo.ServiceException;
import com.dh.poo.User_src.UsuarioService;
import com.dh.poo.User_src.Usuario;

import javax.swing.*;
import javax.swing.border.LineBorder;
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

    public void armarPortalUsuario(int id, boolean admin){
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
        constraints.insets = new Insets(5, 20, 5, 20);

        label = new JLabel("<html><b> Portal de " + user.getNombre() + "</b></html>");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(label, constraints);

        button = new JButton("Depositar");
        constraints.gridx = 0;
        constraints.gridy++;
        add(button,constraints);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelDepositar(user);
            }
        });


        button = new JButton("Transferir");
        constraints.gridx = 0;
        constraints.gridy++;
        add(button,constraints);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelTransferir(user);
            }
        });

        button = new JButton("Tarjetas");
        constraints.gridx = 0;
        constraints.gridy++;
        add(button,constraints);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelTarjetas(user, admin);
            }
        });

        button = new JButton("Cuentas");
        constraints.gridx = 0;
        constraints.gridy++;
        add(button,constraints);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelCuentas(user, admin);

            }
        });

        JButton mostrarResumenUsuario = new JButton("Ver resumen");
        constraints.gridx = 0;
        constraints.gridy++;
        add(mostrarResumenUsuario, constraints);

        mostrarResumenUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelResumenMovimientos(user, admin);
            }
        });

        if(admin){
            //Boton eliminar Usuario
            JButton deleteButton = new JButton("Eliminar usuario");
            constraints.gridx = 0;
            constraints.gridy++;
            deleteButton.setForeground(Color.red);
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

            //Boton editar usuario
            JButton updateButton = new JButton("Editar Usuario");
            constraints.gridx = 0;
            constraints.gridy++;
            updateButton.setForeground(Color.blue);
            add(updateButton, constraints);
            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panelManager.mostrarPanelUpdateUsuario(user);
                }
            });


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
                panelManager.mostrarPanelUsers();
            }
        });

    }

}

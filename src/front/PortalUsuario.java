package front;

import com.dh.poo.ServiceException;
import com.dh.poo.UsuarioService;
import com.dh.poo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PortalUsuario extends JPanel{
    private PanelManager panelManager;
    private JLabel label;
    private Usuario user;
    public PortalUsuario(PanelManager m){this.panelManager = m;}

    public void armarPortalUsuario(int id){
        UsuarioService userService = new UsuarioService();
        this.removeAll();

        try{
            user = userService.read(id);
        }catch(ServiceException e){
            e.printStackTrace();
        }

        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        label = new JLabel(user.getNombre());
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(label);

        //Boton volver
        JButton backButton = new JButton("Volver");
        constraints.gridx = 0;
        constraints.gridy = 4;
        add(backButton, constraints);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelUsers();
            }
        });


    }

}

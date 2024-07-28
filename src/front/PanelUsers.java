package front;

import com.dh.poo.ServiceException;
import com.dh.poo.TarjetaCredito_src.TarjetaCredito;
import com.dh.poo.TarjetaCredito_src.TarjetaCreditoService;
import com.dh.poo.TarjetaDebito_src.TarjetaDebito;
import com.dh.poo.TarjetaDebito_src.TarjetaDebitoService;
import com.dh.poo.User_src.Usuario;
import com.dh.poo.User_src.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PanelUsers extends JPanel {
    private PanelManager panelManager;
    private JButton boton;
    private JLabel label;
    private List<Usuario> users = new ArrayList<>();

    public PanelUsers(PanelManager m){this.panelManager = m;}


    public PanelManager getPanelManager() {
        return panelManager;
    }

    public void armarPanelUsers(){
        UsuarioService userService = new UsuarioService();
        TarjetaDebitoService tarjetaDS = new TarjetaDebitoService();
        TarjetaCreditoService tarjetaCs = new TarjetaCreditoService();
        this.removeAll();

        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 15, 5, 15);

        try{
            users = userService.readAll();
        }catch(ServiceException e){
            e.printStackTrace();
        }

        label = new JLabel("Elegir Usuario");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(label, constraints);

        if (users.isEmpty()){
            label = new JLabel("No hay usuarios registrados");
            constraints.gridx = 0;
            constraints.gridy = 1;

            add(label, constraints);
        }

        for(Usuario user: users){
            boton = new JButton(user.getNombre());
            constraints.gridx = 0;
            constraints.gridy++;
            add(boton, constraints);

            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int id = user.getIdUsuario();
                    if(user.isAdmin()){
                        panelManager.mostrarPanelAdmin();
                    }else{
                        panelManager.mostrarPortalUsuario(id,false);
                    }
                }

            });

        }

    }

}

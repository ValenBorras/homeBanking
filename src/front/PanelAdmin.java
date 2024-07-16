package front;

import com.dh.poo.ServiceException;
import com.dh.poo.User_src.Usuario;
import com.dh.poo.User_src.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PanelAdmin extends JPanel {
    private PanelManager panelManager;
    private JButton boton;
    private JLabel label;
    private List<Usuario> users = new ArrayList<>();

    public PanelAdmin(PanelManager m){this.panelManager = m;}


    public PanelManager getPanelManager() {
        return panelManager;
    }

    public void armarPanelAdmin(){
        UsuarioService userService = new UsuarioService();
        this.removeAll();

        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);


        try{
            users = userService.readAll();
        }catch(ServiceException e){
            e.printStackTrace();
        }

        label = new JLabel("Usuarios en sistema:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(label, constraints);

        if (users.size() == 1){
            label = new JLabel("No hay usuarios registrados");
            constraints.gridx = 0;
            constraints.gridy = 1;

            add(label, constraints);
        }

        for(Usuario user: users){
            if(!user.isAdmin()){
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
                            panelManager.mostrarPortalUsuario(id, true);
                        }
                    }

                });
            }
        }


        boton = new JButton("Crear usuario");
        constraints.gridx = 0;
        constraints.gridy++;

        add(boton, constraints);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelCrearUsuario();
            }
        });

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

    }

}

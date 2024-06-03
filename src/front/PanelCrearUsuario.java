package front;

import com.dh.poo.ServiceException;
import com.dh.poo.Usuario;
import com.dh.poo.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PanelCrearUsuario extends JPanel{
    private PanelManager panelManager;
    public PanelCrearUsuario(PanelManager m){this.panelManager = m;}

    public void armarPanelCrearUsuario(){
        UsuarioService userService = new UsuarioService();

        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        //Field nombre
        JLabel nameLabel = new JLabel("Nombre:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(nameLabel, constraints);

        JTextField nameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(nameField, constraints);

        //Field email
        JLabel emailLabel = new JLabel("Email:");
        constraints.gridx = 0;
        constraints.gridy = 1 ;
        add(emailLabel, constraints);

        JTextField emailField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(emailField, constraints);

        //Password field
        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(passwordLabel, constraints);

        JTextField passwordField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(passwordField, constraints);

        //Boton volver
        JButton backButton = new JButton("Volver");
        constraints.gridx = 0;
        constraints.gridy = 4;
        add(backButton, constraints);

        //Boton Confirmar
        JButton submitButton = new JButton("Confirmar");
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        add(submitButton, constraints);

        //Listener enviar.
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelUsers();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String password = passwordField.getText();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(panelManager.getFrame(), "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {

                    Usuario user = new Usuario();
                    user.setNombre(name);
                    user.setEmail(email);
                    user.setPassword(password);

                    try{
                        userService.create(user);
                    }catch(ServiceException er){
                        er.printStackTrace();
                    }

                    nameField.setText("");
                    emailField.setText("");
                    passwordField.setText("");

                    panelManager.mostrarPanelUsers();
                }
            }
        });

    }
}

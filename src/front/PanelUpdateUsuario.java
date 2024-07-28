package front;

import com.dh.poo.ServiceException;
import com.dh.poo.User_src.Usuario;
import com.dh.poo.User_src.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelUpdateUsuario extends JPanel{
    private PanelManager panelManager;
    public PanelUpdateUsuario(PanelManager m){this.panelManager = m;}

    public void armarPanelUpdateUsuario(Usuario usuario){
        UsuarioService userService = new UsuarioService();
        this.removeAll();

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
        backButton.setForeground(Color.blue);
        add(backButton, constraints);

        //Boton Confirmar
        JButton submitButton = new JButton("Confirmar");
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        submitButton.setForeground(Color.green);
        constraints.fill = GridBagConstraints.NONE;
        add(submitButton, constraints);

        nameField.setText(usuario.getNombre());
        emailField.setText(usuario.getEmail());
        passwordField.setText(usuario.getPassword());

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

                    Usuario userU = new Usuario();
                    userU.setIdUsuario(usuario.getIdUsuario());
                    userU.setNombre(name);
                    userU.setEmail(email);
                    userU.setPassword(password);

                    try{
                        userService.update(userU);
                    }catch(ServiceException er){
                        er.printStackTrace();
                    }

                    panelManager.mostrarPanelUsers();
                }
            }
        });

    }
}
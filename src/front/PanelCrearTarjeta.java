package front;


import com.dh.poo.ServiceException;
import com.dh.poo.TarjetaCredito_src.TarjetaCredito;
import com.dh.poo.TarjetaCredito_src.TarjetaCreditoService;

import com.dh.poo.User_src.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCrearTarjeta extends JPanel{
    private PanelManager panelManager;
    private JLabel label;
    public PanelCrearTarjeta(PanelManager m){this.panelManager = m;}

    public void armarPanelCrearTarjeta(Usuario usuario){
        TarjetaCreditoService creditoService = new TarjetaCreditoService();

        this.removeAll();
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        label = new JLabel("<html><b>Crear tarjeta de credito para "+ usuario.getNombre()+ "</b></html>");
        constraints.gridy = 0;
        constraints.gridx = 0;
        add(label,constraints);


        label = new JLabel("Limite de credito: ");
        constraints.gridy++;
        constraints.gridx = 0;
        add(label,constraints);

        JTextField limiteField = new JTextField(20);
        constraints.gridx = 1;
        add(limiteField, constraints);

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
        submitButton.setForeground(Color.blue);
        constraints.fill = GridBagConstraints.NONE;
        add(submitButton, constraints);

        //Listener volver.
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelUsers();
            }
        });

        //Listener confirmar
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String limite = limiteField.getText();

                if (limite.isEmpty()) {
                    JOptionPane.showMessageDialog(panelManager.getFrame(), "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {

                    TarjetaCredito tarjetaCredito = new TarjetaCredito();
                    tarjetaCredito.setIdUsuario(usuario.getIdUsuario());
                    tarjetaCredito.setDisponible(Double.parseDouble(limite));
                    tarjetaCredito.setaPagar(0);

                    try{
                        creditoService.create(tarjetaCredito);
                    }catch(ServiceException er){
                        er.printStackTrace();
                    }

                    limiteField.setText("");

                    panelManager.mostrarPortalUsuario(usuario.getIdUsuario(), true);
                }
            }
        });

    }
}

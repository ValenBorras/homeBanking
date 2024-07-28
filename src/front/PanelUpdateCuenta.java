package front;

import com.dh.poo.Cuenta_src.Cuenta;
import com.dh.poo.Cuenta_src.CuentaService;
import com.dh.poo.ServiceException;
import com.dh.poo.User_src.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelUpdateCuenta extends JPanel{
    private PanelManager panelManager;
    public PanelUpdateCuenta(PanelManager m){this.panelManager = m;}

    public void armarPanelUpdateCuenta(Cuenta cuenta){
        CuentaService cuentaService = new CuentaService();
        this.removeAll();

        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        //Field cbu
        JLabel cbuLabel = new JLabel("CBU:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(cbuLabel, constraints);

        JTextField cbuField = new JTextField(22);
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(cbuField, constraints);

        //Field alias
        JLabel aliasLabel = new JLabel("Alias:");
        constraints.gridx = 0;
        constraints.gridy = 1 ;
        add(aliasLabel, constraints);

        JTextField aliasField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(aliasField, constraints);

        //Tipo de cuenta
        JLabel tipoLabel = new JLabel("Tipo:");
        constraints.gridx = 0;
        constraints.gridy = 2 ;
        add(tipoLabel, constraints);

        String[] tipos = { "Cuenta Corriente", "Caja de Ahorro", "Caja de Ahorro en USD"};
        JComboBox<String> box = new JComboBox<>(tipos);
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(box, constraints);

        cbuField.setText(cuenta.getCbu());
        aliasField.setText(cuenta.getAlias());

        //Boton volver
        JButton backButton = new JButton("Cancelar");
        constraints.gridx = 0;
        constraints.gridy = 4;
        backButton.setForeground(Color.red);
        add(backButton, constraints);

        //Boton Confirmar
        JButton submitButton = new JButton("Actualizar");
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        submitButton.setForeground(Color.green);
        constraints.fill = GridBagConstraints.NONE;
        add(submitButton, constraints);

        //Listener Volver
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelUsers();
            }
        });

        //Listener enviar.
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cbu = cbuField.getText();
                String alias = aliasField.getText();
                String tipo = box.getItemAt(box.getSelectedIndex());

                if (cbu.isEmpty() || alias.isEmpty() || tipo.isEmpty()) {
                    JOptionPane.showMessageDialog(panelManager.getFrame(), "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {

                    Cuenta cuentaU = new Cuenta();
                    cuentaU.setIdCuenta(cuenta.getIdCuenta());
                    cuentaU.setBalance(cuenta.getBalance());
                    cuentaU.setIdUsuario(cuenta.getIdUsuario());
                    cuentaU.setCbu(cbu);
                    cuentaU.setAlias(alias);
                    cuentaU.setTipo(tipo);

                    try{
                        cuentaService.update(cuentaU);
                    }catch(ServiceException er){
                        er.printStackTrace();
                    }

                    panelManager.mostrarPanelUsers();
                }
            }
        });
        switch (cuenta.getTipo()){
            case "Cuenta Corriente": box.setSelectedIndex(0);
                break;
            case "Caja de Ahorro": box.setSelectedIndex(1);
                break;
            case "Caja de Ahorro en USD": box.setSelectedIndex(2);
                break;
        }
    }
}
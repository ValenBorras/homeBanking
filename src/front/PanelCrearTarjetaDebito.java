package front;


import com.dh.poo.Cuenta_src.Cuenta;
import com.dh.poo.Cuenta_src.CuentaService;
import com.dh.poo.ServiceException;
import com.dh.poo.TarjetaCredito_src.TarjetaCredito;
import com.dh.poo.TarjetaCredito_src.TarjetaCreditoService;

import com.dh.poo.TarjetaDebito_src.TarjetaDebito;
import com.dh.poo.TarjetaDebito_src.TarjetaDebitoService;
import com.dh.poo.User_src.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PanelCrearTarjetaDebito extends JPanel{
    private PanelManager panelManager;
    private JLabel label;
    private List<Cuenta> cuentas;
    private Cuenta cuenta;
    public PanelCrearTarjetaDebito(PanelManager m){this.panelManager = m;}

    public void armarPanelCrearTarjetaDebito(Usuario usuario){
        TarjetaDebitoService debitoService = new TarjetaDebitoService();
        CuentaService cuentaService = new CuentaService();

        this.removeAll();
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        try{
            cuentas = cuentaService.readAllFromUserID(usuario.getIdUsuario());
        }catch(ServiceException e){
            e.printStackTrace();
        }

        ArrayList<String> aliases = new ArrayList<>();
        for(Cuenta cuenta: cuentas){
            aliases.add(cuenta.getAlias());
        }
        String[] aliasesArray = aliases.toArray(new String[0]);

        label = new JLabel("<html><b>Crear tarjeta de debito para "+ usuario.getNombre()+ "</b></html>");
        constraints.gridy = 0;
        constraints.gridx = 0;
        add(label,constraints);


//DEBITO
            label = new JLabel("Seleccionar Cuenta:");
            constraints.gridx=0;
            constraints.gridy=1;
            add(label, constraints);

            JComboBox<String> aliasCuenta = new JComboBox<>(aliasesArray);
            constraints.gridx = 1;
            constraints.gridy = 1;
            add(aliasCuenta, constraints);



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
                String aliasC = aliasCuenta.getItemAt(aliasCuenta.getSelectedIndex());

                if (aliasC.isEmpty()) {
                    JOptionPane.showMessageDialog(panelManager.getFrame(), "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {

                    try{
                        cuenta = cuentaService.readFromAlias(aliasC);
                    }catch (ServiceException er){
                        er.printStackTrace();
                    }


                    TarjetaDebito tarjetaDebito = new TarjetaDebito();
                    tarjetaDebito.setIdUsuario(usuario.getIdUsuario());
                    tarjetaDebito.setIdCuenta(cuenta.getIdCuenta());

                    try{
                        debitoService.create(tarjetaDebito);
                    }catch(ServiceException er){
                        er.printStackTrace();
                    }

                    aliasCuenta.setSelectedIndex(0);

                    panelManager.mostrarPortalUsuario(usuario.getIdUsuario(), true);
                }
            }
        });

    }
}

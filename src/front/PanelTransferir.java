package front;

import com.dh.poo.Cuenta_src.Cuenta;
import com.dh.poo.Cuenta_src.CuentaService;
import com.dh.poo.ServiceException;
import com.dh.poo.Transfer_src.Transfer;
import com.dh.poo.Transfer_src.TransferService;
import com.dh.poo.User_src.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PanelTransferir extends JPanel{
    private PanelManager panelManager;
    private List<Cuenta> cuentas;
    private Cuenta cuentaDest;
    private JLabel label;
    public PanelTransferir(PanelManager m){this.panelManager = m;}

    public void armarPanelTransferir(Usuario usuario){
        CuentaService cuentaService = new CuentaService();
        TransferService transferService = new TransferService();

        this.removeAll();

        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
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

        //Field monto
        label = new JLabel("Monto:");
        constraints.gridx=0;
        constraints.gridy=0;
        add(label, constraints);

        JTextField monto = new JTextField(15);
        constraints.gridx=1;
        constraints.gridy=0;
        add(monto, constraints);

        //Selec. Cuenta
        label = new JLabel("Seleccionar Cuenta Origen:");
        constraints.gridx=0;
        constraints.gridy=1;
        add(label, constraints);

        JComboBox<String> aliasOrig = new JComboBox<>(aliasesArray);
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(aliasOrig, constraints);

        //Cuenta Destino
        label = new JLabel("Alias de cuenta destino:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(label, constraints);

        JTextField aliasDest = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(aliasDest, constraints);


        //Boton cancelar
        JButton backButton = new JButton("Cancelar");
        constraints.gridx = 0;
        constraints.gridy = 3;
        backButton.setForeground(Color.red);
        add(backButton, constraints);

        //Boton Confirmar
        JButton submitButton = new JButton("Confirmar");
        constraints.gridx = 1;
        constraints.gridy = 3;
        submitButton.setForeground(Color.green);
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        add(submitButton, constraints);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aliasO = aliasOrig.getItemAt(aliasOrig.getSelectedIndex());
                String aliasD = aliasDest.getText();
                int vMonto = Integer.parseInt(monto.getText());
                try{
                    cuentaDest = cuentaService.readFromAlias(aliasD);
                }catch (ServiceException er){
                    er.printStackTrace();
                }

                if (aliasD.isEmpty() || vMonto <= 0 || aliasO.isEmpty()) {
                    JOptionPane.showMessageDialog(panelManager.getFrame(), "Por favor, complete todos los campos. El monto no puede ser negativo ni == 0 ", "Error", JOptionPane.ERROR_MESSAGE);
                } else if(cuentaDest == null){
                    JOptionPane.showMessageDialog(panelManager.getFrame(), "No existe una cuenta con ese alias", "Error", JOptionPane.ERROR_MESSAGE);
                }else{

                    for(Cuenta cuenta: cuentas){
                        if(Objects.equals(cuenta.getAlias(), aliasO)){

                            if(cuenta.getBalance() >= vMonto){
                                try{
                                    cuentaService.debitar(cuenta, vMonto);
                                }catch (ServiceException er){
                                    er.printStackTrace();
                                }

                                try{
                                    cuentaService.depositar(cuentaDest, vMonto);
                                }catch (ServiceException er){
                                    er.printStackTrace();
                                }

                                Transfer transfer = new Transfer();
                                transfer.setMonto(vMonto);
                                transfer.setAliasDesde(aliasO);
                                transfer.setAliasHasta(aliasD);

                                try{
                                    transferService.create(transfer);
                                }catch(ServiceException er){
                                    er.printStackTrace();
                                }
                            }else{
                                JOptionPane.showMessageDialog(panelManager.getFrame(), "Saldo insuficiente", "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        }
                    }

                    monto.setText("");
                    aliasDest.setText("");
                    aliasOrig.setSelectedIndex(0);

                    panelManager.mostrarPortalUsuario(usuario.getIdUsuario(), false);
                }
            }
        });

        //Listener cancelar.
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPortalUsuario(usuario.getIdUsuario(), false);
            }
        });

    }
}
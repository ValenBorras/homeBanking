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

public class PanelDepositar extends JPanel{
    private PanelManager panelManager;
    private List<Cuenta> cuentas;
    private JLabel label;
    public PanelDepositar(PanelManager m){this.panelManager = m;}

    public void armarPanelDepositar(Usuario usuario){
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
        label = new JLabel("Seleccionar Cuenta:");
        constraints.gridx=0;
        constraints.gridy=1;
        add(label, constraints);

        JComboBox<String> aliasOrig = new JComboBox<>(aliasesArray);
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(aliasOrig, constraints);

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
                int vMonto = Integer.parseInt(monto.getText());

                if (vMonto <= 0 || aliasO.isEmpty()) {
                    JOptionPane.showMessageDialog(panelManager.getFrame(), "Por favor, complete todos los campos. El monto no puede ser negativo ni == 0 ", "Error", JOptionPane.ERROR_MESSAGE);
                }else{

                    for(Cuenta cuenta: cuentas){
                        if(Objects.equals(cuenta.getAlias(), aliasO)){
                                try{
                                    cuentaService.depositar(cuenta, vMonto);
                                }catch (ServiceException er){
                                    er.printStackTrace();
                                }

                                Transfer transfer = new Transfer();
                                transfer.setMonto(vMonto);
                                transfer.setAliasDesde("banco");
                                transfer.setAliasHasta(aliasO);

                                try{
                                    transferService.create(transfer);
                                }catch(ServiceException er){
                                    er.printStackTrace();
                                }

                        }
                    }

                    monto.setText("");
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

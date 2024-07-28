package front;

import com.dh.poo.Cuenta_src.Cuenta;
import com.dh.poo.Cuenta_src.CuentaService;
import com.dh.poo.ServiceException;
import com.dh.poo.Tarjeta;
import com.dh.poo.TarjetaCredito_src.TarjetaCredito;
import com.dh.poo.TarjetaCredito_src.TarjetaCreditoService;
import com.dh.poo.TarjetaDebito_src.TarjetaDebito;
import com.dh.poo.TarjetaDebito_src.TarjetaDebitoService;
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

public class PanelPagarConTarjeta extends JPanel{
    private PanelManager panelManager;
    private List<Cuenta> cuentas;
    private JLabel label;
    private List<Tarjeta> tarjetas = new ArrayList<>();
    public PanelPagarConTarjeta(PanelManager m){this.panelManager = m;}

    public void armarPanelPagarConTarjeta(Usuario usuario){
        TarjetaDebitoService debitoService = new TarjetaDebitoService();
        TarjetaCreditoService creditoService = new TarjetaCreditoService();


        this.removeAll();

        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        try{
            tarjetas.addAll(debitoService.readAllFromUser(usuario.getIdUsuario()));
        }catch(ServiceException e){
            e.printStackTrace();
        }

        try{
            tarjetas.addAll(creditoService.readAllFromUser(usuario.getIdUsuario()));
        }catch(ServiceException e){
            e.printStackTrace();
        }

        ArrayList<String> nrosTarj = new ArrayList<>();
        for(Tarjeta tarjeta: tarjetas){
            nrosTarj.add(String.valueOf(tarjeta.getNumeroTarjeta()));
        }
        String[] nrosTarjArray = nrosTarj.toArray(new String[0]);
        //Field monto
        label = new JLabel("Monto:");
        constraints.gridx=0;
        constraints.gridy=0;
        add(label, constraints);

        JTextField montoField = new JTextField(15);
        constraints.gridx=1;
        constraints.gridy=0;
        add(montoField, constraints);

        //Selec. Cuenta
        label = new JLabel("Seleccionar Tarjeta:");
        constraints.gridx=0;
        constraints.gridy=1;
        add(label, constraints);

        JComboBox<String> nroTarj = new JComboBox<>(nrosTarjArray);
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(nroTarj, constraints);

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

        //Listener Confirmar
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long nroTarjeta = Long.parseLong(nroTarj.getItemAt(nroTarj.getSelectedIndex()));
                double monto = Double.parseDouble(montoField.getText());

                for(Tarjeta tarjeta: tarjetas){
                    if(tarjeta.getNumeroTarjeta() == nroTarjeta){
                        tarjeta.pagarConTarjeta(monto);
                    }
                }

                montoField.setText("");
                nroTarj.setSelectedIndex(0);
                panelManager.mostrarPortalUsuario(usuario.getIdUsuario(), false);
                nrosTarj.toArray(new String[0]);
                tarjetas.clear();
            }
        });

        //Listener cancelar.
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nrosTarj.toArray(new String[0]);
                tarjetas.clear();
                panelManager.mostrarPortalUsuario(usuario.getIdUsuario(), false);
            }
        });

    }
}

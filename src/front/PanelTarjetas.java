package front;

import com.dh.poo.ServiceException;
import com.dh.poo.Tarjeta;
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

import static javax.swing.SwingConstants.WEST;

public class PanelTarjetas extends JPanel {
    private PanelManager panelManager;
    private JButton boton;
    private JLabel label;
    private List<TarjetaCredito> tCredito = new ArrayList<>();
    private List<TarjetaDebito> tDebito = new ArrayList<>();


    public PanelTarjetas(PanelManager m){this.panelManager = m;}


    public PanelManager getPanelManager() {
        return panelManager;
    }

    public void armarPanelTarjetas(Usuario usuario, Boolean admin){
        TarjetaDebitoService debitoService = new TarjetaDebitoService();
        TarjetaCreditoService creditoService = new TarjetaCreditoService();
        this.removeAll();

        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5,5,  5);


        try{
            tDebito = debitoService.readAllFromUser(usuario.getIdUsuario());
        }catch(ServiceException e){
            e.printStackTrace();
        }

        try{
            tCredito = creditoService.readAllFromUser(usuario.getIdUsuario());
        }catch(ServiceException e){
            e.printStackTrace();
        }

        boton = new JButton("Simular pago con tarjeta");
        constraints.gridy = 0;
        constraints.gridx = 0;
        add(boton, constraints);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelPagarConTarjeta(usuario);
            }
        });

        if (tCredito.isEmpty() && tDebito.isEmpty()){
            label = new JLabel("El usuario no tiene tarjetas");
            constraints.gridx = 0;
            constraints.gridy = 1;
            add(label, constraints);
        }
        if(!tCredito.isEmpty()){
            label = new JLabel("Tus tarjetas de credito: ");
            constraints.gridx = 0;
            constraints.gridy++;
            Font fontBold = new Font(label.getFont().getName(), Font.BOLD, label.getFont().getSize());
            label.setFont(fontBold);
            add(label, constraints);

            label = new JLabel("----------------");
            constraints.gridx = 0;
            constraints.gridy++;
            add(label, constraints);
        }

        for(TarjetaCredito tarjetaCredito: tCredito){

            label=new JLabel("<html><b>Nro: </b>" + String.valueOf(tarjetaCredito.getNumeroTarjeta()).replaceAll("(.{4})(?!$)", "$1 ") + "</html>");
            constraints.gridy++;
            constraints.gridx = 0;
            add(label, constraints);

            label = new JLabel("<html><b>CVV: </b>" + tarjetaCredito.getCvv() + "</html>");
            constraints.gridx = 1;
            add(label, constraints);

            label = new JLabel("<html><b>Cierra: </b>" + tarjetaCredito.getFechaCierre() + "</html>");
            constraints.gridx = 0;
            constraints.gridy++;
            add(label, constraints);
            System.out.println(tarjetaCredito.getFechaCierre());

            label = new JLabel("<html><b>Vence: </b>" + tarjetaCredito.getFechaVencimiento() + "</html>");
            constraints.gridx = 1;
            add(label, constraints);
            System.out.println(tarjetaCredito.getFechaVencimiento());

            label = new JLabel("<html><b>Disponible: </b>"+ tarjetaCredito.getDisponible() + "</html>" );
            constraints.gridx = 0;
            constraints.gridy++;
            add(label,constraints);

            label = new JLabel("<html><b>A Pagar: </b>" + tarjetaCredito.getaPagar() + "</html>");
            constraints.gridx = 1;
            add(label,constraints);

            label = new JLabel("----------------");
            constraints.gridx = 0;
            constraints.gridy++;
            add(label, constraints);

            boton = new JButton("Generar Reporte del mes");
            constraints.gridx = 0;
            constraints.gridy++;
            boton.setForeground(Color.blue);
            add(boton, constraints);

            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int mes = Integer.parseInt(JOptionPane.showInputDialog(null, "Por favor, ingrese el numero del mes del cual desea el reporte (Enero = 1, Febrero =2, etc...):"));
                    if(mes< 1 || mes > 12 ){
                        JOptionPane.showMessageDialog(panelManager.getFrame(), "Por favor, ingrese un numero de mes valido ", "Error", JOptionPane.ERROR_MESSAGE);

                    }else{
                        panelManager.mostrarPanelReporteMes(tarjetaCredito, mes, usuario, admin);
                    }
                }
            });

            if(admin){
                boton = new JButton("Cobrar tarjeta");
                constraints.gridx = 1;
                boton.setForeground(Color.blue);
                add(boton, constraints);

                boton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tarjetaCredito.cobrarTarjeta();

                        panelManager.mostrarPortalUsuario(usuario.getIdUsuario(), admin);
                    }
                });

                boton = new JButton("Eliminar tarjeta");
                constraints.gridx = 3;
                boton.setForeground(Color.red);
                add(boton, constraints);

                boton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            creditoService.delete(tarjetaCredito.getTarjetaID());
                        } catch (ServiceException ex) {
                            throw new RuntimeException(ex);
                        }

                        panelManager.mostrarPortalUsuario(usuario.getIdUsuario(), admin);
                    }
                });

            }


        }

        if(admin){
            boton = new JButton("Crear tarjetaCredito");
            constraints.gridx = 1;
            constraints.gridy++;
            boton.setForeground(Color.blue);
            add(boton, constraints);
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panelManager.mostrarPanelCrearTarjeta(usuario);
                }
            });

        }

        label = new JLabel(" ");
        constraints.gridx = 0;
        constraints.gridy++;
        add(label,constraints);

        if(!tDebito.isEmpty()){
            label = new JLabel("Tus tarjetas de debito: ");
            constraints.gridx = 0;
            constraints.gridy++;
            Font fontBold = new Font(label.getFont().getName(), Font.BOLD, label.getFont().getSize());
            label.setFont(fontBold);
            add(label, constraints);

            label = new JLabel("----------------");
            constraints.gridx = 0;
            constraints.gridy++;
            add(label, constraints);
        }

        for(TarjetaDebito tarjeta: tDebito){

            label=new JLabel("<html><b>Nro: </b>" + String.valueOf(tarjeta.getNumeroTarjeta()).replaceAll("(.{4})(?!$)", "$1 ") + "</html>");
            constraints.gridy++;
            constraints.gridx = 0;
            add(label, constraints);

            label = new JLabel("<html><b>CVV: </b>" + tarjeta.getCvv() + "</html>");
            constraints.gridx = 1;
            add(label, constraints);

            label = new JLabel("<html><b>Vence: </b>" + tarjeta.getFechaVencimiento() + "</html>");
            constraints.gridx = 0;
            constraints.gridy++;
            add(label, constraints);

            label = new JLabel("<html><b>Vence: </b>" + tarjeta.getFechaVencimiento() + "</html>");
            constraints.gridx = 0;
            constraints.gridy++;
            add(label, constraints);

            label = new JLabel("----------------");
            constraints.gridx = 0;
            constraints.gridy++;
            add(label, constraints);

            if(admin){
                boton = new JButton("Eliminar tarjeta");
                constraints.gridx = 1;
                boton.setForeground(Color.red);
                add(boton, constraints);

                boton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            debitoService.delete(tarjeta.getTarjetaID());
                        } catch (ServiceException ex) {
                            throw new RuntimeException(ex);
                        }

                        panelManager.mostrarPortalUsuario(usuario.getIdUsuario(), admin);
                    }
                });
            }
        }

        boton = new JButton("Volver");
        constraints.gridx = 0;
        constraints.gridy++;
        boton.setForeground(Color.blue);
        add(boton, constraints);
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPortalUsuario(usuario.getIdUsuario(), admin);
            }
        });

        if(admin){
            boton = new JButton("Crear tarjeta Debito");
            constraints.gridx = 1;
            boton.setForeground(Color.blue);
            add(boton, constraints);
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panelManager.mostrarPanelCrearTarjetaDebito(usuario);
                }
            });

        }

    }

}

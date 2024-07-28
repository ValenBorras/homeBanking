package front;

import com.dh.poo.MovimientoTarjeta_src.MovimientoTarjeta;
import com.dh.poo.MovimientoTarjeta_src.MovimientoTarjetaService;
import com.dh.poo.ServiceException;
import com.dh.poo.Tarjeta;
import com.dh.poo.User_src.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PanelReporteMes extends JPanel {
    private PanelManager panelManager;
    private JButton boton;
    private JLabel label;
    private List<MovimientoTarjeta> movimientos =  new ArrayList<>();

    public PanelReporteMes(PanelManager m){this.panelManager = m;}
    public PanelManager getPanelManager() {
        return panelManager;
    }

    public void armarPanelReporteMes(Tarjeta tarjeta, int mes, Usuario usuario, Boolean admin) {
        MovimientoTarjetaService movimientoService = new MovimientoTarjetaService();

        this.removeAll();
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        String nombreMes = "";
        nombreMes = switch (mes) {
            case 1 -> "Enero";
            case 2 -> "Febrero";
            case 3 -> "Marzo";
            case 4 -> "Abril";
            case 5 -> "Mayo";
            case 6 -> "Junio";
            case 7 -> "Julio";
            case 8 -> "Agosto";
            case 9 -> "Septiembre";
            case 10 -> "Octubre";
            case 11 -> "Noviembre";
            case 12 -> "Diciembre";
            default -> nombreMes;
        };

        label = new JLabel("<html><b>Todos los movimientos de: "+ nombreMes +"</html></b>");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(label, constraints);

        label = new JLabel("----------------");
        constraints.gridx = 0;
        constraints.gridy++;
        add(label, constraints);

        try {
            movimientos = movimientoService.movsDelMes(tarjeta.getNumeroTarjeta(), mes);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        if(movimientos.isEmpty()){
            label = new JLabel("La tarjeta no tiene movimientos registrados en ese mes");
            constraints.gridx = 0;
            constraints.gridy++;
            add(label, constraints);
        }else{
            for(MovimientoTarjeta movimiento : movimientos) {

                label = new JLabel("<html><b>Numero tarjeta: </b>"+ movimiento.getNumeroTarjeta());
                constraints.gridy++;
                constraints.gridx = 0;
                add(label,constraints);

                label = new JLabel("<html><b>Monto: </b>"+ movimiento.getMonto());
                constraints.gridy++;
                constraints.gridx = 0;
                add(label,constraints);

                label = new JLabel("<html><b>Fecha: </b>"+ movimiento.getFecha());
                constraints.gridy++;
                constraints.gridx = 0;
                add(label,constraints);

                label = new JLabel("----------------");
                constraints.gridx = 0;
                constraints.gridy++;
                add(label, constraints);
            }
        }

        boton = new JButton("Volver");
        constraints.gridy++;
        boton.setForeground(Color.blue);
        add(boton, constraints);
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPortalUsuario(usuario.getIdUsuario(), admin);
            }
        });

    }

}
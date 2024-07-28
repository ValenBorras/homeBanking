package front;

import com.dh.poo.ServiceException;
import com.dh.poo.Transfer_src.Transfer;
import com.dh.poo.Transfer_src.TransferService;
import com.dh.poo.User_src.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PanelResumenMovimientos extends JPanel {
    private PanelManager panelManager;
    private JButton boton;
    private JLabel label;
    private List<Transfer> movimientos =  new ArrayList<>();

    public PanelResumenMovimientos(PanelManager m){this.panelManager = m;}
    public PanelManager getPanelManager() {
        return panelManager;
    }

    public void armarPanelResumenMovimientos(Usuario usuario, Boolean admin) {
        TransferService transferService = new TransferService();

        this.removeAll();
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        List<String> contenidoList = new ArrayList<>();

        label = new JLabel("<html><b>Ultimos 15 movimientos registrados:</html></b>");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(label, constraints);

        label = new JLabel("----------------");
        constraints.gridx = 0;
        constraints.gridy++;
        add(label, constraints);
        contenidoList.add("Reporte de movimiento en cuentas de el usuario: "+ usuario.getNombre() +"\n \n");

        try {
            movimientos = transferService.allTransfersFromUser(usuario.getIdUsuario());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        Collections.reverse(movimientos);

        if(movimientos.isEmpty()){
            label = new JLabel("El usuario no tiene movimientos registrados");
            constraints.gridx = 0;
            constraints.gridy = 0;
            add(label, constraints);
        }else{
            int limit;
            if(movimientos.size() < 15){
                limit = movimientos.size();
            }else{
                limit = 15;
            }
            for (int i = 0; i < limit; i++) {
                Transfer movimiento = movimientos.get(i);
                label = new JLabel("<html><b>Alias desde donde se envio el dinero: </b>"+ movimiento.getAliasDesde());
                constraints.gridy++;
                constraints.gridx = 0;
                add(label,constraints);
                contenidoList.add("Alias desde donde se envio el dinero: "+ movimiento.getAliasDesde()+ "\n");

                label = new JLabel("<html><b>Alias de donde se recibio el dinero: "+ movimiento.getAliasHasta());
                constraints.gridy++;
                constraints.gridx = 0;
                add(label,constraints);
                contenidoList.add("Alias de donde se recibio el dinero: "+ movimiento.getAliasHasta() + "\n");

                label = new JLabel("<html><b>Monto del movimiento: </b>"+ movimiento.getMonto());
                constraints.gridy++;
                constraints.gridx = 0;
                add(label,constraints);
                contenidoList.add("Monto del movimiento: "+ movimiento.getMonto()+"\n");

                label = new JLabel("<html><b>Fecha y hora: </b>"+ movimiento.getFecha());
                constraints.gridy++;
                constraints.gridx = 0;
                add(label,constraints);
                contenidoList.add("Fecha y hora: "+ movimiento.getFecha()+"\n");

                label = new JLabel("----------------");
                constraints.gridx = 0;
                constraints.gridy++;
                add(label, constraints);
                contenidoList.add("----------------\n");

            }
        }

        String[] contenido = contenidoList.toArray(new String[0]);

        if(admin){
            boton = new JButton("Generar archivo con reporte");
            constraints.gridy++;
            boton.setForeground(Color.blue);
            add(boton, constraints);

            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    // Crear un JFileChooser
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Guardar archivo");

                    // Mostrar el cuadro de diálogo de guardar
                    int userSelection = fileChooser.showSaveDialog(panelManager.getFrame());

                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        File fileToSave = fileChooser.getSelectedFile();
                        try (FileWriter escritor = new FileWriter(fileToSave)) {
                            escritor.write(Arrays.toString(contenido));
                            escritor.flush();
                            JOptionPane.showMessageDialog(panelManager.getFrame(), "Archivo guardado exitosamente.");
                        } catch (IOException er) {
                            JOptionPane.showMessageDialog(panelManager.getFrame(), "Error al guardar el archivo: " + er.getMessage());
                            er.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(panelManager.getFrame(), "Guardado cancelado por el usuario.");
                    }
                }
            });
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
package front;

import javax.swing.*;
import java.awt.*;

public class PortalUsuario extends JPanel{
    private PanelManager panelManager;
    private JLabel label;
    public PortalUsuario(PanelManager m){this.panelManager = m;}

    public void armarPortalUsuario(){

        this.setLayout(new BorderLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        label = new JLabel("aca va el portal del usuario");
        constraints.gridx = 0;
        constraints.gridy = 0;

        add(label);
    }

}

package panels;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import controller.Controlador;

public class PanelPrincipalProveedor extends JPanel {

	
	public PanelPrincipalProveedor(Controlador controlador) {
		this.setLayout(new BorderLayout(0, 0));
		setBackground(Color.GRAY);
	}
}

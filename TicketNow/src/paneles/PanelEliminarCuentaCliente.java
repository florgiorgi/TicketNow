package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controlador.Controlador;

public class PanelEliminarCuentaCliente extends PanelEliminarCuenta {

	private JPanel panelSuperior;

	public PanelEliminarCuentaCliente(Controlador controlador, String cliente) {
		super(controlador, cliente);

		panelSuperior = new PanelSuperior();
		panelSuperior.setBackground(Color.WHITE);
		panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

	}

	private class PanelSuperior extends JPanel {

		JButton btnVolver = new JButton();
		ImageIcon icono = new ImageIcon(PanelRegistro.class.getResource("/paneles/back.png"));
		JLabel lblEliminarCuenta = new JLabel("  Eliminar cuenta");

		public PanelSuperior() {
			setLayout(new BorderLayout(0, 0));
			inicializarBoton();
			inicializarTitulo();
		}

		private void inicializarBoton() {
			btnVolver.setBackground(Color.WHITE);
			btnVolver.setIcon(icono);
			btnVolver.setPreferredSize(new Dimension(icono.getIconWidth(), icono.getIconHeight()));
			btnVolver.setBorderPainted(false);
			add(btnVolver, BorderLayout.WEST);

			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VistaTicketNow.changePanel("perfilCliente", PanelEliminarCuentaCliente.this, controlador);
				}
			});
		}

		private void inicializarTitulo() {
			lblEliminarCuenta.setFont(new Font("Dialog", Font.BOLD, 25));
			lblEliminarCuenta.setHorizontalAlignment(SwingConstants.LEFT);
			add(lblEliminarCuenta, BorderLayout.CENTER);
		}
	}
}

package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controlador.Controlador;

public class PanelResultadosEncontrados extends JPanel {

	private JPanel panelSuperior;
	private JPanel panelInferior;
	private JPanel panelCentral;

	JList listEspectaculos = new JList();
	private Controlador controlador;

	public PanelResultadosEncontrados(Controlador controlador) {
		this.setLayout(new BorderLayout(0, 0));
		this.controlador = controlador;

		panelSuperior = new PanelSuperior();
		panelSuperior.setBackground(Color.WHITE);
		add(panelSuperior, BorderLayout.NORTH);

		panelInferior = new PanelInferior();
		panelInferior.setBackground(Color.WHITE);
		add(panelInferior, BorderLayout.SOUTH);

		panelCentral = new PanelCentral();
		panelCentral.setBackground(Color.WHITE);
		add(panelCentral, BorderLayout.CENTER);
	}

	private class PanelSuperior extends JPanel {

		JButton btnvolver = new JButton();

		ImageIcon icono = new ImageIcon(PanelRegistro.class.getResource("/paneles/back.png"));

		public PanelSuperior() {
			setLayout(new BorderLayout(0, 0));
			inicializarBotones();
		}

		private void inicializarBotones() {
			btnvolver.setIcon(icono);
			btnvolver.setPreferredSize(new Dimension(icono.getIconWidth(), icono.getIconHeight()));
			btnvolver.setBorderPainted(false);
			add(btnvolver, BorderLayout.WEST);
			btnvolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VistaTicketNow.changePanel("cliente", PanelResultadosEncontrados.this, controlador);
				}
			});

		}
	}

	private class PanelInferior extends JPanel {
		JButton btnMasInfo = new JButton("Más información");

		public PanelInferior() {
			inicializarBotones();
		}

		private void inicializarBotones() {
			btnMasInfo.setForeground(Color.WHITE);
			btnMasInfo.setFont(new Font("Dialog", Font.BOLD, 14));
			btnMasInfo.setBackground(new Color(0, 102, 204));
			btnMasInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(listEspectaculos.isSelectionEmpty())
						JOptionPane.showMessageDialog(null, "Seleccione un espectáculo para ver sus detalles", "Ocurrió algo inesperado",
								JOptionPane.ERROR_MESSAGE);
					else
						VistaTicketNow.changePanel("informacion", PanelResultadosEncontrados.this, controlador);
				}
			});
			add(btnMasInfo);
		}
	}

	private class PanelCentral extends JPanel {
		JPanel panelCentralSuperior = new JPanel();
		JPanel panelCentralCentral = new JPanel();
		JPanel panelCentralIzquierdo = new JPanel();

		JLabel lblResultadosEncontrados = new JLabel("                 RESULTADOS ENCONTRADOS");

		JButton btnOrdenar = new JButton("Ordenar");
		JList listOrdenar = new JList();

		public PanelCentral() {
			setLayout(new BorderLayout(0, 0));

			panelCentralSuperior.setBackground(new Color(153, 204, 255));
			add(panelCentralSuperior, BorderLayout.NORTH);

			inicializarPanelCentralSuperior();

			panelCentralCentral.setBackground(new Color(255, 255, 255));
			add(panelCentralCentral, BorderLayout.CENTER);
			panelCentralCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

			inicializarPanelCentralCentral();

			panelCentralIzquierdo.setBackground(Color.LIGHT_GRAY);
			add(panelCentralIzquierdo, BorderLayout.WEST);
			panelCentralIzquierdo.setLayout(new BoxLayout(panelCentralIzquierdo, BoxLayout.Y_AXIS));

			inicializarPanelCentralIzquierdo();
		}

		private void inicializarPanelCentralSuperior() {
			lblResultadosEncontrados.setFont(new Font("Dialog", Font.BOLD, 18));
			lblResultadosEncontrados.setHorizontalAlignment(SwingConstants.LEFT);
			lblResultadosEncontrados.setForeground(Color.WHITE);
			lblResultadosEncontrados.setBackground(Color.WHITE);
			panelCentralSuperior.add(lblResultadosEncontrados);
		}

		private void inicializarPanelCentralCentral() {
			listEspectaculos.setFont(new Font("Dialog", Font.BOLD, 13));
			listEspectaculos.setModel(new AbstractListModel() {
				String[] values = new String[] { "Nombre: Espectaculo 1 - Lugar: Hoyts Palermo - Precio: 120$",
						"Nombre: Espectaculo 2 - Lugar: Hoyts Abasto - Precio: 120$", "Nombre: Espectaculo 3 - Lugar: Hoyts Unicenter - Precio: 120$", "Nombre: Espectaculo 4 - Lugar: Hoyts Belgrano - Precio: 120$" };

				public int getSize() {
					return values.length;
				}

				public Object getElementAt(int index) {
					return values[index];
				}
			});
			panelCentralCentral.add(listEspectaculos);
			
			
		}

		private void inicializarPanelCentralIzquierdo() {
			btnOrdenar.setForeground(Color.WHITE);
			btnOrdenar.setBackground(new Color(0, 102, 204));
			btnOrdenar.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnOrdenar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(listOrdenar.isSelectionEmpty())
						JOptionPane.showMessageDialog(null, "Seleccione una opción para ordenar los resultados", "Ocurrió algo inesperado",
								JOptionPane.ERROR_MESSAGE);
				}
			});

			listOrdenar.setFont(new Font("Dialog", Font.BOLD, 13));
			listOrdenar.setBackground(new Color(204, 204, 204));
			listOrdenar.setModel(new AbstractListModel() {
				String[] values = new String[] { "Lugar", "Precio", "Puntuaciones" };

				public int getSize() {
					return values.length;
				}

				public Object getElementAt(int index) {
					return values[index];
				}
			});
			panelCentralIzquierdo.add(listOrdenar);
			panelCentralIzquierdo.add(btnOrdenar);
		}
	}

}

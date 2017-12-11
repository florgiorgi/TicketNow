package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controlador.Controlador;
import database.NoHayEntradasRemanentesException;
import espectaculo.Espectaculo;

public class PanelInformacionEspectaculo extends JPanel {

	private JPanel panelSuperior;
	private JPanel panelCentral;

	private Controlador controlador;
	private String cliente;
	private Set<Espectaculo> espectaculos;
	private Espectaculo espectaculo;
	private String[] condicion;
	JLabel label_2;

	public PanelInformacionEspectaculo(Controlador controlador, String cliente, Set<Espectaculo> set,
			String espectaculo, String lugar, String[] condicion) {
		setLayout(new BorderLayout(0, 0));
		this.controlador = controlador;
		this.cliente = cliente;
		this.espectaculos = set;
		this.espectaculo = controlador.obtenerEspectaculo(espectaculo, lugar);
		this.condicion = condicion;
		
		JPanel panelSuperior = new PanelSuperior();
		panelSuperior.setBackground(Color.WHITE);
		add(panelSuperior, BorderLayout.NORTH);

		JPanel panelCentral = new PanelCentral();
		panelCentral.setBackground(Color.WHITE);
		add(panelCentral, BorderLayout.CENTER);

	}

	public class PanelSuperior extends JPanel {

		JButton btnVolver = new JButton();
		ImageIcon icono1 = new ImageIcon(PanelRegistro.class.getResource("/paneles/back.png"));

		JLabel lblNombreDelEspectculo = new JLabel("   " + espectaculo.getNombre());

		JButton btnCarrito = new JButton();
		ImageIcon icono2 = new ImageIcon(PanelRegistro.class.getResource("/paneles/carrito.png"));

		private PanelSuperior() {
			setLayout(new BorderLayout(0, 0));
			initializePanelSuperior();
		}

		private void initializePanelSuperior() {
			btnVolver.setBackground(Color.WHITE);
			btnVolver.setIcon(icono1);
			btnVolver.setPreferredSize(new Dimension(icono1.getIconWidth(), icono1.getIconHeight()));
			btnVolver.setBorderPainted(false);
			add(btnVolver, BorderLayout.WEST);

			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VistaTicketNow.changePanel("lista", PanelInformacionEspectaculo.this, controlador, cliente,
							controlador.obtenerEspectaculoPorCondicion(condicion[0], condicion[1], condicion[2]), condicion);
				}
			});

			lblNombreDelEspectculo.setFont(new Font("Dialog", Font.BOLD, 23));
			add(lblNombreDelEspectculo);

			btnCarrito.setBackground(Color.WHITE);
			btnCarrito.setIcon(icono2);
			btnCarrito.setPreferredSize(new Dimension(icono2.getIconWidth(), icono2.getIconHeight()));
			btnCarrito.setBorderPainted(false);
			add(btnCarrito, BorderLayout.EAST);

			btnCarrito.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ImageIcon icono_ticket = new ImageIcon(
							PanelRegistro.class.getResource("/paneles/icono-ticket.png"));

					SpinnerNumberModel sModel = new SpinnerNumberModel(0, 0,
							30 /* Poner el maximo segun lo q hay en la bd */, 1);
					JSpinner spinner = new JSpinner(sModel);
					JOptionPane optionpane = new JOptionPane();
					UIManager UI = new UIManager();
					UI.put("OptionPane.background", Color.white);
					UI.put("Panel.background", Color.white);

					int option = optionpane.showOptionDialog(null, spinner, "Comprar entradas", JOptionPane.OK_OPTION,
							JOptionPane.QUESTION_MESSAGE, icono_ticket, null, null);
					if (option == optionpane.OK_OPTION) {
						controlador.agregarCompra(espectaculo.getNombre(), espectaculo.getLugarDeRetiro(),
								(Integer) spinner.getValue(), Integer.parseInt(espectaculo.getPrecio()), cliente);
						
						try {
						espectaculo = controlador.actualizarEspectaculo(espectaculo.getNombre(), espectaculo.getLugarDeRetiro(),
								(Integer) spinner.getValue(), cliente);
						JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
						} catch (NoHayEntradasRemanentesException m1) {
							JOptionPane.showMessageDialog(null, m1.getMessage(), "Ocurrió algo inesperado",
									JOptionPane.ERROR_MESSAGE);
						}
						
					}
					
					label_2.setText(espectaculo.getCantidadEntradas());
				}
			});
			
			
			
		}
	}

	public class PanelCentral extends JPanel {

		public PanelCentral() {
			setLayout(new BorderLayout(0, 0));

			JPanel panelCentralImagen = new JPanel();
			panelCentralImagen.setBackground(Color.WHITE);
			add(panelCentralImagen, BorderLayout.NORTH);

			JLabel label = new JLabel("");
			String nombre = espectaculo.getNombre();
			nombre = nombre.replace(" ", "_");
			if(PanelInformacionEspectaculo.class.getResource("/paneles/" + nombre + ".png") == null)
				label.setIcon(new ImageIcon(PanelInformacionEspectaculo.class.getResource("/paneles/default.png")));
			else
				label.setIcon(new ImageIcon(PanelInformacionEspectaculo.class.getResource("/paneles/" + nombre + ".png")));
			panelCentralImagen.add(label);

			JLabel label_14 = new JLabel("    ");
			panelCentralImagen.add(label_14);

			JPanel panelCentralCentral = new JPanel();
			add(panelCentralCentral, BorderLayout.CENTER);
			panelCentralCentral.setLayout(new BorderLayout(0, 0));

			JPanel panelCaracteristicas = new JPanel();
			panelCentralCentral.add(panelCaracteristicas, BorderLayout.NORTH);
			panelCaracteristicas.setBackground(Color.WHITE);
			panelCaracteristicas.setLayout(new GridLayout(3, 2, 0, 0));

			JLabel lblNewLabel = new JLabel("Cantidad disponible :    ");
			lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
			panelCaracteristicas.add(lblNewLabel);

			label_2 = new JLabel(espectaculo.getCantidadEntradas());
			label_2.setFont(new Font("Dialog", Font.PLAIN, 13));
			panelCaracteristicas.add(label_2);

			JLabel lblPrecio = new JLabel("Precio :    ");
			lblPrecio.setHorizontalAlignment(SwingConstants.TRAILING);
			lblPrecio.setFont(new Font("Dialog", Font.BOLD, 14));
			panelCaracteristicas.add(lblPrecio);

			JLabel label_1 = new JLabel(espectaculo.getPrecio() + "$");
			label_1.setFont(new Font("Dialog", Font.PLAIN, 13));
			panelCaracteristicas.add(label_1);

			JLabel lblNewLabel_1 = new JLabel("Lugar :    ");
			lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 14));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
			panelCaracteristicas.add(lblNewLabel_1);

			JLabel lblNewLabel_2 = new JLabel(espectaculo.getLugarDeRetiro());
			lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 13));
			panelCaracteristicas.add(lblNewLabel_2);

			JPanel panelInformativo = new JPanel();
			panelInformativo.setBackground(Color.WHITE);
			panelCentralCentral.add(panelInformativo, BorderLayout.EAST);
			panelInformativo.setLayout(new BorderLayout(0, 0));

			JLabel lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblNewLabel_3.setIcon(new ImageIcon(PanelInformacionEspectaculo.class.getResource("/paneles/info.png")));
			panelInformativo.add(lblNewLabel_3, BorderLayout.NORTH);

			JTextArea txtrCarsEs = new JTextArea();
			txtrCarsEs.setTabSize(5);
			txtrCarsEs.setText(espectaculo.getDescripcion());
			txtrCarsEs.setBackground(new Color(255, 204, 102));
			txtrCarsEs.setColumns(20);
			txtrCarsEs.setRows(6);
			txtrCarsEs.setWrapStyleWord(true);
			txtrCarsEs.setLineWrap(true);
			panelInformativo.add(txtrCarsEs, BorderLayout.CENTER);

			JPanel panelInformativoDerecho = new JPanel();
			panelInformativoDerecho.setBackground(Color.WHITE);
			panelInformativo.add(panelInformativoDerecho, BorderLayout.EAST);

			JLabel label_15 = new JLabel(" ");
			label_15.setBackground(Color.WHITE);
			panelInformativoDerecho.add(label_15);

			JPanel panelPuntuacion = new JPanel();
			panelPuntuacion.setBackground(Color.WHITE);
			panelInformativo.add(panelPuntuacion, BorderLayout.SOUTH);
			panelPuntuacion.setLayout(new BoxLayout(panelPuntuacion, BoxLayout.Y_AXIS));

			JLabel label_5 = new JLabel("    ");
			label_5.setFont(new Font("Dialog", Font.BOLD, 50));
			panelPuntuacion.add(label_5);

			JPanel panelPuntuar = new JPanel();
			panelPuntuar.setAlignmentY(Component.BOTTOM_ALIGNMENT);
			panelPuntuacion.add(panelPuntuar);
			panelPuntuar.setBackground(Color.WHITE);
			panelPuntuar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

			JSlider slider = new JSlider();
			slider.setBackground(Color.WHITE);
			slider.setMaximum(5);
			slider.setPaintLabels(true);
			slider.setForeground(Color.WHITE);
			panelPuntuar.add(slider);

			JLabel label_13 = new JLabel();
			label_13.setText("5");
			panelPuntuar.add(label_13);

			JPanel panelBotonPuntuar = new JPanel();
			panelBotonPuntuar.setBackground(Color.WHITE);
			panelPuntuacion.add(panelBotonPuntuar);

			JButton btnPuntuar = new JButton("Puntuar");
			panelBotonPuntuar.add(btnPuntuar);
			btnPuntuar.setBackground(new Color(0, 102, 204));
			btnPuntuar.setForeground(Color.WHITE);

			slider.addChangeListener(new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent e) {

					label_13.setText(slider.getValue() + " ");

				}

			});
			

			JPanel panelIzquierdo = new JPanel();
			panelIzquierdo.setBackground(Color.WHITE);
			panelCentralCentral.add(panelIzquierdo, BorderLayout.CENTER);
			panelIzquierdo.setLayout(new BorderLayout(0, 0));

			JPanel panelCPClientes = new JPanel();
			panelCPClientes.setBackground(Color.WHITE);
			panelIzquierdo.add(panelCPClientes, BorderLayout.NORTH);
			panelCPClientes.setLayout(new BoxLayout(panelCPClientes, BoxLayout.Y_AXIS));

			JLabel label_3 = new JLabel(" ");
			panelCPClientes.add(label_3);
			label_3.setFont(new Font("Dialog", Font.BOLD, 15));

			JLabel lblComentarios = new JLabel("Comentarios");
			panelCPClientes.add(lblComentarios);
			lblComentarios.setFont(new Font("Dialog", Font.BOLD, 18));
			lblComentarios.setForeground(new Color(51, 51, 51));
			lblComentarios.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblComentarios.setHorizontalAlignment(SwingConstants.CENTER);

			JLabel label_4 = new JLabel(" ");
			panelCPClientes.add(label_4);
			label_4.setFont(new Font("Dialog", Font.BOLD, 15));

			JList list = new JList();
			panelCPClientes.add(list);
			list.setFont(new Font("Dialog", Font.PLAIN, 12));
			list.setModel(new AbstractListModel() {
				String[] values = new String[] { "Usuario: MariaM - Comentario: Hermosa pelicula!",
						"Usuario: LautaroG - Comentario: Un poco aburrida!" };

				public int getSize() {
					return values.length;
				}

				public Object getElementAt(int index) {
					return values[index];
				}
			});

			JLabel label_6 = new JLabel(" ");
			panelCPClientes.add(label_6);

			JLabel lblPuntuacion = new JLabel("Puntuación");
			panelCPClientes.add(lblPuntuacion);
			lblPuntuacion.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblPuntuacion.setFont(new Font("Dialog", Font.BOLD, 18));

			JLabel label_7 = new JLabel(" ");
			label_7.setFont(new Font("Dialog", Font.BOLD, 15));
			panelCPClientes.add(label_7);

			JLabel label_10 = new JLabel();
			if (espectaculo.getPuntaje() == null) {
				label_10.setText("No hay puntaje registrado en el sistema");
				label_10.setFont(new Font("Dialog", Font.PLAIN, 12));
			} else
				label_10.setText(espectaculo.getPuntaje());
			label_10.setAlignmentX(Component.CENTER_ALIGNMENT);
			label_10.setFont(new Font("Dialog", Font.PLAIN, 14));
			panelCPClientes.add(label_10);

			JLabel label_12 = new JLabel(" ");
			label_12.setFont(new Font("Dialog", Font.BOLD, 15));
			panelCPClientes.add(label_12);

			btnPuntuar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					espectaculo = controlador.puntuarEspectaculo(espectaculo.getNombre(),
							espectaculo.getLugarDeRetiro(), slider.getValue());
					JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
					label_10.setText(espectaculo.getPuntaje());
				}

			});

			JPanel panelDejaComentario = new JPanel();
			panelDejaComentario.setBackground(Color.LIGHT_GRAY);
			panelIzquierdo.add(panelDejaComentario, BorderLayout.CENTER);
			panelDejaComentario.setLayout(new BoxLayout(panelDejaComentario, BoxLayout.Y_AXIS));

			JLabel lblDejaTuComentario = new JLabel("Dejá tu comentario");
			lblDejaTuComentario.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblDejaTuComentario.setHorizontalAlignment(SwingConstants.CENTER);
			lblDejaTuComentario.setFont(new Font("Dialog", Font.BOLD, 13));
			panelDejaComentario.add(lblDejaTuComentario);

			JTextArea textArea = new JTextArea();
			textArea.setColumns(15);
			textArea.setRows(7);
			textArea.setWrapStyleWord(true);
			textArea.setLineWrap(true);
			Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
			textArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(2, 2, 3, 4)));
			panelDejaComentario.add(textArea);

			JPanel panelDejaComentarioIzquierdo = new JPanel();
			panelDejaComentarioIzquierdo.setBackground(Color.WHITE);
			panelIzquierdo.add(panelDejaComentarioIzquierdo, BorderLayout.WEST);

			JLabel label_8 = new JLabel("                  ");
			panelDejaComentarioIzquierdo.add(label_8);

			JPanel panelDejaComentarioDerecho = new JPanel();
			panelDejaComentarioDerecho.setBackground(Color.WHITE);
			panelIzquierdo.add(panelDejaComentarioDerecho, BorderLayout.EAST);

			JLabel label_9 = new JLabel("                  ");
			panelDejaComentarioDerecho.add(label_9);

			JPanel panelComentar = new JPanel();
			panelComentar.setBackground(Color.WHITE);
			panelIzquierdo.add(panelComentar, BorderLayout.SOUTH);

			JButton btnNewButton = new JButton("Comentar");
			btnNewButton.setBackground(new Color(0, 102, 204));
			btnNewButton.setForeground(Color.WHITE);
			btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
			panelComentar.add(btnNewButton);

		}
	}

}

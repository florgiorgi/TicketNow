package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import controlador.Controlador;
import usuario.ContraseñaIncorrectaException;

public class PanelAgregarEspectaculo extends JPanel {

	private JPanel panelSuperior;
	private JPanel panelDerecho;
	private JPanel panelIzquierdo;
	private JPanel panelCentral;

	private Controlador controlador;

	public PanelAgregarEspectaculo(Controlador controlador) {

		setLayout(new BorderLayout(0, 0));
		this.controlador = controlador;

		panelSuperior = new PanelSuperior();
		panelSuperior.setBackground(Color.WHITE);
		add(panelSuperior, BorderLayout.NORTH);

		panelCentral = new PanelCentral();
		panelCentral.setBackground(Color.WHITE);
		add(panelCentral, BorderLayout.CENTER);

		panelDerecho = new JPanel();
		panelDerecho.setBackground(Color.WHITE);
		FlowLayout flowLayout1 = (FlowLayout) panelDerecho.getLayout();
		flowLayout1.setHgap(50);
		add(panelDerecho, BorderLayout.WEST);

		panelIzquierdo = new JPanel();
		panelIzquierdo.setBackground(Color.WHITE);
		FlowLayout flowLayout2 = (FlowLayout) panelIzquierdo.getLayout();
		flowLayout2.setHgap(50);
		add(panelIzquierdo, BorderLayout.EAST);

	}

	private class PanelSuperior extends JPanel {
		private JButton btnVolver = new JButton();
		private JLabel titulo = new JLabel("  Nuevo espectáculo:");

		public PanelSuperior() {
			setLayout(new BorderLayout(0, 0));
			inicializarTitulo();
			inicializarBotones();
		}

		/**
		 * Método que inicializa el titulo y lo agrega al panel
		 */
		private void inicializarTitulo() {
			titulo.setFont(new Font("Dialog", Font.BOLD, 22));
			add(titulo, BorderLayout.CENTER);
		}

		/**
		 * Método que inicializa el boton volver y lo agrega al panel
		 */
		private void inicializarBotones() {
			btnVolver.setBackground(Color.WHITE);
			ImageIcon icono = new ImageIcon(PanelRegistro.class.getResource("/paneles/back.png"));
			btnVolver.setIcon(icono);
			btnVolver.setPreferredSize(new Dimension(icono.getIconWidth(), icono.getIconHeight()));
			btnVolver.setBorderPainted(false);
			add(btnVolver, BorderLayout.WEST);

			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VistaTicketNow.changePanel("proveedor", PanelAgregarEspectaculo.this, controlador);
				}
			});
		}
	}

	private class PanelCentral extends JPanel {

		private JPanel panelRegistrarse = new JPanel();

		private JLabel lblNombre = new JLabel("Nombre:");
		private JLabel lblCantidadDisponible = new JLabel("Cantidad de entradas:");
		private JLabel lblFechaDeEstreno = new JLabel("Fecha de estreno:");
		private JLabel lblPromocion = new JLabel("Promoción:");
		private JLabel lblFotos = new JLabel("Foto:");
		private JLabel lblCategoria = new JLabel("Categoria:");
		private JLabel lblLugar = new JLabel("Lugar de Retiro:");
		private JLabel lblPrecio = new JLabel("Precio:");
		private JLabel lblCaracteristicas = new JLabel("Caracteristicas:");

		private JTextField nombreField = new JTextField();
		private JSpinner cantidadDisponibleField = new JSpinner();
		private JTextField fechaDeEstrenoField = new JTextField("AAAA-MM-DD");
		private JComboBox promocionBox = new JComboBox();
		private JButton fotosBtn = new JButton("Cargar fotos");
		private JComboBox categoriaBox = new JComboBox();
		private JComboBox lugarBox = new JComboBox();
		private JTextField precioField = new JTextField();
		private JTextArea caracteristicasPane = new JTextArea();

		private JPanel panelInferior = new JPanel();

		private Font fuente = new Font("Dialog", Font.BOLD, 14);

		public PanelCentral() {
			setLayout(new BorderLayout(0, 0));

			panelRegistrarse.setLayout(new GridLayout(9, 2, 15, 30));
			panelRegistrarse.setBackground(Color.WHITE);
			add(panelRegistrarse, BorderLayout.CENTER);

			inicializarCampos();

			panelInferior = new PanelInferior();
			panelInferior.setBackground(Color.WHITE);
			add(panelInferior, BorderLayout.SOUTH);
		}

		private void inicializarCampos() {
			lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblNombre);
			lblNombre.setFont(fuente);

			panelRegistrarse.add(nombreField);
			nombreField.setFont(fuente);

			lblCantidadDisponible.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblCantidadDisponible);
			lblCantidadDisponible.setFont(fuente);

			panelRegistrarse.add(cantidadDisponibleField);
			cantidadDisponibleField.setFont(fuente);

			lblFechaDeEstreno.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblFechaDeEstreno);
			lblFechaDeEstreno.setFont(fuente);

			panelRegistrarse.add(fechaDeEstrenoField);
			fechaDeEstrenoField.setFont(fuente);

			lblPromocion.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblPromocion);
			lblPromocion.setFont(fuente);

			promocionBox.setModel(
					new DefaultComboBoxModel(new String[] { "2x1", "Banco Asociados", "Descuento a Jubilados" }));
			panelRegistrarse.add(promocionBox);
			promocionBox.setFont(fuente);

			lblFotos.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblFotos);
			lblFotos.setFont(fuente);

			panelRegistrarse.add(fotosBtn);
			fotosBtn.setFont(fuente);
			fotosBtn.setForeground(new Color(255, 255, 255));
			fotosBtn.setBackground(new Color(0, 102, 204));

			fotosBtn.addActionListener(new ActionListener() {

				File fichero;

				public void actionPerformed(ActionEvent e) {
					int resultado;

					CargarFoto ventana = new CargarFoto();

					FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG y PNG", "jpg", "png");

					ventana.jfchCargarfoto.setFileFilter(filtro);

					resultado = ventana.jfchCargarfoto.showOpenDialog(null);

					if (JFileChooser.APPROVE_OPTION == resultado) {
						fichero = ventana.jfchCargarfoto.getSelectedFile();

						try {
							ImageIcon icon = new ImageIcon(fichero.toString());
							Icon icono = new ImageIcon(icon.getImage());
							JLabel fotoCargada = new JLabel(); /* Aca guardo la foto que va a ir a la base de datos */
							fotoCargada.setText(null);
							fotoCargada.setIcon(icono);
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Error abriendo la imagen " + ex);
						}

					}
				}
			});

			lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblCategoria);
			lblCategoria.setFont(fuente);

			categoriaBox.setModel(
					new DefaultComboBoxModel(new String[] { "Seleccione categoria", "Cine", "Teatro", "Cancha" }));
			panelRegistrarse.add(categoriaBox);
			categoriaBox.setFont(fuente);

			lblLugar.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblLugar);
			lblLugar.setFont(fuente);

			categoriaBox.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						switch (e.getItem().toString()) {
						case "Cine":
							lugarBox.setModel(new DefaultComboBoxModel(new String[] { "Hoyts Abasto", "Hoyts Dot",
									"Hoyts Moreno", "Hoyts Moron", "Hoyts Quilmes", "Hoyts Rosario", "Hoyts Temperley",
									"Hoyts Unicenter", "Hoyts NuevoCentro", "Hoyts Patio Olmos", "Hoyts Salta" }));
							break;
						case "Teatro":
							lugarBox.setModel(new DefaultComboBoxModel(new String[] { "Teatro Colon", "Teatro Gran Rex",
									"Teatro Metropolitan", "Teatro Argentino de La Plata", "Teatro Maipo",
									"Teatro Lola Membrives", "Teatro Opera", "Teatro Coliseo" }));
							break;
						case "Cancha":
							lugarBox.setModel(new DefaultComboBoxModel(new String[] { "Antonio Vespusio Liberti",
									"Libertadores de América", "Ciudad de La Plata", "Presidente Peron",
									"Mario Alberto Kempes", "Jose Amalfitani", "Alberto J. Armando",
									"Tomas Adolfo Ducó", "Pedro Bidegain" }));
							break;
						}
					}
				}
			});

			panelRegistrarse.add(lugarBox);
			lugarBox.setFont(fuente);

			lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblPrecio);
			lblPrecio.setFont(fuente);

			panelRegistrarse.add(precioField);
			precioField.setFont(fuente);

			lblCaracteristicas.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblCaracteristicas);
			lblCaracteristicas.setFont(fuente);

			JScrollPane scroll = new JScrollPane(caracteristicasPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			caracteristicasPane.setWrapStyleWord(true);
			caracteristicasPane.setLineWrap(true);
			panelRegistrarse.add(scroll);
		}

		private class PanelInferior extends JPanel {

			JButton btnAgregar = new JButton("Agregar espectáculo");

			public PanelInferior() {
				FlowLayout flowLayout1 = (FlowLayout) this.getLayout();
				flowLayout1.setVgap(20);
				inicializarBotones();
			}

			private void inicializarBotones() {
				btnAgregar.setFont(new Font("Dialog", Font.BOLD, 15));
				btnAgregar.setVerticalAlignment(SwingConstants.TOP);
				btnAgregar.setForeground(new Color(255, 255, 255));
				btnAgregar.setBackground(new Color(0, 102, 204));
				add(btnAgregar);

				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!categoriaBox.getSelectedItem().toString().equals("Seleccione categoria")) {
							/*
							 * try { if (controlador.agregarEspectaculo( Pasar todos los campos de arriba
							 * --> HAY QUE CAMBIAR LA CLASE ESPECTACULO PQ NO LOS TIENE A TODOS ) {
							 */ JOptionPane.showMessageDialog(null,
									"Su espectaculo ha sido dado de alta en el sistema.");
							VistaTicketNow.changePanel("proveedor", PanelAgregarEspectaculo.this, controlador);
							/*
							 * } } catch (ContraseñaIncorrectaException m) {
							 * JOptionPane.showMessageDialog(null, m.getMessage(),
							 * "Ocurrió algo inesperado", JOptionPane.ERROR_MESSAGE);
							 * 
							 * }
							 */
						} else {
							JOptionPane.showMessageDialog(null, "Por favor indique el la categoria de venta",
									"Ocurrió algo inesperado", JOptionPane.ERROR_MESSAGE);
						}

					}
				});
				/*
				 * El listener de este boton tiene que hacer algo similar al de panel de
				 * registrarse ---> Idem al inicializar un espectaculo ( similar a la clase
				 * usuario con las excepciones )
				 */
			}

		}
	}
}

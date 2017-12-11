package paneles;

import usuario.ApellidoInvalidoException;
import usuario.CodigoPostalInvalidoException;
import usuario.ContraseñaIncorrectaException;
import usuario.ContraseñaInvalidaException;
import usuario.DNIInvalidoException;
import usuario.DireccionInvalidaException;
import usuario.FechaNacInvalidaException;
import usuario.LocalidadInvalidaException;
import usuario.MailInvalidoException;
import usuario.NombreInvalidoException;
import usuario.TelefonoInvalidoException;
import usuario.UsuarioExistenteException;
import usuario.UsuarioInvalidoException;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;

import controlador.Controlador;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PanelRegistro extends JPanel {

	private JPanel panelSuperior;
	private JPanel panelDerecho;
	private JPanel panelIzquierdo;
	private JPanel panelCentral;

	private Controlador controlador;

	public PanelRegistro(Controlador controlador) {

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
		private JLabel titulo = new JLabel(" Ingrese sus datos personales:");

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
					VistaTicketNow.changePanel("menu", PanelRegistro.this, controlador);
				}
			});
		}
	}

	private class PanelCentral extends JPanel {

		private JPanel panelRegistrarse = new JPanel();

		private JLabel lblTipoUsuario = new JLabel("Tipo de usuario:");
		private JLabel lblUsuario = new JLabel("Usuario:");
		private JLabel lblContraseña = new JLabel("Contraseña:");
		private JLabel lblConfirmarContraseña = new JLabel("Confirmar contraseña:");
		private JLabel lblNombre = new JLabel("Nombre:");
		private JLabel lblApellido = new JLabel("Apellido:");
		private JLabel lblFechaNacimiento = new JLabel("Fecha de nacimiento:");
		private JLabel lblDireccionDeCorreo = new JLabel("E-mail:");
		private JLabel lblTelefono = new JLabel("Teléfono:");
		private JLabel lblTipoDeDocumento = new JLabel("Tipo de documento:");
		private JLabel lblDni = new JLabel("Numero de documento:");
		private JLabel lblPais = new JLabel("Pais:");
		private JLabel lblProvincia = new JLabel("Provincia:");
		private JLabel lblLocalidad = new JLabel("Localidad:");
		private JLabel lblDireccion = new JLabel("Direccion:");
		private JLabel lblCodigoPostal = new JLabel("Codigo Postal:");

		private JComboBox tipoUsuario = new JComboBox();
		private JTextField usuarioField = new JTextField();
		private JTextField contraseñaField = new JTextField();
		private JTextField contraseñaConfField = new JTextField();
		private JTextField nombreField = new JTextField();
		private JTextField apellidoField = new JTextField();
		private JTextField fechaNacimientoField = new JTextField("AAAA-MM-DD");
		private JTextField direccionCorreoField = new JTextField();
		private JTextField telefonoField = new JTextField();
		private JComboBox tipoDocumentoBox = new JComboBox();
		private JTextField dniField = new JTextField();
		private JComboBox paisBox = new JComboBox();
		private JComboBox provinciaBox = new JComboBox();
		private JTextField localidadField = new JTextField();
		private JTextField direccionField = new JTextField();
		private JTextField codigoPostalField = new JTextField();
		private JPanel panelInferior;

		private Font fuente = new Font("Dialog", Font.BOLD, 14);
		private Font fuente1 = new Font("Dialog", Font.PLAIN, 14);

		public PanelCentral() {
			setLayout(new BorderLayout(0, 0));

			panelRegistrarse.setLayout(new GridLayout(16, 10, 15, 12));
			panelRegistrarse.setBackground(Color.WHITE);
			add(panelRegistrarse, BorderLayout.CENTER);

			inicializarCampos();

			panelInferior = new PanelInferior();
			panelInferior.setBackground(Color.WHITE);
			add(panelInferior, BorderLayout.SOUTH);
		}

		private void inicializarCampos() {

			lblTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblTipoUsuario);
			lblTipoUsuario.setFont(fuente);

			tipoUsuario.setModel(new DefaultComboBoxModel(new String[] { "Cliente", "Proveedor" }));
			panelRegistrarse.add(tipoUsuario);
			tipoUsuario.setFont(fuente1);

			lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblUsuario);
			lblUsuario.setFont(fuente);

			usuarioField.setColumns(10);
			panelRegistrarse.add(usuarioField);
			usuarioField.setFont(fuente1);

			lblContraseña.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblContraseña);
			lblContraseña.setFont(fuente);

			contraseñaField.setColumns(10);
			panelRegistrarse.add(contraseñaField);
			contraseñaField.setFont(fuente1);

			lblConfirmarContraseña.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblConfirmarContraseña);
			lblConfirmarContraseña.setFont(fuente);

			contraseñaConfField.setColumns(10);
			panelRegistrarse.add(contraseñaConfField);
			contraseñaConfField.setFont(fuente1);

			lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblNombre);
			lblNombre.setFont(fuente);

			nombreField.setColumns(10);
			panelRegistrarse.add(nombreField);
			nombreField.setFont(fuente1);

			lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblApellido);
			lblApellido.setFont(fuente);

			apellidoField.setColumns(10);
			panelRegistrarse.add(apellidoField);
			apellidoField.setFont(fuente1);

			lblFechaNacimiento.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblFechaNacimiento);
			lblFechaNacimiento.setFont(fuente);

			fechaNacimientoField.setColumns(10);
			panelRegistrarse.add(fechaNacimientoField);
			fechaNacimientoField.setFont(fuente1);

			lblDireccionDeCorreo.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblDireccionDeCorreo);
			lblDireccionDeCorreo.setFont(fuente);

			direccionCorreoField.setColumns(10);
			panelRegistrarse.add(direccionCorreoField);
			direccionCorreoField.setFont(fuente1);

			lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblTelefono);
			lblTelefono.setFont(fuente);

			telefonoField.setColumns(10);
			panelRegistrarse.add(telefonoField);
			telefonoField.setFont(fuente1);

			lblTipoDeDocumento.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblTipoDeDocumento);
			lblTipoDeDocumento.setFont(fuente);

			tipoDocumentoBox.setModel(new DefaultComboBoxModel(new String[] { "DNI", "CI", "Pasaporte" }));
			panelRegistrarse.add(tipoDocumentoBox);
			tipoDocumentoBox.setFont(fuente1);

			lblDni.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblDni);
			lblDni.setFont(fuente);

			dniField.setColumns(10);
			panelRegistrarse.add(dniField);
			dniField.setFont(fuente1);

			lblPais.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblPais);
			lblPais.setFont(fuente);

			paisBox.setModel(
					new DefaultComboBoxModel(new String[] { "Seleccione pais", "Argentina", "Paraguay", "Uruguay" }));
			panelRegistrarse.add(paisBox);
			paisBox.setFont(fuente1);

			lblProvincia.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblProvincia);
			lblProvincia.setFont(fuente);

			paisBox.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						switch (e.getItem().toString()) {
						case "Argentina":
							provinciaBox.setModel(new DefaultComboBoxModel(new String[] { "Buenos Aires", "Catamarca",
									"Chaco", "Chubut", "Córdoba", "Corrientes", "Entre Ríos", "Formosa", "Jujuy",
									"La Pampa", "La Rioja", "Mendoza", "Misiones", "Neuquén", "Río Negro", "Salta",
									"San Juan", "San Luis", "Santa Cruz", "Santa Fe", "Santiago del Estero",
									"Tierra del Fuego", "Tucumán" }));
							break;
						case "Paraguay":
							provinciaBox.setModel(new DefaultComboBoxModel(new String[] { "Asunción", "Concepcion",
									"San Pedro", "Cordillera", "Guairá", "Caaguazú", "Caazapá", "Itapúa", "Misiones",
									"Paraguarí", "Alto Paraná", "Central", "Ñeembucú", "Amambay", "Canindeyú",
									"Presidente Hayes", "Boquerón", "Alto Paraguay" }));
							break;
						case "Uruguay":
							provinciaBox.setModel(new DefaultComboBoxModel(new String[] { "Artigas", "Canelones",
									"Cerro Largo", "Colonia", "Durazno", "Flores", "Florida", "Lavalleja", "Maldonado",
									"Montevideo", "Paysandú", "Rio Negro", "Rivera", "Rocha", "Salto", "San Jose",
									"Soriano", "Tacuarembó", "Treinta y Tres" }));
							break;
						}
					}
				}
			});

			panelRegistrarse.add(provinciaBox);
			provinciaBox.setFont(fuente1);

			lblLocalidad.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblLocalidad);
			lblLocalidad.setFont(fuente);

			localidadField.setColumns(10);
			panelRegistrarse.add(localidadField);
			localidadField.setFont(fuente1);

			lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblDireccion);
			lblDireccion.setFont(fuente);

			direccionField.setColumns(10);
			panelRegistrarse.add(direccionField);
			direccionField.setFont(fuente1);

			lblCodigoPostal.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblCodigoPostal);
			lblCodigoPostal.setFont(fuente);

			codigoPostalField.setColumns(10);
			panelRegistrarse.add(codigoPostalField);
			codigoPostalField.setFont(fuente1);

		}

		private class PanelInferior extends JPanel {
			JButton btnRegistrarse = new JButton("Registrarse");

			public PanelInferior() {
				inicializarBotones();
			}

			private void inicializarBotones() {
				btnRegistrarse.setFont(new Font("Dialog", Font.BOLD, 15));
				btnRegistrarse.setVerticalAlignment(SwingConstants.TOP);
				btnRegistrarse.setForeground(new Color(255, 255, 255));
				btnRegistrarse.setBackground(new Color(0, 102, 204));
				add(btnRegistrarse);

				btnRegistrarse.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						if(!paisBox.getSelectedItem().toString().equals("Seleccione pais")) {
							try {
								if (controlador.agregarUsuario(tipoUsuario.getSelectedItem().toString(),
									usuarioField.getText(), contraseñaField.getText(),
									contraseñaConfField.getText(), nombreField.getText(), apellidoField.getText(),
									fechaNacimientoField.getText(), direccionCorreoField.getText(),
									telefonoField.getText(), dniField.getText(), paisBox.getSelectedItem().toString(),
									provinciaBox.getSelectedItem().toString(), localidadField.getText(), direccionField.getText(),
									codigoPostalField.getText())) {
								JOptionPane.showMessageDialog(null, "Su cuenta ha sido creada con éxito.");
								VistaTicketNow.changePanel("menu", PanelRegistro.this, controlador);
								}
						} catch (ContraseñaIncorrectaException m) {
							JOptionPane.showMessageDialog(null, m.getMessage(), "Ocurrió algo inesperado",
									JOptionPane.ERROR_MESSAGE);
			
						} catch (ContraseñaInvalidaException m1) {
							JOptionPane.showMessageDialog(null, m1.getMessage(), "Ocurrió algo inesperado",
									JOptionPane.ERROR_MESSAGE);

						} catch (UsuarioInvalidoException m2) {
							JOptionPane.showMessageDialog(null, m2.getMessage(), "Ocurrió algo inesperado",
									JOptionPane.ERROR_MESSAGE);

						} catch (NombreInvalidoException m3) {
							JOptionPane.showMessageDialog(null, m3.getMessage(), "Ocurrió algo inesperado",
									JOptionPane.ERROR_MESSAGE);

						} catch (ApellidoInvalidoException m4) {
							JOptionPane.showMessageDialog(null, m4.getMessage(), "Ocurrió algo inesperado",
									JOptionPane.ERROR_MESSAGE);

						} catch (FechaNacInvalidaException m5) {
							JOptionPane.showMessageDialog(null, m5.getMessage(), "Ocurrió algo inesperado",
									JOptionPane.ERROR_MESSAGE);

						} catch (MailInvalidoException m6) {
							JOptionPane.showMessageDialog(null, m6.getMessage(), "Ocurrió algo inesperado",
									JOptionPane.ERROR_MESSAGE);

						} catch (TelefonoInvalidoException m7) {
							JOptionPane.showMessageDialog(null, m7.getMessage(), "Ocurrió algo inesperado",
									JOptionPane.ERROR_MESSAGE);

						} catch (DNIInvalidoException m8) {
							JOptionPane.showMessageDialog(null, m8.getMessage(), "Ocurrió algo inesperado",
									JOptionPane.ERROR_MESSAGE);

						} catch (UsuarioExistenteException m9) {
							JOptionPane.showMessageDialog(null, m9.getMessage(), "Ocurrió algo inesperado",
									JOptionPane.ERROR_MESSAGE);
							
						} catch (LocalidadInvalidaException m10) {
							JOptionPane.showMessageDialog(null, m10.getMessage(), "Ocurrió algo inesperado",
									JOptionPane.ERROR_MESSAGE);
							
						} catch (DireccionInvalidaException m11) {
							JOptionPane.showMessageDialog(null, m11.getMessage(), "Ocurrió algo inesperado",
									JOptionPane.ERROR_MESSAGE);
							
						}  catch (CodigoPostalInvalidoException m12) {
							JOptionPane.showMessageDialog(null, m12.getMessage(), "Ocurrió algo inesperado",
									JOptionPane.ERROR_MESSAGE);
							
						} catch (HeadlessException e1) {
							/* */
							e1.printStackTrace();
						} catch (SQLException e1) {
							/* */
							e1.printStackTrace();
						}
						
						} else {
							JOptionPane.showMessageDialog(null, "Por favor indique el pais de residencia", "Ocurrió algo inesperado",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});
			}

		}

	}

}

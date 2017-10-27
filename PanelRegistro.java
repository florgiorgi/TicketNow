package panels;

import usuario.ApellidoInvalidoException;
import usuario.ContraseñaIncorrectaException;
import usuario.ContraseñaInvalidaException;
import usuario.DNIInvalidoException;
import usuario.FechaNacInvalidaException;
import usuario.MailInvalidoException;
import usuario.NombreInvalidoException;
import usuario.TelefonoInvalidoException;
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

import controller.Controlador;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
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
		private JButton btnVolver = new JButton("");
		private JLabel titulo = new JLabel("      Ingrese sus datos personales:");

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
			ImageIcon icono = new ImageIcon(PanelRegistro.class.getResource("/panels/back.png"));
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

		private JTextField usuarioField = new JTextField();
		private JPasswordField contraseñaField = new JPasswordField();
		private JPasswordField contraseñaConfField = new JPasswordField();
		private JTextField nombreField = new JTextField();
		private JTextField apellidoField = new JTextField();
		private JTextField fechaNacimientoField = new JTextField("DD/MM/AAAA");
		private JTextField direccionCorreoField = new JTextField();
		private JTextField telefonoField = new JTextField();
		private JComboBox tipoDocumentoBox = new JComboBox();
		private JTextField dniField = new JTextField();
		private JPanel panelInferior;

		private Font fuente = new Font("Dialog", Font.BOLD, 14);

		public PanelCentral() {
			setLayout(new BorderLayout(0, 0));

			panelRegistrarse.setLayout(new GridLayout(11, 4, 10, 30));
			panelRegistrarse.setBackground(Color.WHITE);
			add(panelRegistrarse, BorderLayout.CENTER);

			inicializarCampos();

			panelInferior = new PanelInferior();
			panelInferior.setBackground(Color.WHITE);
			add(panelInferior, BorderLayout.SOUTH);
		}

		private void inicializarCampos() {
			lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblUsuario);
			lblUsuario.setFont(fuente);

			usuarioField.setColumns(10);
			panelRegistrarse.add(usuarioField);
			usuarioField.setFont(fuente);

			lblContraseña.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblContraseña);
			lblContraseña.setFont(fuente);

			contraseñaField.setColumns(10);
			panelRegistrarse.add(contraseñaField);
			contraseñaField.setFont(new Font("Dialog", Font.BOLD, 15));

			lblConfirmarContraseña.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblConfirmarContraseña);
			lblConfirmarContraseña.setFont(fuente);

			contraseñaConfField.setColumns(10);
			panelRegistrarse.add(contraseñaConfField);
			contraseñaConfField.setFont(fuente);

			lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblNombre);
			lblNombre.setFont(fuente);

			nombreField.setColumns(10);
			panelRegistrarse.add(nombreField);
			nombreField.setFont(fuente);

			lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblApellido);
			lblApellido.setFont(fuente);

			apellidoField.setColumns(10);
			panelRegistrarse.add(apellidoField);
			apellidoField.setFont(fuente);

			lblFechaNacimiento.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblFechaNacimiento);
			lblFechaNacimiento.setFont(fuente);

			fechaNacimientoField.setColumns(10);
			panelRegistrarse.add(fechaNacimientoField);
			fechaNacimientoField.setFont(fuente);

			lblDireccionDeCorreo.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblDireccionDeCorreo);
			lblDireccionDeCorreo.setFont(fuente);

			direccionCorreoField.setColumns(10);
			panelRegistrarse.add(direccionCorreoField);
			direccionCorreoField.setFont(fuente);

			lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblTelefono);
			lblTelefono.setFont(fuente);

			telefonoField.setColumns(10);
			panelRegistrarse.add(telefonoField);
			telefonoField.setFont(fuente);

			lblTipoDeDocumento.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblTipoDeDocumento);
			lblTipoDeDocumento.setFont(fuente);

			tipoDocumentoBox.setModel(new DefaultComboBoxModel(new String[] { "DNI", "Pasaporte" }));
			panelRegistrarse.add(tipoDocumentoBox);
			tipoDocumentoBox.setFont(fuente);

			lblDni.setHorizontalAlignment(SwingConstants.CENTER);
			panelRegistrarse.add(lblDni);
			lblDni.setFont(fuente);

			dniField.setColumns(10);
			panelRegistrarse.add(dniField);
			dniField.setFont(fuente);
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

						try {
							if (controlador.agregarUsuario(usuarioField.getText(), contraseñaField.getPassword(),
									contraseñaConfField.getPassword(), nombreField.getText(), apellidoField.getText(),
									fechaNacimientoField.getText(), direccionCorreoField.getText(),
									telefonoField.getText(), dniField.getText())) {
								JOptionPane.showMessageDialog(null, "Su cuenta ha sido creada con éxito.");
								VistaTicketNow.changePanel("menu", PanelRegistro.this, controlador);
							}
						} catch (ContraseñaIncorrectaException m) {
							JOptionPane.showMessageDialog(null, m.getMessage(), "Ocurrió algo inesperado",
									JOptionPane.ERROR_MESSAGE);
							System.out.println(m.getMessage());
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

						}

						controlador.printDatabase();
					}
				});
			}

		}

	}

}

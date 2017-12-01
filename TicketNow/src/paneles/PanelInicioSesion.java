package paneles;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

import controlador.Controlador;

public class PanelInicioSesion extends JPanel {

	private JPanel panelSuperior;
	private JPanel panelInferior;
	private JPanel panelCentral;

	private Controlador controlador;

	public PanelInicioSesion(Controlador controlador, int ancho, int altura) {
		this.setLayout(new BorderLayout(0, 0));
		this.controlador = controlador;

		JPanel panelPrincipal = new JPanel();
		add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new BorderLayout(0, 0));

		panelCentral = new PanelCentral();
		panelCentral.setBackground(Color.WHITE);
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);

		panelSuperior = new PanelSuperior(ancho, altura);
		panelSuperior.setBackground(Color.WHITE);
		panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

		panelInferior = new PanelInferior();
		panelInferior.setBackground(Color.LIGHT_GRAY);
		panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

	}

	private class PanelSuperior extends JPanel {
		private JLabel imagen = new JLabel();

		public PanelSuperior(int ancho, int altura) {
			inicializarImagen(ancho, altura);
		}

		/**
		 * Método que inicializa la imagen segun el ancho y alto de la pantalla y la
		 * agrega al panel
		 * 
		 * @param anchoPantalla
		 *            Ancho de la resolución de la pantalla
		 * @param alturaPantalla
		 *            Altura de la resolución de la pantalla
		 */

		private void inicializarImagen(int anchoPantalla, int alturaPantalla) {
			int anchoImagen = (int) Math.ceil(anchoPantalla / 5);
			int alturaImagen = (int) Math.ceil(anchoPantalla / 5);

			imagen.setIcon(new ImageIcon(PanelInicioSesion.class.getResource("/paneles/logo.png")));
			imagen.setPreferredSize(new Dimension(anchoImagen, alturaImagen));
			imagen.setHorizontalAlignment(SwingConstants.CENTER);
			add(imagen);
		}
	}

	private class PanelInferior extends JPanel {
		private JLabel mensaje = new JLabel("Si aún no se ha registrado haga click aqui: ");
		private JButton btnRegistrarse = new JButton("Registrarse");

		public PanelInferior() {
			inicializarMensaje();
			inicializarBotones();
		}

		/**
		 * Método que inicializa el mensaje y lo agrega al panel
		 */
		private void inicializarMensaje() {
			mensaje.setFont(new Font("Dialog", Font.BOLD, 15));
			add(mensaje);
		}

		/**
		 * Método que inicializa el boton registrarse y lo agrega al panel
		 */
		private void inicializarBotones() {
			btnRegistrarse.setForeground(Color.WHITE);
			btnRegistrarse.setBackground(new Color(25, 25, 102));
			btnRegistrarse.setFont(new Font("Dialog", Font.BOLD, 15));
			add(btnRegistrarse);

			btnRegistrarse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VistaTicketNow.changePanel("registrarse", PanelInicioSesion.this, controlador);
				}
			});
		}
	}

	private class PanelCentral extends JPanel {

		private JPanel panelInicioSesion = new JPanel();

		private JComboBox tipoDeUsuarioField = new JComboBox();
		private JTextField emailField = new JTextField();
		private JPasswordField contraseñaField = new JPasswordField();

		private JLabel tipoDeUsuario = new JLabel("Tipo de usuario:");
		private JLabel email = new JLabel("Email:");
		private JLabel contraseña = new JLabel("Contraseña:");

		private JButton btnIniciarSesion = new JButton("Iniciar sesion");

		public PanelCentral() {
			this.setLayout(new BorderLayout(0, 0));

			panelInicioSesion.setBackground(Color.WHITE);
			add(panelInicioSesion, BorderLayout.CENTER);
			panelInicioSesion.setLayout(null);

			JPanel panelDerecho = new JPanel();
			panelDerecho.setBackground(Color.WHITE);
			FlowLayout flowLayout1 = (FlowLayout) panelDerecho.getLayout();
			flowLayout1.setHgap(100);
			add(panelDerecho, BorderLayout.WEST);

			JPanel panelIzquierdo = new JPanel();
			panelIzquierdo.setBackground(Color.WHITE);
			FlowLayout flowLayout2 = (FlowLayout) panelIzquierdo.getLayout();
			flowLayout2.setHgap(100);
			add(panelIzquierdo, BorderLayout.EAST);

			inicializarCampos();
			inicializarBoton();
		}

		/**
		 * Método que inicializa los campos a completar para iniciar sesión
		 */
		private void inicializarCampos() {
			panelInicioSesion.setBackground(Color.WHITE);
			add(panelInicioSesion, BorderLayout.CENTER);
			panelInicioSesion.setLayout(new GridLayout(0, 1, 0, 10));

			tipoDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
			panelInicioSesion.add(tipoDeUsuario);

			tipoDeUsuarioField.setModel(new DefaultComboBoxModel(new String[] { "Cliente", "Proveedor" }));
			panelInicioSesion.add(tipoDeUsuarioField);

			email.setHorizontalAlignment(SwingConstants.CENTER);
			panelInicioSesion.add(email);

			panelInicioSesion.add(emailField);
			emailField.setColumns(10);

			contraseña.setHorizontalAlignment(SwingConstants.CENTER);
			panelInicioSesion.add(contraseña);

			panelInicioSesion.add(contraseñaField);

			btnIniciarSesion.setForeground(Color.WHITE);
			btnIniciarSesion.setBackground(new Color(0, 102, 204));
			panelInicioSesion.add(btnIniciarSesion);

		}

		/**
		 * Método que inicializa el botón de iniciar sesion y define su listener
		 */
		private void inicializarBoton() {
			btnIniciarSesion.setForeground(Color.WHITE);
			btnIniciarSesion.setBackground(new Color(0, 102, 204));
			panelInicioSesion.add(btnIniciarSesion);

			btnIniciarSesion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					try {
						if (controlador.usuarioCorrecto(tipoDeUsuarioField.getSelectedItem().toString(),
								emailField.getText(), contraseñaField.getPassword())) {
							if (tipoDeUsuarioField.getSelectedItem().equals("Cliente")) {
								VistaTicketNow.changePanel("cliente", PanelInicioSesion.this, controlador);
							} else {
								VistaTicketNow.changePanel("proveedor", PanelInicioSesion.this, controlador);
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"El usuario y contraseña no coinciden. Inténtelo nuevamente.",
									"Ocurrió algo inesperado", JOptionPane.ERROR_MESSAGE);
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			JLabel label = new JLabel();
			panelInicioSesion.add(label);
		}
	}

}

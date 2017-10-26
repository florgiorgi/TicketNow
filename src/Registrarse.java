package panels;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.BoxLayout;

public class Registrarse extends JPanel {

	private JPanel panelSuperior;
	private JPanel panelDerecho;
	private JPanel panelIzquierdo;
	private JPanel panelCentral;
	private JPanel panelInferior;

	/**
	 * Create the panel.
	 */
	public Registrarse(int ancho, int altura) {

		setLayout(new BorderLayout(0, 0));

		panelSuperior = new PanelSuperior();
		panelSuperior.setBackground(Color.WHITE);
		add(panelSuperior, BorderLayout.NORTH);

		panelCentral = new PanelCentral();
		panelCentral.setBackground(Color.WHITE);
		add(panelCentral, BorderLayout.CENTER);

		panelDerecho = new JPanel();
		panelDerecho.setBackground(Color.WHITE);
		FlowLayout flowLayout_1 = (FlowLayout) panelDerecho.getLayout();
		flowLayout_1.setVgap(20);
		flowLayout_1.setHgap(30);
		add(panelDerecho, BorderLayout.WEST);

		panelIzquierdo = new JPanel();
		panelIzquierdo.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panelIzquierdo.getLayout();
		flowLayout.setVgap(20);
		flowLayout.setHgap(30);
		add(panelIzquierdo, BorderLayout.EAST);

		panelInferior = new PanelInferior();
		panelInferior.setBackground(Color.WHITE);
		add(panelInferior, BorderLayout.SOUTH);

	}

	private class PanelSuperior extends JPanel {
		private JButton btnBack = new JButton("");
		private JLabel title = new JLabel(" Ingrese sus datos personales:");

		public PanelSuperior() {
			setLayout(new BorderLayout(0, 0));
			setLabels();
			setButtons();
		}

		private void setLabels() {
			title.setFont(new Font("Dialog", Font.BOLD, 22));
			add(title, BorderLayout.CENTER);
		}

		private void setButtons() {
			btnBack.setBackground(Color.WHITE);
			ImageIcon icon = new ImageIcon(Registrarse.class.getResource("/panels/back.png"));
			btnBack.setIcon(icon);
			btnBack.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));

			add(btnBack, BorderLayout.WEST);

			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TicketNow.changePanel("menu", Registrarse.this);
				}
			});
		}
	}

	private class PanelCentral extends JPanel {
		private JLabel lblUsuario = new JLabel("Usuario:");
		private JLabel lblContraseña = new JLabel("Contraseña:");
		private JLabel lblConfirmarContraseña = new JLabel("Confirmar contraseña:");
		private JLabel lblNombre = new JLabel("Nombre:");
		private JLabel lblApellido = new JLabel("Apellido:");
		private JLabel lblDireccionDeCorreo = new JLabel("E-mail:");
		private JLabel lblTipoDeDocumento = new JLabel("Tipo de documento:");
		private JLabel lblNumeroDeDocumento = new JLabel("Numero de documento:");

		private JTextField usuarioField = new JTextField();
		private JPasswordField contraseñaField = new JPasswordField();
		private JPasswordField contraseñaConfField = new JPasswordField();
		private JTextField nombreField = new JTextField();
		private JTextField apellidoField = new JTextField();
		private JTextField direccionCorreoField = new JTextField();
		private JComboBox tipoDocumentoBox = new JComboBox();
		private JTextField numeroDeDocumentoField = new JTextField();

		public PanelCentral() {
			setLayout(new GridLayout(9, 4, 5, 10));
			setLabelsFields();
		}

		private void setLabelsFields() {
			lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblUsuario);

			add(usuarioField);
			usuarioField.setColumns(10);

			lblContraseña.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblContraseña);

			contraseñaField.setColumns(10);
			add(contraseñaField);

			lblConfirmarContraseña.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblConfirmarContraseña);

			contraseñaConfField.setColumns(10);
			add(contraseñaConfField);

			lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblNombre);

			nombreField.setColumns(10);
			add(nombreField);

			lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblApellido);

			apellidoField.setColumns(10);
			add(apellidoField);

			lblDireccionDeCorreo.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblDireccionDeCorreo);

			direccionCorreoField.setColumns(10);
			add(direccionCorreoField);

			lblTipoDeDocumento.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblTipoDeDocumento);

			tipoDocumentoBox.setModel(new DefaultComboBoxModel(new String[] { "Seleccione", "DNI", "Pasaporte" }));
			add(tipoDocumentoBox);

			lblNumeroDeDocumento.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblNumeroDeDocumento);

			numeroDeDocumentoField.setColumns(10);
			add(numeroDeDocumentoField);
		}

	}

	private class PanelInferior extends JPanel {
		JPanel panel = new JPanel();
		JButton btnRegistrarse = new JButton("Registrarse");

		public PanelInferior() {
			setButton();
		}

		private void setButton() {
			btnRegistrarse.setFont(new Font("Dialog", Font.BOLD, 15));
			btnRegistrarse.setVerticalAlignment(SwingConstants.TOP);
			btnRegistrarse.setForeground(new Color(255, 255, 255));
			btnRegistrarse.setBackground(new Color(0, 102, 204));
			add(btnRegistrarse);
		}
	}

}

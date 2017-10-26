package panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class MenuPrincipal extends JPanel {

	private JPanel panelSuperior;
	private JPanel panelInferior;
	private JPanel panelCentral;
	
	public MenuPrincipal(int ancho, int altura) {
		
		setLayout(new BorderLayout(0, 0));

		JPanel panelPrincipal = new JPanel();
		add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new BorderLayout(0, 0));

		panelCentral = new PanelCentral();
		panelCentral.setBackground(new Color(255, 255, 255));
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);
		
		panelSuperior = new PanelSuperior(ancho, altura);
		panelSuperior.setBackground(Color.WHITE);
		panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
		
		panelInferior = new PanelInferior();
		panelInferior.setBackground(new Color(204, 204, 204));
		panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

	}

	private class PanelSuperior extends JPanel {
		JLabel image = new JLabel();
		
		public PanelSuperior(int ancho, int altura) {
			setLabels(ancho, altura);
		}

		private void setLabels(int anchoPantalla, int alturaPantalla) {
			image.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/panels/logo.png")));
			int anchoImagen = (int) Math.ceil(anchoPantalla / 5);
			int alturaImagen = (int) Math.ceil(anchoPantalla / 5);		
			
			if(anchoImagen >= 277 && alturaPantalla >= 178) {
				Dimension dim = new Dimension(anchoImagen, alturaImagen);
				image.setPreferredSize(dim);
			}
			
			add(image);
		}
	}

	private class PanelInferior extends JPanel {
		JLabel message = new JLabel("Si aún no se ha registrado haga click aquí: ");
		JButton btnRegistrarse = new JButton("Registrarse");

		public PanelInferior() {
			setLabels();
			setButtons();
		}

		private void setLabels() {
			add(message);
		}

		private void setButtons() {
			btnRegistrarse.setForeground(Color.WHITE);
			btnRegistrarse.setBackground(new Color(25, 25, 102));
			add(btnRegistrarse);
			btnRegistrarse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TicketNow.changePanel("registrarse", MenuPrincipal.this);
				}
			});
		}
	}

	private class PanelCentral extends JPanel {

		private JPanel panelInicioSesion = new JPanel();
		private JTextField textField = new JTextField();
		private JPasswordField passwordField = new JPasswordField();
		private JButton btnIniciarSesion = new JButton("Iniciar sesión");
		private JComboBox comboBox = new JComboBox();

		public PanelCentral() {
			setLayout(new BorderLayout(0, 0));
			setLabels();
			setPanel();
		}

		private void setPanel() {
			panelInicioSesion.setBackground(Color.WHITE);
			add(panelInicioSesion, BorderLayout.CENTER);
			panelInicioSesion.setLayout(new GridLayout(0, 1, 0, 10));

			JLabel tipoDeUsuario = new JLabel("Tipo de usuario:");
			tipoDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
			panelInicioSesion.add(tipoDeUsuario);

			comboBox.setModel(new DefaultComboBoxModel(new String[] { "Seleccione", "Cliente", "Proveedor" }));
			panelInicioSesion.add(comboBox);

			

			JLabel nombre = new JLabel("Nombre:");
			nombre.setHorizontalAlignment(SwingConstants.CENTER);
			panelInicioSesion.add(nombre);

			panelInicioSesion.add(textField);
			textField.setColumns(10);

			

			JLabel contraseña = new JLabel("Contraseña:");
			contraseña.setHorizontalAlignment(SwingConstants.CENTER);
			panelInicioSesion.add(contraseña);

			panelInicioSesion.add(passwordField);


			btnIniciarSesion.setForeground(Color.WHITE);
			btnIniciarSesion.setBackground(new Color(0, 102, 204));
			panelInicioSesion.add(btnIniciarSesion);
			
			JLabel label = new JLabel("");
			panelInicioSesion.add(label);

		}

		private void setLabels() {
			JLabel labelWest = new JLabel("                                                ");
			add(labelWest, BorderLayout.WEST);

			JLabel labelEast = new JLabel("                                                ");
			add(labelEast, BorderLayout.EAST);



		}

		private void setButtons() {
			btnIniciarSesion.setForeground(Color.WHITE);
			btnIniciarSesion.setBackground(new Color(0, 102, 204));
			panelInicioSesion.add(btnIniciarSesion);
		}
	}

}
 
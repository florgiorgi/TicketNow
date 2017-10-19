package panels;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import javax.swing.JLabel;

import javax.swing.SwingConstants;

public class MenuPrincipal extends JComponent {

	private JButton button1 = new JButton("Iniciar sesión");
	private JButton button2 = new JButton("Registrarse");
	private JLabel image = new JLabel();

	public MenuPrincipal(int ancho, int altura) {

		this.setBackground(Color.WHITE);
		this.setBounds(0, 0, ancho, altura);

		setButtons(ancho, altura);
		setLabels(ancho, altura);

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TicketNow.changePanel("iniciar sesion", MenuPrincipal.this);
			}
		});

		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TicketNow.changePanel("registrarse", MenuPrincipal.this);
			}
		});
	}

	private void setButtons(int ancho, int altura) {
		button1.setBackground(new Color(0, 102, 204));
		button1.setForeground(new Color(255, 255, 255));
		button1.setBounds(ancho / 2 - 70, altura / 2, ancho - 533, altura - 628);
		this.add(button1);

		button2.setBackground(new Color(0, 102, 204));
		button2.setForeground(new Color(255, 255, 255));
		button2.setBounds(ancho / 2 - 70, altura / 2 + 70, ancho - 533, altura - 628);
		this.add(button2);

	}

	private void setLabels(int ancho, int altura) {
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setIcon(new ImageIcon(TicketNow.class.getResource("/panels/logo.png")));
		image.setBounds(0, 0, ancho, altura / 2);
		this.add(image);
	}
}

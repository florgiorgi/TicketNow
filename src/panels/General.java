package panels;


import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class General extends JComponent {

	private JLabel image = new JLabel();
	private JLabel title = new JLabel("¡Bienvenido a TicketNow!");
	private JButton button1 = new JButton();
	private JButton button2 = new JButton();
	private JButton button3 = new JButton();
	
	public General(int ancho, int altura) {
		setLabels(ancho, altura);
		setButtons(ancho, altura);
		
	}

	private void setLabels(int ancho, int altura) {
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("DejaVu Sans", Font.PLAIN, ancho - altura + 20));
		title.setBounds(0, 0, ancho, altura / 2 + 100);
		this.add(title);

		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setIcon(new ImageIcon(TicketNow.class.getResource("/panels/logo.png")));
		image.setBounds(ancho - 660, altura - 650, ancho - 430, altura - 540);
		this.add(image);
	}
	
	private void setButtons(int ancho, int altura) {
		ImageIcon icon1 = new ImageIcon(TicketNow.class.getResource("/panels/exit.png"));
		button1.setIcon(icon1);
		button1.setBackground(Color.WHITE);
		button1.setBorderPainted(false);
		button1.setBounds(ancho - 100, altura - 625, icon1.getIconWidth(), icon1.getIconHeight());
		this.add(button1);

		ImageIcon icon2 = new ImageIcon(TicketNow.class.getResource("/panels/carrito.png"));
		button2.setIcon(icon2);
		button1.setBackground(Color.WHITE);
		button2.setBorderPainted(false);
		button2.setBounds(ancho - 170, altura - 625, icon2.getIconWidth(), icon2.getIconHeight());
		this.add(button2);
		
		ImageIcon icon3 = new ImageIcon(TicketNow.class.getResource("/panels/perfil.png"));
		button3.setIcon(icon3);
		button1.setBackground(Color.WHITE);
		button3.setBorderPainted(false);
		button3.setBounds(ancho - 240, altura - 625, icon3.getIconWidth(), icon3.getIconHeight());
		this.add(button3);
	}
}

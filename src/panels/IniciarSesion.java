package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class IniciarSesion extends JComponent {

	private JTextField field1 = new JTextField();
	private JPasswordField field2 = new JPasswordField();
	private JButton button1 = new JButton("Iniciar sesión");
	private JButton button2 = new JButton();
	private static int times = 0;
	private final static int MAX_TIMES = 3;

	private JLabel title = new JLabel("Ingrese a TicketNow");
	private JLabel title1 = new JLabel("Usuario");
	private JLabel title2 = new JLabel("Contraseña");
	
	public IniciarSesion(int ancho, int altura) {

		setTextFields(ancho, altura);
		setButtons(ancho, altura);
		setLabels(ancho, altura);

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				times++;
				boolean validate = validateFields();
				if (validate && times <= MAX_TIMES)
					TicketNow.changePanel("ticketnow", IniciarSesion.this);

				if (!validate)
					JOptionPane.showMessageDialog(null, "Los datos ingresados son incorrectos. Vuelva a intentarlo.",
							"Ocurrió algo inesperado", JOptionPane.ERROR_MESSAGE);
				else if(times > MAX_TIMES)
					JOptionPane.showMessageDialog(null,
							"Su cuenta ha sido momentaneamente bloqueada por cuestiones de seguridad",
							"Ocurrió algo inesperado", JOptionPane.WARNING_MESSAGE);

			}

		});

		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TicketNow.changePanel("menu", IniciarSesion.this);
			}
		});

	}

	private void setTextFields(int ancho, int altura) {
		field1.setBounds(ancho / 2 - 70, altura / 2 - 100, ancho - 533, altura - 628);
		this.add(field1);

		field2.setBounds(ancho / 2 - 70, altura / 2 - 15, ancho - 533, altura - 628);
		this.add(field2);
	}

	private void setButtons(int ancho, int altura) {
		button1.setBackground(new Color(0, 102, 204));
		button1.setForeground(new Color(255, 255, 255));
		button1.setBounds(ancho / 2 - 70, altura / 2 + 50, ancho - 533, altura - 628);
		this.add(button1);

		ImageIcon icon = new ImageIcon(TicketNow.class.getResource("/panels/back.png"));
		button2.setIcon(icon);
		button2.setBackground(Color.WHITE);
		button2.setBounds(10, 10, icon.getIconWidth(), icon.getIconHeight());
		button2.setBorderPainted(false);
		this.add(button2);
	}

	private void setLabels(int ancho, int altura) {
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("DejaVu Sans", Font.PLAIN, ancho - altura + 30));
		title.setBounds(0, 0, ancho, altura / 2);
		this.add(title);

		title1.setHorizontalAlignment(SwingConstants.CENTER);
		title1.setBounds(ancho / 2 - 70, altura / 2 - 135, ancho - 533, altura - 628);
		this.add(title1);

		title2.setHorizontalAlignment(SwingConstants.CENTER);
		title2.setBounds(ancho / 2 - 70, altura / 2 - 50, ancho - 533, altura - 628);
		this.add(title2);
	}

	private boolean validateFields() {
		if (field1.getText().equals("") || field2.getPassword().equals(""))
			return false;

		return true;
	}

}
package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Registrarse extends JComponent {

	private JLabel title = new JLabel("Crear cuenta");

	private JButton button1 = new JButton("Registrarse");
	private JButton button2 = new JButton();

	private JLabel title1 = new JLabel("Usuario");
	private JLabel title2 = new JLabel("Contraseña");
	private JLabel title3 = new JLabel("Confirmar contraseña");
	private JLabel title4 = new JLabel("Nombre");
	private JLabel title5 = new JLabel("Apellido");
	private JLabel title6 = new JLabel("Fecha de nacimiento");
	private JLabel title7 = new JLabel("Direccion de correo");
	private JLabel title8 = new JLabel("Telefono");
	private JLabel title9 = new JLabel("Tipo de documento");
	private JLabel title10 = new JLabel("Numero de documento");

	private JTextField field1 = new JTextField();
	private JTextField field2 = new JTextField();
	private JTextField field3 = new JTextField();
	private JTextField field4 = new JTextField();
	private JTextField field5 = new JTextField();
	private JTextField field6 = new JTextField();
	private JTextField field7 = new JTextField();
	private JTextField field8 = new JTextField();
	private JTextField field9 = new JTextField();

	private ArrayList<JTextField> fields = new ArrayList<JTextField>();

	private JComboBox<String> field10 = new JComboBox<String>();

	private JRadioButton rbutton1 = new JRadioButton("Celular");
	private JRadioButton rbutton2 = new JRadioButton("Telefono fijo");
	private ButtonGroup bg = new ButtonGroup();

	public Registrarse(int ancho, int altura) {

		setTextFields(ancho, altura);
		setButtons(ancho, altura);
		setLabels(ancho, altura);

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean validate = validateFields();

				if (!validate)
					JOptionPane.showMessageDialog(null, "Los datos ingresados son incorrectos. Vuelva a intentarlo.",
							"Ocurrió algo inesperado", JOptionPane.ERROR_MESSAGE);
				else {
					JOptionPane.showMessageDialog(null, "Su cuenta ha sido creada con éxito.");
					TicketNow.changePanel("menu", Registrarse.this);
				}
			}
		});

		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TicketNow.changePanel("menu", Registrarse.this);
			}
		});

	}

	private void setTextFields(int ancho, int altura) {

		fields.add(field1);
		field1.setBounds(ancho - 583, altura - 563, ancho - 483, altura - 638);
		this.add(field1);

		fields.add(field2);
		field2.setBounds(ancho - 583, altura - 493, ancho - 483, altura - 638);
		this.add(field2);

		fields.add(field3);
		field3.setBounds(ancho - 333, altura - 493, ancho - 483, altura - 638);
		this.add(field3);

		fields.add(field4);
		field4.setBounds(ancho - 583, altura - 423, ancho - 483, altura - 638);
		this.add(field4);

		fields.add(field5);
		field5.setBounds(ancho - 333, altura - 423, ancho - 483, altura - 638);
		this.add(field5);

		fields.add(field6);
		field6.setBounds(ancho - 583, altura - 353, ancho - 483, altura - 638);
		this.add(field6);

		fields.add(field7);
		field7.setBounds(ancho - 583, altura - 283, ancho - 483, altura - 638);
		this.add(field7);

		fields.add(field8);
		field8.setBounds(ancho - 583, altura - 213, ancho - 483, altura - 638);
		this.add(field8);

		fields.add(field9);
		field9.setBounds(ancho - 333, altura - 143, ancho - 483, altura - 638);
		this.add(field9);

		field10.setBounds(ancho - 583, altura - 143, ancho - 483, altura - 638);
		field10.addItem("DNI");
		field10.addItem("Pasaporte");
		this.add(field10);

	}

	private void setButtons(int ancho, int altura) {
		button1.setFont(new Font("DejaVu Sans", Font.BOLD, ancho - altura));
		button1.setBackground(new Color(0, 102, 204));
		button1.setForeground(new Color(255, 255, 255));
		button1.setBounds(ancho / 2 - 75, altura - 90, ancho - 533, altura - 628);
		this.add(button1);

		ImageIcon icon = new ImageIcon(TicketNow.class.getResource("/panels/back.png"));
		button2.setIcon(icon);
		button2.setBackground(Color.WHITE);
		button2.setBounds(10, 10, icon.getIconWidth(), icon.getIconHeight());
		button2.setBorderPainted(false);
		this.add(button2);

		rbutton1.setBounds(ancho - 378, altura - 218, ancho - 533, altura - 648);
		rbutton1.setBackground(Color.WHITE);
		rbutton2.setBounds(ancho - 378, altura - 198, ancho - 533, altura - 648);
		rbutton2.setBackground(Color.WHITE);
		this.add(rbutton1);
		this.add(rbutton2);

		bg.add(rbutton1);
		bg.add(rbutton2);
	}

	private void setLabels(int ancho, int altura) {
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("DejaVu Sans", Font.PLAIN, ancho - altura + 15));
		title.setBounds(100, 10, 200, 70);
		this.add(title);

		title1.setBounds(ancho - 583, altura - 598, ancho - 483, altura - 628);
		this.add(title1);

		title2.setBounds(ancho - 583, altura - 528, ancho - 483, altura - 628);
		this.add(title2);

		title3.setBounds(ancho - 333, altura - 528, ancho - 483, altura - 628);
		this.add(title3);

		title4.setBounds(ancho - 583, altura - 458, ancho - 483, altura - 628);
		this.add(title4);

		title5.setBounds(ancho - 333, altura - 458, ancho - 483, altura - 628);
		this.add(title5);

		title6.setBounds(ancho - 583, altura - 388, ancho - 483, altura - 628);
		this.add(title6);

		title7.setBounds(ancho - 583, altura - 318, ancho - 483, altura - 628);
		this.add(title7);

		title8.setBounds(ancho - 583, altura - 248, ancho - 483, altura - 628);
		this.add(title8);

		title9.setBounds(ancho - 583, altura - 178, ancho - 483, altura - 628);
		this.add(title9);

		title10.setBounds(ancho - 333, altura - 178, ancho - 483, altura - 628);
		this.add(title10);

	}

	private boolean validateFields() {
		for (JTextField f : fields) {
			if (f.getText().equals(""))
				return false;
		}

		if(!field1.getText().equals(field2.getText()))
			return false;
		
		
		
		return true;
	}

}

package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import controlador.Controlador;

public class PanelEliminarCuenta extends JPanel {

	private JPanel panelSuperior;
	private JPanel panelInferior;
	private JPanel panelCentral;
	JPanel panelPrincipal = new JPanel();
	
	Controlador controlador;

	public PanelEliminarCuenta(Controlador controlador) {

		this.setLayout(new BorderLayout(0, 0));
		this.controlador = controlador;

		add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new BorderLayout(0, 0));

		panelCentral = new PanelCentral();
		panelCentral.setBackground(Color.WHITE);
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);

		panelInferior = new PanelInferior();
		panelInferior.setBackground(Color.WHITE);
		panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

	}


	public class PanelCentral extends JPanel {

		JPanel panelCentralSuperior = new JPanel();
		JLabel lblpor = new JLabel("¿Por qué quieres darte de baja de nuestro sistema?");

		JPanel panelCentralCentral = new JPanel();
		JRadioButton rdbtn1 = new JRadioButton("No entiendo como usar TicketNow");
		JRadioButton rdbtn2 = new JRadioButton("No creo que TicketNow sea útil");
		JRadioButton rdbtn3 = new JRadioButton("Uso otra aplicación para comprar mis entradas");
		JRadioButton rdbtn4 = new JRadioButton("Me preocupa la privacidad de mis datos personales");
		JRadioButton rdbtn5 = new JRadioButton("Recibo muchas ofertas por correo electrónico");
		JRadioButton rdbtn6 = new JRadioButton("Tengo otra cuenta en TicketNow");
		JRadioButton rdbtn7 = new JRadioButton("Otro motivo");
		JLabel lblPorFavor = new JLabel("Mas información");

		JPanel panelCentralIzquierdo = new JPanel();
		JPanel panelCentralDerecho = new JPanel();
		JPanel panelCentralInferior = new JPanel();

		public PanelCentral() {
			setLayout(new BorderLayout(0, 0));

			panelCentralSuperior.setBackground(Color.WHITE);
			add(panelCentralSuperior, BorderLayout.NORTH);

			inicializarPanelCentralSuperior();

			panelCentralCentral.setBackground(Color.WHITE);
			add(panelCentralCentral, BorderLayout.CENTER);
			panelCentralCentral.setLayout(new BoxLayout(panelCentralCentral, BoxLayout.Y_AXIS));

			inicializarPanelCentralCentral();

			panelCentralDerecho.setBackground(Color.WHITE);
			add(panelCentralDerecho, BorderLayout.EAST);

			JLabel label_1 = new JLabel("                                          ");
			label_1.setBackground(Color.WHITE);
			panelCentralDerecho.add(label_1);

			panelCentralIzquierdo.setBackground(Color.WHITE);
			add(panelCentralIzquierdo, BorderLayout.WEST);

			JLabel label = new JLabel("                                          ");
			panelCentralIzquierdo.add(label);

			panelCentralInferior.setBackground(Color.WHITE);
			add(panelCentralInferior, BorderLayout.SOUTH);
			panelCentralInferior.setLayout(new BorderLayout(0, 0));

			inicializarPanelCentralInferior();

		}

		public void inicializarPanelCentralSuperior() {
			lblpor.setFont(new Font("Dialog", Font.PLAIN, 18));
			panelCentralSuperior.add(lblpor);
		}

		public void inicializarPanelCentralCentral() {
			JLabel lblNewLabel = new JLabel(" ");
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 30));
			panelCentralCentral.add(lblNewLabel);

			rdbtn1.setFont(new Font("Dialog", Font.PLAIN, 12));
			rdbtn1.setBackground(Color.WHITE);
			panelCentralCentral.add(rdbtn1);

			rdbtn2.setFont(new Font("Dialog", Font.PLAIN, 12));
			rdbtn2.setBackground(Color.WHITE);
			panelCentralCentral.add(rdbtn2);

			rdbtn3.setFont(new Font("Dialog", Font.PLAIN, 12));
			rdbtn3.setBackground(Color.WHITE);
			panelCentralCentral.add(rdbtn3);

			rdbtn4.setFont(new Font("Dialog", Font.PLAIN, 12));
			rdbtn4.setBackground(Color.WHITE);
			panelCentralCentral.add(rdbtn4);

			rdbtn5.setFont(new Font("Dialog", Font.PLAIN, 12));
			rdbtn5.setBackground(Color.WHITE);
			panelCentralCentral.add(rdbtn5);

			rdbtn6.setFont(new Font("Dialog", Font.PLAIN, 12));
			rdbtn6.setBackground(Color.WHITE);
			panelCentralCentral.add(rdbtn6);

			rdbtn7.setFont(new Font("Dialog", Font.PLAIN, 12));
			rdbtn7.setBackground(Color.WHITE);
			panelCentralCentral.add(rdbtn7);
			
			ButtonGroup group = new ButtonGroup();
		    group.add(rdbtn1);
		    group.add(rdbtn2);
		    group.add(rdbtn3);
		    group.add(rdbtn4);
		    group.add(rdbtn5);
		    group.add(rdbtn6);
		    group.add(rdbtn7);
		    

			JLabel label_4 = new JLabel(" ");
			label_4.setFont(new Font("Dialog", Font.BOLD, 30));
			panelCentralCentral.add(label_4);

			lblPorFavor.setFont(new Font("Dialog", Font.PLAIN, 12));
			panelCentralCentral.add(lblPorFavor);
		}

		public void inicializarPanelCentralInferior() {
			JPanel panelInferiorIzquierdo = new JPanel();
			panelInferiorIzquierdo.setBackground(Color.WHITE);
			panelCentralInferior.add(panelInferiorIzquierdo, BorderLayout.WEST);

			JLabel label_2 = new JLabel("                                          ");
			panelInferiorIzquierdo.add(label_2);

			JPanel panelInferiorDerecho = new JPanel();
			panelInferiorDerecho.setBackground(Color.WHITE);
			panelCentralInferior.add(panelInferiorDerecho, BorderLayout.EAST);

			JLabel label_3 = new JLabel("                                          ");
			panelInferiorDerecho.add(label_3);

			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panelCentralInferior.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

			JTextArea textArea = new JTextArea();
			textArea.setRows(13);
			panel.add(textArea);
			Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
			textArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(2, 2, 3, 4)));
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);

			JLabel lblCaracteres = new JLabel("Caracteres totales: 0");
			lblCaracteres.setFont(new Font("Dialog", Font.PLAIN, 12));
			lblCaracteres.setBackground(new Color(255, 255, 255));
			panel.add(lblCaracteres);

			String texto = textArea.getText();
			int tamañoTexto = texto.length();

			textArea.addKeyListener(new KeyListener() {
				int cantidad = 0;
				public void keyPressed(KeyEvent keyEvent) {
					 if(keyEvent.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
						 if(cantidad != 0)
							 cantidad--;
					 }
					 else
						 cantidad++;
					lblCaracteres.setText("Caracteres totales: " + cantidad);
				}

				@Override
				public void keyTyped(KeyEvent e) {
					
					
				}

				@Override
				public void keyReleased(KeyEvent e) {
					
					
				}
			});

			JLabel lblNewLabel_1 = new JLabel(" ");
			lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 30));
			lblNewLabel_1.setBackground(new Color(255, 255, 255));
			panel.add(lblNewLabel_1);
		}

	}

	public class PanelInferior extends JPanel {

		JButton btnEliminarCuenta = new JButton("Eliminar Cuenta");

		public PanelInferior() {
			FlowLayout flowLayout1 = (FlowLayout) this.getLayout();
			flowLayout1.setVgap(20);
			btnEliminarCuenta.setBackground(Color.RED);
			btnEliminarCuenta.setForeground(Color.WHITE);
			btnEliminarCuenta.setFont(new Font("Dialog", Font.BOLD, 15));
			add(btnEliminarCuenta);
		
			btnEliminarCuenta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Su cuenta ha sido eliminada con éxito");
					VistaTicketNow.changePanel("menu", PanelEliminarCuenta.this, controlador);
				}
			});
		}
		
		
	}
}

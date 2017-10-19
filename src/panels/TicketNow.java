package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.IOException;

public class TicketNow {

	private JFrame frame  = new JFrame("TicketNow Application");
	private static JPanel panel = new JPanel();
	private static Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private static int ancho = pantalla.width / 2;
	private static int altura = pantalla.height - 100;
	private static Dimension frameSize = new Dimension(ancho, altura);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicketNow window = new TicketNow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TicketNow() {
		try {
			initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initialize() throws IOException {
		initializePanel();
		initializeFrame();
	}

	private void initializePanel() {
		panel.setPreferredSize(new Dimension(ancho, altura));
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BorderLayout());
		changePanel("menu");
	}

	private void initializeFrame() {
		frame.add(panel);
		frame.setResizable(false);
		frame.setBounds(pantalla.width / 2 - frameSize.width / 2, pantalla.height / 2 - frameSize.height / 2,
				frameSize.width, frameSize.height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void changePanel(String option) {
		switch (option){
		case "menu":
			panel.add(new MenuPrincipal(ancho, altura), BorderLayout.CENTER); break;
		case "iniciar sesion":
			panel.add(new IniciarSesion(ancho, altura), BorderLayout.CENTER ); break;
		case "registrarse":
			panel.add(new Registrarse(ancho, altura), BorderLayout.CENTER); break;
		case "ticketnow":
			panel.add(new General(ancho, altura), BorderLayout.CENTER); break;
		}
	}
	
	public static void changePanel(String option, JComponent comp){
		panel.remove(comp); 
		panel.revalidate(); 
		changePanel(option); 
		panel.repaint(); 
	}

}

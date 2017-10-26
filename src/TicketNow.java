package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.IOException;

public class TicketNow {

	private static JFrame frame  = new JFrame("TicketNow Application");
	private static JPanel panel = new JPanel();
	private static Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private static int anchoPantalla = pantalla.width;
	private static int alturaPantalla = pantalla.height;
	private static int anchoPanel = alturaPantalla;
	private static int alturaPanel = alturaPantalla - ( 100 * (anchoPantalla/alturaPantalla) / 2 );

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
		panel.setPreferredSize(new Dimension(anchoPanel, alturaPanel)); 
		panel.setBackground(Color.WHITE); 
		panel.setLayout(new BorderLayout()); 
		changePanel("menu");  
		
	}

	private void initializeFrame() {
		frame.add(panel);
		frame.setResizable(true);
		frame.setBounds(0, 0, anchoPanel, alturaPanel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private static void changePanel(String option) {
		switch (option){
		case "menu":
			panel.add(new MenuPrincipal(anchoPantalla, alturaPantalla), BorderLayout.CENTER);
				break;
		case "registrarse":
			panel.add(new Registrarse(anchoPantalla, alturaPantalla), BorderLayout.CENTER);
		}
	}
	
	public static void changePanel(String option, JPanel comp){
		panel.remove(comp); 
		panel.revalidate(); 
		changePanel(option); 
		panel.repaint(); 
	}

	

}
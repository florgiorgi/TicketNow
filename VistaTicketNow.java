package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controlador;

public class VistaTicketNow {

	private static JFrame frame = new JFrame("TicketNow");
	private static JPanel panel = new JPanel();
	private static Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();

	private static int anchoPantalla = pantalla.width;
	private static int alturaPantalla = pantalla.height;

	private static int anchoPanel = alturaPantalla;
	private static int alturaPanel = alturaPantalla - (100 * (anchoPantalla / alturaPantalla));

	public VistaTicketNow(Controlador controlador) {
		initializePanel();
		initializeFrame();
		changePanel("menu", controlador);
	}
	
	/**
	 * Método que inicializa el panel principal de la aplicación
	 */
	private void initializePanel() {
		panel.setPreferredSize(new Dimension(anchoPanel, alturaPanel)); 
		panel.setBackground(Color.WHITE); 
		panel.setLayout(new BorderLayout()); 
	}

	/**
	 * Método que inicializa la ventana de la aplicación
	 */
	private void initializeFrame() {
		frame.add(panel);
		frame.setResizable(true);
		frame.setBounds(0, 0, anchoPanel, alturaPanel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Método que cambia el panel actual por uno nuevo
	 * @param panelNuevo
	 *            Panel a insertar en la ventana
	 * @param controlador
	 *            Controlador para el constructor del nuevo panel
	 */
	private static void changePanel(String panelNuevo, Controlador controlador) {
		switch (panelNuevo) {
		case "menu":
			panel.add(new PanelInicioSesion(controlador, anchoPantalla, alturaPantalla), BorderLayout.CENTER);
			break;
		case "registrarse":
			panel.add(new PanelRegistro(controlador), BorderLayout.CENTER);
			break;
		case "cliente":
			panel.add(new PanelPrincipalCliente(controlador), BorderLayout.CENTER);
			break;
		case "proveedor":
			panel.add(new PanelPrincipalProveedor(controlador), BorderLayout.CENTER);
			break;
		}
	}

	/**
	 * Método que cambia el panel actual
	 * @param panelNuevo
	 *            Panel a insertar en la ventana
	 * @param panelActual
	 *            Panel a remover de la ventana
	 * @param controlador
	 *            Controlador para el constructor del nuevo panel
	 */
	public static void changePanel(String panelNuevo, JPanel panelActual, Controlador controlador) {
		panel.remove(panelActual);
		panel.revalidate();
		changePanel(panelNuevo, controlador);
	}

}
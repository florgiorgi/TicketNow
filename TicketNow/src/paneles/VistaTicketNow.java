package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controlador.Controlador;
import espectaculo.Espectaculo;

public class VistaTicketNow {

	private static JFrame frame = new JFrame("TicketNow");
	private static JPanel panel = new JPanel();
	private static Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();

	private static int anchoPantalla = pantalla.width;
	private static int alturaPantalla = pantalla.height;

	private static int anchoPanel = alturaPantalla;
	private static int alturaPanel = alturaPantalla - (70 * (anchoPantalla / alturaPantalla));

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
		}
	}
	
	private static void changePanel(String panelNuevo, Controlador controlador, String usuario) {
		switch (panelNuevo) {
		case "cliente":
			panel.add(new PanelPrincipalCliente(controlador, usuario), BorderLayout.CENTER);
			break;
		case "proveedor":
			panel.add(new PanelPrincipalProveedor(controlador, usuario), BorderLayout.CENTER);
			break;
		case "agregar":
			panel.add(new PanelAgregarEspectaculo(controlador, usuario), BorderLayout.CENTER);
			break;
		case "perfilProveedor":
			panel.add(new PanelPerfilProveedor(controlador, usuario), BorderLayout.CENTER);
			break;
		case "bajaProveedor":
			panel.add(new PanelEliminarCuentaProveedor(controlador, usuario), BorderLayout.CENTER);
			break;
		case "bajaCliente":
			panel.add(new PanelEliminarCuentaCliente(controlador, usuario), BorderLayout.CENTER);
			break;
		case "perfilCliente":
			panel.add(new PanelPerfilCliente(controlador, usuario), BorderLayout.CENTER);
			break;
		case "finalizarCompra":
			panel.add(new PanelFinalizarCompra(controlador, usuario), BorderLayout.CENTER);
			break;
		}
	}
	
	private static void changePanel(String panelNuevo, Controlador controlador, String usuario, String espectaculo, String lugar) {
		switch (panelNuevo) {
		case "modificar":
			panel.add(new PanelModificarEspectaculo(controlador, usuario, espectaculo, lugar), BorderLayout.CENTER);
			break;
		}
	}
	
	private static void changePanel(String panelNuevo, Controlador controlador, String usuario, Set<Espectaculo> set, String[] str) {
		switch (panelNuevo) {
		case "lista":		
			panel.add(new PanelResultadosEncontrados(controlador, usuario, set, str), BorderLayout.CENTER);
			break;
		}
	}
	
	private static void changePanel(String panelNuevo, Controlador controlador, String usuario, Set<Espectaculo> set, String espectaculo, String lugar, String[] str) {
		switch (panelNuevo) {
		case "informacion":
			panel.add(new PanelInformacionEspectaculo(controlador, usuario, set, espectaculo, lugar, str), BorderLayout.CENTER);
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
	
	public static void changePanel(String panelNuevo, JPanel panelActual, Controlador controlador, String usuario) {
		panel.remove(panelActual);
		panel.revalidate();
		changePanel(panelNuevo, controlador, usuario);
	}
	
	public static void changePanel(String panelNuevo, JPanel panelActual, Controlador controlador, String usuario, String espectaculo, String lugar) {
		panel.remove(panelActual);
		panel.revalidate();
		changePanel(panelNuevo, controlador, usuario, espectaculo, lugar);
	}

	public static void changePanel(String panelNuevo, JPanel panelActual, Controlador controlador, String usuario, Set<Espectaculo> espectaculo, String[] str) {
		panel.remove(panelActual);
		panel.revalidate();
		changePanel(panelNuevo, controlador, usuario, espectaculo, str);
	}
	
	public static void changePanel(String panelNuevo, JPanel panelActual, Controlador controlador, String usuario, Set<Espectaculo> espectaculo, String espect, String lugar, String[] cond) {
		panel.remove(panelActual);
		panel.revalidate();
		changePanel(panelNuevo, controlador, usuario, espectaculo, espect,lugar,  cond);
	}
}
package paneles;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;

public class CargarFoto extends JFrame {

	private JPanel contentPane;
	public static JFileChooser jfchCargarfoto = new JFileChooser();
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private int height = pantalla.height;
	private int width = pantalla.width;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CargarFoto frame = new CargarFoto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea el frame para acceder a los archivos
	 */
	public CargarFoto() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width/2, height/2 + 100);		
		setLocationRelativeTo(null);	
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		contentPane.add(jfchCargarfoto, BorderLayout.CENTER);
	}

}

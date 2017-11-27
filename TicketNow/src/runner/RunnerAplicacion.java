package runner;

import java.awt.EventQueue;

import controlador.Controlador;
import paneles.VistaTicketNow;

public class RunnerAplicacion {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controlador controlador = new Controlador();
					VistaTicketNow vista = new VistaTicketNow(controlador);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

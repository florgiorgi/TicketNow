package usuario;

import espectaculo.Espectaculo;
import espectaculo.Funcion;
import compra.Compra;
import fecha.Hora;
import fecha.Fecha;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

	private String nombre;
	private String apellido;
	private String usuario;
	private String mail;
	private Fecha fechaNac;
	private String DNI;
	private String contraseña;
	private List<Compra> compras;

	public Cliente(String nombre, String apellido, String usuario, String mail, Fecha fechaNac, String DNI, String contraseña) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.mail = mail;
		this.fechaNac = fechaNac;
		this.DNI = DNI;
		this.contraseña = contraseña;
		this.compras = new ArrayList<Compra>();
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public String getMail() {
		return this.mail;
	}

	public Fecha getFechaNac() {
		return this.fechaNac;
	}

	public String getDNI() {
		return this.DNI;
	}

	public String getContraseña() {
		return this.contraseña;
	}
	
	public List<Compra> getCompras() {
		return this.compras;
	}
	
	public void modificarDatos(String nombre, String apellido, String mail, Fecha fechaNac, String DNI, String contraseña) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.fechaNac = fechaNac;
		this.DNI = DNI;
		this.contraseña = contraseña;
	}
	
	//devuelve true si se pudo realizar la compra
	public boolean comprar(Espectaculo esp, Funcion func, Integer dia, Integer mes, Integer año, Integer horas, Integer minutos, Integer cantEntradas) {
		Hora hora = new Hora(horas, minutos);
		Fecha fecha = new Fecha(dia, mes, año);
		
		if(esp.getFunciones().contains(func) && func.getCantidadDisp() >= cantEntradas) {
			Compra compra = new Compra(this, esp, func, fecha, hora, cantEntradas);
			if(pagar(compra)) {
				compras.add(compra);
				func.setCantidadDisp(func.getCantidadDisp() - cantEntradas);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	//devuelve true si se pudo modificar la compra
	//quizas no es necesario y sólo hay que ir para atrás y adelante con las pantallas de comprar y confirmar compra
	public boolean modificarCompra(Compra compra, Espectaculo esp, Funcion func, Integer cantEntradas) {
		if(compra == null)
			throw new IllegalArgumentException();
		return compra.modificarCompra(esp, func, cantEntradas);
	}
	
	//devuelve true si se aprobó el pago
	public boolean pagar(Compra compra) {
		//Llama al sistema de pago externo
		return true;
	}
	
	@Override
	public String toString() {
		return this.nombre + " " + this.apellido + " " + this.compras;
	}

}

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
	private String fechaNac;
	private String DNI;
	private String contrase�a;
	private List<Compra> compras;

	public Cliente(String nombre, String apellido, String usuario, String mail, String fechaNac, String DNI, String contrase�a) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.mail = mail;
		this.fechaNac = fechaNac;
		this.DNI = DNI;
		this.contrase�a = contrase�a;
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

	public String getFechaNac() {
		return this.fechaNac;
	}

	public String getDNI() {
		return this.DNI;
	}

	public String getContrase�a() {
		return this.contrase�a;
	}
	
	public List<Compra> getCompras() {
		return this.compras;
	}
	
	public void modificarDatos(String nombre, String apellido, String mail, String fechaNac, String DNI, String contrase�a) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.fechaNac = fechaNac;
		this.DNI = DNI;
		this.contrase�a = contrase�a;
	}
	
	//devuelve true si se pudo realizar la compra
	public boolean comprar(Espectaculo esp, Funcion func, Double precio, Integer dia, Integer mes, Integer a�o, Integer horas, Integer minutos, Integer cantEntradas) {
		Hora hora = new Hora(horas, minutos);
		Fecha fecha = new Fecha(dia, mes, a�o);
		
		Compra compra = new Compra(this, esp, func, precio, fecha, hora, cantEntradas);
		
		if(pagar(compra)) {
			compras.add(compra);
			return true;
		} else {
			return false;
		}
	}
	
	//devuelve true si se pudo modificar la compra
	public boolean modificarCompra(Compra compra, Espectaculo esp, Funcion func, Integer cantEntradas) {
		if(compra == null)
			throw new IllegalArgumentException();
		return compra.modificarCompra(esp, func, cantEntradas);
	}
	
	//devuelve true si se aprob� el pago
	public boolean pagar(Compra compra) {
		//Llama al sistema de pago externo
		return true;
	}

}
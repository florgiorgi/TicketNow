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
	private String telefono;
	private String contraseña;
	private List<Compra> compras;

	public Cliente(String usuario, String contraseña, String nombre, String apellido, Fecha fechaNac, String mail,
			String telefono, String DNI) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.mail = mail;
		this.fechaNac = fechaNac;
		this.telefono = telefono;
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

	public void modificarDatos(String nombre, String apellido, String mail, Fecha fechaNac, String DNI,
			String contraseña) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.fechaNac = fechaNac;
		this.DNI = DNI;
		this.contraseña = contraseña;
	}
}
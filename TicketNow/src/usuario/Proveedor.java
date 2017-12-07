package usuario;

import java.util.HashSet;
import java.util.Set;
import espectaculo.Espectaculo;


import espectaculo.Funcion;


public class Proveedor extends Usuario {

	private String categoria;
	private Set<Espectaculo> espectaculos;

	public Proveedor(String usuario, String contraseña, String contraseñaChequeo, String nombre, String apellido,
			String fechaNac, String mail, String telefono, String DNI, String pais, String provincia, String localidad, String direccion, String codigoPostal) {
		super(usuario, contraseña, contraseñaChequeo, nombre, apellido, fechaNac, mail, telefono, DNI, pais, provincia, localidad, direccion, codigoPostal);
		this.espectaculos = new HashSet<Espectaculo>();
	}

	// Que inicialice categoria una vez q entra sino no se como hacerlo
	public String getCategoria() {
		return this.categoria;
	}

	public Set<Espectaculo> getEspectaculos() {
		return this.espectaculos;
	}

	public void agregarEspectaculo(Espectaculo esp) {
		espectaculos.add(esp);
	}

	// devuelve true si pudo borrar el espectaculo
	public boolean eliminarEspectaculo(Espectaculo esp) {
		return espectaculos.remove(esp);
	}

	// devuelve true si pudo modificar el espectaculo
	public boolean modificarEspectaculo(Espectaculo esp, String nombre, String descripcion, String categoria,
			String estreno) {
		for (Espectaculo e : espectaculos) {
			if (e.equals(esp)) {
				return e.modificarEspectaculo(nombre, descripcion, categoria, estreno);
			}
		}
		return false;
	}

	

}
package usuario;

import java.util.HashSet;
import java.util.Set;
import espectaculo.Espectaculo;
import fecha.Fecha;
import espectaculo.Funcion;
import fecha.Hora;

public class Proveedor extends Usuario {

	private String categoria;
	private Set<Espectaculo> espectaculos;

	public Proveedor(String usuario, char[] contraseña, char[] contraseñaChequeo, String nombre, String apellido,
			String fechaNac, String mail, String telefono, String DNI) {
		super(usuario, contraseña, contraseñaChequeo, nombre, apellido, fechaNac, mail, telefono, DNI);
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
			Fecha estreno) {
		for (Espectaculo e : espectaculos) {
			if (e.equals(esp)) {
				return e.modificarEspectaculo(nombre, descripcion, categoria, estreno);
			}
		}
		return false;
	}

	// devuelve true si pudo agregar la funcion
	public boolean agregarFuncion(Espectaculo esp, Funcion func) {
		for (Espectaculo e : espectaculos) {
			if (e.equals(esp)) {
				return e.agregarFuncion(func);
			}
		}
		return false;
	}

	// devuelve true si pudo eliminar la funcion
	public boolean eliminarFuncion(Espectaculo esp, Funcion func) {
		for (Espectaculo e : espectaculos) {
			if (e.equals(esp)) {
				return e.eliminarFuncion(func);
			}
		}
		return false;
	}

	// devuelve true si pudo modificar la funcion
	public boolean modificarFuncion(Espectaculo esp, Funcion funcion, Hora hora, String sala, Double precio,
			Integer cantidadDisp) {
		for (Espectaculo e : espectaculos) {
			if (e.equals(esp)) {
				return e.modificarFuncion(funcion, hora, sala, precio, cantidadDisp);
			}
		}
		return false;
	}


}
package espectaculo;

import java.util.HashSet;
import java.util.Set;

public class Espectaculo {

	private String nombre;
	private String descripcion;
	private String categoria;
	private String fechaEstreno;
	private String promocion;
	private Set<Funcion> funciones;

	public Espectaculo(String nombre, String descripcion, String categoria, String fechaEstreno, String promocion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.fechaEstreno = fechaEstreno;
		this.promocion = promocion;
		this.funciones = new HashSet<Funcion>();
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public String getEstreno() {
		return this.fechaEstreno;
	}

	public Set<Funcion> getFunciones() {
		return this.funciones;
	}

	public String getPromocion() {
		return this.promocion;
	}

	// devuelve true si se pudo modificar el espectaculo
	public boolean modificarEspectaculo(String nombre, String descripcion, String categoria, String fechaEstreno) {
		if (nombre == null || descripcion == null || categoria == null || fechaEstreno == null)
			return false;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.fechaEstreno = fechaEstreno;
		return true;
	}

	// devuelve true si pudo agregar la funcion
	public boolean agregarFuncion(Funcion funcion) {
		return funciones.add(funcion);
	}

	// devuelve true si pudo eliminar la funcion
	public boolean eliminarFuncion(Funcion funcion) {
		return funciones.remove(funcion);
	}

	// devuelve true si pudo modificar la funcion
	public boolean modificarFuncion(Funcion funcion, String hora, String sala, Double precio, Integer cantidadDisp) {
		for (Funcion f : funciones) {
			if (f.equals(funcion)) {
				return f.modificarFuncion(hora, sala, precio, cantidadDisp);
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaEstreno == null) ? 0 : fechaEstreno.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Espectaculo other = (Espectaculo) obj;
		if (fechaEstreno == null) {
			if (other.fechaEstreno != null)
				return false;
		} else if (!fechaEstreno.equals(other.fechaEstreno))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.nombre;
	}
}
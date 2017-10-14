package espectaculo;

import java.util.HashSet;
import java.util.Set;
import fecha.Fecha;
import fecha.Hora;

public class Espectaculo {
	
	private String nombre;
	private String descripcion;
	private String categoria;
	private Fecha estreno;
	private Set<Funcion> funciones;
	
	public Espectaculo(String nombre, String descripcion, String categoria, Fecha estreno) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.estreno = estreno;
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

	public Fecha getEstreno() {
		return this.estreno;
	}

	public Set<Funcion> getFunciones() {
		return this.funciones;
	}
	
	//devuelve true si se pudo modifcar el espectaculo
	public boolean modificarEspectaculo(String nombre, String descripcion, String categoria, Fecha estreno) {
		if(nombre == null || descripcion == null || categoria == null || estreno == null)
			return false;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.estreno = estreno;
		return true;
	}
	
	//devuelve true si pudo agregar la funcion
	public boolean agregarFuncion(Funcion funcion) {
		return funciones.add(funcion);
	}
	
	//devuelve true si pudo eliminar la funcion
	public boolean eliminarFuncion(Funcion funcion) {
		return funciones.remove(funcion);
	}
	
	//devuelve true si pudo modificar la funcion
	public boolean modificarFuncion(Funcion funcion, Hora hora, String sala, Double precio, Integer cantidadDisp) {
		for(Funcion f : funciones) {
			if(f.equals(funcion)) {
				return f.modificarFuncion(hora, sala, precio, cantidadDisp);
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estreno == null) ? 0 : estreno.hashCode());
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
		if (estreno == null) {
			if (other.estreno != null)
				return false;
		} else if (!estreno.equals(other.estreno))
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

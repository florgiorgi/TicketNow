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
	public boolean modificarFuncion(Funcion funcion, Hora hora, String sala, Integer precio, Integer cantidadDisp) {
		for(Funcion f : funciones) {
			if(f.equals(funcion)) {
				return f.modificarFuncion(hora, sala, precio, cantidadDisp);
			}
		}
		return false;
	}
}

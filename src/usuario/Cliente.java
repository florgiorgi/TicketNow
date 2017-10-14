package usuario;

import java.util.Set;
import espectaculo.Espectaculo;
import fecha.Fecha;
import espectaculo.Funcion;
import fecha.Hora;

public class Proveedor extends Cliente {

	private String nombreProv;
	private String categoria;
	private Set<Espectaculo> espectaculos;
	
	public Proveedor(String nombre, String apellido, String usuario, String mail, String fechaNac, String DNI, String contraseña) {
		super(nombre, apellido, usuario, mail, fechaNac, DNI, contraseña);
	}

	public String getNombreProv() {
		return this.nombreProv;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public Set<Espectaculo> getEspectaculos() {
		return this.espectaculos;
	}

	public void agregarEspectaculo(Espectaculo esp) {
		espectaculos.add(esp);
	}
	
	//devuelve true si pudo borrar el espectaculo
	public boolean eliminarEspectaculo(Espectaculo esp) {
		return espectaculos.remove(esp);
	}
	
	//devuelve true si pudo modificar el espectaculo
	public boolean modificarEspectaculo(Espectaculo esp, String nombre, String descripcion, String categoria, Fecha estreno) {
		for(Espectaculo e : espectaculos) {
			if(e.equals(esp)) {
				return e.modificarEspectaculo(nombre, descripcion, categoria, estreno);
			}
		}
		return false;
	}
	
	//devuelve true si pudo agregar la funcion
	public boolean agregarFuncion(Espectaculo esp, Funcion func) {
		for(Espectaculo e : espectaculos) {
			if(e.equals(esp)) {
				return e.agregarFuncion(func);
			}
		}
		return false;
	}
	
	//devuelve true si pudo eliminar la funcion
	public boolean eliminarFuncion(Espectaculo esp, Funcion func) {
		for(Espectaculo e : espectaculos) {
			if(e.equals(esp)) {
				return e.eliminarFuncion(func);
			}
		}
		return false;
	}
	
	//devuelve true si pudo modificar la funcion
	//quizas no es necesario y sólo hay que ir para atrás y adelante con las pantallas de comprar y confirmar compra
	public boolean modificarFuncion(Espectaculo esp, Funcion funcion, Hora hora, String sala, Double precio, Integer cantidadDisp) {
		for(Espectaculo e : espectaculos) {
			if(e.equals(esp)) {
				return e.modificarFuncion(funcion, hora, sala, precio, cantidadDisp);
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		super.toString();
		return "\n" + "Espectaculos: " + this.espectaculos;
	}
	
}

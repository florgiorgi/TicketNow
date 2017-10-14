package espectaculo;

import fecha.Hora;

public class Funcion {

	private Hora hora;
	private String sala;
	private Integer precio;
	private Integer cantidadDisp;
	
	public Funcion(Hora hora, String sala, Integer precio, Integer cantidadDisp) {
		if(hora == null || sala == null || precio == null || cantidadDisp == null)
			throw new IllegalArgumentException();
		if(precio <= 0 || cantidadDisp <= 0)
			throw new IllegalArgumentException();
		this.hora = hora;
		this.sala = sala;
		this.precio = precio;
		this.cantidadDisp = cantidadDisp;
	}

	public Hora getHora() {
		return this.hora;
	}

	public String getSala() {
		return this.sala;
	}

	public Integer getPrecio() {
		return this.precio;
	}

	public Integer getCantidadDisp() {
		return this.cantidadDisp;
	}
	
	//devuelve true si se pudo modificar la funcion
	public boolean modificarFuncion(Hora hora, String sala, Integer precio, Integer cantidadDisp) {
		if(hora == null || sala == null || precio == null || cantidadDisp == null)
			return false;
		if(precio <= 0 || cantidadDisp <= 0)
			return false;
		this.hora = hora;
		this.sala = sala;
		this.precio = precio;
		this.cantidadDisp = cantidadDisp;
		return true;
	}
	
}

package espectaculo;

import fecha.Fecha;
import fecha.Hora;

public class Funcion {
	
	private String nombre;
	private String lugar;
	private Hora hora;
	private Fecha fecha;
	private String sala;
	private Double precio;
	private Integer cantidadDisp;
	
	public Funcion(String nombre, String lugar, Fecha fecha, Hora hora, String sala, Double precio, Integer cantidadDisp) {
		if(hora == null || sala == null || precio == null || cantidadDisp == null)
			throw new IllegalArgumentException();
		if(precio <= 0 || cantidadDisp <= 0)
			throw new IllegalArgumentException();
		this.nombre = nombre;
		this.lugar = lugar;
		this.fecha = fecha;
		this.hora = hora;
		this.sala = sala;
		this.precio = precio;
		this.cantidadDisp = cantidadDisp;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getLugar(){
		return this.lugar;
	}
	
	public Hora getHora() {
		return this.hora;
	}
	
	public Fecha getFecha() {
		return this.fecha;
	}

	public String getSala() {
		return this.sala;
	}

	public Double getPrecio() {
		return this.precio;
	}

	public Integer getCantidadDisp() {
		return this.cantidadDisp;
	}
	
	public void setCantidadDisp(Integer cant) {
		this.cantidadDisp = cant;
	}
	
	//devuelve true si se pudo modificar la funcion
	public boolean modificarFuncion(Hora hora, String sala, Double precio, Integer cantidadDisp) {
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcion other = (Funcion) obj;
		if (hora == null) {
			if (other.hora != null)
				return false;
		} else if (!hora.equals(other.hora))
			return false;
		if (sala == null) {
			if (other.sala != null)
				return false;
		} else if (!sala.equals(other.sala))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hora == null) ? 0 : hora.hashCode());
		result = prime * result + ((sala == null) ? 0 : sala.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return "Hora: " + this.hora.toString() + " Sala: " + this.sala;
	}

}
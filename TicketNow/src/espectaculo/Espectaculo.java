package espectaculo;

import java.util.HashSet;
import java.util.Set;

import usuario.NombreInvalidoException;
import usuario.UsuarioInvalidoException;

public class Espectaculo {

	private String nombre;
	private String descripcion;
	private String categoria;
	private String fechaEstreno;
	private String promocion;

	/* Agregar campos nuevos como lugar, cargar foto, precio, cantidad de entradas */
	public Espectaculo(String nombre, String descripcion, String categoria, String fechaEstreno, String promocion) {
		if(!nombreValido(nombre))
			throw new NombreInvalidoException("El nombre de usuario es inválido.");
		if(!fechaValida(fechaEstreno))
			throw new FechaEstrenoInvalidaException("La fecha de nacimiento es inválida.");
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.fechaEstreno = fechaEstreno;
		this.promocion = promocion;
	}
	
	public boolean nombreValido(String nombre) {
		if(nombre == null || nombre.equals(" "))
			return false;
		
		char[] aux = nombre.toCharArray();
		
		for (int i = 0; i < aux.length; i++) {
			if (!Character.isLetterOrDigit(aux[i]) && Character.compare(aux[i], '.') != 0
					&& Character.compare(aux[i], '_') != 0 && Character.compare(aux[i], '-') != 0)
				return false;
		}
		return true;
	}
	
	private boolean fechaValida(String fecha) {
		if(fecha == null)
			return false;
		
		if (!formatoValido(fecha))
			return false;

		String[] f = fecha.split("-");
		
		Integer año = Integer.parseInt(f[0]);
		Integer mes = Integer.parseInt(f[1]);
		Integer dia = Integer.parseInt(f[2]);
		
		if (dia < 0 || mes < 0 || mes > 12 || año < 1930 || año > 2018)
			return false;
		if (f[1] == "01" || f[1] == "03" || f[1] == "05" || f[1] == "07" || f[1] == "08" || f[1] == "10"
				|| f[1] == "12") {
			if (dia > 31)
				return false;
		} else if (f[1] == "02") {
			if (dia > 28)
				return false;
		} else {
			if (dia > 30)
				return false;
		}
		
		return true;
	}

	private boolean formatoValido(String fecha) {
		char[] aux = fecha.toCharArray();

		for (int i = 0; i < aux.length; i++) {
			if (i == 4 || i == 7) {
				if (aux[i] != '-')
					return false;
			} else {
				if (!Character.isDigit(aux[i]))
					return false;
			}
		}
		return true;
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
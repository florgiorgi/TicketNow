package espectaculo;

import usuario.NombreInvalidoException;

public class Espectaculo {

	private String nombre;
	private String fechaEstreno;
	private String promocion;
	private String categoria;
	private String lugarDeRetiro;
	private String precio;
	private String descripcion;
	private String imagen;
	private String cantidadEntradas;
	private String entradasVendidas;
	private String puntaje;
	
	public Espectaculo(String nombre, String cantidadEntradas, String fechaEstreno, String promocion, String categoria,
			String lugarDeRetiro, String precio, String descripcion, String entradasVendidas, String puntaje) {
		if (!nombreValido(nombre))
			throw new NombreInvalidoException("El nombre del espectaculo es invalido.");
		if (!cantidadEntradasValida(cantidadEntradas))
			throw new CantidadDeEntradasInvalidaException("La cantidad de entradas es invalida.");
		if (!fechaValida(fechaEstreno))
			throw new FechaEstrenoInvalidaException("La fecha de estreno es invalida.");
		if (!promocionValida(promocion))
			throw new PromocionInvalidaException("La promocion es invalida.");
		if (!categoriaValida(categoria))
			throw new CategoriaInvalidaException("La categoria es invalida.");
		if (!precioValido(precio))
			throw new PrecioInvalidoException("El precio es invalido.");
		if (!descripcionValida(descripcion))
			throw new DescripcionInvalidaException("La descripcion es inválida.");
		this.nombre = nombre;
		this.cantidadEntradas = cantidadEntradas;
		this.fechaEstreno = fechaEstreno;
		this.promocion = promocion;
		this.categoria = categoria;
		this.lugarDeRetiro = lugarDeRetiro;
		this.precio = precio;
		this.descripcion = descripcion;
		this.imagen = "imagen";
		this.entradasVendidas = entradasVendidas;
		this.puntaje = puntaje;
	}

	public boolean nombreValido(String nombre) {
		if (nombre == null || nombre.equals(""))
			return false;

		char[] aux = nombre.toCharArray();

		for (int i = 0; i < aux.length; i++) {
			if (!Character.isLetterOrDigit(aux[i]) && Character.compare(aux[i], ' ') != 0
					&& Character.compare(aux[i], '!') != 0 && Character.compare(aux[i], '?') != 0
					&& Character.compare(aux[i], '-') != 0 && Character.compare(aux[i], '.') != 0)
				return false;
		}
		return true;
	}

	private boolean cantidadEntradasValida(String cantidadEntradas) {
		if (cantidadEntradas == null || cantidadEntradas.equals("0") || cantidadEntradas.equals(""))
			return false;
		char[] aux = cantidadEntradas.toCharArray();
		for (int i = 0; i < aux.length; i++) {
			if (!Character.isDigit(aux[i])) {
				return false;
			}
		}
		int auxInt = Integer.parseInt(cantidadEntradas);
		if (auxInt < 0)
			return false;
		return true;
	}

	private boolean fechaValida(String fecha) {
		if (fecha == null)
			return false;

		if (!formatoValido(fecha))
			return false;

		String[] f = fecha.split("-");

		Integer año = Integer.parseInt(f[0]);
		Integer mes = Integer.parseInt(f[1]);
		Integer dia = Integer.parseInt(f[2]);

		if (dia < 0 || mes < 0 || mes > 12 || año < 2017 || año > 2020)
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

	private boolean promocionValida(String promocion) {
		
		if (promocion.equals("Sin promocion") || promocion.equals("2x1") || promocion.equals("Banco Asociados")
				|| promocion.equals("Descuento a Jubilados")) {
			return true;
		}
		return false;
	}

	private boolean categoriaValida(String categoria) {
		if (categoria.equals("Cine") || categoria.equals("Teatro") || categoria.equals("Cancha")) {
			return true;
		}
		return false;
	}

	private boolean precioValido(String precio) {
		if (precio == null || precio.equals(""))
			return false;
		char[] aux = precio.toCharArray();
		for (int i = 0; i < aux.length; i++) {
			if (!Character.isDigit(aux[i]))
				return false;
		}
		Integer auxI = Integer.parseInt(precio);
		if (auxI < 0)
			return false;
		return true;
	}

	private boolean descripcionValida(String descripcion) {
		if (descripcion == null || descripcion.equals(""))
			return false;
		
		
		return true;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCantidadEntradas() {
		return cantidadEntradas;
	}
	
	public String getCantidadEntradasVendidas() {
		return entradasVendidas;
	}

	public String getFechaEstreno() {
		return fechaEstreno;
	}

	public String getPromocion() {
		return promocion;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getLugarDeRetiro() {
		return lugarDeRetiro;
	}

	public String getPrecio() {
		return precio;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public String getPuntaje() {
		return puntaje;
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
package compra;


public class Ticket {
	
	private Double precio;
	private String fecha;
	
	public Ticket(Double precio, String fecha) {
		if(precio == null || fecha == null)
			throw new IllegalArgumentException();
		if(precio < 0)
			throw new IllegalArgumentException();
		this.precio = precio;
		this.fecha = fecha;
	}
	
	public Double getPrecio() {
		return this.precio;
	}
	
	public String getFecha() { 
		return this.fecha;
	}
	
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	@Override
	public String toString() {
		return this.precio.toString();
	}
	
}
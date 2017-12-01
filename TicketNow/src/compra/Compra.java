package compra;

import usuario.Cliente;
import espectaculo.Espectaculo;
import espectaculo.Funcion;

public class Compra {

	private Cliente cliente;
	private Espectaculo espectaculo;
	private Funcion funcion;
	private String fecha;
	private String hora;
	private Ticket ticket;
	private Double precio;
	private Integer cantEntradas;
	
	public Compra(Cliente cliente, Espectaculo espectaculo, Funcion funcion, String fecha, String hora, Integer cantEntradas) {
		if(cliente == null || espectaculo == null || funcion == null || fecha == null || hora == null || cantEntradas == null)
			throw new IllegalArgumentException();
		this.cliente = cliente;
		this.espectaculo = espectaculo;
		this.funcion = funcion;
		this.fecha = fecha;
		this.hora = hora;
		this.precio = funcion.getPrecio()*cantEntradas;
		this.ticket = new Ticket(this.precio, fecha);
		this.cantEntradas = cantEntradas;
	}

	public Cliente getCliente() {
		return this.cliente;
	}
	
	public Funcion getFuncion() {
		return this.funcion;
	}
	
	public Espectaculo getEspectaculo() {
		return this.espectaculo;
	}

	public String getFecha() {
		return this.fecha;
	}
	
	public String getHora() {
		return this.hora;
	}

	public Ticket getTicket() {
		return this.ticket;
	}
	
	public Double getPrecio() {
		return this.precio;
	}

	public Integer getCantEntradas() {
		return this.cantEntradas;
	}
	
	//devuelve true si se pudo modificar la compra
	public boolean modificarCompra(Espectaculo esp, Funcion func, Integer cantEntradas) {
		if(esp == null || func == null || cantEntradas == null)
			return false;
		if(cantEntradas <= 0)
			return false;
		this.espectaculo = esp;
		this.funcion = func;
		this.cantEntradas = cantEntradas;
		this.precio = cantEntradas*func.getPrecio();
		this.ticket.setPrecio(this.precio);
		return true;
	}
	
	@Override
	public String toString() {
		return this.fecha.toString() + " a las " + this.hora.toString() + " - " + this.espectaculo.toString() + " - " + this.funcion.toString() + " - Precio: " + this.precio.toString(); 
	}
}
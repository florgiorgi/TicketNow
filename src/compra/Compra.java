package compra;

import usuario.Cliente;
import espectaculo.Espectaculo;
import espectaculo.Funcion;
import fecha.Fecha;
import fecha.Hora;

public class Compra {

	private Cliente cliente;
	private Espectaculo espectaculo;
	private Funcion funcion;
	private Fecha fecha;
	private Hora hora;
	private Ticket ticket;
	private Integer cantEntradas;
	
	public Compra(Cliente cliente, Espectaculo espectaculo, Funcion funcion, Double precio, Fecha fecha, Hora hora, Integer cantEntradas) {
		if(cliente == null || espectaculo == null || funcion == null || precio == null || fecha == null || hora == null || cantEntradas == null)
			throw new IllegalArgumentException();
		this.cliente = cliente;
		this.espectaculo = espectaculo;
		this.funcion = funcion;
		this.fecha = fecha;
		this.hora = hora;
		this.ticket = new Ticket(precio, fecha);
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

	public Fecha getFecha() {
		return this.fecha;
	}
	
	public Hora getHora() {
		return this.hora;
	}

	public Ticket getTicket() {
		return this.ticket;
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
		return true;
	}
}

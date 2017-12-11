package compra;

import usuario.Cliente;
import espectaculo.Espectaculo;

public class Compra {

	private String clienteID;
	private String espectaculo;
	private String lugar;
	private Integer cantEntradas;
	private Integer total;
	
	public Compra(String clienteID, String espectaculo, String lugar, Integer cantEntradas, Integer total) {
		this.clienteID = clienteID;
		this.espectaculo = espectaculo;
		this.lugar = lugar;
		this.cantEntradas = cantEntradas;
		this.total = total;
	}

	public String getClienteID() {
		return clienteID;
	}
	
	public String getEspectaculo() {
		return espectaculo;
	}
	
	public String getLugar() {
		return lugar;
	}
	
	public Integer getCantidadEntradas() {
		return cantEntradas;
	}
	
	public Integer getTotal() {
		return total;
	}
	
}
package usuario;

import compra.Compra;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

	private List<Compra> compras;

	public Cliente(String usuario, String contraseña, String constraseñaChequeo, String nombre, String apellido,
			String fechaNac, String mail, String telefono, String DNI, String pais, String provincia, String localidad, String direccion, String codigoPostal) {
		super(usuario, contraseña, constraseñaChequeo, nombre, apellido, fechaNac, mail, telefono, DNI, pais, provincia, localidad, direccion, codigoPostal);
		this.compras = new ArrayList<Compra>();

	}

	public List<Compra> getCompras() {
		return this.compras;
	}
}
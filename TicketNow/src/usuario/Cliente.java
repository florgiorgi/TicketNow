package usuario;

import compra.Compra;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

	private List<Compra> compras;

	public Cliente(String usuario, char[] contraseña, char[] constraseñaChequeo, String nombre, String apellido,
			String fechaNac, String mail, String telefono, String DNI) {
		super(usuario, contraseña, constraseñaChequeo, nombre, apellido, fechaNac, mail, telefono, DNI);
		this.compras = new ArrayList<Compra>();

	}

	public List<Compra> getCompras() {
		return this.compras;
	}
}
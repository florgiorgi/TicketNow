package controlador;

import database.Database;
import usuario.Cliente;
import usuario.Proveedor;

public class Controlador {

	private Database database = new Database();

	public boolean agregarUsuario(String tipoUsuario, String usuario, char[] contraseña, char[] contraseñaChequeo,
			String nombre, String apellido, String fechaNac, String mail, String telefono, String DNI) {
		if (tipoUsuario.equals("Cliente"))
			return database.add(new Cliente(usuario, contraseña, contraseñaChequeo, nombre, apellido, fechaNac, mail,
					telefono, DNI));
		else if (tipoUsuario.equals("Proveedor"))
			return database.add(new Proveedor(usuario, contraseña, contraseñaChequeo, nombre, apellido, fechaNac, mail,
					telefono, DNI));

		return false;
	}

	public boolean usuarioCorrecto(String tipoUsuario, String usuario, char[] contraseña) {
		if (tipoUsuario.equals("Cliente"))
			return database.containsCliente(usuario, contraseña);
		else if (tipoUsuario.equals("Proveedor"))
			return database.containsProveedor(usuario, contraseña);

		return false;
	}

	public void printDatabase() {
		System.out.println(database);
	}
	
	public Database getDatabase(){
		return database;
	}
}

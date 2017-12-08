package controlador;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import database.Database;
import espectaculo.Espectaculo;
import usuario.Cliente;
import usuario.Proveedor;
import usuario.Usuario;
import usuario.UsuarioExistenteException;

public class Controlador {

	private Database database = new Database();

	public boolean agregarUsuario(String tipoUsuario, String usuario, String contraseña, String contraseñaChequeo,
			String nombre, String apellido, String fechaNac, String mail, String telefono, String DNI, String pais,
			String provincia, String localidad, String direccion, String codigoPostal) throws SQLException {
		
		try {
			database.addUsuario(tipoUsuario, new Usuario(usuario, contraseña, contraseñaChequeo, nombre, apellido, fechaNac,
					mail, telefono, DNI, pais, provincia, localidad, direccion, codigoPostal));
		} catch (UsuarioExistenteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ocurrió algo inesperado",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (tipoUsuario.equals("Cliente"))
			return database.addCliente(new Cliente(usuario, contraseña, contraseñaChequeo, nombre, apellido, fechaNac,
					mail, telefono, DNI, pais, provincia, localidad, direccion, codigoPostal));
		else if (tipoUsuario.equals("Proveedor"))
			return database.addProveedor(new Proveedor(usuario, contraseña, contraseñaChequeo, nombre, apellido,
					fechaNac, mail, telefono, DNI, pais, provincia, localidad, direccion, codigoPostal));

		return false;
	}

	public boolean usuarioCorrecto(String tipoUsuario, String mail, char[] contraseña) throws SQLException {
		if (tipoUsuario.equals("Cliente"))
			return database.containsCliente(mail, contraseña);
		if (tipoUsuario.equals("Proveedor"))
			return database.containsProveedor(mail, contraseña);

		return false;
	}
	
	public boolean agregarEspectaculo(String nombre, String cantidad, String fechaDeEstreno, String promocion, String categoria, String lugar, String precio, String caracteristicas, String proveedorMail) throws SQLException {
		database.addEspectaculo(new Espectaculo(nombre, cantidad, fechaDeEstreno, promocion, categoria, lugar, precio, caracteristicas), proveedorMail);
		return true;
	}
}

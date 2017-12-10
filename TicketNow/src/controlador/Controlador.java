package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

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
			database.addUsuario(tipoUsuario, new Usuario(usuario, contraseña, contraseñaChequeo, nombre, apellido,
					fechaNac, mail, telefono, DNI, pais, provincia, localidad, direccion, codigoPostal));
		} catch (UsuarioExistenteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ocurrió algo inesperado", JOptionPane.ERROR_MESSAGE);
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

	public boolean agregarEspectaculo(String nombre, String cantidad, String fechaDeEstreno, String promocion,
			String categoria, String lugar, String precio, String caracteristicas, String proveedorMail)
			throws SQLException {
		database.addEspectaculo(
				new Espectaculo(nombre, cantidad, fechaDeEstreno, promocion, categoria, lugar, precio, caracteristicas, "0"),
				proveedorMail);
		return true;
	}

	public Proveedor obtenerProveedor(String mail) {
		try {
			ResultSet rs = database.getProveedor(mail);
			return new Proveedor(rs.getString("username"), rs.getString("contra"), rs.getString("contra"),
					rs.getString("nombre"), rs.getString("apellido"), rs.getString("cumple"), rs.getString("email"),
					rs.getString("telefono"), rs.getString("dni"), rs.getString("pais"), rs.getString("provincia"),
					rs.getString("localidad"), rs.getString("direccion"), rs.getString("codigopostal"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Set<Espectaculo> obtenerEspectaculosProveedor(String mail){
		try {
			return database.getEspectaculosPorProveedor(mail);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void eliminarEspectaculo(String nombre, String lugar) {
		try {
			database.removeEspectaculo(nombre, lugar);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Espectaculo obtenerEspectaculo(String espectaculo) {
		try {
			ResultSet rs = database.getEspetaculo(espectaculo);
			return new Espectaculo(rs.getString("espnombre"),rs.getString("cantidadentradas"), rs.getString("estreno"), rs.getString("promocion"), rs.getString("categoria")
					, rs.getString("lugarretiro"), rs.getString("precio"), rs.getString("espdescripcion"), rs.getString("entradasvendidas"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean modificarEspectaculo(String nombre, String cantidad, String fechaDeEstreno, String promocion,
			String categoria, String lugar, String precio, String caracteristicas) {
		try {
			return database.updateEspectaculo(
					new Espectaculo(nombre, cantidad, fechaDeEstreno, promocion, categoria, lugar, precio, caracteristicas, "0"));
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean modificarUsuario(String tipoUsuario, String usuario, String contraseña, String contraseñaChequeo,
			String nombre, String apellido, String fechaNac, String mail, String telefono, String DNI, String pais,
			String provincia, String localidad, String direccion, String codigoPostal) {
		try {
			return database.updateUsuario(
					new Usuario(usuario, contraseña, contraseñaChequeo, nombre, apellido,
							fechaNac, mail, telefono, DNI, pais, provincia, localidad, direccion, codigoPostal));
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void eliminarCuenta(String proveedor) {
		database.eliminarUsuario(proveedor);
	}
	
	
	public Set<Espectaculo> obtenerEspectaculoPorCondicion(String condicion){
		try {
			return database.getEspectaculos(condicion);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}

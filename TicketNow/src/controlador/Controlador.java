package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import javax.swing.JOptionPane;

import compra.Compra;
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
		database.addEspectaculo(new Espectaculo(nombre, cantidad, fechaDeEstreno, promocion, categoria, lugar, precio,
				caracteristicas, "0", null), proveedorMail);
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

	public Set<Espectaculo> obtenerEspectaculosProveedor(String mail) {
		try {
			return database.getEspectaculosPorProveedor(mail);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Set<Compra> obtenerComprasCliente(String mail) {
		try {
			return database.getComprasCliente(mail);
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

	public Espectaculo obtenerEspectaculo(String espectaculo, String lugar) {
		try {
			ResultSet rs = database.getEspetaculo(espectaculo, lugar);
			return new Espectaculo(rs.getString("espnombre"), rs.getString("cantidadentradas"), rs.getString("estreno"),
					rs.getString("promocion"), rs.getString("categoria"), rs.getString("lugarretiro"),
					rs.getString("precio"), rs.getString("espdescripcion"), rs.getString("entradasvendidas"),
					rs.getString("puntaje"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Cliente obtenerCliente(String mail) {
		try {
			ResultSet rs = database.getCliente(mail);
			return new Cliente(rs.getString("username"), rs.getString("contra"), rs.getString("contra"),
					rs.getString("nombre"), rs.getString("apellido"), rs.getString("cumple"), rs.getString("email"),
					rs.getString("telefono"), rs.getString("dni"), rs.getString("pais"), rs.getString("provincia"),
					rs.getString("localidad"), rs.getString("direccion"), rs.getString("codigopostal"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean modificarEspectaculo(String nombre, String cantidad, String fechaDeEstreno, String promocion,
			String categoria, String lugar, String precio, String caracteristicas) {
		try {
			return database.updateEspectaculo(nombre, cantidad, fechaDeEstreno, promocion, categoria, lugar, precio,
					caracteristicas);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean modificarUsuario(String tipoUsuario, String usuario, String contraseña, String contraseñaChequeo,
			String nombre, String apellido, String fechaNac, String mail, String telefono, String DNI, String pais,
			String provincia, String localidad, String direccion, String codigoPostal) {
		try {
			return database.updateUsuario(new Usuario(usuario, contraseña, contraseñaChequeo, nombre, apellido,
					fechaNac, mail, telefono, DNI, pais, provincia, localidad, direccion, codigoPostal));
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void eliminarCuenta(String proveedor) {
		database.eliminarUsuario(proveedor);
	}

	public Set<Espectaculo> obtenerEspectaculoPorCondicion(String busqueda, String lugar, String promocion) {
		try {
			return database.getEspectaculos(busqueda, lugar, promocion);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Espectaculo puntuarEspectaculo(String nombre, String lugar, int value) {
		try {
			database.puntuarEspectaculo(nombre, lugar, value);
			return obtenerEspectaculo(nombre, lugar);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void agregarCompra(String espectaculo, String lugar, Integer cantidad, Integer precio,
			String nombreUsuario) {
		try {
			database.agregarCompra(espectaculo, lugar, cantidad, precio, nombreUsuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void modificarCompra(String espectaculo, String lugar, Integer cantEntradas, String mail, Integer precio) {
		try {
			database.modificarCompra(espectaculo, lugar, cantEntradas, mail, precio);
			database.actualizarEspectaculo(espectaculo, lugar, cantEntradas, mail);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removerCompra(String espectaculo, String lugar, String mail) {
		database.removerCompra(espectaculo, lugar, mail);

	}

	public void eliminarCompras(String mail) {
		database.removerCompras(mail);

	}

	public Espectaculo actualizarEspectaculo(String nombre, String lugar, Integer cantidad, String mail) {
		try {
			database.actualizarEspectaculo(nombre, lugar, cantidad, mail);
			return obtenerEspectaculo(nombre, lugar);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Set<Espectaculo> obtenerMasVendidos() {
		try {
			return database.getEspectaculosMasVendidos();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
}

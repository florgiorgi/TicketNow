
package database;

import java.util.Set;

import espectaculo.Espectaculo;
import espectaculo.EspectaculoExistenteException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashSet;

import usuario.*;

public class Database {

	Connection con;
	String connect_string = "jdbc:postgresql://localhost:9999/u2017b-3";
	String driver_class = "org.postgresql.Driver";

	Statement stmt;
	ResultSet rs;

	public boolean conectar(String cUsuario, String cPassword) {
		try {
			Class.forName(driver_class); // Esto chequea que el driver se encuentre instalado
		} catch (java.lang.ClassNotFoundException e) { // Captura el error
			System.err.print("Oracle Class no found exception: ");
			System.err.println(e.getMessage());
			return false;
		}
		try {
			con = DriverManager.getConnection(connect_string, cUsuario, cPassword); // Hace la conexion
		} catch (SQLException ex) {
			System.err.println("Error de Conexion: " + ex.getMessage());
			return false;
		}
		return true;
	}

	public boolean desconectar() {
		try {
			con.close(); // Cierre la conexion
		} catch (SQLException ex) {
			System.err.println("Error de desconexion: " + ex.getMessage());
			return false;
		}
		return true;
	}

	public boolean ejecutasql(String cSql) {
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(cSql);
			stmt.close();
		} catch (SQLException ex) {
			System.err.println("Error de Sql (DML): " + ex.getMessage());
			return false;
		}
		return true;
	}

	public ResultSet gXrGenerico(String cSql) {
		try {
			stmt = con.createStatement();
			// stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
			// ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(cSql);
			rs.next();
			// System.out.println(rs.getString(1));
		} catch (SQLException ex) {
			System.err.println("Error de Sql (query): " + ex.getMessage());
		}

		return rs;

	}


	public boolean addCliente(Cliente cliente) throws SQLException {
		conectar("u2017b-3", "passwordING1");
		ejecutasql("INSERT INTO cliente VALUES( " + "'" + cliente.getMail() + "');");
		return true;
	}

	public void addUsuario(String tipoUsuario, Usuario usuario) throws SQLException {
		if (usuarioExists(usuario.getUsuario())) {
			throw new UsuarioExistenteException("El nombre de usuario que ingresó ya esta registrado.");
		}

		if (tipoUsuario.equals("Proveedor")) {
			if (proveedorMailExists(usuario.getMail())) {
				throw new UsuarioExistenteException(
						"El mail que ingresó ya esta registrado en el sistema como un proveedor.");
			}
		} else if (tipoUsuario.equals("Cliente")) {
			if (clienteMailExists(usuario.getMail())) {
				throw new UsuarioExistenteException(
						"El mail que ingresó ya esta registrado en el sistema como un cliente.");
			}
		}

		ejecutasql("INSERT INTO usuario VALUES( " + "'" + usuario.getUsuario() + "'," + "'"
				+ usuario.getContraseña().toString() + "'," + "'" + usuario.getNombre() + "'," + "'"
				+ usuario.getApellido() + "'," + "'" + usuario.getMail() + "'," + "'" + usuario.getFechaNac() + "',"
				+ "'" + usuario.getTelefono() + "'," + "'" + usuario.getDNI() + "'," + "'" + usuario.getPais() + "',"
				+ "'" + usuario.getProvincia() + "'," + "'" + usuario.getLocalidad() + "'," + "'"
				+ usuario.getDireccion() + "'," + "'" + usuario.getCodigoPostal() + "');");
	}

	public boolean usuarioExists(String usuario) throws SQLException {
		conectar("u2017b-3", "passwordING1");
		ResultSet rs = gXrGenerico("SELECT * FROM usuario WHERE username = '" + usuario + "';");
		if (rs.getRow() == 0) {
			return false;
		}
		return true;
	}

	public boolean addProveedor(Proveedor proveedor) throws SQLException {
		conectar("u2017b-3", "passwordING1");
		ejecutasql("INSERT INTO proveedor VALUES( " + "'" + proveedor.getMail() + "');");
		return true;
	}

	public String getUserPassword(String mail) throws SQLException {
		conectar("u2017b-3", "passwordING1");
		ResultSet rs = gXrGenerico("SELECT contra FROM usuario WHERE email = '" + mail + "';");
		return rs.getString("contra");
	}

	public boolean proveedorMailExists(String mail) throws SQLException {
		conectar("u2017b-3", "passwordING1");
		ResultSet rs = gXrGenerico("SELECT * FROM proveedor WHERE email = '" + mail + "';");
		if (rs.getRow() == 0) {
			return false;
		}
		return true;
	}

	public boolean clienteMailExists(String mail) throws SQLException {
		conectar("u2017b-3", "passwordING1");
		ResultSet rs = gXrGenerico("SELECT * FROM cliente WHERE email = '" + mail + "';");
		if (rs.getRow() == 0) {
			return false;
		}
		return true;
	}

	public boolean containsCliente(String mail, char[] contraseña) throws SQLException {
		if (clienteMailExists(mail)) {
			String c = getUserPassword(mail);
			if (Arrays.equals(c.toCharArray(), contraseña)) {
				return true;
			}
		}

		return false;
	}

	public boolean containsProveedor(String mail, char[] contraseña) throws SQLException {
		if (proveedorMailExists(mail)) {
			String c = getUserPassword(mail);
			if (Arrays.equals(c.toCharArray(), contraseña)) {
				return true;
			}
		}

		return false;
	}
	
	/* Si se fija en usuario y existe un cliente y proveedor registrados con el mismo mail va a romper */
	public ResultSet getProveedor(String mail) throws SQLException {
		conectar("u2017b-3", "passwordING1");
		ResultSet rs = gXrGenerico("SELECT * FROM usuario WHERE email = '" + mail + "';");
		return rs;
	}
	
	public String getProveedorID(String mail) throws SQLException {
		conectar("u2017b-3", "passwordING1");
		ResultSet rs = gXrGenerico("SELECT id FROM proveedor WHERE email = '" + mail + "';");
		return rs.getString("id");
	}

	// FUNCIONES PARA ESPECTACULO ---------------------------------------------

	public void addEspectaculo(Espectaculo espectaculo, String proveedor) throws SQLException {
		if (espectaculoExists(espectaculo.getNombre(), espectaculo.getLugarDeRetiro())) {
			throw new EspectaculoExistenteException("El espectaculo que ingreso ya esta registrado.");
		}
		
		conectar("u2017b-3", "passwordING1");
		ejecutasql("INSERT INTO espectaculo VALUES( " + "'" + espectaculo.getNombre() + "'," + "'"
				+ espectaculo.getDescripcion() + "'," + "'" + espectaculo.getCategoria() + "'," + "'"
				+ espectaculo.getFechaEstreno() + "'," + "'" + espectaculo.getPromocion() + "'," + "'"
				+ espectaculo.getCantidadEntradas() + "'," + "'" + espectaculo.getLugarDeRetiro() + "'," + "'" 
				+ espectaculo.getPrecio() + "'," + getProveedorID(proveedor) + ", '0');");
	}
	
	public boolean espectaculoExists(String name, String lugar) throws SQLException {
		conectar("u2017b-3", "passwordING1");
		ResultSet rs = gXrGenerico("SELECT * FROM espectaculo WHERE espnombre = '" + name + "' AND lugarretiro = '" + lugar + "';");
		if (rs.getRow() == 0) {
			return false;
		}
		return true;
	}

	
	public Set<Espectaculo> getEspectaculos() throws SQLException {
		Set<Espectaculo> espectaculos = new HashSet<Espectaculo>();
		conectar("u2017b-3", "passwordING1");
		ResultSet rs = gXrGenerico("SELECT * FROM espectaculo;");
		while (rs.next()) {
			String nomb = rs.getString("espnombre");
			String desc = rs.getString("espdescripcion");
			String cat = rs.getString("categoria");
			String est = rs.getString("estreno");
			String promo = rs.getString("promocion");
			String cantEntradas = rs.getString("cantidadEntradas");
			String lugarRetiro = rs.getString("lugarRetiro");
			String precio = rs.getString("precio");
			String entradasVendidas = rs.getString("entradasVendidas");
			Espectaculo e = new Espectaculo(nomb, cantEntradas, est, promo, cat, lugarRetiro, precio, desc, entradasVendidas);
			espectaculos.add(e);
		}
		return espectaculos;
	}

	public Set<Espectaculo> getEspectaculos(String condicion) throws SQLException {
		Set<Espectaculo> espectaculos = new HashSet<Espectaculo>();
		conectar("u2017b-3", "passwordING1");
		String lugar = "WHERE categoria = '" + condicion + "'";
		String promocion = "WHERE promocion = '" + condicion + "'";
		// String fecha = "WHERE estreno = '" + condicion + "'";
		ResultSet rs;
		if (condicion.equals("teatro") || condicion.equals("cine") || condicion.equals("canchas")) {
			rs = gXrGenerico("SELECT * FROM espectaculo " + lugar + ";");
		} else if (condicion.equals("2x1") || condicion.equals("Banco Asociados")
				|| condicion.equals("Descuento a Jubilados")) {
			rs = gXrGenerico("SELECT * FROM espectaculo " + promocion + ";");
		} else {
			rs = gXrGenerico("SELECT * FROM espectaculo;");
		}

		while (rs.next()) {
			String nomb = rs.getString("espnombre");
			String desc = rs.getString("espdescripcion");
			String cat = rs.getString("categoria");
			String est = rs.getString("estreno");
			String promo = rs.getString("promocion");
			String cantEntradas = rs.getString("cantidadEntradas");
			String lugarRetiro = rs.getString("lugarRetiro");
			String precio = rs.getString("precio");
			String entradasVendidas = rs.getString("entradasVendidas");
			Espectaculo e = new Espectaculo(nomb, cantEntradas, est, promo, cat, lugarRetiro, precio, desc, entradasVendidas);
			espectaculos.add(e);
		}
		return espectaculos;
	}

	public Set<Espectaculo> getEspectaculosMasVendidos() throws SQLException {
		Set<Espectaculo> espectaculos = new HashSet<Espectaculo>();
		conectar("u2017b-3", "passwordING1");
		ResultSet rs = gXrGenerico("SELECT * FROM espectaculo ORDER BY cantvendida DESC;");
		int count = 0;
		while (rs.next() && count < 3) {
			String nomb = rs.getString("espnombre");
			String desc = rs.getString("espdescripcion");
			String cat = rs.getString("categoria");
			String est = rs.getString("estreno");
			String promo = rs.getString("promocion");
			String cantEntradas = rs.getString("cantidadEntradas");
			String lugarRetiro = rs.getString("lugarRetiro");
			String precio = rs.getString("precio");
			String entradasVendidas = rs.getString("entradasVendidas");
			Espectaculo e = new Espectaculo(nomb, cantEntradas, est, promo, cat, lugarRetiro, precio, desc, entradasVendidas);
			espectaculos.add(e);
			count++;
		}
		return espectaculos;
	}
	
	public Set<Espectaculo> getEspectaculosPorProveedor(String mail) throws SQLException {
		Set<Espectaculo> espectaculos = new HashSet<Espectaculo>();
		String id = getProveedorID(mail);
		conectar("u2017b-3", "passwordING1");
		ResultSet rs = gXrGenerico("SELECT * FROM espectaculo WHERE proveedorid = '" + id + "';");
		while (rs.next()) {
			String nomb = rs.getString("espnombre");
			String desc = rs.getString("espdescripcion");
			String cat = rs.getString("categoria");
			String est = rs.getString("estreno");
			String promo = rs.getString("promocion");
			String cantEntradas = rs.getString("cantidadEntradas");
			String lugarRetiro = rs.getString("lugarRetiro");
			String precio = rs.getString("precio");
			String entradasVendidas = rs.getString("entradasVendidas");
			Espectaculo e = new Espectaculo(nomb, cantEntradas, est, promo, cat, lugarRetiro, precio, desc, entradasVendidas);
			espectaculos.add(e);
		}
		return espectaculos;
	}

	
	public void addEntradasVendidas(String nombreEspectaculo, String lugarEspectaculo, int vendidas) throws SQLException {
		int entradasYaVendidas = 0;
		conectar("u2017b-3", "passwordING1");
		ResultSet rs = gXrGenerico("SELECT * FROM espectaculo WHERE espnombre = '" + nombreEspectaculo + "' AND lugarRetiro = '" + lugarEspectaculo + "';");
		if (rs.next()) {
			entradasYaVendidas = Integer.parseInt(rs.getString("entradasVendidas"));
		}
		
		ejecutasql("UPDATE espectaculo SET entradasVendidas = '" + (entradasYaVendidas + vendidas) + "' WHERE espnombre = '" + nombreEspectaculo + "' AND lugarRetiro = '" + lugarEspectaculo + "';");
	}
}

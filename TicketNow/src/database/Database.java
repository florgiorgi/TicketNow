package database;

import java.util.Set;

import espectaculo.Espectaculo;
import espectaculo.Funcion;

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

	// Agrega un usuario a la base de datos
	public boolean addCliente(Cliente cliente) throws SQLException {
		if (userExists(cliente.getUsuario())) {
			System.out.println("excepcion");
			throw new UsuarioExistenteException(
					"El usuario que intenta registrar ya existe en el sistema. Verifique el nombre de usuario ingresado.");
		}
		
		conectar("u2017b-3", "passwordING1");
		/* Hay que agregar pais y bla */
		ejecutasql("INSERT INTO usuario VALUES( " + "'" + cliente.getUsuario() + "'," + "'"
				+ cliente.getContraseña().toString() + "'," + "'" + cliente.getNombre() + "'," + "'"
				+ cliente.getApellido() + "'," + "'" + cliente.getMail() + "'," + "'" + cliente.getFechaNac() + "',"
				+ "'" + cliente.getTelefono() + "'," + "'" + cliente.getDNI() + "'," + "'" + cliente.getPais() + "'," + "'" + cliente.getProvincia() + "'," + "'" + cliente.getLocalidad() + "'," + "'"
				+ cliente.getDireccion() + "'," + "'" + cliente.getCodigoPostal() + "');");
		ejecutasql("INSERT INTO cliente VALUES( " + "'" + cliente.getUsuario() + "');");
		return true;
	}

	public boolean addProveedor(Proveedor proveedor) throws SQLException {
		if (userExists(proveedor.getUsuario())) {
			throw new UsuarioExistenteException(
					"El usuario que intenta registrar ya existe en el sistema. Verifique el nombre de usuario ingresado.");
		}

		conectar("u2017b-3", "passwordING1");
		ejecutasql("INSERT INTO usuario VALUES( " + "'" + proveedor.getUsuario() + "'," + "'"
				+ proveedor.getContraseña().toString() + "'," + "'" + proveedor.getNombre() + "'," + "'"
				+ proveedor.getApellido() + "'," + "'" + proveedor.getMail() + "'," + "'" + proveedor.getFechaNac()
				+ proveedor.getTelefono() + "'," + "'" + proveedor.getDNI() + "'," + "'" + proveedor.getPais() + "'," + "'" + proveedor.getProvincia() + "'," + "'" + proveedor.getLocalidad() + "'," + "'"
				+ proveedor.getDireccion() + "'," + "'" + proveedor.getCodigoPostal() + "');");
		ejecutasql("INSERT INTO proveedor VALUES( '" + proveedor.getUsuario() + "';");
		return true;
	}

	public String getUserPassword(String mail) throws SQLException {
		conectar("u2017b-3", "passwordING1");
		ResultSet rs = gXrGenerico("SELECT contra FROM usuario WHERE email = '" + mail + "';");
		return rs.getString("contra");
	}

	public boolean userExists(String username) throws SQLException{
    	conectar("u2017b-3", "passwordING1");
    	ResultSet rs = gXrGenerico("SELECT * FROM usuario WHERE username = '"+username+"';");
    	if(rs.getRow() == 0) {
    		return false;
    	}
    	return true;
    }
	
	public boolean mailExists(String mail) throws SQLException{
    	conectar("u2017b-3", "passwordING1");
    	ResultSet rs = gXrGenerico("SELECT * FROM usuario WHERE email = '"+mail+"';");
    	if(rs.getRow() == 0) {
    		return false;
    	}
    	return true;
    }

	public boolean containsCliente(String mail, char[] contraseña) throws SQLException {
		if(mailExists(mail)) {
			String c = getUserPassword(mail);
			if (Arrays.equals(c.toCharArray(), contraseña)) {
				return true;
			}
		}

		return false;
	}

	public boolean containsProveedor(String mail, char[] contraseña) throws SQLException {
		if (userExists(mail)) {
			String c = getUserPassword(mail);
			if (c.toCharArray().equals(contraseña))
				return true;
		}

		return false;
	}

	// FUNCIONES PARA ESPECTACULO ---------------------------------------------

	public void addEspectaculo(Espectaculo espectaculo) throws SQLException {
		conectar("u2017b-3", "passwordING1");
		ejecutasql("INSERT INTO espectaculo VALUES( " + "'" + espectaculo.getNombre() + "'," + "'"
				+ espectaculo.getDescripcion() + "'," + "'" + espectaculo.getCategoria() + "'," + "'"
				+ espectaculo.getEstreno() + "'," + "'" + espectaculo.getPromocion() + "'" + ");");
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
			Espectaculo e = new Espectaculo(nomb, desc, cat, est, promo);
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
			Espectaculo e = new Espectaculo(nomb, desc, cat, est, promo);
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
			Espectaculo e = new Espectaculo(nomb, desc, cat, est, promo);
			espectaculos.add(e);
			count++;
		}
		return espectaculos;
	}

	// FUNCIONES PARA FUNCIONES

	public void addFuncion(Funcion funcion) {
		conectar("u2017b-3", "passwordING1");
		ejecutasql(
				"INSERT INTO funcion VALUES( " + "'" + funcion.getNombre() + "'," + "'" + funcion.getHora().toString()
						+ "'," + "'" + funcion.getFecha() + "'," + "'" + funcion.getPrecio() + "'," + "'"
						+ funcion.getLugar() + "'," + "'" + funcion.getNombre() + "'" + ");");
	}

	/*
	 * 
	 * public boolean containsProveedor(String mail, char[] contraseña) { for
	 * (Proveedor each : proveedores) if (each.getMail().equals(mail)) return
	 * checkContraseña(each, contraseña); return false; }
	 * 
	 * public boolean checkContraseña(Usuario c, char[] contraseña) { if
	 * (c.getContraseña().length != contraseña.length) return false; else return
	 * Arrays.equals(c.getContraseña(), contraseña); }
	 * 
	 * @Override public String toString() { StringBuffer str = new StringBuffer();
	 * 
	 * str.append("Clientes:\n"); for (Cliente each : clientes) { str.append(each);
	 * str.append("\n"); }
	 * 
	 * str.append("Proveedores:\n"); for (Proveedor each : proveedores) {
	 * str.append(each); str.append("\n"); }
	 * 
	 * return str.toString(); }
	 * 
	 * public Set<Cliente> getClientes() { return clientes; }
	 * 
	 * public Set<Proveedor> getProveedores() { return proveedores; }
	 */
}

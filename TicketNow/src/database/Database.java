package database;

import java.util.Set;

import compra.Compra;
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

		if (usuarioMailExists(usuario.getMail())) {
			throw new UsuarioExistenteException("El mail que ingresó ya esta registrado en el sistema.");
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

	public boolean usuarioMailExists(String mail) throws SQLException {
		conectar("u2017b-3", "passwordING1");
		ResultSet rs = gXrGenerico("SELECT * FROM usuario WHERE email = '" + mail + "';");
		if (rs.getRow() == 0) {
			return false;
		}
		return true;
	}

	public boolean containsCliente(String mail, char[] contraseña) throws SQLException {
		if (usuarioMailExists(mail)) {
			String c = getUserPassword(mail);
			if (Arrays.equals(c.toCharArray(), contraseña)) {
				return true;
			}
		}

		return false;
	}

	public boolean containsProveedor(String mail, char[] contraseña) throws SQLException {
		if (usuarioMailExists(mail)) {
			String c = getUserPassword(mail);
			if (Arrays.equals(c.toCharArray(), contraseña)) {
				return true;
			}
		}

		return false;
	}

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

	public void eliminarUsuario(String mail) {
		conectar("u2017b-3", "passwordING1");
		ejecutasql("DELETE FROM usuario WHERE email = '" + mail + "';");
	}

	public boolean updateUsuario(Usuario usuario) throws SQLException {
		ejecutasql("UPDATE usuario SET contra = '" + usuario.getContraseña() + "', nombre = '" + usuario.getNombre()
				+ "', apellido ='" + usuario.getApellido() + "', cumple = '" + usuario.getFechaNac() + "', telefono = '"
				+ usuario.getTelefono() + "', pais = '" + usuario.getPais() + "', provincia = '"
				+ usuario.getProvincia() + "', localidad = '" + usuario.getLocalidad() + "', direccion = '"
				+ usuario.getDireccion() + "', codigoPostal = '" + usuario.getCodigoPostal() + "' WHERE email = '"
				+ usuario.getMail() + "';");
		return true;
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
				+ espectaculo.getPrecio() + "'," + "'" + getProveedorID(proveedor) + "'," + "'"
				+ espectaculo.getCantidadEntradasVendidas() + "');");
	}

	public boolean espectaculoExists(String name, String lugar) throws SQLException {
		conectar("u2017b-3", "passwordING1");
		ResultSet rs = gXrGenerico(
				"SELECT * FROM espectaculo WHERE espnombre = '" + name + "' AND lugarretiro = '" + lugar + "';");
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
			String puntaje = rs.getString("puntaje");
			Espectaculo e = new Espectaculo(nomb, cantEntradas, est, promo, cat, lugarRetiro, precio, desc,
					entradasVendidas, puntaje);
			espectaculos.add(e);
		}
		return espectaculos;
	}

	public Set<Espectaculo> getEspectaculos(String busqueda, String lugar, String promocion)
			throws SQLException {

		Set<Espectaculo> espectaculos = new HashSet<Espectaculo>();
		boolean primero = true;
		conectar("u2017b-3", "passwordING1");
		String base = "SELECT * FROM espectaculo";
		if (busqueda != null || lugar != null || promocion != null) {
			base += " WHERE ";
		} else {
			base += ";";
		}
		if (busqueda != null) {
			primero = false;
			base += "espnombre LIKE '" + busqueda + "%'";
		}
		if (lugar != null) {
			if (!primero) {
				base += " AND ";
			}
			primero = false;
			base += "categoria = '" + lugar + "'";
		}
		if (promocion != null) {
			if (!primero) {
				base += " AND ";
			}
			primero = false;
			base += "promocion = '" + promocion + "'";
		}
		base += ";";

		ResultSet rs;
		rs = gXrGenerico(base);

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
			String puntaje = rs.getString("puntaje");
			Espectaculo e = new Espectaculo(nomb, cantEntradas, est, promo, cat, lugarRetiro, precio, desc,
					entradasVendidas, puntaje);
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
			String puntaje = rs.getString("puntaje");
			Espectaculo e = new Espectaculo(nomb, cantEntradas, est, promo, cat, lugarRetiro, precio, desc,
					entradasVendidas, puntaje);
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
			String puntaje = rs.getString("puntaje");
			Espectaculo e = new Espectaculo(nomb, cantEntradas, est, promo, cat, lugarRetiro, precio, desc,
					entradasVendidas, puntaje);
			espectaculos.add(e);
		}
		return espectaculos;
	}

	public boolean removeEspectaculo(String nombre, String lugar) throws SQLException {
		conectar("u2017b-3", "passwordING1");
		ejecutasql("DELETE FROM espectaculo WHERE espnombre = '" + nombre + "'AND lugarretiro = '" + lugar + "';");
		return true;
	}

	public void addEntradasVendidas(String nombreEspectaculo, String lugarEspectaculo, int vendidas)
			throws SQLException {
		int entradasYaVendidas = 0;
		conectar("u2017b-3", "passwordING1");
		ResultSet rs = gXrGenerico("SELECT * FROM espectaculo WHERE espnombre = '" + nombreEspectaculo
				+ "' AND lugarRetiro = '" + lugarEspectaculo + "';");
		if (rs.next()) {
			entradasYaVendidas = Integer.parseInt(rs.getString("entradasVendidas"));
		}

		ejecutasql("UPDATE espectaculo SET entradasVendidas = '" + (entradasYaVendidas + vendidas)
				+ "' WHERE espnombre = '" + nombreEspectaculo + "' AND lugarRetiro = '" + lugarEspectaculo + "';");
	}

	public ResultSet getEspetaculo(String espectaculo) throws SQLException {
		conectar("u2017b-3", "passwordING1");
		ResultSet rs1 = gXrGenerico("SELECT * FROM espectaculo WHERE espnombre = '" + espectaculo + "';");
		String lugar = rs1.getString("lugarretiro");
		ResultSet rs = gXrGenerico("SELECT * FROM espectaculo WHERE espnombre = '" + espectaculo + "' AND lugarRetiro = '" + lugar + "';");
		return rs;
	}

	public boolean updateEspectaculo(String nombre, String cantidad, String fechaDeEstreno, String promocion,
			String categoria, String lugar, String precio, String caracteristicas) throws SQLException {
		conectar("u2017b-3", "passwordING1");
		ejecutasql("UPDATE espectaculo SET espdescripcion = '" + caracteristicas + "', lugarretiro = '" + lugar
				+ "', estreno = '" + fechaDeEstreno + "', promocion = '" + promocion + "', precio = '" + precio
				+ "', cantidadEntradas = '" + cantidad + "' WHERE espnombre = '" + nombre + "' AND lugarRetiro = '"
				+ lugar + "';");
		return true;
	}

	public void puntuarEspectaculo(String espectaculo, String lugar, int value) throws SQLException {
		ResultSet rs = gXrGenerico("SELECT * FROM espectaculo WHERE espnombre = '" + espectaculo + "' AND lugarretiro = '" + lugar + "';");
		String str = rs.getString("puntaje");
		Integer puntaje;
		Integer nuevopuntaje;
		if (str != null) {
			puntaje = Integer.parseInt(str);
			nuevopuntaje = (puntaje + value) / 2;
		} else
			nuevopuntaje = value;
		conectar("u2017b-3", "passwordING1");
		String npuntaje = "" + nuevopuntaje;
		ejecutasql("UPDATE espectaculo SET puntaje = '" + npuntaje + "' WHERE espnombre = '" + espectaculo
				+ "' AND lugarRetiro = '" + lugar + "';");

	}

	public void agregarCompra(String espectaculo, String lugar, Integer cantidad, Integer precio, String nombreUsuario) throws SQLException {
		if (compraExists(espectaculo, lugar, nombreUsuario)) {
			actualizarCompra(espectaculo, lugar, cantidad, nombreUsuario);
			return;
		}
		Integer total = cantidad * precio;
		conectar("u2017b-3", "passwordING1");
		ejecutasql("INSERT INTO compra VALUES( " + "'" + nombreUsuario + "'," + "'" + espectaculo + "'," + "'"
				+ cantidad + "'," + "'" + lugar + "'," + "'" + total +"');");

	}
	
	public void actualizarCompra(String espectaculo, String lugar, Integer cantidad, String nombreUsuario) throws SQLException {
		ResultSet rs = gXrGenerico("SELECT * FROM compra WHERE espnombre = '" + espectaculo + "' AND lugar = '" + lugar
				+ "' AND clienteid = '" + nombreUsuario + "';");
		String cant = rs.getString("cantidad");
		Integer c = Integer.parseInt(cant);
		cantidad += c;
		conectar("u2017b-3", "passwordING1");
		ejecutasql("UPDATE compra SET cantidad = '" + cantidad + "' WHERE espnombre = '" + espectaculo
				+ "' AND lugar = '" + lugar + "' AND clienteid = '" + nombreUsuario + "';");
	}

	public boolean compraExists(String nombre, String lugar, String nombreUsuario) throws SQLException {
		conectar("u2017b-3", "passwordING1");
		ResultSet rs = gXrGenerico("SELECT * FROM compra WHERE espnombre = '" + nombre + "' AND lugar = '" + lugar
				+ "' AND clienteid = '" + nombreUsuario + "';");
		if (rs.getRow() == 0) {
			return false;
		}
		return true;
	}
	
	public Set<Compra> getComprasCliente(String mail) throws SQLException {
		Set<Compra> compras = new HashSet<Compra>();
		conectar("u2017b-3", "passwordING1");
		ResultSet rs = gXrGenerico("SELECT * FROM compra WHERE clienteid = '" + mail + "';");
		while (rs.next()) {
			String nomb = rs.getString("espnombre");
			String lugar = rs.getString("lugar");
			String cantidad = rs.getString("cantidad");
			String total = rs.getString("total");
			Compra c = new Compra(mail, nomb, lugar, Integer.parseInt(cantidad), Integer.parseInt(total));
			compras.add(c);
		}
		return compras;
	}

	public void modificarCompra(String espectaculo, String lugar, Integer cantEntradas, String mail, Integer precio) {
		Integer total = cantEntradas * precio;
		conectar("u2017b-3", "passwordING1");
		ejecutasql("UPDATE compra SET cantidad = '" + cantEntradas + "', total = '" + total
				+ "' WHERE espnombre = '" + espectaculo
				+ "' AND lugar = '" + lugar + "' AND clienteid = '" + mail + "';");
		
	}
	
	public void removerCompra(String espectaculo, String lugar, String mail) {
		conectar("u2017b-3", "passwordING1");
		ejecutasql("DELETE FROM compra WHERE espnombre = '" + espectaculo + "'AND lugar = '" + lugar + "'AND clienteid = '" + mail + "';");
		
	}

	public void removerCompras(String mail) {
		conectar("u2017b-3", "passwordING1");
		ejecutasql("DELETE FROM compra WHERE clienteid = '" + mail + "';");
		
	}

	public void actualizarEspectaculo(String nombre, String lugar, Integer cantidad, String mail) throws SQLException {
		conectar("u2017b-3", "passwordING1");
		ResultSet rs = gXrGenerico("SELECT * FROM espectaculo WHERE espnombre = '" + nombre + "' AND lugarretiro = '" + lugar + "';");
		String ce = rs.getString("cantidadentradas");
		String ev = rs.getString("entradasvendidas");
		System.out.println("hola2");
		Integer cantEntr = Integer.parseInt(ce);
		Integer entrVend = Integer.parseInt(ev);
		if(cantEntr-cantidad < 0)
			throw new NoHayEntradasRemanentesException("No hay la cantidad de entradas que desea disponible. Puede comprar hasta " + cantEntr + " entradas");
		
		cantEntr -= cantidad;
		entrVend += cantidad;
		
		ejecutasql("UPDATE espectaculo SET cantidadentradas = '" + cantEntr + "', entradasvendidas = '" + entrVend
				+ "' WHERE espnombre = '" + nombre
				+ "' AND lugarretiro = '" + lugar + "';");
		
	}
}
package database;

import java.util.Set;

import espectaculo.Espectaculo;
import espectaculo.Funcion;
import fecha.Fecha;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashSet;

import usuario.*;

public class Database {

	private Set<Cliente> clientes;
	private Set<Proveedor> proveedores;
	
	Connection con;
	String connect_string = "jdbc:postgresql://localhost:9999/u2017b-3";
	String driver_class = "org.postgresql.Driver";	
	
   Statement stmt;
   ResultSet rs;
   
   public boolean conectar(String cUsuario, String cPassword) {
		try {
		 Class.forName(driver_class); //Esto chequea que el driver se encuentre instalado
		} catch(java.lang.ClassNotFoundException e) {  //Captura el error
		System.err.print("Oracle Class no found exception: ");
		System.err.println(e.getMessage());
		return false;
	}
	try {
	con = DriverManager.getConnection(connect_string, cUsuario,  cPassword); //Hace la conexion
    } catch(SQLException ex) {
		System.err.println("Error de Conexion: "  + ex.getMessage());
		return false;
	} 
	return true;
    }

    public boolean desconectar() {
    try {
	      con.close(); //Cierre la conexion
    } catch(SQLException ex) {
		System.err.println("Error de desconexion: "  + ex.getMessage());
		return false;
	}
    return true;
    }

    public boolean ejecutasql(String cSql){
		try {
       stmt = con.createStatement();
       stmt.executeUpdate(cSql);
		   stmt.close();
    } catch(SQLException ex) {
		System.err.println("Error de Sql (DML): "  + ex.getMessage());
		return false;
	}
	return true;
    }

    public ResultSet gXrGenerico(String cSql) {
	try {
       stmt = con.createStatement();
       //stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
       rs = stmt.executeQuery(cSql);
       rs.next();
       //System.out.println(rs.getString(1));
    } catch(SQLException ex) {
		System.err.println("Error de Sql (query): "  + ex.getMessage());
	}

    return rs;

    }
    
//    Agrega un usuario a la base de datos
    public void addCliente(Cliente cliente) throws SQLException {
    	conectar("u2017b-3", "passwordING1");
    	ejecutasql("INSERT INTO usuario VALUES( " + 
    				"'" + cliente.getUsuario() + "'," + 
    				"'" + cliente.getContraseña().toString() + "'," +
    				"'" + cliente.getNombre() + "'," +
    				"'" + cliente.getApellido() + "'," +
    				"'" + cliente.getMail() + "'," +
    				"'" + cliente.getFechaNac() + "');");
    	ejecutasql("INSERT INTO cliente VALUES( " + 
				"'" + cliente.getUsuario() +"');");
    	
	}
    
    public void addProveedor(Proveedor proveedor) throws SQLException {
    	conectar("u2017b-3", "passwordING1");
    	ejecutasql("INSERT INTO usuario VALUES( " + 
    				"'" + proveedor.getUsuario() + "'," + 
    				"'" + proveedor.getContraseña().toString() + "'," +
    				"'" + proveedor.getNombre() + "'," +
    				"'" + proveedor.getApellido() + "'," +
    				"'" + proveedor.getMail() + "'," +
    				"'" + proveedor.getFechaNac() + "');");
    	ejecutasql("INSERT INTO proveedor VALUES( '" + 
				proveedor.getUsuario()+"';");
	}
    
    public String getUserPassword(String username) throws SQLException{
    	conectar("u2017b-3", "passwordING1");
    	ResultSet rs = gXrGenerico("SELECT contra FROM usuario WHERE username = '"+username+"';");
    	System.out.println(rs.getString("contra"));
    	return rs.getString("contra");
    }
    
    public boolean userExists(String username) throws SQLException{
    	conectar("u2017b-3", "passwordING1");
    	ResultSet rs = gXrGenerico("SELECT * FROM usuario WHERE username = '"+username+"';");
    	if(!rs.next()){
    		return false;
    	}
    	return true;
    }
    
//    FUNCIONES PARA ESPECTACULO ---------------------------------------------
    
    public void addEspectaculo(Espectaculo espectaculo) throws SQLException {
    	conectar("u2017b-3", "passwordING1");
    	ejecutasql("INSERT INTO espectaculo VALUES( " + 
    				"'" + espectaculo.getNombre() + "'," +
    				"'" + espectaculo.getDescripcion() + "'," +
    				"'" + espectaculo.getCategoria() + "'," +
    				"'" + fechaToDate(espectaculo.getEstreno()) + "'," +
    				"'" + espectaculo.getPromocion() + "'" + ");");
    }
    
    public Set<Espectaculo> getEspectaculos() throws SQLException{
    	Set<Espectaculo> espectaculos = new HashSet<Espectaculo>();
    	conectar("u2017b-3", "passwordING1");
    	ResultSet rs = gXrGenerico("SELECT * FROM espectaculo;");
    	while(rs.next()){
    		String nomb = rs.getString("espnombre");
    		String desc = rs.getString("espdescripcion");
    		String cat = rs.getString("categoria");
    		String est = rs.getString("estreno");
    		String promo = rs.getString("promocion"); 
    		Espectaculo e = new Espectaculo(nomb, desc, cat, dateToFecha(est), promo);
    		espectaculos.add(e);
    	}
    	return espectaculos;
    }
    
    public Set<Espectaculo> getEspectaculos(String condicion) throws SQLException{
    	Set<Espectaculo> espectaculos = new HashSet<Espectaculo>();
    	conectar("u2017b-3", "passwordING1");
    	String lugar = "WHERE categoria = '" + condicion + "'";
    	String promocion = "WHERE promocion = '" + condicion + "'";
    	//String fecha = "WHERE estreno = '" + condicion + "'";
    	ResultSet rs;
    	if(condicion.equals("teatro") || condicion.equals("cine") || condicion.equals("canchas")){
    		rs = gXrGenerico("SELECT * FROM espectaculo " + lugar + ";");	
    	}
    	else if(condicion.equals("2x1") || condicion.equals("Banco Asociados") || condicion.equals("Descuento a Jubilados")) {
    		rs = gXrGenerico("SELECT * FROM espectaculo " + promocion + ";");
    	}
    	else {
    		rs = gXrGenerico("SELECT * FROM espectaculo;");
    	}
    	
    	while(rs.next()){
    		String nomb = rs.getString("espnombre");
    		String desc = rs.getString("espdescripcion");
    		String cat = rs.getString("categoria");
    		String est = rs.getString("estreno");
    		String promo = rs.getString("promocion"); 
    		Espectaculo e = new Espectaculo(nomb, desc, cat, dateToFecha(est), promo);
    		espectaculos.add(e);
    	}
    	return espectaculos;
    }
    
    public Set<Espectaculo> getEspectaculosMasVendidos() throws SQLException{
    	Set<Espectaculo> espectaculos = new HashSet<Espectaculo>();
    	conectar("u2017b-3", "passwordING1");
    	ResultSet rs = gXrGenerico("SELECT * FROM espectaculo ORDER BY cantvendida DESC;");
    	int count = 0;
    	while(rs.next() && count < 3){
    		String nomb = rs.getString("espnombre");
    		String desc = rs.getString("espdescripcion");
    		String cat = rs.getString("categoria");
    		String est = rs.getString("estreno");
    		String promo = rs.getString("promocion"); 
    		Espectaculo e = new Espectaculo(nomb, desc, cat, dateToFecha(est), promo);
    		espectaculos.add(e);
    		count++;
    	}
    	return espectaculos;
    }
    
//    FUNCIONES PARA FUNCIONES
    
    public void addFuncion(Funcion funcion){
    	conectar("u2017b-3", "passwordING1");
    	ejecutasql("INSERT INTO funcion VALUES( " + 
    				"'" + funcion.getNombre() + "'," +
    				 "'"+ funcion.getHora().toString() + "'," +
    				 "'"+ fechaToDate(funcion.getFecha()) + "'," +
    				 "'"+ funcion.getPrecio() + "'," +
    				 "'"+ funcion.getLugar() + "'," +
    				 "'"+ funcion.getNombre() + "'" +
    				 ");");
    }
    
    
    
    
    public Fecha dateToFecha(String date){
    	return new Fecha(new Integer(String.valueOf(date.substring(8,9))), 
    					 new Integer(String.valueOf(date.substring(5,6))), 
    					 new Integer(String.valueOf(date.substring(0,3))));
    }
    
    public String fechaToDate(Fecha fecha){
    	return fecha.getAño()+"-"+fecha.getMes()+"-"+fecha.getDia();
    }
    
    

	public Database() {
		clientes = new HashSet<Cliente>();
		proveedores = new HashSet<Proveedor>();
	}

	public boolean add(Cliente c) {
		for (Cliente each : clientes)
			if (each.equals(c))
				throw new UsuarioExistenteException(
						"El usuario que intenta registrar ya existe en el sistema. Verifique el nombre de usuario, mail y DNI ingresados.");

		return clientes.add(c);
	}

	public boolean add(Proveedor p) {
		for (Proveedor each : proveedores)
			if (each.equals(p))
				throw new UsuarioExistenteException(
						"El usuario que intenta registrar ya existe en el sistema. Verifique el nombre de usuario, mail y DNI ingresados.");

		return proveedores.add(p);
	}

	public boolean containsCliente(String c, char[] contraseña) {
		for (Cliente each : clientes)
			if (each.getUsuario().equals(c))
				return checkContraseña(each, contraseña);
		return false;
	}

	public boolean containsProveedor(String p, char[] contraseña) {
		for (Proveedor each : proveedores)
			if (each.getUsuario().equals(p))
				return checkContraseña(each, contraseña);
		return false;
	}

	public boolean checkContraseña(Usuario c, char[] contraseña) {
		if (c.getContraseña().length != contraseña.length)
			return false;
		else
			return Arrays.equals(c.getContraseña(), contraseña);
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();

		str.append("Clientes:\n");
		for (Cliente each : clientes) {
			str.append(each);
			str.append("\n");
		}

		str.append("Proveedores:\n");
		for (Proveedor each : proveedores) {
			str.append(each);
			str.append("\n");
		}

		return str.toString();
	}
	
	public Set<Cliente> getClientes(){
		return clientes;
	}
	
	public Set<Proveedor> getProveedores(){
		return proveedores;
	}
}

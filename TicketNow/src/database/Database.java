package database;

import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

import usuario.*;

public class Database {

	private Set<Cliente> clientes;
	private Set<Proveedor> proveedores;

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
}

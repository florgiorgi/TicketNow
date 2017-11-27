package usuario;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fecha.Fecha;

public class Usuario {

	private String nombre;
	private String apellido;
	private String usuario;
	private String mail;
	private Fecha fechaNac;
	private String DNI;
	private String telefono;
	private char[] contraseña;

	public Usuario(String usuario, char[] contraseña, char[] constraseñaChequeo, String nombre, String apellido,
			String fechaNac, String mail, String telefono, String DNI) {
		if (!usuarioValido(usuario))
			throw new UsuarioInvalidoException("El nombre de usuario es inválido");
		if (!contraseñaValida(contraseña))
			throw new ContraseñaInvalidaException("La contraseña es inválida");
		if (!contraseñaCorrecta(contraseña, constraseñaChequeo))
			throw new ContraseñaIncorrectaException("Las contraseñas no coinciden");
		if (!nombreValido(nombre))
			throw new NombreInvalidoException("El nombre es inválido");
		if (!apellidoValido(apellido))
			throw new ApellidoInvalidoException("El apellido es inválido");
		if (!fechaNacValida(fechaNac))
			throw new FechaNacInvalidaException("La fecha de nacimiento es inválida");
		if (!mailValido(mail))
			throw new MailInvalidoException("El mail es inválido");
		if (!telefonoValido(telefono))
			throw new TelefonoInvalidoException("El teléfono es inválido");
		if (!DNIValido(DNI))
			throw new DNIInvalidoException("El DNI es inválido");
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.mail = mail;
		this.telefono = telefono;
		this.DNI = DNI;
		this.contraseña = contraseña;
	}

	private boolean contraseñaCorrecta(char[] c1, char[] c2) {
		if (c1.length != c2.length)
			return false;
		else
			return Arrays.equals(c1, c2);
	}

	private boolean usuarioValido(String usuario) {
		if (usuario == null || usuario.equals(""))
			return false;

		char[] aux = usuario.toCharArray();

		for (int i = 0; i < aux.length; i++) {
			/* una letra, un numero o .-_ */
			if (!Character.isLetterOrDigit(aux[i]) && Character.compare(aux[i], '.') != 0
					&& Character.compare(aux[i], '_') != 0 && Character.compare(aux[i], '-') != 0)
				return false;
		}
		return true;
	}

	private boolean contraseñaValida(char[] contraseña) {
		if (contraseña == null || contraseña.length == 0)
			return false;

		for (int i = 0; i < contraseña.length; i++) {
			if (!Character.isLetterOrDigit(contraseña[i]))
				return false;
		}
		return true;
	}

	private boolean nombreValido(String nombre) {
		if (nombre == null || nombre.equals(""))
			return false;

		char[] aux = nombre.toCharArray();

		for (int i = 0; i < aux.length; i++) {
			if (!Character.isLetter(aux[i]))
				return false;
		}
		return true;
	}

	private boolean apellidoValido(String apellido) {
		if (apellido == null || apellido.equals(""))
			return false;

		char[] aux = apellido.toCharArray();

		for (int i = 0; i < aux.length; i++) {
			if (!Character.isLetter(aux[i]) && !Character.isSpaceChar(aux[i]))
				return false;
		}
		return true;
	}

	private boolean fechaNacValida(String fecha) {
		if (formatoValido(fecha)) {

			Integer dia = Integer.valueOf(fecha.substring(0, 2));
			Integer mes = Integer.valueOf(fecha.substring(3, 5));
			Integer año = Integer.valueOf(fecha.substring(6, 10));

			try {
				this.fechaNac = new Fecha(dia, mes, año);
			} catch (IllegalArgumentException e) {
				return false;
			}
			return true;
		}
		return false;
	}

	private boolean formatoValido(String fecha) {
		char[] aux = fecha.toCharArray();

		for (int i = 0; i < aux.length; i++) {
			if (i == 2 || i == 5) {
				if (aux[i] != '/')
					return false;
			} else {
				if (!Character.isDigit(aux[i]))
					return false;
			}
		}
		return true;
	}
	
	private boolean mailValido(String mail) {
		String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	            + "[A-Za-z]+(\\.[A-Za-z]+)*(\\.[A-Za-z]{2,})$";
		
		Pattern pattern = Pattern.compile(PATTERN_EMAIL);
		 
     
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
	}

	private boolean telefonoValido(String telefono) {
		if (telefono == null || telefono.equals(""))
			return false;

		char[] aux = telefono.toCharArray();

		for (int i = 0; i < aux.length; i++) {
			if (!Character.isDigit(aux[i]))
				return false;
		}
		return true;
	}

	private boolean DNIValido(String dni) {
		if (dni == null || dni.equals(""))
			return false;

		char[] aux = dni.toCharArray();

		for (int i = 0; i < aux.length; i++) {
			if (!Character.isDigit(aux[i]))
				return false;
		}
		return true;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public String getMail() {
		return this.mail;
	}

	public Fecha getFechaNac() {
		return this.fechaNac;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public String getDNI() {
		return this.DNI;
	}

	public char[] getContraseña() {
		return this.contraseña;
	}

	public void modificarDatos(String nombre, String apellido, String mail, Fecha fechaNac, String DNI,
			char[] contraseña) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.fechaNac = fechaNac;
		this.DNI = DNI;
		this.contraseña = contraseña;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Usuario other = (Usuario) obj;

		if (getUsuario().equals(other.usuario) || getDNI().equals(other.DNI) || getMail().equals(other.mail))
			return true;

		return false;
	}

	@Override
	public String toString() {
		return this.usuario;
	}
}

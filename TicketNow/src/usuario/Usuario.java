package usuario;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario {
	
	private String nombre;
	private String apellido;
	private String usuario;
	private String mail;
	private String fechaNac;
	private String DNI;
	private String telefono;
	private String contraseña;
	private String pais;;
	private String provincia;
	private String localidad;
	private String direccion;
	private String codigoPostal;

	public Usuario(String usuario, String contraseña, String constraseñaChequeo, String nombre, String apellido,
			String fechaNac, String mail, String telefono, String DNI, String pais, String provincia, String localidad,
			String direccion, String codigoPostal) {
		if (!usuarioValido(usuario))
			throw new UsuarioInvalidoException("El nombre de usuario es inválido.");
		if (!contraseñaValida(contraseña))
			throw new ContraseñaInvalidaException("La contraseña es inválida. Recuerde que la misma debe contener entre 4 y 8 caracteres. ");
		if (!contraseñaCorrecta(contraseña, constraseñaChequeo))
			throw new ContraseñaIncorrectaException("Las contraseñas no coinciden.");
		if (!nombreValido(nombre))
			throw new NombreInvalidoException("El nombre es inválido.");
		if (!apellidoValido(apellido))
			throw new ApellidoInvalidoException("El apellido es inválido.");
		if (!fechaNacValida(fechaNac))
			throw new FechaNacInvalidaException("La fecha de nacimiento es inválida.");
		if (!mailValido(mail))
			throw new MailInvalidoException("El mail es inválido.");
		if (!telefonoValido(telefono))
			throw new TelefonoInvalidoException("El teléfono es inválido.");
		if (!dniValido(DNI))
			throw new DNIInvalidoException("El DNI es inválido. Recuerde que el formato del mismo es unicamente 8 cifras.");
		if(!localidadValida(localidad))
			throw new LocalidadInvalidaException("La localidad es inválida.");
		if(!direccionValida(direccion))
			throw new DireccionInvalidaException("La dirección es invalida.");
		if(!codigoPostalValido(codigoPostal))
			throw new CodigoPostalInvalidoException("El codigo postal es invalido. Recuerde que el formato del mismo es de unicamente 4 cifras.");
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.mail = mail;
		this.telefono = telefono;
		this.DNI = DNI;
		this.contraseña = contraseña;
		this.pais = pais;
		this.fechaNac = fechaNac;
		this.provincia = provincia;
		this.localidad = localidad;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
	}

	private boolean contraseñaCorrecta(String c1, String c2) {
		if (c1.length() != c2.length())
			return false;
		else
			return c1.equals(c2);
	}

	private boolean usuarioValido(String usuario) {
		if (usuario == null || usuario.equals(""))
			return false;

		char[] aux = usuario.toCharArray();

		for (int i = 0; i < aux.length; i++) {
			if (!Character.isLetterOrDigit(aux[i]) && Character.compare(aux[i], '.') != 0
					&& Character.compare(aux[i], '_') != 0 && Character.compare(aux[i], '-') != 0)
				return false;
		}
		return true;
	}

	private boolean contraseñaValida(String contraseña) {
		if (contraseña == null || contraseña.length() == 0)
			return false;

		for (int i = 0; i < contraseña.length(); i++) {
			if (!Character.isLetterOrDigit(contraseña.charAt(i)))
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
		if(fecha == null)
			return false;
		
		if (!formatoValido(fecha))
			return false;

		String[] f = fecha.split("-");
		
		Integer año = Integer.parseInt(f[0]);
		Integer mes = Integer.parseInt(f[1]);
		Integer dia = Integer.parseInt(f[2]);
		
		if (dia < 0 || mes < 0 || mes > 12 || año < 1930 || año > 2018)
			return false;
		if (f[1] == "01" || f[1] == "03" || f[1] == "05" || f[1] == "07" || f[1] == "08" || f[1] == "10"
				|| f[1] == "12") {
			if (dia > 31)
				return false;
		} else if (f[1] == "02") {
			if (dia > 28)
				return false;
		} else {
			if (dia > 30)
				return false;
		}
		
		return true;
	}

	private boolean formatoValido(String fecha) {
		char[] aux = fecha.toCharArray();

		for (int i = 0; i < aux.length; i++) {
			if (i == 4 || i == 7) {
				if (aux[i] != '-')
					return false;
			} else {
				if (!Character.isDigit(aux[i]))
					return false;
			}
		}
		return true;
	}

	private boolean mailValido(String mail) {
		if(mail == null)
			return false;
		
		String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z]+(\\.[A-Za-z]+)*(\\.[A-Za-z]{2,})$";

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

	private boolean dniValido(String dni) {
		if (dni == null || dni.equals("") || dni.length() != 8)
			return false;

		char[] aux = dni.toCharArray();
	
		for (int i = 0; i < aux.length; i++) {
			if (!Character.isDigit(aux[i]))
				return false;
		}
		return true;
	}

	private boolean paisValido(String pais) {
		if (pais.equals("Argentina") || pais.equals("Paraguay") || pais.equals("Uruguay")) {
			return true;
		}

		return false;
	}

	private boolean localidadValida(String localidad) {
		if(localidad == null || localidad.equals(""))
			return false;
		
		char[] aux = localidad.toCharArray();
		for (int i = 0; i < aux.length; i++) {
			if (!Character.isLetter(aux[i]))
				return false;
		}
		return true;
	}

	private boolean direccionValida(String direccion) {
		if(direccion == null || direccion.equals(""))
			return false;
		
		String[] str = direccion.split(" ");
		String calle = str[0];
		String numero = str[1];

		char[] aux1 = calle.toCharArray();
		for (int i = 0; i < aux1.length; i++) {
			if (!Character.isLetter(aux1[i]))
				return false;
		}

		char[] aux2 = numero.toCharArray();
		for (int i = 0; i < aux2.length; i++) {
			if (!Character.isDigit(aux2[i]))
				return false;
		}

		return true;
	}

	private boolean codigoPostalValido(String codigoPostal) {
		if (codigoPostal == null || codigoPostal.length() != 4)
			return false;

		char[] aux = codigoPostal.toCharArray();

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

	public String getFechaNac() {
		return this.fechaNac;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public String getDNI() {
		return this.DNI;
	}

	public String getContraseña() {
		return this.contraseña;
	}

	public String getPais() {
		return this.pais;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public String getCodigoPostal() {
		return this.codigoPostal;
	}

	public void modificarDatos(String nombre, String apellido, String mail, String fechaNac, String DNI,
			String contraseña) {
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
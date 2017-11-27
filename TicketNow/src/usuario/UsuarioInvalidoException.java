package usuario;

public class UsuarioInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 2L;
	
	public UsuarioInvalidoException(String msg) {
		super(msg);
	}
}

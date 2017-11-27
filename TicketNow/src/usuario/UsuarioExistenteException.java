package usuario;

public class UsuarioExistenteException extends RuntimeException {

	private static final long serialVersionUID = 5L;
	
	public UsuarioExistenteException(String msg) {
		super(msg);
	}
}

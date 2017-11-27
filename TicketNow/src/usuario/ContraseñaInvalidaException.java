package usuario;

public class ContraseñaInvalidaException extends RuntimeException {

	private static final long serialVersionUID = 3L;
	
	public ContraseñaInvalidaException(String msg) {
		super(msg);
	}
}

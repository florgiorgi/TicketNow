package usuario;

public class LocalidadInvalidaException extends RuntimeException {

	private static final long serialVersionUID = 4L;
	
	public LocalidadInvalidaException(String msg) {
		super(msg);
	}
}

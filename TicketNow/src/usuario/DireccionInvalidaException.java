package usuario;

public class DireccionInvalidaException extends RuntimeException {

	private static final long serialVersionUID = 4L;
	
	public DireccionInvalidaException(String msg) {
		super(msg);
	}
}

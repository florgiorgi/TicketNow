package usuario;

public class ApellidoInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 5L;
	
	public ApellidoInvalidoException(String msg) {
		super(msg);
	}
}

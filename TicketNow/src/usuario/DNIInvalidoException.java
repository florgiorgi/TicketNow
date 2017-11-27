package usuario;

public class DNIInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 9L;
	
	public DNIInvalidoException(String msg) {
		super(msg);
	}
}

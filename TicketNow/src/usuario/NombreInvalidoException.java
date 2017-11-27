package usuario;

public class NombreInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 4L;
	
	public NombreInvalidoException(String msg) {
		super(msg);
	}
}

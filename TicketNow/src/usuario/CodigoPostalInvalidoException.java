package usuario;

public class CodigoPostalInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 4L;
	
	public CodigoPostalInvalidoException(String msg) {
		super(msg);
	}
}
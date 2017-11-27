package usuario;

public class ContraseñaIncorrectaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ContraseñaIncorrectaException(String msg) {
		super(msg);
	}

}

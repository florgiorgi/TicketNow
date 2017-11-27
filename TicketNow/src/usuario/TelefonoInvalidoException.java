package usuario;

public class TelefonoInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 8L;
	
	public TelefonoInvalidoException(String msg) {
		super(msg);
	}
}

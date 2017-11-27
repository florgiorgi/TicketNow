package usuario;

public class MailInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 7L;
	
	public MailInvalidoException(String msg) {
		super(msg);
	}
}

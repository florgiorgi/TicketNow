package espectaculo;

public class EspectaculoExistenteException extends RuntimeException {

	private static final long serialVersionUID = 25L;
	
	public EspectaculoExistenteException(String msg) {
		super(msg);
	}
	
}
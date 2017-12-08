
package espectaculo;

public class PrecioInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 24L;
	
	public PrecioInvalidoException(String msg) {
		super(msg);
	}

}
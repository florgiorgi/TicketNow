package espectaculo;

public class CategoriaInvalidaException extends RuntimeException {
	
	private static final long serialVersionUID = 22L;

	public CategoriaInvalidaException(String msg) {
		super(msg);
	}

}

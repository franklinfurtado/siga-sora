package co.ao.sigp.catequese.domain.exception;

public class EntidadeEmUsoException extends NegocioException {

	private static final long serialVersionUID = 1L;
	
	public EntidadeEmUsoException() {}

	public EntidadeEmUsoException(String message) {
		super(message);
	}
}
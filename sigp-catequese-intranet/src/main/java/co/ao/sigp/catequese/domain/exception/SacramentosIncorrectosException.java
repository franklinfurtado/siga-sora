package co.ao.sigp.catequese.domain.exception;

public class SacramentosIncorrectosException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public SacramentosIncorrectosException(String mensagem) {
		super(mensagem);
	}
}
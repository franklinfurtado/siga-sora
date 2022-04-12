package co.ao.sigp.catequese.domain.exception;

public class SacramentosJaAdicionadoNaListaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public SacramentosJaAdicionadoNaListaException(String mensagem) {
		
		super(String.format("O sacramento %s ja existe na tabela", mensagem));
	}
}
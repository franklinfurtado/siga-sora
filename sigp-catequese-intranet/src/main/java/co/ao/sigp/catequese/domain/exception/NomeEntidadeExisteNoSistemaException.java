package co.ao.sigp.catequese.domain.exception;

public class NomeEntidadeExisteNoSistemaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public NomeEntidadeExisteNoSistemaException(String nome) {
		super(String.format("O nome (%s) já existe no sistema, tente outro nome", nome));
	}

	public NomeEntidadeExisteNoSistemaException(String descricaoErro, String other) {
		super(String.format("%s %s", descricaoErro, other));
	}
}
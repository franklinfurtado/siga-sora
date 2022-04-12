package co.ao.sigp.catequese.domain.exception;

public class EntidadeEmUsoExceptionCatequista extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoExceptionCatequista(String nome) {
		super(String.format(
				"Erro!!! Não foi possivel eliminar o catequista (%s) porque já existe histórico na catequese do catequista.",
				nome));
	}
}

package co.ao.sigp.catequese.domain.exception;

public class EntidadeEmUsoExceptionAnoLetivo extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoExceptionAnoLetivo(String nome) {
		super(String.format(
				"Erro!!! Não foi possivel eliminar o ano letivo (%s) porque já existem turmas de catequese e/ou inscrições de catecumenos registados com esta etapa. Elimine as turmas de catequese e/ou inscrições de catecumenos com este ano letivo e tente novamente",
				nome));
	}

}

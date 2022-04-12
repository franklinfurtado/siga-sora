package co.ao.sigp.catequese.domain.exception;

public class EntidadeEmUsoExceptionEtapaCatequese extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoExceptionEtapaCatequese(String nome) {
		super(String.format(
				"Erro!!! Não foi possivel eliminar a etapa da catequese (%s) porque já existem centros ou inscrições de catecumenos ou turmas registados com esta etapa. Elimine os centros ou inscrições de catecumenos ou turmas com esta etapa e tente novamente",
				nome));
	}

}

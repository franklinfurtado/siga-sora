package co.ao.sigp.catequese.domain.exception;

public class EntidadeEmUsoExceptionHorarioCatequese extends NegocioException {

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoExceptionHorarioCatequese(String message) {
		super(String.format("Erro!!! Não foi possivel eliminar o horario (%s) porque já existem turmas ou centros de catequese registados com este horario. Elimine as turmas ou centros de catequese com este horario e tente novamente", message));
	}
}
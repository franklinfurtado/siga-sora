package co.ao.sigp.catequese.domain.exception;

public class EntidadeEmUsoExceptionAnoLetivoEstado extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoExceptionAnoLetivoEstado(String nome) {
		super(String.format(
				"Erro!!! Não foi possivel eliminar o estado (%s) porque já existem anos letivos registados com esta etapa. Elimine os anos letivos com este estado e tente novamente",
				nome));
	}

}

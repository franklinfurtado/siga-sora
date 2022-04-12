package co.ao.sigp.catequese.domain.exception;

public class EntidadeEmUsoExceptionPastoral extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoExceptionPastoral(String nome) {
		super(String.format("Erro!!! Não foi possivel eliminar a pastoral (%s) porque já existem Grupos/Movimentos registados com esta pastoral. Elimine os grupos ou movimentos com esta pastoral e tente novamente", nome));
	}
}
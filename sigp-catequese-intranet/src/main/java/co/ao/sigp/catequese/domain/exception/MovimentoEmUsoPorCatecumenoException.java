package co.ao.sigp.catequese.domain.exception;

public class MovimentoEmUsoPorCatecumenoException extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public MovimentoEmUsoPorCatecumenoException(String nome) {
		super(String.format(
				"Erro!!! Não foi possivel eliminar o grupo ou movimento (%s) porque já existem Catecumenos registados com esta Grupo ou Movimento",
				nome));
	}
}
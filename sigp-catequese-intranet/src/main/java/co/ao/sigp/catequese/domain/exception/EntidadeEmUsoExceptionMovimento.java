package co.ao.sigp.catequese.domain.exception;

public class EntidadeEmUsoExceptionMovimento extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoExceptionMovimento(String nome) {
		super(String.format(
				"Erro!!! Não foi possivel eliminar o grupo ou movimento (%s) porque já existem Catequistas ou Catecumenos registados com esta Grupo ou Movimento. Elimine os catequistas e catecumenos com este grupo ou movimento e tente novamente",
				nome));
	}
}
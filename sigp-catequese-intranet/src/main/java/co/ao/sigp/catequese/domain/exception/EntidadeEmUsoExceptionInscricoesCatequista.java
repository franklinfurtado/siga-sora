package co.ao.sigp.catequese.domain.exception;

public class EntidadeEmUsoExceptionInscricoesCatequista extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoExceptionInscricoesCatequista(String nome) {
		super(String.format(
				"Erro!!! Não foi possível eliminar a inscrição de (%s) porque já se encontra registado(a) na base de dados de catequistas, remova primeiro da base de dados de catequistas e tente novamente",
				nome));
	}

}

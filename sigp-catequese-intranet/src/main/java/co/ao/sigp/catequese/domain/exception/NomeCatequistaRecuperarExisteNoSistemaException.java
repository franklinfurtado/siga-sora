package co.ao.sigp.catequese.domain.exception;

public class NomeCatequistaRecuperarExisteNoSistemaException extends NomeEntidadeExisteNoSistemaException {

	private static final long serialVersionUID = 1L;

	public NomeCatequistaRecuperarExisteNoSistemaException(String nome) {
		super(String.format("Não foi possível recuperar o catequista (%s), porque já existe registo de outra inscrição de um catequista com o mesmo nome", nome), "");
	}
}
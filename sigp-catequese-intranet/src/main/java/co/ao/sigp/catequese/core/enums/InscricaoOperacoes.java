package co.ao.sigp.catequese.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InscricaoOperacoes {

	APROVAR("Aprovar"),
	REJEITAR("Rejeitar"),
	EXCLUIR("Excluir"), 
	NOVO("Registado"), 
	EDITAR("Editado"), 
	RECUPERAR("Recuperado"), 
	REINICIAR("Reiniciar");

	private final String operacao;
}

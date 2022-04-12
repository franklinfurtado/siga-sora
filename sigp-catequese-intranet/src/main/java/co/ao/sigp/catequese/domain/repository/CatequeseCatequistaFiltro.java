package co.ao.sigp.catequese.domain.repository;

import java.util.List;

import co.ao.sigp.catequese.core.enums.EstadoDeRegisto;
import co.ao.sigp.catequese.domain.model.ParoquiaPastoral;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatequeseCatequistaFiltro {

	private String codigo;
	
	private String nomeCompleto;

	private List<String> gruposOuMovimentos;

	private ParoquiaPastoral pastoral;

	private String numeroTelefone;

	private EstadoDeRegisto estadoDeRegisto;

	private boolean filtroAtivo = Boolean.FALSE;

	public CatequeseCatequistaFiltro(EstadoDeRegisto estadoDeRegisto) {
		
		this.estadoDeRegisto = estadoDeRegisto;
	}
}

package co.ao.sigp.catequese.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import co.ao.sigp.catequese.core.enums.EstadoDeRegisto;
import co.ao.sigp.catequese.domain.model.CatequeseInscricoesEstado;
import co.ao.sigp.catequese.domain.model.ParoquiaGruposMovimentos;
import co.ao.sigp.catequese.domain.model.ParoquiaPastoral;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class CatequeseCatequistaInscricoesFiltro {

	private String nomeCompleto;

	private LocalDateTime dataInscricaoInicial;

	private LocalDateTime dataInscricaoFinal;

	private LocalDateTime dataRemocaoInicial;

	private LocalDateTime dataRemocaoFinal;
	
	private List<String> sacramentos;
	
	private CatequeseInscricoesEstado estado;
	
	private ParoquiaGruposMovimentos grupoOuMovimento;
	
	private ParoquiaPastoral pastoral;
	
	private String numeroTelefone;
	
	private boolean filtroAtivo = Boolean.FALSE;
	
	private EstadoDeRegisto estadoDeRegisto;
	
	public CatequeseCatequistaInscricoesFiltro(EstadoDeRegisto estadoDeRegisto) {

		this.estadoDeRegisto = estadoDeRegisto;
	}
}

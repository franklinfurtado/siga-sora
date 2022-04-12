package co.ao.sigp.catequese.domain.model.vo;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

import co.ao.sigp.catequese.core.util.jsf.lazydatamodel.LazyLoadIModel;
import co.ao.sigp.catequese.domain.model.CatequeseInscricoesEstado;
import co.ao.sigp.catequese.domain.model.CatequeseInscricoesTipo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatequeseInscricoesCatequistaVO implements LazyLoadIModel {

	private Integer id;
	private String nomeCompleto;
	private LocalDateTime dataInscricao;
	private LocalDateTime dataRemocao;
	private CatequeseInscricoesEstado estadoInscricao;
	private String estado;
	private CatequeseInscricoesTipo tipoInscricao;
	private String tipo;
	private boolean temBatismo;
	private boolean temComunhao;
	private boolean temCrisma;
	private boolean temMatrimonio;
	private String sacramentos;

	public void gerarSacramentos() {

		StringBuilder sacramentosBuilder = new StringBuilder();

		if (temBatismo) {
			sacramentosBuilder.append("Batismo");
		}

		if (temComunhao) {
			sacramentosBuilder.append(", Comunhão");
		}

		if (temCrisma) {
			sacramentosBuilder.append(", Crisma");
		}

		if (temMatrimonio) {
			sacramentosBuilder.append(", Matrimonio");
		}

		this.sacramentos = sacramentosBuilder.toString();
	}
	
	public void setEstadoInscricao(CatequeseInscricoesEstado estadoInscricao) {

		this.estadoInscricao = estadoInscricao;
		
		this.estado = estadoInscricao.getEstado();
	}

	public String getSacramentos() {

		gerarSacramentos();

		return this.sacramentos;
	}

	public String getCssEstado() {
		
		if (estadoInscricao != null) {
		
			return Arrays.asList(CatequeseInscricoesEstado.values()).stream()
					.filter(item -> item == estadoInscricao).map(item -> item.getCorCSS())
					.collect(Collectors.toList()).get(0);
		}

		return "";
	}

	public CatequeseInscricoesCatequistaVO(Integer id, String nomeCompleto, LocalDateTime dataInscricao,
			LocalDateTime dataRemocao, CatequeseInscricoesEstado estadoInscricao, CatequeseInscricoesTipo tipoInscricao,
			boolean temBatismo, boolean temComunhao, boolean temCrisma, boolean temMatrimonio) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.dataInscricao = dataInscricao;
		this.dataRemocao = dataRemocao;
		this.estadoInscricao = estadoInscricao;
		this.estado = estadoInscricao.getEstado();
		this.tipoInscricao = tipoInscricao;
		this.tipo = tipoInscricao.getTipo();
		this.temBatismo = temBatismo;
		this.temComunhao = temComunhao;
		this.temCrisma = temCrisma;
		this.temMatrimonio = temMatrimonio;
	}
}

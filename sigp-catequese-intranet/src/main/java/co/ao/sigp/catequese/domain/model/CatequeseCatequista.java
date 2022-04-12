/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import co.ao.sigp.catequese.core.enums.EstadoDeRegisto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author franklin.furtado
 */
@Entity
@Table(name = "catequese_catequista")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class CatequeseCatequista implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@EqualsAndHashCode.Include
	private Integer id;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	private String codigoCatequista;

	private LocalDate dataInicioCatecismo;

	@Basic(optional = false)
	@NotNull
	private int removido;

	private LocalDateTime dataRemocao;

	@JoinColumn(name = "id_dados_pessoais", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private GeralDadosPessoais idDadosPessoais;

	@JoinColumn(name = "id_sala_catequese_atual", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private CatequeseSalaCatequese idSalaCatequeseAtual;

	@OneToMany(mappedBy = "idCatequista", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CatequeseCatequistaSacramento> catequistaSacramentos;

	public void setCatequeseCatequistaSacramentoList(List<CatequeseCatequistaSacramento> catequeseCatequistaSacramentos) {

		if (catequeseCatequistaSacramentos != null)

			catequeseCatequistaSacramentos.forEach(item -> item.setIdCatequista(this));

		this.catequistaSacramentos = catequeseCatequistaSacramentos;
	}

	public void prepararNovoRegisto() {

		if (this.idDadosPessoais == null)

			this.idDadosPessoais = new GeralDadosPessoais();

		this.idDadosPessoais.prepararNovoRegisto();

		this.setRemovido(EstadoDeRegisto.NAO_APAGADO.getValue());

		this.catequistaSacramentos = new ArrayList<CatequeseCatequistaSacramento>();
	}

	public void prepararEditarRegisto() {

		this.idDadosPessoais.prepararEditar();
	}

	public boolean isNovoRegisto() {

		return this.id == null;
	}

	public boolean isApagado() {

		return this.removido == EstadoDeRegisto.APAGADO.getValue();
	}

	public String getNomeCompleto() {

		if (this.idDadosPessoais != null)

			return this.idDadosPessoais.getNomeCompleto();

		return "";
	}

	public void apagar(EstadoDeRegisto tipoRemocao) {

		this.dataRemocao = LocalDateTime.now();

		this.removido = tipoRemocao.getValue();
	}

	public void recuperar() {

		this.dataRemocao = null;

		this.removido = EstadoDeRegisto.NAO_APAGADO.getValue();
	}

	private List<Integer> getListaSacramentosObrigatorios() {

		return Arrays.asList(CatequeseSacramentosPadrao.BATISMO.getId(), CatequeseSacramentosPadrao.COMUNHAO.getId(), CatequeseSacramentosPadrao.CRIASMA.getId());
	}

	public boolean isSacramentosCorretos() {

		if (catequistaSacramentos.isEmpty())

			return false;

		return catequistaSacramentos.stream().map(CatequeseCatequistaSacramento::getIdSacramento).collect(Collectors.toList()).stream().map(CatequeseSacramento::getId).collect(Collectors.toList())
				.containsAll(getListaSacramentosObrigatorios());
	}

	public void adicionarSacramentoAoCatequista(CatequeseCatequistaSacramento catequistaSacramento) {

		catequistaSacramento.setIdCatequista(this);
		
		this.catequistaSacramentos.add(catequistaSacramento);
	}

	public void removerSacramentoAoCatequista(CatequeseCatequistaSacramento item) {

		this.catequistaSacramentos.remove(item);
	}

	public boolean sacramentoJaExiste(CatequeseCatequistaSacramento catequistaSacramento) {
		
		return this.catequistaSacramentos.contains(catequistaSacramento);
	}
}

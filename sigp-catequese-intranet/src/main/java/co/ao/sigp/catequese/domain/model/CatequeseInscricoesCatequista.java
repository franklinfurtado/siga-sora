/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import co.ao.sigp.catequese.core.enums.EstadoDeRegisto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author franklin.furtado
 */
@Entity
@Table(name = "catequese_inscricoes_catequista")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@DynamicUpdate
public class CatequeseInscricoesCatequista implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@EqualsAndHashCode.Include
	private Integer id;

	@CreationTimestamp
	private LocalDateTime dataInscricao;

	private LocalDateTime dataRemocao;

	@Basic(optional = false)
	@NotNull
	private int removido;

	@Basic(optional = false)
	@NotNull(message = "Informe o estado da inscrição")
	@Enumerated(EnumType.STRING)
	@Column(name = "estado_inscricao")
	private CatequeseInscricoesEstado estadoInscricao;

	@Basic(optional = false)
	@NotNull(message = "Informe o tipo de inscrição")
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_inscricao")
	private CatequeseInscricoesTipo tipoInscricao;

	@JoinColumn(name = "id_dados_pessoais", referencedColumnName = "id")
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotNull(message = "Informe os dados pessoais da inscrição")
	private GeralDadosPessoais idDadosPessoais;

	private boolean temBatismo;
	private String paroquiaBatismo;
	private LocalDate dataBatismo;

	private boolean temComunhao;
	private String paroquiaComunhao;
	private LocalDate dataComunhao;

	private boolean temCrisma;
	private String paroquiaCrisma;
	private LocalDate dataCrisma;

	private boolean temMatrimonio;
	private String paroquiaMatrimonio;
	private LocalDate dataMatrimonio;

	@Column(name = "tem_grupo_movimento")
	private boolean temGrupoMovimento;

	public void prepararSalvarRegisto() {

		this.estadoInscricao = CatequeseInscricoesEstado.CRIADO;

		this.tipoInscricao = CatequeseInscricoesTipo.LOCAL;

		if (this.idDadosPessoais == null)

			this.idDadosPessoais = new GeralDadosPessoais();

		this.idDadosPessoais.prepararNovoRegisto();

		this.setRemovido(EstadoDeRegisto.NAO_APAGADO.getValue());
	}

	public void prepararEditarRegisto() {

		this.idDadosPessoais.prepararEditar();
	}

	public void carregarDistrito(UtilitarioDistrito distrito) {

		if (distrito != null)

			this.idDadosPessoais.getIdEndereco().setIdDistrito(distrito);
	}

	public void aprovarInscricao() {
		
		this.estadoInscricao = CatequeseInscricoesEstado.APROVADO;
	}

	public void rejeitarInscricao() {
		
		this.estadoInscricao = CatequeseInscricoesEstado.REJEITADO;
	}

	public void reiniciarInscricao() {
		
		this.estadoInscricao = CatequeseInscricoesEstado.CRIADO;
	}

	public boolean isNovoRegisto() {
		
		return this.id == null;
	}

	public boolean sacramentosCorretos() {
		
		return temBatismo && temComunhao && temCrisma;
	}

	public boolean isApagado() {
		
		return this.removido == EstadoDeRegisto.APAGADO.getValue();
	}

	public String getNomeCompleto() {

		if (this.idDadosPessoais != null)

			return this.idDadosPessoais.getNomeCompleto();

		return "";
	}

	public void apagarInscricao(EstadoDeRegisto tipoRemocao) {

		this.dataRemocao = LocalDateTime.now();

		this.removido = tipoRemocao.getValue();
	}

	public void recuperarInscricao() {

		this.dataRemocao = null;

		this.removido = EstadoDeRegisto.NAO_APAGADO.getValue();
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Basic;
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
import org.hibernate.annotations.UpdateTimestamp;

import co.ao.sigp.catequese.core.enums.EstadoDeRegisto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author franklin.furtado
 */
@Entity
@Table(name = "catequese_inscricoes_catecumeno")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CatequeseInscricoesCatecumeno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @EqualsAndHashCode.Include
    private Integer id;

	@CreationTimestamp
	private LocalDate dataInscricao;
	
	@UpdateTimestamp
	private LocalDate dataRemocao;
    
    @Basic(optional = false)
	@NotNull(message = "Informe se a inscrição encontra-se removida ou não")
    private int removido;
    
    private Boolean temBatismo;
    private String paroquiaBatismo;
    private LocalDate dataBatismo;
    
    private Boolean temComunhao;
    private String paroquiaComunhao;
    private LocalDate dataComunhao;

    @JoinColumn(name = "id_centro_catequese_preferencial", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	@NotNull(message = "Informe o centro de catequese preferencial")
    private CatequeseCentroCatequese idCentroCatequesePreferencial;

    @JoinColumn(name = "id_centro_catequese_alternativo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	@NotNull(message = "Informe o centro de catequese alternativo")
    private CatequeseCentroCatequese idCentroCatequeseAlternativo;

    @JoinColumn(name = "id_etapa_catequese", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	@NotNull(message = "Informe a etapa da catequese")
    private CatequeseEtapaCatequese idEtapaCatequese;

	@Basic(optional = false)
	@NotNull(message = "Informe o estado da inscrição")
	@Enumerated(EnumType.STRING)
    @Column(name = "estado_inscricao")
	private CatequeseInscricoesEstado estado_inscricao;

	@Basic(optional = false)
	@NotNull(message = "Informe o tipo de inscrição")
	@Enumerated(EnumType.STRING)
    @Column(name = "tipo_inscricao")
	private CatequeseInscricoesTipo tipo_inscricao;

    @JoinColumn(name = "id_ano_letivo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	@NotNull(message = "Informe o ano letivo")
    private CatequeseAnoLetivo idAnoLetivo;

    @JoinColumn(name = "id_dados_pessoais", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	@NotNull(message = "Informe os dados pessoais")
    private GeralDadosPessoais idDadosPessoais;
    
    public void prepararNovoRegisto(){
        this.idAnoLetivo = new CatequeseAnoLetivo();
		this.estado_inscricao = CatequeseInscricoesEstado.CRIADO;
		this.tipo_inscricao = CatequeseInscricoesTipo.LOCAL;
        this.idCentroCatequeseAlternativo = new CatequeseCentroCatequese();
        this.idCentroCatequesePreferencial = new CatequeseCentroCatequese();
        this.idDadosPessoais = new GeralDadosPessoais();
        this.idDadosPessoais.prepararNovoRegisto();
        this.idEtapaCatequese = new CatequeseEtapaCatequese();
        this.setRemovido(EstadoDeRegisto.NAO_APAGADO.getValue());
    }
}

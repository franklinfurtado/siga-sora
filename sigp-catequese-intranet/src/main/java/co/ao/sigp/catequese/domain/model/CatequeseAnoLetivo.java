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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author franklin.furtado
 */
@Entity
@Table(name = "catequese_ano_letivo")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class CatequeseAnoLetivo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Integer id;
    
    @Basic(optional = false)
    @NotNull(message = "Informe o ano")
    @Column(name = "ano")
    private int ano;
    
    @Basic(optional = false)
    @NotNull(message = "Informe a data de início")
    @Column(name = "data_inicio")
    private LocalDate dataInicio;
    
    @Basic(optional = false)
    @NotNull(message = "Informe a data de término")
    @Column(name = "data_fim")
    private LocalDate dataFim;
    
    @Basic(optional = false)
    @NotBlank(message = "Informe a descrição")
    @Size(max = 100, message = "A descrição deve ter no máximo 100 caracteres")
    @Column(name = "descricao")
    private String descricao;
    
    @Basic(optional = false)
    @NotNull(message = "Informe o estado do ano letivo")
    @Column(name = "estado_ano_letivo")
	@Enumerated(EnumType.STRING)
    private CatequeseAnoLetivoEstados estadoAnoLetivo;
    
    public boolean isNovoRegisto() {
    	return this.id == null;
    }
    
    @PrePersist
    public void antesSalvar() {
    	
    	this.descricao = "Ano letivo de " + this.ano;
    	
    	this.estadoAnoLetivo = CatequeseAnoLetivoEstados.CRIADO;
    }
    
    @PreUpdate
    public void criarDescricao() {
    	
    	this.descricao = "Ano letivo de " + this.ano;
    }

	public boolean isDataFimInferiorOuIgualInicial() {
		
		return dataFim.isBefore(dataInicio) || (dataInicio.compareTo(dataFim) == 0);
	}
}

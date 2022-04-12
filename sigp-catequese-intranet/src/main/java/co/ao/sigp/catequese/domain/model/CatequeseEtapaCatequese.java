/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author franklin.furtado
 */
@Entity
@Table(name = "catequese_etapa_catequese")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class CatequeseEtapaCatequese implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Integer id;
    
    @Basic(optional = false)
    @NotBlank(message = "Informe a descrição/nome da etapa")
    @Column(name = "nome_etapa")
    private String nomeEtapa;
    
    @Basic
    @Column(name = "ano_limite_inscricao")
    private int anoLimiteInscricao;
    
    @Basic
    @Column(name = "mes_limite_inscricao")
    private String mesLimiteInscricao;
    
    public boolean isNovoRegisto() {
    	return this.id == null;
    }
}

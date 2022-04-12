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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author franklin.furtado
 */
@Entity
@Table(name = "paroquial_grupos_movimentos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class ParoquiaGruposMovimentos implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Integer id;
    
    @Basic(optional = false)
    @NotBlank(message = "Informe o nome do grupo ou movimento")
    @Size(max = 45, message = "O nome do grupo ou movimento deve ter no máximo 45 caracteres")
    @Column(name = "nome")
    private String nome;
    
    @Size(max = 45)
    @Column(name = "nome_coordenador")
    private String nomeCoordenador;
    
    @Column(name = "is_catequista")
    private Boolean isCatequista;
    
    @NotNull(message = "Selecione a pastoral")
    @JoinColumn(name = "idPastoral", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private ParoquiaPastoral idPastoral;
    
    
    public void prepararNovoRegisto() {
    	this.idPastoral = new ParoquiaPastoral();
    }
    
    public boolean isNovoRegisto() {
    	return this.id == null;
    }
    
    public boolean isCatequistaSelecionadoNomeResponsavelVazio() {
    	return isCatequista && nomeCoordenador.isBlank();
    }
}

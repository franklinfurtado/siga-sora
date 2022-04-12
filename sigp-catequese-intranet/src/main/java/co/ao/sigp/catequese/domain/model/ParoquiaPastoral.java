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
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author franklin.furtado
 */
@Entity
@Table(name = "paroquia_pastoral")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class ParoquiaPastoral implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Integer id;
    
    @Basic(optional = false)
    @NotBlank(message = "Informe o nome da pastoral")
    @Size(max = 45, message = "O nome da pastoral deve ter no máximo 45 caracteres")
    @Column(name = "nome_pastoral")
    private String nomePastoral;
    
    @Size(max = 80, message = "O nome do Responsável deve ter no máximo 80 caracteres")
    @Column(name = "nome_resonsavel")
    private String nomeResonsavel;
    
    @Column(name = "is_catequista")
    private Boolean isCatequista;
    
    public boolean isNovoRegisto() {
    	return this.id == null;
    }
    
    public boolean isCatequistaSelecionadoNomeResponsavelVazio() {
    	return isCatequista && nomeResonsavel.isBlank();
    }
    
    public String getInformacaoCatequista() {
    	
    	if (isCatequista) {
    		
    		return "Sim";
    		
    	} else if(!nomeResonsavel.isBlank()) {
    		
    		return "Não";
    	}
    	
    	return "";
    }
}

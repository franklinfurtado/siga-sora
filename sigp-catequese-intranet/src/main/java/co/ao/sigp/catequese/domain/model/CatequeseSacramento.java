/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import co.ao.sigp.catequese.core.enums.CatequeseSacramentos;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author franklin.furtado
 */
@Entity
@Table(name = "catequese_sacramento")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
public class CatequeseSacramento implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @EqualsAndHashCode.Include
    private Integer id;
    
    @Basic(optional = false)
    @NotBlank(message = "Informe o nome do sacramento")
    @Size(max = 45, message = "O nome do sacramento deve ter no maximo 45 caracteres")
    private String nomeSacramento;
    
    public CatequeseSacramento(Integer id) {
    	
    	this.id = id;
    }

	public void carregarNomeSacramento() {
		
		this.nomeSacramento = CatequeseSacramentos.getNomeById(id);
	}
}

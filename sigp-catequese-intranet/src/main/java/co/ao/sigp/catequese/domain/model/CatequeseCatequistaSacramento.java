/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author franklin.furtado
 */
@Entity
@Table(name = "catequese_catequista_sacramento")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class CatequeseCatequistaSacramento implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CatequeseCatequistaSacramentoPK id = new CatequeseCatequistaSacramentoPK();
    
    private LocalDate dataConclusaoSacramento;

    private String paroquia;
    
    @ManyToOne()
    @MapsId("idCatequista")
    @JoinColumn(name = "id_catequista")
    private CatequeseCatequista idCatequista;
    
    @EqualsAndHashCode.Include
    @ManyToOne
    @MapsId("idSacramento")
    @JoinColumn(name = "id_sacramento")
    private CatequeseSacramento idSacramento;
    
    public void carregarNomeSacramento() {
    	
    	this.idSacramento.carregarNomeSacramento();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author franklin.furtado
 */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatequeseCatequistaSacramentoPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "id_catequista")
	private Integer idCatequista;

	@Column(name = "id_sacramento")
	private Integer idSacramento;
}

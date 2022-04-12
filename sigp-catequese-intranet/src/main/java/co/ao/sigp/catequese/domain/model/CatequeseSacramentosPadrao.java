/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author franklinfurtado
 */
@Getter
@AllArgsConstructor
public enum CatequeseSacramentosPadrao {
	
    BATISMO(1, "Batismo"),
    COMUNHAO(2, "Comunhão"),
    CRIASMA(3, "Crisma"),
    MATRIMONIO(4, "Matrimônio");

	private final Integer id;
	
    private final String value;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.core.enums;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author franklinfurtado
 */
@Getter
@AllArgsConstructor
public enum CatequeseSacramentos {
    
    BATISMO(1, "Batismo"),
    COMUNHAO(2, "Comunha"),
    CRISMA(3, "Crisma"),
    MATRIMONIO(4, "Matrimonio");

    private final Integer id;
    
    private final String nome;
    
    public static String getNomeById(Integer id) {
    	
    	return Arrays.asList(CatequeseSacramentos.values()).stream().filter(sacramento -> sacramento.getId() == id).findAny().orElseThrow().getNome();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author franklinfurtado
 */
@Getter
@Setter
@AllArgsConstructor
public class ParoquiaGruposMovimentosVO {
	
    private Integer id;
    private String nome;
    private String nomeCoordenador;
    private Boolean isCatequista;
    private String nomePastoral;
    

    public String getInformacaoCatequista() {
    	
    	if (isCatequista) {
    		
    		return "Sim";
    		
    	} else if(nomeCoordenador != null && !nomeCoordenador.isBlank()) {
    		
    		return "Não";
    	}
    	
    	return "";
    }
}

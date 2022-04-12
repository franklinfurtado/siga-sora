/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author franklinfurtado
 */
@Getter
@AllArgsConstructor
public enum EstadoDeRegisto {
    
    NAO_APAGADO(0),
    APAGADO(1),
    APAGADO_PERMANENTEMENTE(2);

    private final Integer value;
}

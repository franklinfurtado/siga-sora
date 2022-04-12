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
public enum TipoListar {
    CARREGAR_DADOS_BASE_DADOS(true),
    NAO_CARREGAR_DADOS_BASE_DADOS(false);

    private final boolean value;
}

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
public enum ListarMembrosCatequese {
    
    LISTAR_POR_PASTORAL("Pastoral"),
    LISTAR_POR_ANO_LETIVO("Ano Letivo"),
    LISTAR_POR_GRUPO_MOVIMENTO("Grupo ou Movimento");

    private final String descricao;
}

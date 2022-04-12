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
public enum URLsSistema {
	
	SYSTEM_EMPTY(""),
	
    CATECUMENO_VER_LISTA("/listar_catecumeno_pesquisa"),
    CATECUMENO_INSCRICAO("/catecumeno_inscricoes"),
    
    CATEQUISTA_INSCRICAO_MOSTRAR_TODOS_ELIMINADOS("/lista-de-inscricoes-de-catequistas-eliminadas"),
    CATEQUISTA_INSCRICAO_MOSTRAR_TODOS("/lista-de-inscricoes-de-catequistas"),
    CATEQUISTA_INSCRICAO_FICHA_REGISTO("/ficha-inscricao-de-catequista-registo"),
    CATEQUISTA_INSCRICAO_FICHA_VER("/ficha-inscricao-de-catequista-ver"),
	
    CATEQUISTAS_MOSTRAR_TODOS("/lista-de-catequistas"),
    CATEQUISTAS_MOSTRAR_TODOS_ELIMINADOS("/lista-de-catequistas-eliminados"),
    CATEQUISTAS_FICHA_VER("/ficha-de-catequista-ver"),
    CATEQUISTAS_FICHA_REGISTO("/ficha-de-catequistas-registo");
    
    private final String url;
}

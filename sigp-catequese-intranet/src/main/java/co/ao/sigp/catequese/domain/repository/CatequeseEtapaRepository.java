/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.repository;

/**
 *
 * @author franklin.furtado
 */

import co.ao.sigp.catequese.domain.model.CatequeseEtapaCatequese;
import org.springframework.stereotype.Repository;
 
@Repository
public interface CatequeseEtapaRepository extends CustomJpaRepository<CatequeseEtapaCatequese, Integer>{
    
}
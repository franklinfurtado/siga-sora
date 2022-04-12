/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author franklin.furtado
 */

import co.ao.sigp.catequese.domain.model.UtilitarioDistrito;
 
@Repository
public interface UtilitarioDistritoRepository extends CustomJpaRepository<UtilitarioDistrito, Integer>{
    
    @Query("SELECT entity FROM UtilitarioDistrito entity WHERE entity.idMunicipio.id = :id")
    public List<UtilitarioDistrito> listarDistritosPorMunicipio(@Param("id") Integer id);
    
    @Query("SELECT entity FROM UtilitarioDistrito entity WHERE entity.id = :id")
    public UtilitarioDistrito pesquisarDistrito(@Param("id") Integer id);
}

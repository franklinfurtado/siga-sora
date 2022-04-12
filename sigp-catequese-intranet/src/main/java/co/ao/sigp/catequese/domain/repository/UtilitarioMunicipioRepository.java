/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.ao.sigp.catequese.domain.model.UtilitarioMunicipio;
 
@Repository
public interface UtilitarioMunicipioRepository extends CustomJpaRepository<UtilitarioMunicipio, Integer>{
    
    @Query("SELECT entity FROM UtilitarioMunicipio entity WHERE entity.idProvincia.id = 1")
    public List<UtilitarioMunicipio> listarMunicipioDeLuanda();
}

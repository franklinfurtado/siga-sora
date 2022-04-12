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

import co.ao.sigp.catequese.domain.model.Paroquia;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 
@Repository
public interface ParoquiaRepository extends CustomJpaRepository<Paroquia, Integer>{
    
    @Query("SELECT entity.nomeParoquia FROM Paroquia entity")
    public List<String> listarNomesParoquias();
}

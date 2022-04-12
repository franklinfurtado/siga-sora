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

import co.ao.sigp.catequese.domain.model.ParoquiaGruposMovimentos;
import co.ao.sigp.catequese.domain.model.vo.ParoquiaGruposMovimentosVO;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
@Repository
public interface ParoquiaGrupoMovimentoRepository extends CustomJpaRepository<ParoquiaGruposMovimentos, Integer>{
    
    @Query("SELECT new co.ao.sigp.catequese.domain.model.vo.ParoquiaGruposMovimentosVO(entity.id, entity.nome, entity.nomeCoordenador, entity.isCatequista, entity.idPastoral.nomePastoral) FROM ParoquiaGruposMovimentos entity")
    public List<ParoquiaGruposMovimentosVO> listarTodosVO();
    
    @Query("SELECT new co.ao.sigp.catequese.domain.model.vo.ParoquiaGruposMovimentosVO(entity.id, entity.nome, entity.nomeCoordenador, entity.isCatequista, entity.idPastoral.nomePastoral) FROM ParoquiaGruposMovimentos entity where entity.idPastoral.id = :idPastoral")
    public List<ParoquiaGruposMovimentosVO> listarTodosVOPorPastoral(@Param("idPastoral") int idPastoral);
    
    @Query("SELECT entity FROM ParoquiaGruposMovimentos entity where entity.idPastoral.id = :idPastoral")
    public List<ParoquiaGruposMovimentos> listarTodosPorPastoral(@Param("idPastoral") int idPastoral);
}

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

import co.ao.sigp.catequese.domain.model.CatequeseCatecumeno;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
@Repository
public interface CatequeseCatecumenoRepository extends CustomJpaRepository<CatequeseCatecumeno, Integer>{
    
    @Query("SELECT CASE WHEN (COUNT(catequeseCatecumeno) > 0) THEN true ELSE false END FROM CatequeseCatecumeno catequeseCatecumeno WHERE catequeseCatecumeno.idDadosPessoais.nome = :nome and catequeseCatecumeno.id = :id")
    public boolean nomeExistenteNoSistemaCustom(@Param("id") Integer id, @Param("nome") String nome);
    
    @Query("SELECT CASE WHEN (COUNT(catequeseCatecumeno) > 0) THEN true ELSE false END FROM CatequeseCatecumeno catequeseCatecumeno WHERE catequeseCatecumeno.idDadosPessoais.nome = :nome")
    public boolean nomeExistenteNoSistemaCustom(@Param("nome") String nome);
    
    @Query("SELECT catequeseCatecumeno FROM CatequeseCatecumeno catequeseCatecumeno WHERE catequeseCatecumeno.removido = :removido")
    public List<CatequeseCatecumeno> listarPorEstado(@Param("removido") int removido);
    
    @Query("SELECT CASE WHEN (COUNT(catequeseCatecumeno) > 0) THEN true ELSE false END FROM CatequeseCatecumeno catequeseCatecumeno WHERE catequeseCatecumeno.idDadosPessoais.id = :idDadosGeraisInscricao")
    public boolean existeCatecumenoComInscricao(@Param("idDadosGeraisInscricao") Integer idDadosGeraisInscricao);
}

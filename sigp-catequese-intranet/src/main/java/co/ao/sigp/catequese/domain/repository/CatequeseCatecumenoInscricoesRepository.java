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
 
import co.ao.sigp.catequese.domain.model.CatequeseInscricoesCatecumeno;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
@Repository
public interface CatequeseCatecumenoInscricoesRepository extends CustomJpaRepository<CatequeseInscricoesCatecumeno, Integer>{
    
    @Query("SELECT CASE WHEN (COUNT(entity) > 0) THEN true ELSE false END FROM CatequeseInscricoesCatecumeno entity WHERE entity.idDadosPessoais.nome = :nome and entity.id = :id")
    public boolean nomeExistenteNoSistemaCustom(@Param("id") Integer id, @Param("nome") String nome);
    
    @Query("SELECT CASE WHEN (COUNT(entity) > 0) THEN true ELSE false END FROM CatequeseInscricoesCatecumeno entity WHERE entity.idDadosPessoais.nome = :nome")
    public boolean nomeExistenteNoSistemaCustom(@Param("nome") String nome);
    
    @Query("SELECT entity FROM CatequeseInscricoesCatecumeno entity WHERE entity.removido = :removido")
    public List<CatequeseInscricoesCatecumeno> listarPorEstado(@Param("removido") int removido);
}

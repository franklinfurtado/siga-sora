/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author franklin.furtado
 */

import co.ao.sigp.catequese.domain.model.CatequeseCatequista;
import co.ao.sigp.catequese.domain.model.GeralDadosPessoais;
 
@Repository
public interface CatequeseCatequistaRepository extends CatequeseCatequistaRepositoryQueries, CustomJpaRepository<CatequeseCatequista, Integer>{
    
    @Query("SELECT CASE WHEN (COUNT(catequeseCatequista) > 0) THEN true ELSE false END FROM CatequeseCatequista catequeseCatequista WHERE catequeseCatequista.idDadosPessoais.nome = :nome and catequeseCatequista.id = :id")
    public boolean nomeExistenteNoSistemaCustom(@Param("id") Integer id, @Param("nome") String nome);
    
    @Query("SELECT CASE WHEN (COUNT(catequeseCatequista) > 0) THEN true ELSE false END FROM CatequeseCatequista catequeseCatequista WHERE catequeseCatequista.idDadosPessoais.nome = :nome")
    public boolean nomeExistenteNoSistemaCustom(@Param("nome") String nome);
    
    @Query("SELECT catequeseCatequista FROM CatequeseCatequista catequeseCatequista WHERE catequeseCatequista.removido = :removido")
    public List<CatequeseCatequista> listarPorEstado(@Param("removido") int removido);
    
    @Query("SELECT CASE WHEN (COUNT(catequeseCatequista) > 0) THEN true ELSE false END FROM CatequeseCatequista catequeseCatequista WHERE catequeseCatequista.idDadosPessoais.id = :idDadosGeraisInscricao")
    public boolean existeCatequistaComInscricao(@Param("idDadosGeraisInscricao") Integer idDadosGeraisInscricao);
    
    @Query("SELECT entity.codigoCatequista FROM CatequeseCatequista entity where entity.id = (select max(id) from CatequeseCatequista)")
    public String ultimoCodigoCatequista();
    
    @Query("SELECT CASE WHEN (COUNT(catequistaTurma) > 0) THEN true ELSE false END FROM CatequeseCatequistaTurma catequistaTurma WHERE catequistaTurma.idCatequista.id = :id")
    public boolean catequistaTemTurma (@Param("id") Integer id);

	public Optional<CatequeseCatequista> findByIdDadosPessoais(GeralDadosPessoais dadosPessoais);
}

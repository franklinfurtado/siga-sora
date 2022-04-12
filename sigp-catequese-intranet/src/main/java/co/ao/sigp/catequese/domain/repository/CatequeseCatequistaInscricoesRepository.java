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

import co.ao.sigp.catequese.domain.model.CatequeseInscricoesCatequista;
import co.ao.sigp.catequese.domain.model.GeralDadosPessoais;
import co.ao.sigp.catequese.domain.model.vo.CatequeseInscricoesCatequistaVO;

@Repository
public interface CatequeseCatequistaInscricoesRepository extends CatequeseCatequistaInscricoesRepositoryQueries,
		CustomJpaRepository<CatequeseInscricoesCatequista, Integer> {

	@Query("SELECT CASE WHEN (COUNT(entity) > 0) THEN true ELSE false END FROM CatequeseInscricoesCatequista entity WHERE entity.idDadosPessoais.nomeCompleto = :nomeCompleto and entity.idDadosPessoais.id != :id and entity.removido = 0")
	public boolean nomeExistenteNoSistemaCustom(@Param("id") Integer id, @Param("nomeCompleto") String nomeCompleto);

	@Query("SELECT CASE WHEN (COUNT(entity) > 0) THEN true ELSE false END FROM CatequeseInscricoesCatequista entity WHERE entity.idDadosPessoais.nomeCompleto = :nomeCompleto and entity.removido = 0")
	public boolean nomeExistenteNoSistemaCustom(@Param("nomeCompleto") String nomeCompleto);

	@Query("SELECT entity FROM CatequeseInscricoesCatequista entity WHERE entity.removido = :removido")
	public List<CatequeseInscricoesCatequista> listarTodosPorEstado(@Param("removido") int removido);

	@Query("SELECT new co.ao.sigp.catequese.domain.model.vo.CatequeseInscricoesCatequistaVO(entity.id, entity.idDadosPessoais.nomeCompleto, entity.dataInscricao, entity.dataRemocao, entity.estadoInscricao, entity.tipoInscricao, entity.temBatismo, entity.temComunhao, entity.temCrisma, entity.temMatrimonio) FROM CatequeseInscricoesCatequista entity WHERE entity.removido = :estadoDeRegisto")
	public List<CatequeseInscricoesCatequistaVO> listarTodosVOPorEstado(@Param("estadoDeRegisto") int estadoDeRegisto);

	public Optional<CatequeseInscricoesCatequista> findByIdDadosPessoais(GeralDadosPessoais dadosPessoais);
}

package co.ao.sigp.catequese.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import co.ao.sigp.catequese.domain.model.CatequeseInscricoesCatequista;
import co.ao.sigp.catequese.domain.model.vo.CatequeseInscricoesCatequistaVO;
import co.ao.sigp.catequese.domain.repository.CatequeseCatequistaInscricoesFiltro;
import co.ao.sigp.catequese.domain.repository.CatequeseCatequistaInscricoesRepositoryQueries;

@Repository
public class CatequeseCatequistaInscricoesRepositoryImpl implements CatequeseCatequistaInscricoesRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<CatequeseInscricoesCatequistaVO> filtrar(CatequeseCatequistaInscricoesFiltro filtro, int totalRegistrosPorPagina, int primeiroRegistro) {

		var builder = manager.getCriteriaBuilder();

		var criteria = builder.createQuery(CatequeseInscricoesCatequistaVO.class);

		var root = criteria.from(CatequeseInscricoesCatequista.class);

		From<?, ?> dadosGeraisJoin = root.join("idDadosPessoais", JoinType.INNER);

		From<?, ?> grupoMovimentoJoin = dadosGeraisJoin.join("idGrupoMovimento", JoinType.LEFT);

		From<?, ?> contactosJoin = dadosGeraisJoin.join("idContactos", JoinType.LEFT);

		criteria.select(builder.construct(CatequeseInscricoesCatequistaVO.class, root.get("id"), dadosGeraisJoin.get("nomeCompleto"), root.get("dataInscricao"), root.get("dataRemocao"), root.get("estadoInscricao"),
				root.get("tipoInscricao"), root.get("temBatismo"), root.get("temComunhao"), root.get("temCrisma"), root.get("temMatrimonio")));

		var predicates = criarRestrincoes(filtro, builder, root, dadosGeraisJoin, grupoMovimentoJoin, contactosJoin);

		criteria.where(predicates);

		TypedQuery<CatequeseInscricoesCatequistaVO> query = manager.createQuery(criteria);

		query.setMaxResults(50);

		filtro.setFiltroAtivo(Boolean.FALSE);

		return query.getResultList();
	}

	private Predicate[] criarRestrincoes(CatequeseCatequistaInscricoesFiltro filtro, CriteriaBuilder builder, Root<CatequeseInscricoesCatequista> root, From<?, ?> dadosGeraisJoin, From<?, ?> grupoMovimentoJoin,
			From<?, ?> contactosJoin) {

		List<Predicate> predicates = new ArrayList<>();

		if (StringUtils.hasLength(filtro.getNomeCompleto())) {

			predicates.add(builder.like(builder.lower(dadosGeraisJoin.get("nomeCompleto")), "%" + filtro.getNomeCompleto().toLowerCase() + "%"));
		}

		if (filtro.getDataInscricaoInicial() != null) {

			predicates.add(builder.greaterThanOrEqualTo(root.get("dataInscricao"), filtro.getDataInscricaoInicial()));
		}

		if (filtro.getDataInscricaoFinal() != null) {

			predicates.add(builder.lessThanOrEqualTo(root.get("dataInscricao"), filtro.getDataInscricaoFinal()));
		}

		if (filtro.getDataRemocaoInicial() != null) {

			predicates.add(builder.greaterThanOrEqualTo(root.get("dataRemocao"), filtro.getDataRemocaoInicial()));
		}

		if (filtro.getDataRemocaoFinal() != null) {

			predicates.add(builder.lessThanOrEqualTo(root.get("dataRemocao"), filtro.getDataRemocaoFinal()));
		}

		if (filtro.getGrupoOuMovimento() != null) {

			predicates.add(builder.equal(grupoMovimentoJoin.get("id"), filtro.getGrupoOuMovimento().getId()));
		}

		if (filtro.getEstado() != null) {

			predicates.add(builder.equal(root.get("estadoInscricao"), filtro.getEstado()));
		}

		if (StringUtils.hasLength(filtro.getNumeroTelefone())) {

			predicates.add(
					builder.or(builder.like(contactosJoin.get("numeroTelefone1"), "%" + filtro.getNumeroTelefone() + "%"), builder.like(contactosJoin.get("numeroTelefone2"), "%" + filtro.getNumeroTelefone() + "%")));
		}

		predicates.add(builder.equal(root.get("removido"), filtro.getEstadoDeRegisto().getValue()));

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}

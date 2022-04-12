package co.ao.sigp.catequese.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import co.ao.sigp.catequese.domain.model.CatequeseCatequista;
import co.ao.sigp.catequese.domain.model.CatequeseCatequistaSacramento;
import co.ao.sigp.catequese.domain.model.vo.CatequeseCatequistaVO;
import co.ao.sigp.catequese.domain.repository.CatequeseCatequistaFiltro;
import co.ao.sigp.catequese.domain.repository.CatequeseCatequistaRepositoryQueries;

@Repository
public class CatequeseCatequistaRepositoryImpl implements CatequeseCatequistaRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<CatequeseCatequistaVO> filtrar(CatequeseCatequistaFiltro filtro, int totalRegistrosPorPagina, int primeiroRegistro) {

		var builder = manager.getCriteriaBuilder();

		var criteria = builder.createQuery(CatequeseCatequistaVO.class);

		var root = criteria.from(CatequeseCatequista.class);

		var dadosGeraisJoin = root.join("idDadosPessoais", JoinType.INNER);

		var contactosJoin = dadosGeraisJoin.join("idContactos", JoinType.LEFT);

		var grupoMovimentoJoin = dadosGeraisJoin.join("idGrupoMovimento", JoinType.LEFT);

		ListJoin<CatequeseCatequista, CatequeseCatequistaSacramento> catequistaSacramentoJoin = root.joinList("catequistaSacramentos");

		var sacramentosJoin = catequistaSacramentoJoin.get("idSacramento");

//		var salaCatequeseAtualJoin = root.join("idSalaCatequeseAtual", JoinType.LEFT);
//
//		var centroCatequeseAtualJoin = salaCatequeseAtualJoin.join("idCentroCatequese", JoinType.INNER);

		criteria.select(builder.construct(CatequeseCatequistaVO.class, root.get("id"), root.get("codigoCatequista"), dadosGeraisJoin.get("nomeCompleto"), root.get("dataInicioCatecismo"), root.get("dataRemocao"),
				contactosJoin.get("numeroTelefone1"), contactosJoin.get("numeroTelefone2"), grupoMovimentoJoin.get("nome"), builder.function("group_concat", String.class, sacramentosJoin.get("nomeSacramento"))));

		var predicates = criarRestrincoes(filtro, builder, root, dadosGeraisJoin, grupoMovimentoJoin, contactosJoin);

		criteria.where(predicates);
		
		criteria.groupBy(root.get("id"));

		criteria.orderBy(builder.asc(dadosGeraisJoin.get("nomeCompleto")));

		TypedQuery<CatequeseCatequistaVO> query = manager.createQuery(criteria);

		query.setMaxResults(50);

		filtro.setFiltroAtivo(Boolean.FALSE);

		return query.getResultList();
	}

	private Predicate[] criarRestrincoes(CatequeseCatequistaFiltro filtro, CriteriaBuilder builder, Root<CatequeseCatequista> root, From<?, ?> dadosGeraisJoin, From<?, ?> grupoMovimentoJoin, From<?, ?> contactosJoin) {

		List<Predicate> predicates = new ArrayList<>();

		if (StringUtils.hasLength(filtro.getNomeCompleto()))

			predicates.add(builder.like(builder.lower(dadosGeraisJoin.get("nomeCompleto")), "%" + filtro.getNomeCompleto().toLowerCase() + "%"));

		if (StringUtils.hasLength(filtro.getCodigo()))

			predicates.add(builder.like(builder.lower(root.get("codigoCatequista")), "%" + filtro.getCodigo().toLowerCase() + "%"));

		if (filtro.getGruposOuMovimentos() != null && !filtro.getGruposOuMovimentos().isEmpty())

			predicates.add(grupoMovimentoJoin.get("nome").in(filtro.getGruposOuMovimentos()));

		if (StringUtils.hasLength(filtro.getNumeroTelefone()))

			predicates.add(
					builder.or(builder.like(contactosJoin.get("numeroTelefone1"), "%" + filtro.getNumeroTelefone() + "%"), builder.like(contactosJoin.get("numeroTelefone2"), "%" + filtro.getNumeroTelefone() + "%")));

		predicates.add(builder.equal(root.get("removido"), filtro.getEstadoDeRegisto().getValue()));

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}

package co.ao.sigp.catequese.domain.repository;

import java.util.List;

import co.ao.sigp.catequese.domain.model.vo.CatequeseInscricoesCatequistaVO;

public interface CatequeseCatequistaInscricoesRepositoryQueries {

	public List<CatequeseInscricoesCatequistaVO> filtrar(CatequeseCatequistaInscricoesFiltro filtro,
			int totalRegistrosPorPagina, int primeiroRegistro);
}

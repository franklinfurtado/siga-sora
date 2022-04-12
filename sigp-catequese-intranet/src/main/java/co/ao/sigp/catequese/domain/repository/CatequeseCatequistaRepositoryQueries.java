package co.ao.sigp.catequese.domain.repository;

import java.util.List;

import co.ao.sigp.catequese.domain.model.vo.CatequeseCatequistaVO;

public interface CatequeseCatequistaRepositoryQueries {

	public List<CatequeseCatequistaVO> filtrar(CatequeseCatequistaFiltro filtro, int totalRegistrosPorPagina, int primeiroRegistro);
}

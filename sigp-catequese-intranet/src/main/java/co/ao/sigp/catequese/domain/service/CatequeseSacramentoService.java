/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.service;

import co.ao.sigp.catequese.domain.model.CatequeseSacramento;
import co.ao.sigp.catequese.domain.repository.CatequeseSacramentoRepository;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author franklin.furtado
 */
@Service
public class CatequeseSacramentoService implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired

	private CatequeseSacramentoRepository repository;

	private final String nomeAtributoId = "id";

	private final String nomeAtributoNome = "nomeSacramento";

	public List<CatequeseSacramento> listarTodos() {
		
		return repository.findAll();
	}

	@Transactional
	public void gravar(CatequeseSacramento sacramento) {
		
		repository.save(sacramento);
	}

	@Transactional
	public void excluir(CatequeseSacramento sacramento) {
		
		repository.delete(sacramento);
	}

	public CatequeseSacramento findById(Integer id) {
		
		return repository.getById(id);
	}

	public boolean nomeExistenteNoSistema(String nome) {
		
		return repository.nomeExistenteNoSistema(nomeAtributoNome, nome);
	}

	public boolean nomeExistenteNoSistema(Integer id, String nome) {
		
		return repository.nomeExistenteNoSistema(nomeAtributoId, id, nomeAtributoNome, nome);
	}

	public boolean existeCatequistaComSacramento(Integer id) {
		
		// TODO: validar remocao de sacramento com catequista registado
		String nomeAtributoEntidadeDendente = "catequeseCatequistaList";

		return repository.existeEntidadesDependentes(nomeAtributoId, id, nomeAtributoEntidadeDendente);
	}

	public boolean existeCatecumenoComSacramento(Integer id) {
		
		// TODO: validar remocao de sacramento com catecumentos registado
		String nomeAtributoEntidadeDendente = "catequeseCatecumenoSacramentoList";

		return repository.existeEntidadesDependentes(nomeAtributoId, id, nomeAtributoEntidadeDendente);
	}
}

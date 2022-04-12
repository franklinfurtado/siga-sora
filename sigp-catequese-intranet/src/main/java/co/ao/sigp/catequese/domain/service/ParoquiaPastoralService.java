/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.ao.sigp.catequese.domain.exception.NegocioException;
import co.ao.sigp.catequese.domain.exception.NomeEntidadeExisteNoSistemaException;
import co.ao.sigp.catequese.domain.exception.EntidadeEmUsoExceptionPastoral;
import co.ao.sigp.catequese.domain.model.ParoquiaPastoral;
import co.ao.sigp.catequese.domain.repository.ParoquiaPastoralRepository;

/**
 *
 * @author franklin.furtado
 */
@Service
public class ParoquiaPastoralService implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String nomeAtributoId = "id";

	private final String nomeAtributoNome = "nomePastoral";

	@Autowired
	private ParoquiaPastoralRepository repositorio;

	public List<ParoquiaPastoral> listarTodos() {
		return repositorio.findAll();
	}

	public boolean nomeExistenteNoSistema(Integer id, String nome) {

		if (id != null) {
			return repositorio.nomeExistenteNoSistema(nomeAtributoId, id, nomeAtributoNome, nome);
		}

		return repositorio.nomeExistenteNoSistema(nomeAtributoNome, nome);
	}

	@Transactional
	public void gravar(ParoquiaPastoral entidade) {

		if (entidade.isCatequistaSelecionadoNomeResponsavelVazio()) {
			throw new NegocioException("Erro!!! Insira o nome do responsável ou deselecione a opção É Catequista");
		}

		if (nomeExistenteNoSistema(entidade.getId(), entidade.getNomePastoral())) {
			throw new NomeEntidadeExisteNoSistemaException(entidade.getNomePastoral());
		}

		repositorio.save(entidade);
	}

	@Transactional
	public void excluir(ParoquiaPastoral pastoral) {
		try {
			repositorio.delete(pastoral);
			repositorio.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExceptionPastoral(pastoral.getNomePastoral());
		}
	}

	public boolean existePastoralNoSistema() {
		return repositorio.count() > 0;
	}

	public ParoquiaPastoral pesquisarPastoralPorId(Integer idPastoral) {
		return repositorio.findById(idPastoral).get();
	}
}

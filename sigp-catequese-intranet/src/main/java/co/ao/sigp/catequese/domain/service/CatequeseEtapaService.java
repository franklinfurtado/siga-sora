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

import co.ao.sigp.catequese.domain.exception.EntidadeEmUsoExceptionEtapaCatequese;
import co.ao.sigp.catequese.domain.exception.NomeEntidadeExisteNoSistemaException;
import co.ao.sigp.catequese.domain.model.CatequeseEtapaCatequese;
import co.ao.sigp.catequese.domain.repository.CatequeseEtapaRepository;

/**
 *
 * @author franklin.furtado
 */
@Service
public class CatequeseEtapaService implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private CatequeseEtapaRepository repositorio;
    
    private final String nomeAtributoId = "id";
    
    private final String nomeAtributoNome = "nomeEtapa";

    
    public List<CatequeseEtapaCatequese> listarTodos() {
        return repositorio.findAll();
    }

	public boolean nomeExistenteNoSistema(Integer id, String nome) {

		if (id != null) {
			return repositorio.nomeExistenteNoSistema(nomeAtributoId, id, nomeAtributoNome, nome);
		}

		return repositorio.nomeExistenteNoSistema(nomeAtributoNome, nome);
	}

	@Transactional
	public void gravar(CatequeseEtapaCatequese entidade) {

		if (nomeExistenteNoSistema(entidade.getId(), entidade.getNomeEtapa())) {
			throw new NomeEntidadeExisteNoSistemaException(entidade.getNomeEtapa());
		}

		repositorio.save(entidade);
	}

	@Transactional
	public void excluir(CatequeseEtapaCatequese entidade) {
		try {
			repositorio.delete(entidade);
			repositorio.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExceptionEtapaCatequese(entidade.getNomeEtapa());
		}
	}
}

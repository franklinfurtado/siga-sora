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

import co.ao.sigp.catequese.domain.exception.EntidadeEmUsoExceptionAnoLetivo;
import co.ao.sigp.catequese.domain.exception.NegocioException;
import co.ao.sigp.catequese.domain.exception.NomeEntidadeExisteNoSistemaException;
import co.ao.sigp.catequese.domain.model.CatequeseAnoLetivo;
import co.ao.sigp.catequese.domain.repository.CatequeseAnoLetivoRepository;

/**
 *
 * @author franklin.furtado
 */
@Service
public class CatequeseAnoLetivoService implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private CatequeseAnoLetivoRepository repositorio;
    
    private final String nomeAtributoId = "id";
    
    private final String nomeAtributoNome = "descricao";

    public List<CatequeseAnoLetivo> listarTodos() {
        return repositorio.findAll();
    }

	public boolean nomeExistenteNoSistema(Integer id, String nome) {

		if (id != null) {
			return repositorio.nomeExistenteNoSistema(nomeAtributoId, id, nomeAtributoNome, nome);
		}

		return repositorio.nomeExistenteNoSistema(nomeAtributoNome, nome);
	}

	@Transactional
	public void gravar(CatequeseAnoLetivo entidade) {
		
		if (entidade.isDataFimInferiorOuIgualInicial()) {
			throw new NegocioException("Erro!!! A data de fim deve ser superior a data de inicio, tente novamente");
		}

		if (nomeExistenteNoSistema(entidade.getId(), entidade.getDescricao())) {
			throw new NomeEntidadeExisteNoSistemaException(entidade.getDescricao());
		}

		repositorio.save(entidade);
	}

	@Transactional
	public void excluir(CatequeseAnoLetivo entidade) {
		try {
			repositorio.delete(entidade);
			repositorio.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExceptionAnoLetivo(entidade.getDescricao());
		}
	}

	public CatequeseAnoLetivo findByID(Integer id) {
		return repositorio.getById(id);
	}
}

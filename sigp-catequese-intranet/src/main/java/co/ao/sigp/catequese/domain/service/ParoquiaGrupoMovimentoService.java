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

import co.ao.sigp.catequese.domain.exception.EntidadeEmUsoExceptionMovimento;
import co.ao.sigp.catequese.domain.exception.NegocioException;
import co.ao.sigp.catequese.domain.exception.NomeEntidadeExisteNoSistemaException;
import co.ao.sigp.catequese.domain.model.ParoquiaGruposMovimentos;
import co.ao.sigp.catequese.domain.model.vo.ParoquiaGruposMovimentosVO;
import co.ao.sigp.catequese.domain.repository.ParoquiaGrupoMovimentoRepository;

/**
 *
 * @author franklin.furtado
 */
@Service
public class ParoquiaGrupoMovimentoService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Autowired

    private ParoquiaGrupoMovimentoRepository repositorio;

    private final String nomeAtributoId = "id";

    private final String nomeAtributoNome = "nome";

    public List<ParoquiaGruposMovimentos> listarTodos() {
        return repositorio.findAll();
    }

    public List<ParoquiaGruposMovimentosVO> listarTodosVO() {
        return repositorio.listarTodosVO();
    }

    public List<ParoquiaGruposMovimentos> listarTodosPorPastoral(int idPastoral) {
        return repositorio.listarTodosPorPastoral(idPastoral);
    }

    public List<ParoquiaGruposMovimentosVO> listarTodosVOPorPastoral(int idPastoral) {
        return repositorio.listarTodosVOPorPastoral(idPastoral);
    }

	public boolean nomeExistenteNoSistema(Integer id, String nome) {

		if (id != null) {
			return repositorio.nomeExistenteNoSistema(nomeAtributoId, id, nomeAtributoNome, nome);
		}

		return repositorio.nomeExistenteNoSistema(nomeAtributoNome, nome);
	}

    @Transactional
    public void gravar(ParoquiaGruposMovimentos entidade) {
		
		if (entidade.isCatequistaSelecionadoNomeResponsavelVazio()) {
			throw new NegocioException(String.format("Erro!!! Insira o nome do responsável ou deselecione a opção É Catequista"));
		}
		
		if (nomeExistenteNoSistema(entidade.getId(), entidade.getNome())) {
			throw new NomeEntidadeExisteNoSistemaException(entidade.getNome());
		}

		repositorio.save(entidade);
    }

    @Transactional
    public void excluir(ParoquiaGruposMovimentos gruposMovimentos) {
    	try {
			repositorio.delete(gruposMovimentos);
			repositorio.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExceptionMovimento(gruposMovimentos.getNome());
		}
    }

    public ParoquiaGruposMovimentos pesquisarPorId(Integer id) {
        return repositorio.findById(id).get();
    }
}

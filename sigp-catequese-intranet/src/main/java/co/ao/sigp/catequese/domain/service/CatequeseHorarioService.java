/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.service;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.ao.sigp.catequese.domain.exception.EntidadeEmUsoExceptionHorarioCatequese;
import co.ao.sigp.catequese.domain.exception.NegocioException;
import co.ao.sigp.catequese.domain.exception.NomeEntidadeExisteNoSistemaException;
import co.ao.sigp.catequese.domain.model.CatequeseHorarioCatequese;
import co.ao.sigp.catequese.domain.model.DiaSemana;
import co.ao.sigp.catequese.domain.repository.CatequeseHorarioRepository;

/**
 *
 * @author franklin.furtado
 */
@Service
public class CatequeseHorarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired

	private CatequeseHorarioRepository repositorio;

	public List<CatequeseHorarioCatequese> listarTodos() {
		return repositorio.findAll();
	}

	public boolean nomeExistenteNoSistema(Integer idHorario, DiaSemana diaSemana, LocalTime horaInicio, LocalTime horaTermino,
			String nomePeriodo) {

		if (idHorario != null) {
			return repositorio.horarioExisteNoSistema(idHorario, diaSemana, horaInicio, horaTermino, nomePeriodo);
		}

		return repositorio.horarioExisteNoSistema(diaSemana, horaInicio, horaTermino, nomePeriodo);
	}

	@Transactional
	public void gravar(CatequeseHorarioCatequese entidade) {
		
		if (entidade.isHoraFimInferiorOuIgualInicial()) {
			throw new NegocioException("Erro!!! A hora de fim deve ser superior a hora de inicio, tente novamente");
		}

		if (nomeExistenteNoSistema(entidade.getId(), entidade.getDiaSemana(), entidade.getHoraInicio(),
				entidade.getHoraTermino(), entidade.getNomePeriodo())) {
			
			throw new NomeEntidadeExisteNoSistemaException(String.format(
					"O horário (%s) inserido já existe no sistema, tente outro horário", entidade.mostrarHorario()), "");
		}

		repositorio.save(entidade);
	}

	@Transactional
	public void excluir(CatequeseHorarioCatequese entidade) {
		try {
			repositorio.delete(entidade);
			repositorio.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExceptionHorarioCatequese(entidade.mostrarHorario());
		}
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.ao.sigp.catequese.core.enums.CatequeseSacramentos;
import co.ao.sigp.catequese.core.enums.EstadoDeRegisto;
import co.ao.sigp.catequese.core.util.jsf.lazydatamodel.LazyLoadPageCriteria;
import co.ao.sigp.catequese.core.util.jsf.lazydatamodel.LazyLoadPageResult;
import co.ao.sigp.catequese.domain.exception.EntidadeEmUsoException;
import co.ao.sigp.catequese.domain.exception.EntidadeEmUsoExceptionCatequista;
import co.ao.sigp.catequese.domain.exception.EntidadeEmUsoExceptionInscricoesCatequista;
import co.ao.sigp.catequese.domain.exception.NomeCatequistaRecuperarExisteNoSistemaException;
import co.ao.sigp.catequese.domain.exception.NomeEntidadeExisteNoSistemaException;
import co.ao.sigp.catequese.domain.exception.SacramentosCatequistaIncorrectosException;
import co.ao.sigp.catequese.domain.model.CatequeseCatequistaSacramento;
import co.ao.sigp.catequese.domain.model.CatequeseInscricoesCatequista;
import co.ao.sigp.catequese.domain.model.GeralDadosPessoais;
import co.ao.sigp.catequese.domain.model.vo.CatequeseInscricoesCatequistaVO;
import co.ao.sigp.catequese.domain.repository.CatequeseCatequistaInscricoesFiltro;
import co.ao.sigp.catequese.domain.repository.CatequeseCatequistaInscricoesRepository;

/**
 *
 * @author franklin.furtado
 */
@Service
public class CatequeseCatequistaInscricoesService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CatequeseCatequistaInscricoesRepository repositorio;

	@Autowired
	private CatequeseCatequistaService catequistaService;

	@Autowired
	private CatequeseSacramentoService sacramentoService;

	List<CatequeseInscricoesCatequistaVO> data;

	public Integer getTotalCatequistas() {

		Long value = repositorio.count();

		return value.intValue();
	}

	public List<CatequeseInscricoesCatequista> listarTodos() {

		return repositorio.findAll();
	}

	public List<CatequeseInscricoesCatequistaVO> listarVO(EstadoDeRegisto estadoDeRegisto) {

		return repositorio.listarTodosVOPorEstado(estadoDeRegisto.getValue());
	}

	public List<CatequeseInscricoesCatequista> listar(EstadoDeRegisto estadoDeRegisto) {

		return repositorio.listarTodosPorEstado(estadoDeRegisto.getValue());
	}

	public CatequeseInscricoesCatequista findByID(Integer id) {

		return repositorio.findById(id).get();
	}

	public List<CatequeseCatequistaSacramento> gerarSacramentos(CatequeseInscricoesCatequista entity) {

		List<CatequeseCatequistaSacramento> listaSacramentos = new ArrayList<>();

		if (entity.isTemBatismo())

			listaSacramentos.add(criarCatequistaSacramento(CatequeseSacramentos.BATISMO, entity.getDataBatismo(), entity.getParoquiaBatismo()));

		if (entity.isTemComunhao())

			listaSacramentos.add(criarCatequistaSacramento(CatequeseSacramentos.COMUNHAO, entity.getDataComunhao(), entity.getParoquiaComunhao()));

		if (entity.isTemCrisma())

			listaSacramentos.add(criarCatequistaSacramento(CatequeseSacramentos.CRISMA, entity.getDataCrisma(), entity.getParoquiaCrisma()));

		if (entity.isTemMatrimonio())
			
			listaSacramentos.add(criarCatequistaSacramento(CatequeseSacramentos.MATRIMONIO, entity.getDataMatrimonio(), entity.getParoquiaMatrimonio()));

		return listaSacramentos;
	}

	public CatequeseCatequistaSacramento criarCatequistaSacramento(CatequeseSacramentos sacramentoEnum, LocalDate dataSacramento, String paroquaSacramento) {

		CatequeseCatequistaSacramento catequistaSacramento = new CatequeseCatequistaSacramento();

		catequistaSacramento.setIdSacramento(sacramentoService.findById(sacramentoEnum.getId()));

		catequistaSacramento.setDataConclusaoSacramento(dataSacramento);

		catequistaSacramento.setParoquia(paroquaSacramento);

		return catequistaSacramento;
	}

	public boolean nomeExistenteNoSistema(Integer id, String nome) {

		if (id != null)

			return repositorio.nomeExistenteNoSistemaCustom(id, nome);

		return repositorio.nomeExistenteNoSistemaCustom(nome);
	}

	@Transactional
	public void gravar(CatequeseInscricoesCatequista entidade) {

		if (nomeExistenteNoSistema(entidade.getId(), entidade.getIdDadosPessoais().getNomeCompleto()))

			throw new NomeEntidadeExisteNoSistemaException(entidade.getIdDadosPessoais().getNomeCompleto());

		if (!entidade.sacramentosCorretos())

			throw new SacramentosCatequistaIncorrectosException();

		repositorio.save(entidade);
	}

	@Transactional
	public void excluir(GeralDadosPessoais dadosPessoais) {

		Optional<CatequeseInscricoesCatequista> entidadeAux = repositorio.findByIdDadosPessoais(dadosPessoais);

		if (entidadeAux.isPresent()) {

			entidadeAux.get().apagarInscricao(EstadoDeRegisto.APAGADO);

			repositorio.save(entidadeAux.get());
		}
	}

	public void excluirInscricao(CatequeseInscricoesCatequista entidade) {

		try {

			catequistaService.excluirCatequistaFromInscricao(entidade.getIdDadosPessoais());

		} catch (EntidadeEmUsoExceptionCatequista e) {

			throw new EntidadeEmUsoExceptionInscricoesCatequista(entidade.getIdDadosPessoais().getNomeCompleto());

		}

		excluir(entidade.getIdDadosPessoais());
	}

	@Transactional
	public void excluirPermanente(CatequeseInscricoesCatequista entidade) {

		entidade.apagarInscricao(EstadoDeRegisto.APAGADO_PERMANENTEMENTE);

		repositorio.save(entidade);
	}

	@Transactional
	public void recuperar(CatequeseInscricoesCatequista entidade) {

		if (!repositorio.nomeExistenteNoSistemaCustom(entidade.getIdDadosPessoais().getNomeCompleto())) {

			entidade.recuperarInscricao();

			repositorio.save(entidade);

		} else

			throw new NomeCatequistaRecuperarExisteNoSistemaException(entidade.getIdDadosPessoais().getNomeCompleto());

	}

	@Transactional
	public void aprovarInscricao(CatequeseInscricoesCatequista entidade) {

		entidade.aprovarInscricao();

		catequistaService.gravarCatequistaFromInscricao(entidade.getIdDadosPessoais(), entidade.getIdDadosPessoais().getIdGrupoMovimento(), gerarSacramentos(entidade));

		repositorio.save(entidade);
	}

	@Transactional
	public void rejeitarInscricao(CatequeseInscricoesCatequista entidade) {

		try {

			catequistaService.excluirCatequistaFromRejeitarInscricao(entidade.getIdDadosPessoais());

		} catch (EntidadeEmUsoException e) {

			throw new EntidadeEmUsoException(
					String.format("Erro!!! Não foi possivel eliminar a inscrição de (%s) porque já existe histórico na catequese do catequista.", entidade.getIdDadosPessoais().getNomeCompleto()));
		}

		entidade.rejeitarInscricao();

		repositorio.save(entidade);
	}

	@Transactional
	public void reiniciarInscricao(CatequeseInscricoesCatequista entidade) {

		try {

			catequistaService.excluirCatequistaFromInscricao(entidade.getIdDadosPessoais());

		} catch (EntidadeEmUsoExceptionCatequista e) {

			throw new EntidadeEmUsoExceptionInscricoesCatequista(entidade.getIdDadosPessoais().getNomeCompleto());

		}

		entidade.reiniciarInscricao();

		repositorio.save(entidade);
	}

	public LazyLoadPageResult<CatequeseInscricoesCatequistaVO> filtrados(LazyLoadPageCriteria criteria, CatequeseCatequistaInscricoesFiltro filtro) {

		if (filtro.isFiltroAtivo()) {

			data = repositorio.filtrar(filtro, criteria.getSize(), criteria.getFirst());
		}

		return new LazyLoadPageResult<>(data, criteria.getSize(), criteria.getFirst(), data.size());
	}
}
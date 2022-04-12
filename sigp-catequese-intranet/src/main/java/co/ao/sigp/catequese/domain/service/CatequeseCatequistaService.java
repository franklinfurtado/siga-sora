/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ao.sigp.catequese.core.enums.EstadoDeRegisto;
import co.ao.sigp.catequese.core.util.jsf.lazydatamodel.LazyLoadPageCriteria;
import co.ao.sigp.catequese.core.util.jsf.lazydatamodel.LazyLoadPageResult;
import co.ao.sigp.catequese.domain.exception.EntidadeEmUsoException;
import co.ao.sigp.catequese.domain.exception.EntidadeEmUsoExceptionCatequista;
import co.ao.sigp.catequese.domain.exception.NomeEntidadeExisteNoSistemaException;
import co.ao.sigp.catequese.domain.exception.SacramentosCatequistaIncorrectosException;
import co.ao.sigp.catequese.domain.exception.SacramentosJaAdicionadoNaListaException;
import co.ao.sigp.catequese.domain.model.CatequeseCatequista;
import co.ao.sigp.catequese.domain.model.CatequeseCatequistaSacramento;
import co.ao.sigp.catequese.domain.model.GeralDadosPessoais;
import co.ao.sigp.catequese.domain.model.ParoquiaGruposMovimentos;
import co.ao.sigp.catequese.domain.model.vo.CatequeseCatequistaVO;
import co.ao.sigp.catequese.domain.repository.CatequeseCatequistaFiltro;
import co.ao.sigp.catequese.domain.repository.CatequeseCatequistaRepository;

/**
 *
 * @author franklin.furtado
 */
@Service
public class CatequeseCatequistaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CatequeseCatequistaRepository repositorio;

	@Autowired
	private CatequeseCatequistaInscricoesService catequistaInscricoesService;
	
	@Autowired
	private CatequeseSacramentoService sacramentoService;
	
	private List<CatequeseCatequistaVO> data;

	public Integer getTotalCatequista() {

		Long value = repositorio.count();

		return value.intValue();
	}

	public CatequeseCatequista findByID(Integer id) {

		return repositorio.findById(id).get();
	}

	@Transactional
	public void excluirCatequistaFromRejeitarInscricao(GeralDadosPessoais dadosPessoais) {

		Optional<CatequeseCatequista> entidade = repositorio.findByIdDadosPessoais(dadosPessoais);

		if (entidade.isPresent()) {

			if (catequistaTemHistoricoNaCatequese(entidade.get()))

				throw new EntidadeEmUsoException();

			repositorio.delete(entidade.get());
		}
	}

	@Transactional
	public void excluirCatequistaFromInscricao(GeralDadosPessoais dadosPessoais) {

		Optional<CatequeseCatequista> entidade = repositorio.findByIdDadosPessoais(dadosPessoais);

		if (entidade.isPresent()) {

			if (catequistaTemHistoricoNaCatequese(entidade.get()))

				throw new EntidadeEmUsoExceptionCatequista(entidade.get().getIdDadosPessoais().getNomeCompleto());

			entidade.get().setRemovido(EstadoDeRegisto.APAGADO.getValue());

			repositorio.save(entidade.get());

			catequistaInscricoesService.excluir(entidade.get().getIdDadosPessoais());
		}
	}

	private boolean nomeExistenteNoSistema(Integer id, String nome) {

		if (id != null)

			return repositorio.nomeExistenteNoSistemaCustom(id, nome);

		return repositorio.nomeExistenteNoSistemaCustom(nome);
	}

	@Transactional
	public void gravar(CatequeseCatequista entidade) {

		if (nomeExistenteNoSistema(entidade.getId(), entidade.getIdDadosPessoais().getNomeCompleto()))

			throw new NomeEntidadeExisteNoSistemaException(entidade.getIdDadosPessoais().getNomeCompleto());

		if (!entidade.isSacramentosCorretos())

			throw new SacramentosCatequistaIncorrectosException();
		
		entidade.setCodigoCatequista(criarCodigoCatequista());
				
		entidade.getCatequistaSacramentos().forEach(catequeseSacramento -> {
			
			catequeseSacramento.setIdSacramento(sacramentoService.findById(catequeseSacramento.getIdSacramento().getId()));
		});
		
		entidade.getCatequistaSacramentos().stream().map(CatequeseCatequistaSacramento::getIdSacramento).collect(Collectors.toList()).forEach(System.out::println);
		
		repositorio.save(entidade);
	}

	public void gravarCatequistaFromInscricao(GeralDadosPessoais dadosPessoais, ParoquiaGruposMovimentos gruposMovimentos, List<CatequeseCatequistaSacramento> catequistaSacramentos) {

		Optional<CatequeseCatequista> entidadeOptional = repositorio.findByIdDadosPessoais(dadosPessoais);

		if (!entidadeOptional.isPresent()) {

			CatequeseCatequista entidade = new CatequeseCatequista();

			entidade.setCodigoCatequista(criarCodigoCatequista());

			entidade.setRemovido(0);

			entidade.setIdDadosPessoais(dadosPessoais);

			entidade.getIdDadosPessoais().setIdGrupoMovimento(gruposMovimentos);

			entidade.setCatequeseCatequistaSacramentoList(catequistaSacramentos);

			gravar(entidade);
		}

	}

	private String criarCodigoCatequista() {

		String codigoUltimoCatequista = repositorio.ultimoCodigoCatequista();

		if (codigoUltimoCatequista != null) {

			return String.format("CTSP%06d", Integer.parseInt(codigoUltimoCatequista.split("CTSP")[1]) + 1);
		}

		return "CTSP000001";
	}

	public boolean catequistaTemHistoricoNaCatequese(CatequeseCatequista entidade) {

		if (entidade.getIdSalaCatequeseAtual() != null)

			return true;

		else if (repositorio.catequistaTemTurma(entidade.getId()))

			return true;

		return false;
	}

	public LazyLoadPageResult<CatequeseCatequistaVO> filtrados(LazyLoadPageCriteria criteria, CatequeseCatequistaFiltro filtro) {

		if (filtro.isFiltroAtivo()) {

			data = repositorio.filtrar(filtro, criteria.getSize(), criteria.getFirst());
		}

		return new LazyLoadPageResult<>(data, criteria.getSize(), criteria.getFirst(), data.size());
	}

	public CatequeseCatequista adicionarSacramentoAoCatequista(CatequeseCatequista entidade, CatequeseCatequistaSacramento catequistaSacramento) {

		catequistaSacramento.carregarNomeSacramento();
		
		if(entidade.sacramentoJaExiste(catequistaSacramento))
			
			throw new SacramentosJaAdicionadoNaListaException(catequistaSacramento.getIdSacramento().getNomeSacramento());
			
		else
			
			entidade.adicionarSacramentoAoCatequista(catequistaSacramento);
		
		return entidade;
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.core.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.faces.navigate.Navigate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.ao.sigp.catequese.core.bean.navegacaopaginas.NavegacaoPaginasCatequistaBean;
import co.ao.sigp.catequese.core.enums.EstadoDeRegisto;
import co.ao.sigp.catequese.core.util.jsf.JSFUtil;
import co.ao.sigp.catequese.core.util.jsf.lazydatamodel.LazyLoadCustomDataModel;
import co.ao.sigp.catequese.domain.exception.NomeEntidadeExisteNoSistemaException;
import co.ao.sigp.catequese.domain.exception.SacramentosCatequistaIncorrectosException;
import co.ao.sigp.catequese.domain.exception.SacramentosJaAdicionadoNaListaException;
import co.ao.sigp.catequese.domain.model.CatequeseCatequista;
import co.ao.sigp.catequese.domain.model.CatequeseCatequistaSacramento;
import co.ao.sigp.catequese.domain.model.vo.CatequeseCatequistaVO;
import co.ao.sigp.catequese.domain.repository.CatequeseCatequistaFiltro;
import co.ao.sigp.catequese.domain.service.CatequeseCatequistaService;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author franklinfurtado
 */
@Controller("catequeseCatequistaBean")
@Scope("view")
public class CatequeseCatequistaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CatequeseCatequistaService service;
	
	@Autowired
	private NavegacaoPaginasCatequistaBean navegacaoCatequista;

	@Getter
	@Setter
	private LazyLoadCustomDataModel<CatequeseCatequistaVO> dataModel;

	@Getter
	@Setter
	private List<CatequeseCatequistaVO> listaSelecionadosVO;

	@Getter
	@Setter
	private CatequeseCatequista entidade;

	@Getter
	@Setter
	private CatequeseCatequistaVO entidadeVO;

	@Getter
	@Setter
	private CatequeseCatequistaFiltro filtro;

	@Getter
	private boolean editar;
	
	@Getter
	@Setter
	private CatequeseCatequistaSacramento catequistaSacramento;
	

	public void carregarPaginaListarAtivos() {

		filtro = new CatequeseCatequistaFiltro(EstadoDeRegisto.NAO_APAGADO);

		dataModel = new LazyLoadCustomDataModel<CatequeseCatequistaVO>(pc -> service.filtrados(pc, filtro));

		filtrar();
	}

	private CatequeseCatequista carregarFicha() {

		Integer id = (Integer) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("id");

		if (id != null) {

			FacesContext.getCurrentInstance().getExternalContext().getFlash().clear();

			return service.findByID(id);
		}

		return null;
	}

	public void carregarFichaSavalEditar() {

		this.entidade = carregarFicha();

		if (this.entidade == null) {

			this.entidade = new CatequeseCatequista();

			this.entidade.prepararNovoRegisto();
			
			this.catequistaSacramento = new CatequeseCatequistaSacramento();
		}
	}

	public Navigate carregarFichaVer() {

		this.entidade = carregarFicha();

		if (this.entidade == null) {

			return navegacaoCatequista.verListaAtivos();
		}

		return null;
	}

	public void filtrar() {

		if (this.listaSelecionadosVO != null)

			this.listaSelecionadosVO.clear();

		this.filtro.setFiltroAtivo(Boolean.TRUE);
	}

	public void prepararEditarRegisto() {

		this.entidade.prepararEditarRegisto();
		
		this.catequistaSacramento = new CatequeseCatequistaSacramento();
		
		habilitarEditar();
	}
	
	public void adicionarSacramentoAoCatequista() {
		
		try {
		
			this.entidade = service.adicionarSacramentoAoCatequista(this.entidade, this.catequistaSacramento);
		
		} catch(SacramentosJaAdicionadoNaListaException exception) {
			
			JSFUtil.mensagemErro(exception.getMessage());
		}
	}
	
	public void removerSacramentoAoCatequista(CatequeseCatequistaSacramento item) {
		
		this.entidade.removerSacramentoAoCatequista(item);
	}

	public void gravar() {

		try {

			service.gravar(this.entidade);

			JSFUtil.salvoComSucesso(String.format("Catequista %s foi salvo com sucesso", this.entidade.getNomeCompleto()));
			
			desabilitarEditar();

		} catch (NomeEntidadeExisteNoSistemaException | SacramentosCatequistaIncorrectosException exception) {

			JSFUtil.mensagemErro(exception.getMessage());

			JSFUtil.scroolPageToTop();
		}
	}

	public boolean naoEditar() {
		
		return entidade != null && !editar && entidade.getId() != null;
	}

	public void habilitarEditar() {
		
		editar = true;
	}
	
	public void desabilitarEditar() {
		
		editar = false;
	}

}

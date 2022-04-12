
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.core.bean;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.ao.sigp.catequese.core.enums.TipoListar;
import co.ao.sigp.catequese.core.util.jsf.JSFUtil;
import co.ao.sigp.catequese.core.util.jsf.Mensagem;
import co.ao.sigp.catequese.domain.exception.EntidadeEmUsoException;
import co.ao.sigp.catequese.domain.exception.NegocioException;
import co.ao.sigp.catequese.domain.model.CatequeseEtapaCatequese;
import co.ao.sigp.catequese.domain.service.CatequeseEtapaService;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author franklinfurtado
 */
@Controller("catequeseEtapaBean")
@Scope("view")
public class CatequeseEtapaBean implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Autowired
    private CatequeseEtapaService service;

    @Setter
    private List<CatequeseEtapaCatequese> listarTodos;

    @Getter @Setter
    private CatequeseEtapaCatequese entidade;

    public void carregarLista(TipoListar tipoListar) {
        if (listarTodos == null || tipoListar == TipoListar.CARREGAR_DADOS_BASE_DADOS) {
            listarTodos = service.listarTodos();
        }
    }

    public List<CatequeseEtapaCatequese> getListarTodos() {
        if (listarTodos == null) {
            listarTodos = service.listarTodos();
        }
        return listarTodos;
    }

    public void prepararNovoRegisto() {
        this.entidade = new CatequeseEtapaCatequese();
    }

    public void cancelarRegisto() {
        this.entidade = null;
    }
    
    public void gravar() {
		try {
			boolean novoRegisto = true;

			if (!this.entidade.isNovoRegisto()) {
				novoRegisto = false;
			}

			service.gravar(this.entidade);

			if (novoRegisto) {
				prepararNovoRegisto();
			} else {
				JSFUtil.closeDialog("AdicionarDialog");
			}

			JSFUtil.salvoComSucesso("Etapa da catequese salva com sucesso");
			carregarLista(TipoListar.CARREGAR_DADOS_BASE_DADOS);
		} catch (NegocioException e) {
			Mensagem.mensagemErro(e.getMessage());
			JSFUtil.updateComponente("internalMessages");
		}
	}

	public void excluir() {
		try {
			String nomePastoralEliminar = entidade.getNomeEtapa();
			service.excluir(this.entidade);
			JSFUtil.salvoComSucesso(String.format("Etapa da catequese (%s) eliminada com sucesso", nomePastoralEliminar));
			carregarLista(TipoListar.CARREGAR_DADOS_BASE_DADOS);
		} catch (EntidadeEmUsoException e) {
			JSFUtil.mensagemErro(e.getMessage());
		}
	}
}

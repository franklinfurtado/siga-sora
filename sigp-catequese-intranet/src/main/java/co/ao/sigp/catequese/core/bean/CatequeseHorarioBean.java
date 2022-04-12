
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
import co.ao.sigp.catequese.domain.model.CatequeseHorarioCatequese;
import co.ao.sigp.catequese.domain.service.CatequeseHorarioService;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author franklinfurtado
 */
@Controller("catequeseHorarioBean")
@Scope("view")
public class CatequeseHorarioBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private CatequeseHorarioService service;

    @Setter
    private List<CatequeseHorarioCatequese> listarTodos;

    @Getter @Setter
    private CatequeseHorarioCatequese entidade;
    
    public List<CatequeseHorarioCatequese> getListarTodos() {
        if (listarTodos == null) {
            listarTodos = service.listarTodos();
        }
        return listarTodos;
    }

    public void carregarLista(TipoListar tipoListar) {
        if (listarTodos == null || tipoListar == TipoListar.CARREGAR_DADOS_BASE_DADOS) {
        	listarTodos = service.listarTodos();
        }
    }

    public void prepararNovoRegisto() {
        this.entidade = new CatequeseHorarioCatequese();
    }

    public void cancelarRegisto() {
        this.entidade = null;
    }

    public String styleClassForHoraInicioSuperiorHoraFim() {
        if (entidade != null && entidade.getHoraTermino() != null && entidade.getHoraInicio() != null) {
            return entidade.isHoraFimInferiorOuIgualInicial() ? "ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all form-control ui-state-error" : "ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all form-control";
        }
        return "ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all form-control";
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

			JSFUtil.salvoComSucesso("Pastoral salva com sucesso");
			carregarLista(TipoListar.CARREGAR_DADOS_BASE_DADOS);
		} catch (NegocioException e) {
			Mensagem.mensagemErro(e.getMessage());
			JSFUtil.updateComponente("internalMessages");
		}
	}

	public void excluir() {
		try {
			String nomePastoralEliminar = entidade.mostrarHorario();
			service.excluir(this.entidade);
			JSFUtil.salvoComSucesso(String.format("Horário (%s) eliminada com sucesso", nomePastoralEliminar));
			carregarLista(TipoListar.CARREGAR_DADOS_BASE_DADOS);
		} catch (EntidadeEmUsoException e) {
			JSFUtil.mensagemErro(e.getMessage());
		}
	}
}

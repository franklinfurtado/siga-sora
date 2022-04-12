/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.core.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.context.FacesContext;

import org.primefaces.model.charts.bar.BarChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.ao.sigp.catequese.core.enums.ListarMembrosCatequese;
import co.ao.sigp.catequese.core.enums.TipoListar;
import co.ao.sigp.catequese.core.enums.URLsSistema;
import co.ao.sigp.catequese.core.util.jsf.JSFUtil;
import co.ao.sigp.catequese.core.util.jsf.Mensagem;
import co.ao.sigp.catequese.core.util.primefaces.BarChartUtils;
import co.ao.sigp.catequese.domain.exception.EntidadeEmUsoException;
import co.ao.sigp.catequese.domain.exception.NegocioException;
import co.ao.sigp.catequese.domain.model.CatequeseAnoLetivo;
import co.ao.sigp.catequese.domain.service.CatequeseAnoLetivoService;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author franklinfurtado
 */
@Controller("catequeseAnoLetivoBean")
@Scope("view")
public class CatequeseAnoLetivoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private CatequeseAnoLetivoService service;

    @Setter
    private List<CatequeseAnoLetivo> listarTodos;

    @Getter @Setter 
    private CatequeseAnoLetivo entidade;
    
    @Getter
    private BarChartModel graficoTotalCatqCatecPorAnoLetivo;

    public CatequeseAnoLetivo getEntityByID(Integer id) {
        return service.findByID(id);
    }

    public void carregarLista(TipoListar tipoListar) {
        if (listarTodos == null || tipoListar == TipoListar.CARREGAR_DADOS_BASE_DADOS) {
            listarTodos = service.listarTodos();
        }
    }

    public List<CatequeseAnoLetivo> getListarTodos() {
        if (listarTodos == null) {
            listarTodos = service.listarTodos();
        }
        return listarTodos;
    }

    public void prepararNovoRegisto() {
        this.entidade = new CatequeseAnoLetivo();
    }

    public void cancelarRegisto() {
        this.entidade = null;
    }

    public String styleClassForDataInicioSuperiorOuIgualDataFim() {
        if (entidade != null && entidade.getDataFim() != null && entidade.getDataInicio() != null) {
            return entidade.isDataFimInferiorOuIgualInicial() ? "ui-state-error" : "";
        }
        return "";
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

			JSFUtil.salvoComSucesso("Ano letivo salvo com sucesso");
			carregarLista(TipoListar.CARREGAR_DADOS_BASE_DADOS);
		} catch (NegocioException e) {
			Mensagem.mensagemErro(e.getMessage());
			JSFUtil.updateComponente("internalMessages");
		}
	}

	public void excluir() {
		try {
			String nomePastoralEliminar = entidade.getDescricao();
			service.excluir(this.entidade);
			JSFUtil.salvoComSucesso(String.format("Ano letivo (%s) eliminada com sucesso", nomePastoralEliminar));
			carregarLista(TipoListar.CARREGAR_DADOS_BASE_DADOS);
		} catch (EntidadeEmUsoException e) {
			JSFUtil.mensagemErro(e.getMessage());
		}
	}
    
    public void montarGraficos() {
        criarGraficoTotalCateqCatecuPorAnoLetivo();
    }

    public void criarGraficoTotalCateqCatecuPorAnoLetivo() {
        List<String> labels = new ArrayList<>();
        List<Number> valueTotalCatecumenos = new ArrayList<>();
        List<Number> valuesTotalCatequistas = new ArrayList<>();
        
        listarTodos.stream().forEach(anoLetivoLocal -> {
            labels.add(String.valueOf(anoLetivoLocal.getAno()));
            valueTotalCatecumenos.add(getTotalCatecumenoPastoral(anoLetivoLocal));
            valuesTotalCatequistas.add(getTotalCatequistaPastoral(anoLetivoLocal));
        });

        graficoTotalCatqCatecPorAnoLetivo = BarChartUtils.getChartModelTotalCatequistaCatecumenos(valueTotalCatecumenos, valuesTotalCatequistas, labels);
    }
    
    private final Integer totalCatequistasPorPastoral = new Random().nextInt(15);
    
    private final Integer totalCatecumenosPorPastoral = new Random().nextInt(15);
    
     public Integer getTotalCatecumenoPastoral(CatequeseAnoLetivo anoLetivoLocal) {
        return totalCatecumenosPorPastoral;
    }

    public Integer getTotalCatequistaPastoral(CatequeseAnoLetivo anoLetivoLocal) {
        return totalCatequistasPorPastoral;
    }
    
    public String verCatequistas() {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("nome", String.valueOf(entidade.getAno()));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("listaCatequistas", new ArrayList<>());
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("descricaoTipoPesquisa", ListarMembrosCatequese.LISTAR_POR_ANO_LETIVO.getDescricao());
        return URLsSistema.CATEQUISTAS_FICHA_VER.getUrl();
    }

    public String verCatecumenos() {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("nome", String.valueOf(entidade.getAno()));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("listaCatecumenos", new ArrayList<>());
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("descricaoTipoPesquisa", ListarMembrosCatequese.LISTAR_POR_ANO_LETIVO.getDescricao());
        return URLsSistema.CATECUMENO_VER_LISTA.getUrl();
    }
}

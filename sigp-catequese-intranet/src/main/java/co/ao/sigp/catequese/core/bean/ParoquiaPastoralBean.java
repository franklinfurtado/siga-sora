/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.core.bean;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.view.ViewScoped;

import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.ao.sigp.catequese.core.enums.ListarMembrosCatequese;
import co.ao.sigp.catequese.core.enums.TipoListar;
import co.ao.sigp.catequese.core.enums.URLsSistema;
import co.ao.sigp.catequese.core.util.jsf.JSFUtil;
import co.ao.sigp.catequese.core.util.jsf.Mensagem;
import co.ao.sigp.catequese.core.util.primefaces.BarChartUtils;
import co.ao.sigp.catequese.domain.exception.EntidadeEmUsoException;
import co.ao.sigp.catequese.domain.exception.NegocioException;
import co.ao.sigp.catequese.domain.model.ParoquiaPastoral;
import co.ao.sigp.catequese.domain.service.ParoquiaPastoralService;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author franklinfurtado
 */
@Controller("paroquiaPastoralBean")
@ViewScoped
public class ParoquiaPastoralBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ParoquiaPastoralService service;

	@Setter
	private List<ParoquiaPastoral> listarTodos;

	@Getter
	@Setter
	private ParoquiaPastoral entidade;

	@Getter
	private boolean mostrarEstatisticas;

	@Getter
	private BarChartModel graficoTotalCatqCatecPorPastoral;

	@Getter
	private DonutChartModel graficoPercentualCatecumenoPorPastoral, graficoPercentualCatequistaPorPastoral;

	private final Integer totalCatequistasPorPastoral = new Random().nextInt(15);

	private final Integer totalCatecumenosPorPastoral = new Random().nextInt(15);

	private Double percentualCatequistasPorPastoral = new Random().nextDouble();

	private Double percentualCatecumenosPorPastoral = new Random().nextDouble();
	

    public List<ParoquiaPastoral> getListarTodos() {
        if (listarTodos == null) {
            listarTodos = service.listarTodos();
        }
        return listarTodos;
    }

	public void carregarLista() {
		carregarLista(TipoListar.CARREGAR_DADOS_BASE_DADOS);
	}

	public void carregarLista(TipoListar tipoListar) {
		if (listarTodos == null || tipoListar == TipoListar.CARREGAR_DADOS_BASE_DADOS) {
			listarTodos = service.listarTodos();
		}
		mostrarEstatisticas = false;
	}

	public void carregarEstatisticas() {
		// TODO alterar funcionamento deste method e a forma de visalizacao das janelas
		// de estatisticas e lista de pastorais
		mostrarEstatisticas = true;
	}

	public void prepararNovoRegisto() {
		if (this.entidade != null) {
			this.entidade = null;
		}
		this.entidade = new ParoquiaPastoral();
	}

	public void cancelarRegisto() {
		this.entidade = null;
	}

	public String styleClassForRequiredNomeCoordenador() {
		if (entidade != null && entidade.getIsCatequista() != null) {
			return entidade.getIsCatequista() && (entidade.getNomeResonsavel().isEmpty())
					? "ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all form-control ui-state-error"
					: "ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all form-control";
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
			String nomePastoralEliminar = entidade.getNomePastoral();
			service.excluir(this.entidade);
			JSFUtil.salvoComSucesso(String.format("Pastoral (%s) eliminada com sucesso", nomePastoralEliminar));
			carregarLista(TipoListar.CARREGAR_DADOS_BASE_DADOS);
		} catch (EntidadeEmUsoException e) {
			JSFUtil.mensagemErro(e.getMessage());
		}
	}

	public void montarGraficos() {
		criarGraficoTotalCateqCatecuPorPastoral();
		criarGraficoPercentualCatequistaPorPastoral();
		criarGraficoPercentualCatecumenoPorPastoral();
	}

	public void criarGraficoTotalCateqCatecuPorPastoral() {
		List<String> labels = new ArrayList<>();
		List<Number> valueTotalCatecumenos = new ArrayList<>();
		List<Number> valuesTotalCatequistas = new ArrayList<>();

		listarTodos.stream().forEach(pastoralLocal -> {
			labels.add(pastoralLocal.getNomePastoral());
			valueTotalCatecumenos.add(getTotalCatecumenoPastoral(pastoralLocal));
			valuesTotalCatequistas.add(getTotalCatequistaPastoral(pastoralLocal));
		});

		graficoTotalCatqCatecPorPastoral = BarChartUtils.getChartModelTotalCatequistaCatecumenos(valueTotalCatecumenos,
				valuesTotalCatequistas, labels);
	}

	public void criarGraficoPercentualCatecumenoPorPastoral() {
		List<String> labels = new ArrayList<>();
		List<Number> valueTotalCatecumenos = new ArrayList<>();

		listarTodos.stream().forEach(pastoralLocal -> {
			labels.add(pastoralLocal.getNomePastoral());
			valueTotalCatecumenos.add(getPercentualCatecumenoPastoral(pastoralLocal));
		});

		graficoPercentualCatecumenoPorPastoral = BarChartUtils
				.getChartModelPercentualCatequistaPorPastoral(valueTotalCatecumenos, labels);
	}

	public void criarGraficoPercentualCatequistaPorPastoral() {
		List<String> labels = new ArrayList<>();
		List<Number> valueTotalCatecquista = new ArrayList<>();

		listarTodos.stream().forEach(pastoralLocal -> {
			labels.add(pastoralLocal.getNomePastoral());
			valueTotalCatecquista.add(getPercentualCatequistaPastoral(pastoralLocal));
		});

		graficoPercentualCatequistaPorPastoral = BarChartUtils
				.getChartModelPercentualCatequistaPorPastoral(valueTotalCatecquista, labels);
	}

	public Integer getTotalCatecumenoPastoral(ParoquiaPastoral pastoral) {
		return totalCatecumenosPorPastoral;
	}

	public Integer getTotalCatequistaPastoral(ParoquiaPastoral pastoral) {
		return totalCatequistasPorPastoral;
	}

	public Double getPercentualCatecumenoPastoral(ParoquiaPastoral pastoral) {
		carregarLista(TipoListar.NAO_CARREGAR_DADOS_BASE_DADOS);

		// TODO: calcular o total de catecumenos pela base de dados
//        Integer totalCatecumenos = catequeseCatecumenoService.getTotalCatecumenos();
		Double totalCatecumenos = getTotalCatecumenoPastoral(pastoral).doubleValue() + 10;

		percentualCatecumenosPorPastoral = (getTotalCatecumenoPastoral(pastoral).doubleValue() / totalCatecumenos)
				* 100;

		return Double.parseDouble(new DecimalFormat("#.##").format(percentualCatecumenosPorPastoral));
	}

	public Double getPercentualCatequistaPastoral(ParoquiaPastoral pastoral) {
		carregarLista(TipoListar.NAO_CARREGAR_DADOS_BASE_DADOS);

		// TODO: calcular o total de catequistas pela base de dados
//        Integer totalCatequista = catequeseCatequistaService.getTotalCatequista();
		Double totalCatequista = getTotalCatequistaPastoral(pastoral).doubleValue() + 10;

		percentualCatequistasPorPastoral = (getTotalCatequistaPastoral(pastoral).doubleValue() / totalCatequista) * 100;

		return Double.parseDouble(new DecimalFormat("#.##").format(percentualCatequistasPorPastoral));
	}

	public String verCatequistas() {
		return JSFUtil.verLista(entidade.getNomePastoral(), "listaCatequistas",
				ListarMembrosCatequese.LISTAR_POR_PASTORAL, URLsSistema.CATEQUISTAS_FICHA_VER);
	}

	public String verCatecumenos() {
		return JSFUtil.verLista(entidade.getNomePastoral(), "listaCatecumenos",
				ListarMembrosCatequese.LISTAR_POR_PASTORAL, URLsSistema.CATECUMENO_VER_LISTA);
	}
}

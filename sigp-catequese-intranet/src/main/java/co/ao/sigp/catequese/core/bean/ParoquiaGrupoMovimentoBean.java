/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.core.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
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
import co.ao.sigp.catequese.domain.model.ParoquiaGruposMovimentos;
import co.ao.sigp.catequese.domain.model.vo.ParoquiaGruposMovimentosVO;
import co.ao.sigp.catequese.domain.service.ParoquiaGrupoMovimentoService;
import co.ao.sigp.catequese.domain.service.ParoquiaPastoralService;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author franklinfurtado
 */
@Controller("paroquiaGrupoMovimentoBean")
@Scope("view")
public class ParoquiaGrupoMovimentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ParoquiaGrupoMovimentoService grupoMovimentoService;
	
	@Autowired
	private ParoquiaPastoralService pastoralService;
	
	@Autowired
	private ParoquiaPastoralBean paroquiaPastoralBean;

	@Setter
	private List<ParoquiaGruposMovimentosVO> listaTodosVO;

	@Getter
	@Setter
	private ParoquiaGruposMovimentos entidade;

	@Getter
	private boolean mostrarEstatisticas;

	@Getter
	@Setter
	private BarChartModel barChartModel;

	@Getter
	@Setter
	private DonutChartModel donutModel;

	public List<ParoquiaGruposMovimentosVO> getListarTodosVO() {
		if (listaTodosVO == null) {
			listaTodosVO = grupoMovimentoService.listarTodosVO();
		}
        return listaTodosVO;
    }
	
	public List<ParoquiaGruposMovimentos> listaPorPastoral(int idPastoral) {
		return grupoMovimentoService.listarTodosPorPastoral(idPastoral);
	}

	public void carregarListaVOPorPastoral(int idPastoral) {
		if (listaTodosVO == null) {
			listaTodosVO = grupoMovimentoService.listarTodosVOPorPastoral(idPastoral);
		}
	}

	public void carregarListaVO(TipoListar tipoListar) {
		if (listaTodosVO == null || tipoListar == TipoListar.CARREGAR_DADOS_BASE_DADOS) {
			listaTodosVO = grupoMovimentoService.listarTodosVO();
		}
		mostrarEstatisticas = false;
	}

	public void carregarEstatisticas() {
		// TODO alterar funcionamento deste method e a forma de visalizacao das janelas
		// de estatisticas e lista de pastorais
		mostrarEstatisticas = true;
	}

	public void prepararNovoRegisto() {
		if (pastoralService.existePastoralNoSistema()) {
			if (this.entidade != null) {
				this.entidade = null;
			}
			this.entidade = new ParoquiaGruposMovimentos();
			paroquiaPastoralBean.carregarLista(TipoListar.CARREGAR_DADOS_BASE_DADOS);
			JSFUtil.abrirDialog("AdicionarDlg", "AdicionarDialog");
		} else {
			JSFUtil.mensagemErro("Não existem pastorais registadas no sistema. Insira uma pastoral antes de adicionar um grupo ou movimento");
		}
	}

	public void prepararEditarRegisto(Integer id) {
		paroquiaPastoralBean.carregarLista(TipoListar.CARREGAR_DADOS_BASE_DADOS);
		entidade = grupoMovimentoService.pesquisarPorId(id);
	}

	public void cancelarRegisto() {
		this.entidade = null;
	}

	public String styleClassForRequiredNomeCoordenador() {
		if (entidade != null && entidade.getIsCatequista() != null) {
			return entidade.getIsCatequista() && (entidade.getNomeCoordenador().isEmpty())
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

			grupoMovimentoService.gravar(this.entidade);

			if (novoRegisto) {
				prepararNovoRegisto();
			} else {
				JSFUtil.closeDialog("AdicionarDialog");
			}

			JSFUtil.salvoComSucesso("Grupo/Movimento salvo com sucesso");
			carregarListaVO(TipoListar.CARREGAR_DADOS_BASE_DADOS);
		} catch (NegocioException e) {
			Mensagem.mensagemErro(e.getMessage());
			JSFUtil.updateComponente("internalMessages");
		}
	}

	public void excluir() {
		try {
			String nomeEntidadeEliminar = entidade.getNome();
			grupoMovimentoService.excluir(this.entidade);
			JSFUtil.salvoComSucesso(String.format("Grupo/Movimento (%s) eliminado com sucesso", nomeEntidadeEliminar));
			carregarListaVO(TipoListar.CARREGAR_DADOS_BASE_DADOS);
		} catch (EntidadeEmUsoException e) {
			JSFUtil.mensagemErro(e.getMessage());
		}
	}

	public void montarGraficos() {
		criarGraficoBar();
		criarGraficoDonut();
	}

	public void criarGraficoDonut() {
		List<String> labels = new ArrayList<>();
		List<Number> valueTotalCatecumenos = new ArrayList<Number>();
		List<Number> valuesTotalCatequistas = new ArrayList<Number>();

		for (ParoquiaGruposMovimentosVO labelValue : listaTodosVO) {
			labels.add(labelValue.getNome());
			valueTotalCatecumenos.add(getTotalCatecumenoGruposMovimentos(labelValue));
			valuesTotalCatequistas.add(getTotalCatecumenoGruposMovimentos(labelValue));
		}

		barChartModel = BarChartUtils.getChartModelTotalCatequistaCatecumenos(valueTotalCatecumenos,
				valuesTotalCatequistas, labels);
	}

	public void criarGraficoBar() {
		donutModel = new DonutChartModel();
		ChartData data = new ChartData();

		DonutChartDataSet dataSet = new DonutChartDataSet();
		List<Number> values = new ArrayList<>();
		values.add(300);
		values.add(50);
		values.add(100);
		dataSet.setData(values);

		List<String> bgColors = new ArrayList<>();
		bgColors.add("rgb(255, 99, 132)");
		bgColors.add("rgb(54, 162, 235)");
		bgColors.add("rgb(255, 205, 86)");
		dataSet.setBackgroundColor(bgColors);

		data.addChartDataSet(dataSet);
		List<String> labels = new ArrayList<>();
		labels.add("Red");
		labels.add("Blue");
		labels.add("Yellow");
		data.setLabels(labels);

		donutModel.setData(data);
	}

	public double getTotalCatecumenoGruposMovimentos(ParoquiaGruposMovimentosVO entity) {
		return Math.random();
	}

	public double getTotalCatequistaGruposMovimentos(ParoquiaGruposMovimentosVO entity) {
		return Math.random();
	}

	public String verCatequistas() {
		return JSFUtil.verLista(entidade.getNome(), "listaCatequistas",
				ListarMembrosCatequese.LISTAR_POR_GRUPO_MOVIMENTO, URLsSistema.CATEQUISTAS_FICHA_VER);
	}

	public String verCatecumenos() {
		return JSFUtil.verLista(entidade.getNome(), "listaCatecumenos",
				ListarMembrosCatequese.LISTAR_POR_GRUPO_MOVIMENTO, URLsSistema.CATECUMENO_VER_LISTA);
	}
}

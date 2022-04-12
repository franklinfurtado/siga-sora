
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.core.bean;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.faces.navigate.Navigate;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.ao.sigp.catequese.core.bean.navegacaopaginas.NavegacaoPaginasCatequistaInscricaoBean;
import co.ao.sigp.catequese.core.enums.EstadoDeRegisto;
import co.ao.sigp.catequese.core.enums.InscricaoOperacoes;
import co.ao.sigp.catequese.core.enums.TipoTelaMensagem;
import co.ao.sigp.catequese.core.util.jsf.JSFUtil;
import co.ao.sigp.catequese.core.util.jsf.lazydatamodel.LazyLoadCustomDataModel;
import co.ao.sigp.catequese.domain.exception.EntidadeEmUsoException;
import co.ao.sigp.catequese.domain.exception.EntidadeEmUsoExceptionInscricoesCatequista;
import co.ao.sigp.catequese.domain.exception.NomeEntidadeExisteNoSistemaException;
import co.ao.sigp.catequese.domain.exception.SacramentosCatequistaIncorrectosException;
import co.ao.sigp.catequese.domain.model.CatequeseInscricoesCatequista;
import co.ao.sigp.catequese.domain.model.CatequeseInscricoesEstado;
import co.ao.sigp.catequese.domain.model.vo.CatequeseInscricoesCatequistaVO;
import co.ao.sigp.catequese.domain.repository.CatequeseCatequistaInscricoesFiltro;
import co.ao.sigp.catequese.domain.service.CatequeseCatequistaInscricoesService;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author franklinfurtado
 */
@Controller("catequeseCatequistaIncricoesBean")
@Scope("view")
public class CatequeseCatequistaInscricoesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CatequeseCatequistaInscricoesService service;

	@Autowired
	private NavegacaoPaginasCatequistaInscricaoBean navegacaoInscricoesCatequista;

	@Getter
	private List<CatequeseInscricoesCatequistaVO> listaVO;

	@Getter
	private LazyLoadCustomDataModel<CatequeseInscricoesCatequistaVO> dataModel;

	@Getter
	@Setter
	private List<CatequeseInscricoesCatequistaVO> listaSelecionadosVO;

	@Getter
	@Setter
	private CatequeseInscricoesCatequista entidade;

	@Getter
	@Setter
	private CatequeseInscricoesCatequistaVO entidadeVO;

	@Getter
	@Setter
	private CatequeseCatequistaInscricoesFiltro filtro;

	@Getter
	@Setter
	private boolean editar;

	@Getter
	@Setter
	private UploadedFile foto;
	

	private CatequeseInscricoesCatequista carregarFichaInscricao() {

		Integer id = (Integer) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("id");

		if (id != null) {

			FacesContext.getCurrentInstance().getExternalContext().getFlash().clear();

			return service.findByID(id);
		}

		return null;
	}

	public void carregarFichaInscricaoSavalEditar() {

		this.entidade = carregarFichaInscricao();

		if (this.entidade == null) {

			this.entidade = new CatequeseInscricoesCatequista();

			this.entidade.prepararSalvarRegisto();
		}
	}

	public Navigate carregarFichaInscricaoVer() {

		this.entidade = carregarFichaInscricao();

		if (this.entidade == null) {

			return navegacaoInscricoesCatequista.verListaAtivos();
		}

		return null;
	}

	public void carrgarPaginaLista(EstadoDeRegisto tipoListar) {

		filtro = new CatequeseCatequistaInscricoesFiltro(tipoListar);

		dataModel = new LazyLoadCustomDataModel<CatequeseInscricoesCatequistaVO>(pc -> service.filtrados(pc, filtro));

		filtrar();

		String mensagem = (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("mensagem");

		Integer tipoMensagem = (Integer) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("tipoMensagem");

		if (mensagem != null && tipoMensagem != null) {

			FacesContext.getCurrentInstance().getExternalContext().getFlash().clear();

			if (tipoMensagem == TipoTelaMensagem.SUCESSO.getTipo()) {

				JSFUtil.mensagemSucesso(mensagem);

			} else if (tipoMensagem == TipoTelaMensagem.ERRO.getTipo()) {

				JSFUtil.mensagemErro(mensagem);
			}
		}
	}

	public void carregarPaginaListarAtivos() {

		carrgarPaginaLista(EstadoDeRegisto.NAO_APAGADO);
	}

	public void carregarPaginaListarApagagos() {

		carrgarPaginaLista(EstadoDeRegisto.APAGADO);
	}

	private void adicionarMensagemPaginaListarInscricoes(String mensagem, TipoTelaMensagem tipoMensagem) {

		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("mensagem", mensagem);

		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("tipoMensagem", tipoMensagem.getTipo());
	}

	public String getNomesCandidatos(List<CatequeseInscricoesCatequistaVO> lista) {

		if (lista != null) {
			return String.join(", ", lista.stream().map(CatequeseInscricoesCatequistaVO::getNomeCompleto).collect(Collectors.toList()));
		}

		return "";
	}

	private void adicionarMensagemSucessoOperacaoInscricoes(InscricaoOperacoes operacao, String nomeCandidato) {

		if (operacao == InscricaoOperacoes.APROVAR) {

			adicionarMensagemPaginaListarInscricoes(String.format("Inscrição/Inscriçōes de %s aprovado com sucesso, foi registado(a) na base de dados de catequistas do sistema", nomeCandidato), TipoTelaMensagem.SUCESSO);

		} else if (operacao == InscricaoOperacoes.REJEITAR) {

			adicionarMensagemPaginaListarInscricoes(String.format("Inscrição/Inscriçōes de %s rejeitada com sucesso", nomeCandidato), TipoTelaMensagem.SUCESSO);

		} else if (operacao == InscricaoOperacoes.EXCLUIR) {

			adicionarMensagemPaginaListarInscricoes(String.format("Inscrição de %s foi excluida com sucesso", nomeCandidato), TipoTelaMensagem.SUCESSO);

		} else if (operacao == InscricaoOperacoes.REINICIAR) {

			adicionarMensagemPaginaListarInscricoes(String.format("Inscrição de %s foi re-definido como criado", nomeCandidato), TipoTelaMensagem.SUCESSO);

		} else if (operacao == InscricaoOperacoes.RECUPERAR) {

			adicionarMensagemPaginaListarInscricoes(String.format("Inscrição de %s foi recuperado com sucesso", nomeCandidato), TipoTelaMensagem.SUCESSO);
		}
	}

	public void prepararEditarRegisto() {

		this.entidade.prepararEditarRegisto();

		habilitarEditar();
	}

	public void gravar() {

		boolean novoRegisto = false;

		try {

			if (this.entidade.isNovoRegisto()) {

				novoRegisto = true;
			}

			service.gravar(this.entidade);

			if (!novoRegisto) {

				JSFUtil.salvoComSucesso(String.format("Inscrição de %s foi alterada com sucesso", this.entidade.getNomeCompleto()));

				this.editar = false;

			} else {

				JSFUtil.salvoComSucesso("Inscrição salva com sucesso");
			}

		} catch (NomeEntidadeExisteNoSistemaException | SacramentosCatequistaIncorrectosException exception) {

			JSFUtil.mensagemErro(exception.getMessage());

			JSFUtil.scroolPageToTop();
		}
	}

	public Navigate rejeitarUnico() {

		try {

			service.rejeitarInscricao(this.entidade);

			adicionarMensagemSucessoOperacaoInscricoes(InscricaoOperacoes.REJEITAR, this.entidade.getNomeCompleto());

		} catch (EntidadeEmUsoException e) {

			adicionarMensagemPaginaListarInscricoes(e.getMessage(), TipoTelaMensagem.ERRO);
		}

		return navegacaoInscricoesCatequista.verListaAtivos();
	}

	public void rejeitarVarios() {

		listaSelecionadosVO.forEach(item -> {

			try {

				service.rejeitarInscricao(converterVoToEntity(item));

				item.setEstadoInscricao(CatequeseInscricoesEstado.REJEITADO);

			} catch (EntidadeEmUsoException e) {

				JSFUtil.mensagemErro(e.getMessage());
			}
		});

		JSFUtil.mensagemSucesso(String.format("Inscrição/Inscriçōes de %s rejeitada(s) com sucesso", getNomesCandidatos(listaSelecionadosVO)));
	}

	public Navigate aprovarUnico() {

		service.aprovarInscricao(this.entidade);

		adicionarMensagemSucessoOperacaoInscricoes(InscricaoOperacoes.APROVAR, this.entidade.getNomeCompleto());

		return navegacaoInscricoesCatequista.verListaAtivos();
	}

	public void aprovarVarios() {

		listaSelecionadosVO.forEach(item -> {

			service.aprovarInscricao(converterVoToEntity(item));

			item.setEstadoInscricao(CatequeseInscricoesEstado.APROVADO);
		});

		JSFUtil.mensagemSucesso(String.format("Inscrição/Inscriçōes de %s aprovada(s) com sucesso, foi registado(a) na base de dados de catequistas do sistema", getNomesCandidatos(listaSelecionadosVO)));
	}

	public Navigate excluir() {

		try {

			if (this.entidade.isApagado()) {

				return excluirPermanenteUnico();

			} else {

				service.excluirInscricao(this.entidade);

				adicionarMensagemSucessoOperacaoInscricoes(InscricaoOperacoes.EXCLUIR, this.entidade.getNomeCompleto());

				return navegacaoInscricoesCatequista.verListaAtivos();
			}

		} catch (EntidadeEmUsoExceptionInscricoesCatequista e) {

			JSFUtil.mensagemErro(e.getMessage());

			return navegacaoInscricoesCatequista.verFichaRegistoDeInscricaoNovo();
		}
	}

	public Navigate reiniciarUnico() {

		try {

			service.reiniciarInscricao(this.entidade);

			adicionarMensagemSucessoOperacaoInscricoes(InscricaoOperacoes.REINICIAR, this.entidade.getNomeCompleto());

			return navegacaoInscricoesCatequista.verListaAtivos();

		} catch (EntidadeEmUsoExceptionInscricoesCatequista e) {

			JSFUtil.mensagemErro(e.getMessage());

			return navegacaoInscricoesCatequista.verFichaRegistoDeInscricaoNovo();
		}

	}

	public Navigate excluirPermanenteUnico() {

		service.excluirPermanente(this.entidade);

		adicionarMensagemSucessoOperacaoInscricoes(InscricaoOperacoes.EXCLUIR, this.entidade.getNomeCompleto());

		return navegacaoInscricoesCatequista.verListaRemovidos();
	}

	public void excluirPermanenteVarios() {

		listaSelecionadosVO.forEach(item -> {

			service.excluirPermanente(converterVoToEntity(item));
		});

		JSFUtil.mensagemSucesso(String.format("Inscrição/Inscriçōes de %s excluida(s) com sucesso", getNomesCandidatos(listaSelecionadosVO)));

		carregarPaginaListarApagagos();
	}

	public Navigate recuperarUnico() {

		try {

			service.recuperar(this.entidade);

			adicionarMensagemSucessoOperacaoInscricoes(InscricaoOperacoes.RECUPERAR, this.entidade.getNomeCompleto());

			return navegacaoInscricoesCatequista.verListaAtivos();

		} catch (NomeEntidadeExisteNoSistemaException exception) {

			JSFUtil.mensagemErro(exception.getMessage());

			return navegacaoInscricoesCatequista.verFichaRegistoDeInscricaoNovo();
		}
	}

	public void recuperarVarios() {

		List<CatequeseInscricoesCatequistaVO> listaFalhados = new ArrayList<CatequeseInscricoesCatequistaVO>();

		listaSelecionadosVO.forEach(item -> {

			try {

				service.recuperar(converterVoToEntity(item));

			} catch (NomeEntidadeExisteNoSistemaException exception) {

				listaFalhados.add(item);
			}
		});

		if (!listaFalhados.isEmpty())

			JSFUtil.mensagemErro(
					String.format("O(s) nome(s) (%s) já existe no sistema, tente remover primeiro da lista de inscriçōes disponíveis o(s) catequista(s) com o(s) mesmo(s) nome(s)", getNomesCandidatos(listaFalhados)));

		else if (listaSelecionadosVO.size() != listaFalhados.size()) {

			listaSelecionadosVO.removeAll(listaFalhados);

			JSFUtil.mensagemSucesso(String.format("Inscrição/Inscriçōes de %s recuperada(s) com sucesso", getNomesCandidatos(listaSelecionadosVO)));
		}

		carregarPaginaListarApagagos();
	}

	private CatequeseInscricoesCatequista converterVoToEntity(CatequeseInscricoesCatequistaVO vo) {

		return service.findByID(vo.getId());
	}

	public void habilitarEditar() {
		
		editar = true;
	}

	public boolean naoEditar() {
		
		return entidade != null && !editar && entidade.getId() != null;
	}

	public void filtrar() {

		if (this.listaSelecionadosVO != null)

			this.listaSelecionadosVO.clear();

		this.filtro.setFiltroAtivo(Boolean.TRUE);
	}

	@Getter
	@Setter
	private CroppedImage croppedImage;

	@Getter
	private UploadedFile originalImageFile;

	public void handleFileUpload(FileUploadEvent event) {

		this.originalImageFile = null;

		this.croppedImage = null;

		UploadedFile file = event.getFile();

		if (file != null && file.getContent() != null && file.getContent().length > 0 && file.getFileName() != null) {

			this.originalImageFile = file;
		}

		System.out.println("lllllkk");
	}

	public void crop() {

		if (this.croppedImage == null || this.croppedImage.getBytes() == null || this.croppedImage.getBytes().length == 0) {

			JSFUtil.mensagemErro("Cropping failed.");
		}
	}

	public StreamedContent getImage() {

		return DefaultStreamedContent.builder().contentType(originalImageFile == null ? null : originalImageFile.getContentType()).stream(() -> {

			if (originalImageFile == null || originalImageFile.getContent() == null || originalImageFile.getContent().length == 0) {

				return null;
			}

			try {

				return new ByteArrayInputStream(originalImageFile.getContent());

			} catch (Exception e) {

				e.printStackTrace();

				return null;
			}
		}).build();
	}

	public StreamedContent getCropped() {

		return DefaultStreamedContent.builder().contentType(originalImageFile == null ? null : originalImageFile.getContentType()).stream(() -> {

			if (croppedImage == null || croppedImage.getBytes() == null || croppedImage.getBytes().length == 0) {

				return null;
			}

			try {

				return new ByteArrayInputStream(this.croppedImage.getBytes());

			} catch (Exception e) {

				e.printStackTrace();

				return null;
			}
		}).build();
	}
}

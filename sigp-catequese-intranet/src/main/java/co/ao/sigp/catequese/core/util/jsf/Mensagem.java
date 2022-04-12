package co.ao.sigp.catequese.core.util.jsf;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 *
 * @author franklinfurtado
 */
public class Mensagem implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void adicionarMensagem(Severity severidade, String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(severidade, mensagem, null));
		context.getExternalContext().getFlash().setKeepMessages(true);
	}

	public static void mensagemInformacao(String mensagem) {
		adicionarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
	}

	public static void mensagemAlerta(String mensagem) {
		adicionarMensagem(FacesMessage.SEVERITY_WARN, mensagem);
	}

	public static void mensagemErro(String mensagem) {
		adicionarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
	}

	public static void excluir(String nomeElemento) {
		mensagemErro(nomeElemento + " eliminado(a) com sucesso");
	}

	public static void excluirPermanente(String nomeElemento) {
		mensagemErro(nomeElemento + " eliminado(a) permanentemente com sucesso");
	}

	public static void recuperar(String nomeElemento) {
		mensagemInformacao(nomeElemento + " recuperado(a) com sucesso");
	}

	public static String registado(String nomeElemento) {
		return nomeElemento + " registado(a) com sucesso";
	}

	public static String editado(String nomeElemento) {
		return nomeElemento + " editado(a) com sucesso";
	}

	public static void nomeExisteSistema() {
		mensagemInformacao("Erro!!! O nome inserido já existe no sistema");
	}

	public static void nomeExisteSistemaRecuperar(String nomeElemento) {
		mensagemInformacao("Erro!!! O nome do elemento a recuperar (" + nomeElemento + "), já existe no sistema");
	}
}

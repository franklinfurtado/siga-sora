package co.ao.sigp.catequese.core.bean.navegacaopaginas;

import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.faces.navigate.Navigate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.ao.sigp.catequese.core.enums.URLsSistema;

@Controller("navegacaoPaginasCatequistaInscricaoBean")
@Scope("view")
public class NavegacaoPaginasCatequistaInscricaoBean {

	public Navigate verFichaRegistoDeInscricaoNovo() {

		return Navigate.to(URLsSistema.CATEQUISTA_INSCRICAO_FICHA_REGISTO.getUrl());
	}
	
	public Navigate verFichaRegistoDeInscricaoEditar(Integer idInscricao) {
		
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("id", idInscricao);

		return Navigate.to(URLsSistema.CATEQUISTA_INSCRICAO_FICHA_REGISTO.getUrl());
	}

	public Navigate verListaAtivos() {

		return Navigate.to(URLsSistema.CATEQUISTA_INSCRICAO_MOSTRAR_TODOS.getUrl());
	}

	public Navigate verListaRemovidos() {

		return Navigate.to(URLsSistema.CATEQUISTA_INSCRICAO_MOSTRAR_TODOS_ELIMINADOS.getUrl());
	}

	public Navigate verFichaInscricoes(Integer idInscricao) {

		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("id", idInscricao);

		return Navigate.to(URLsSistema.CATEQUISTA_INSCRICAO_FICHA_VER.getUrl());
	}
}

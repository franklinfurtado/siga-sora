package co.ao.sigp.catequese.core.bean.navegacaopaginas;

import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.faces.navigate.Navigate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.ao.sigp.catequese.core.enums.URLsSistema;

@Controller("navegacaoPaginasCatequistaBean")
@Scope("view")
public class NavegacaoPaginasCatequistaBean {

	public Navigate verFichaRegistoNovo() {

		return Navigate.to(URLsSistema.CATEQUISTAS_FICHA_REGISTO.getUrl());
	}
	
	public Navigate verFichaRegistoEditar(Integer idInscricao) {
		
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("id", idInscricao);

		return Navigate.to(URLsSistema.CATEQUISTAS_FICHA_REGISTO.getUrl());
	}

	public Navigate verListaAtivos() {

		return Navigate.to(URLsSistema.CATEQUISTAS_MOSTRAR_TODOS.getUrl());
	}

	public Navigate verListaRemovidos() {

		return Navigate.to(URLsSistema.CATEQUISTAS_MOSTRAR_TODOS_ELIMINADOS.getUrl());
	}

	public Navigate verFicha(Integer idInscricao) {

		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("id", idInscricao);

		return Navigate.to(URLsSistema.CATEQUISTAS_FICHA_VER.getUrl());
	}
}

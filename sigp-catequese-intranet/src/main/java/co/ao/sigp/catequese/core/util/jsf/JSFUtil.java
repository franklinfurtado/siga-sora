/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.core.util.jsf;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import co.ao.sigp.catequese.core.enums.ListarMembrosCatequese;
import co.ao.sigp.catequese.core.enums.URLsSistema;

/**
 *
 * @author franklinfurtado
 */
public class JSFUtil implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3067979464286408104L;

	public static void abrirDialog(String nomeFormUpdate, String nomeDialogShow) {
        PrimeFaces.current().ajax().update(nomeFormUpdate);
        PrimeFaces.current().executeScript("PF('" + nomeDialogShow + "').show();");
    }
    
    public static void closeDialog(String nomeDialogShow) {
        PrimeFaces.current().executeScript("PF('" + nomeDialogShow + "').hide();");
    }
    
    public static void updateComponente(String nomeComponente) {
        PrimeFaces.current().ajax().update(nomeComponente);
    }
    
    public static void updateMessageTemporary() {
        PrimeFaces.current().executeScript("setTimeout(function(){$('[id$=internalMessages]').hide(1);},4000);setTimeout(function(){$('[id$=messages]').hide(1);},4000)");
    }
    
    public static void updateComponenteMainForm() {
        updateComponente("mainForm");
    }
    
    public static void updateComponenteInternalMessages() {
        updateComponente("mainForm:internalMessages");
    }
    
    public static void scroolPageToTop() {
    	PrimeFaces.current().executeScript("$('html').animate({ scrollTop: 0 }, 'slow');");
    }
    
    public static void salvoComSucesso(String mensagem) {
        updateComponente(":mainForm");
        updateComponente(":formListar:datalist");
        mensagemSucesso(mensagem);
        PrimeFaces.current().executeScript("$('[id$=internalMessages]').hide(1)");
    }
    
    public static void mensagemSucesso(String mensagem) {
    	PrimeFaces.current().executeScript(
    			"Swal.fire({\n"
    			+ "  icon: 'success',\n"
    			+ "  title: '" + mensagem + "',\n"
    			+ "  showConfirmButton: true,\n"
    			+ "  timer: 5500\n"
    			+ "})");
    }
    
    public static void mensagemErro(String mensagem) {
    	PrimeFaces.current().executeScript(
    			"Swal.fire({\n"
    			+ "  icon: 'error',\n"
    			+ "  title: '" + mensagem + "',\n"
    			+ "  showConfirmButton: true,\n"
    			+ "  timer: 10500\n"
    			+ "})");
    }

	public static String verLista(String nomeEntidade, String nomeLista, ListarMembrosCatequese tipoDeLista,
			URLsSistema urlRetorno) {

		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("nome", nomeEntidade);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put(nomeLista, new ArrayList<>());
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("descricaoTipoPesquisa",
				tipoDeLista.getDescricao());
		return urlRetorno.getUrl();
	}
}

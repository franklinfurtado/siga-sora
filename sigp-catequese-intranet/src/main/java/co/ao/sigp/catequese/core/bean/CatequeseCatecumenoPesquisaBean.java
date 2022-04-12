/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.core.bean;

import co.ao.sigp.catequese.domain.model.CatequeseCatecumeno;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author franklinfurtado
 */
@Controller("catequeseCatecumenoPesquisa")
@Scope("view")
public class CatequeseCatecumenoPesquisaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    private List<CatequeseCatecumeno> listaCatecumenos;
    @Getter
    private String descricaoPesquisa;
    @Getter
    private String nome;

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void inicializarBean() {
        nome = (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("nome");
        descricaoPesquisa = (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("descricaoTipoPesquisa");
        listaCatecumenos = (List<CatequeseCatecumeno>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("listaCatecumenos");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().clear();
    }
}

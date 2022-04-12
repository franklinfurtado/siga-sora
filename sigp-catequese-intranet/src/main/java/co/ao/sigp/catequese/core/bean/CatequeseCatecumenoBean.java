
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.core.bean;

import co.ao.sigp.catequese.core.enums.TipoListar;
import co.ao.sigp.catequese.domain.model.CatequeseCatecumeno;
import co.ao.sigp.catequese.core.util.jsf.JSFUtil;
import co.ao.sigp.catequese.core.util.jsf.Mensagem;
import co.ao.sigp.catequese.domain.service.CatequeseCatecumenoService;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author franklinfurtado
 */
@Controller("catequeseCatecumenoBean")
@Scope("view")
public class CatequeseCatecumenoBean implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Autowired
    private CatequeseCatecumenoService catecumenoCatequeseService;

    @Getter @Setter
    private List<CatequeseCatecumeno> listCatecumenosCatequese;

    @Getter @Setter
    private CatequeseCatecumeno catecumeno;

    public void inicializarBean() {
        if (listCatecumenosCatequese == null) {
            listCatecumenosCatequese = catecumenoCatequeseService.listarOperacionais();
        }
    }

    public void carregarListaCatecumenos(TipoListar tipoListar) {
        if (listCatecumenosCatequese == null || tipoListar == TipoListar.CARREGAR_DADOS_BASE_DADOS) {
            listCatecumenosCatequese = catecumenoCatequeseService.listarOperacionais();
        }
    }

    public void prepararNovoRegisto() {
        this.catecumeno = new CatequeseCatecumeno();
    }

    public void fecharRegisto() {
        this.catecumeno = null;
    }

    public boolean isDadosRepetidos() {
        if (catecumeno.getId() != null) {
            if (catecumenoCatequeseService.nomeExistenteNoSistema(catecumeno.getId(), catecumeno.getIdDadosPessoais().getNome())) {
                Mensagem.mensagemErro("Erro!!! O nome do catecumeno inserido já existe no sistema");
                JSFUtil.updateComponente("internalMessages");
                return true;
            }
        } else {
            if (catecumenoCatequeseService.nomeExistenteNoSistema(catecumeno.getIdDadosPessoais().getNome())) {
                Mensagem.mensagemErro("Erro!!! O nome do catecumeno de Catequese inserido já existe no sistema");
                JSFUtil.updateComponente("internalMessages");
                return true;
            }
        }

        return false;
    }

    public void gravar() {
        if (!isDadosRepetidos()) {
            if (catecumeno.getId() != null) {
                catecumenoCatequeseService.gravar(this.catecumeno);
                Mensagem.mensagemInformacao("Catecumeno editado com sucesso");
                JSFUtil.closeDialog("AdicionarDialog");
                JSFUtil.updateComponente(":mainForm:messages");
                JSFUtil.updateMessageTemporary();
            } else {
                catecumenoCatequeseService.gravar(this.catecumeno);
                Mensagem.mensagemInformacao("Catecumeno registado com sucesso");
                prepararNovoRegisto();
                JSFUtil.updateComponente("internalMessages");
            }
            JSFUtil.updateComponente(":formListar:datalist");
            carregarListaCatecumenos(TipoListar.CARREGAR_DADOS_BASE_DADOS);
        }
    }

    public void excluir() {
        catecumenoCatequeseService.excluir(this.catecumeno);
        Mensagem.mensagemInformacao("catecumeno eliminado com sucesso");
        carregarListaCatecumenos(TipoListar.CARREGAR_DADOS_BASE_DADOS);
    }

    public void excluirPermanente() {
        catecumenoCatequeseService.excluirPermanente(this.catecumeno);
        Mensagem.mensagemInformacao("catecumeno eliminado permanentemente com sucesso");
        carregarListaCatecumenos(TipoListar.CARREGAR_DADOS_BASE_DADOS);
    }

    public void recuperar() {
        catecumenoCatequeseService.recuperar(this.catecumeno);
        Mensagem.mensagemInformacao("catecumeno recuperado com sucesso");
        carregarListaCatecumenos(TipoListar.CARREGAR_DADOS_BASE_DADOS);
    }
}

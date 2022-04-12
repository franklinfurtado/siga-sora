
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.core.bean;

import co.ao.sigp.catequese.core.enums.TipoListar;
import co.ao.sigp.catequese.core.enums.URLsSistema;
import co.ao.sigp.catequese.core.util.jsf.JSFUtil;
import co.ao.sigp.catequese.core.util.jsf.Mensagem;
import co.ao.sigp.catequese.domain.model.CatequeseInscricoesCatecumeno;
import co.ao.sigp.catequese.domain.service.CatequeseCatecumenoInscricoesService;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.file.UploadedFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author franklinfurtado
 */
@Controller("catequeseCatecumenoIncricoesBean")
@Scope("view")
public class CatequeseCatecumenoInscricoesBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private CatequeseCatecumenoInscricoesService service;

    @Getter
    @Setter
    private List<CatequeseInscricoesCatecumeno> lista;

    @Getter
    @Setter
    private CatequeseInscricoesCatecumeno entity;

    @Getter
    @Setter
    private UploadedFile foto;

    public void inicializarBean() {
        if (lista == null) {
            lista = service.listarOperacionais();
        }
    }

    public void carregarLista(TipoListar tipoListar) {
        if (lista == null || tipoListar == TipoListar.CARREGAR_DADOS_BASE_DADOS) {
            lista = service.listarOperacionais();
        }
    }

    public void prepararNovoRegisto() {
        this.entity = new CatequeseInscricoesCatecumeno();
        this.entity.prepararNovoRegisto();
    }

    public void fecharRegisto() {
        this.entity = null;
    }

    public boolean isDadosRepetidos() {
        if (entity.getId() != null) {
            if (service.nomeExistenteNoSistema(entity.getId(), entity.getIdDadosPessoais().getNome())) {
                Mensagem.nomeExisteSistema();
                JSFUtil.updateComponenteInternalMessages();
                return true;
            }
        } else {
            if (service.nomeExistenteNoSistema(entity.getIdDadosPessoais().getNome())) {
                Mensagem.nomeExisteSistema();
                JSFUtil.updateComponenteInternalMessages();
                return true;
            }
        }

        return false;
    }

    public String gravar() {
        if (!isDadosRepetidos()) {
            if (entity.getId() != null) {
                service.gravar(this.entity);
                Mensagem.editado("Inscrição");
            } else {
                service.gravar(this.entity);
                Mensagem.registado("Inscrição");
                prepararNovoRegisto();
            }
            JSFUtil.updateMessageTemporary();
            carregarLista(TipoListar.CARREGAR_DADOS_BASE_DADOS);
            return URLsSistema.CATECUMENO_INSCRICAO.getUrl();
        }
        return "";
    }

    public void verificaExclusao(CatequeseInscricoesCatecumeno inscricoesCatecumenoExcluir) {
        if (service.existeCatecumenoInscricao(inscricoesCatecumenoExcluir.getIdDadosPessoais().getId())) {
            Mensagem.mensagemErro("Erro!!! Não foi possivel eliminar a inscricao porque já existe catecumeno associado a esta inscricao");
            JSFUtil.updateComponenteInternalMessages();
        }
    }

    public void excluir() {
        service.excluir(this.entity);
        Mensagem.excluir("Inscrição");
        carregarLista(TipoListar.CARREGAR_DADOS_BASE_DADOS);
    }

    public void excluirPermanente() {
        service.excluirPermanente(this.entity);
        Mensagem.excluirPermanente("Inscrição");
        carregarLista(TipoListar.CARREGAR_DADOS_BASE_DADOS);
    }

    public void recuperar() {
        service.recuperar(this.entity);
        Mensagem.recuperar("Inscrição");
        carregarLista(TipoListar.CARREGAR_DADOS_BASE_DADOS);
    }
}

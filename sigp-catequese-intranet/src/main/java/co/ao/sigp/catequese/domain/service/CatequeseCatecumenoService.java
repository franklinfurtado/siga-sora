/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.service;

import co.ao.sigp.catequese.core.enums.EstadoDeRegisto;
import co.ao.sigp.catequese.domain.model.CatequeseCatecumeno;
import co.ao.sigp.catequese.domain.repository.CatequeseCatecumenoRepository;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author franklin.furtado
 */
@Service
public class CatequeseCatecumenoService implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private CatequeseCatecumenoRepository repository;
    
    private final String nomeAtributoId = "id";
    
    
    public Integer getTotalCatecumenos() {
        Long value = repository.count();
        
        return value.intValue();
    }
    
    public List<CatequeseCatecumeno> listarTodos() {
        return repository.findAll();
    }
    
    public List<CatequeseCatecumeno> listarOperacionais() {
        return repository.listarPorEstado(EstadoDeRegisto.NAO_APAGADO.getValue());
    }
    
    public List<CatequeseCatecumeno> listarApagados() {
        return repository.listarPorEstado(EstadoDeRegisto.APAGADO.getValue());
    }
    
    public CatequeseCatecumeno findByID(Integer id) {
        return repository.findById(id).get();
    }

    @Transactional
    public void gravar(CatequeseCatecumeno anoLetivo) {
        repository.save(anoLetivo);
    }

    @Transactional
    public void excluir(CatequeseCatecumeno anoLetivo) {
        anoLetivo.setRemovido(EstadoDeRegisto.APAGADO.getValue());
        repository.save(anoLetivo);
    }

    @Transactional
    public void recuperar(CatequeseCatecumeno anoLetivo) {
        anoLetivo.setRemovido(EstadoDeRegisto.NAO_APAGADO.getValue());
        repository.save(anoLetivo);
    }

    @Transactional
    public void excluirPermanente(CatequeseCatecumeno anoLetivo) {
        repository.delete(anoLetivo);
    }

    public boolean nomeExistenteNoSistema(String nome) {
        return repository.nomeExistenteNoSistemaCustom(nome);
    }
    
    public boolean nomeExistenteNoSistema(Integer id, String nome) {
        return repository.nomeExistenteNoSistemaCustom(id, nome);
    }

    public boolean existeSacramentoComCatecumeno(Integer id) {
        //TODO: validar remocao de pastoral com movimento registado
        String nomeAtributoEntidadeDendente = "catequeseCatecumenoSacramentoList";
        
        return repository.existeEntidadesDependentes(nomeAtributoId, id, nomeAtributoEntidadeDendente);
    }

    public boolean existeTurmaComCatecumeno(Integer id) {
        //TODO: validar remocao de pastoral com movimento registado
        String nomeAtributoEntidadeDendente = "catequeseCatecumenoTurmaList";
        
        return repository.existeEntidadesDependentes(nomeAtributoId, id, nomeAtributoEntidadeDendente);
    }

    public boolean existeCatecumenoInscricao(Integer idDadosGeraisInscricao) {
        //TODO: validar remocao de inscricao com catecumeno
        return repository.existeCatecumenoComInscricao(idDadosGeraisInscricao);
    }
}

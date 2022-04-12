/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.ao.sigp.catequese.core.enums.EstadoDeRegisto;
import co.ao.sigp.catequese.domain.model.CatequeseInscricoesCatecumeno;
import co.ao.sigp.catequese.domain.repository.CatequeseCatecumenoInscricoesRepository;

/**
 *
 * @author franklin.furtado
 */
@Service
public class CatequeseCatecumenoInscricoesService implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private CatequeseCatecumenoInscricoesRepository repository;
    
    @Autowired
    private CatequeseCatecumenoService catecumenoService;
    
    
    public Integer getTotalCatecumenos() {
        Long value = repository.count();
        
        return value.intValue();
    }
    
    public List<CatequeseInscricoesCatecumeno> listarTodos() {
        return repository.findAll();
    }
    
    public List<CatequeseInscricoesCatecumeno> listarOperacionais() {
        return repository.listarPorEstado(EstadoDeRegisto.NAO_APAGADO.getValue());
    }
    
    public List<CatequeseInscricoesCatecumeno> listarApagados() {
        return repository.listarPorEstado(EstadoDeRegisto.APAGADO.getValue());
    }
    
    public CatequeseInscricoesCatecumeno findByID(Integer id) {
        return repository.findById(id).get();
    }

    @Transactional
    public void gravar(CatequeseInscricoesCatecumeno entity) {
        repository.save(entity);
    }

    @Transactional
    public void excluir(CatequeseInscricoesCatecumeno entity) {
        entity.setRemovido(EstadoDeRegisto.APAGADO.getValue());
        repository.save(entity);
    }

    @Transactional
    public void recuperar(CatequeseInscricoesCatecumeno entity) {
        entity.setRemovido(EstadoDeRegisto.NAO_APAGADO.getValue());
        repository.save(entity);
    }

    @Transactional
    public void excluirPermanente(CatequeseInscricoesCatecumeno entity) {
        repository.delete(entity);
    }

    public boolean nomeExistenteNoSistema(String nome) {
        return repository.nomeExistenteNoSistemaCustom(nome);
    }
    
    public boolean nomeExistenteNoSistema(Integer id, String nome) {
        return repository.nomeExistenteNoSistemaCustom(id, nome);
    }

    public boolean existeCatecumenoInscricao(Integer idDadosGeraisInscricao) {
        //TODO: validar remocao de inscricao com catecumeno
        return catecumenoService.existeCatecumenoInscricao(idDadosGeraisInscricao);
    }
}

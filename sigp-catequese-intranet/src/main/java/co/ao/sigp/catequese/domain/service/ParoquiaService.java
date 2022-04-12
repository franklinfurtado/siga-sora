/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.service;

import co.ao.sigp.catequese.domain.model.Paroquia;
import co.ao.sigp.catequese.domain.repository.ParoquiaRepository;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author franklin.furtado
 */
@Service
public class ParoquiaService implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ParoquiaRepository repository;
    
    public List<Paroquia> listarTodos() {
        return repository.findAll();
    }
    
    public List<String> listarNomesParoquias() {
        return repository.listarNomesParoquias();
    }
}

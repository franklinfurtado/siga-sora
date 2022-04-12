/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.core.bean;

import co.ao.sigp.catequese.domain.model.Paroquia;
import co.ao.sigp.catequese.domain.service.ParoquiaService;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author franklinfurtado
 */
@Controller("paroquiaBean")
@Scope("view")
public class ParoquiaBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ParoquiaService service;
    
    public List<Paroquia> listarTodos() {
        return service.listarTodos();
    }
    
    public List<String> listarNomesParoquias() {
        return service.listarNomesParoquias();
    }
}

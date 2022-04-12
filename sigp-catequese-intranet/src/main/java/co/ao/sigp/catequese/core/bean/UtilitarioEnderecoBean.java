/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.core.bean;

import co.ao.sigp.catequese.domain.model.UtilitarioDistrito;
import co.ao.sigp.catequese.domain.model.UtilitarioMunicipio;
import co.ao.sigp.catequese.domain.service.UtilitarioEnderecoService;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author franklinfurtado
 */
@Controller("utilitarioEnderecoBean")
@Scope("view")
public class UtilitarioEnderecoBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Autowired
    UtilitarioEnderecoService service;

    public List<UtilitarioDistrito> listarDistritosPorMunicipio(Integer id) {
        return service.listarDistritosPorMunicipio(id);
    }
    
    public List<UtilitarioMunicipio> listarMunicipioDeLuanda() {
        return service.listarMunicipioDeLuanda();
    }
}

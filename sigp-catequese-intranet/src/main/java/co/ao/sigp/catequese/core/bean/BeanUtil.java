/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.core.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.ao.sigp.catequese.core.enums.MesesDoAno;
import co.ao.sigp.catequese.domain.model.CatequeseAnoLetivoEstados;
import co.ao.sigp.catequese.domain.model.CatequeseContactosGrauParentesco;
import co.ao.sigp.catequese.domain.model.CatequeseInscricoesEstado;
import co.ao.sigp.catequese.domain.model.DiaSemana;

/**
 *
 * @author scm
 */
@Controller("beanUtil")
@Scope("view")
public class BeanUtil implements Serializable {

	private static final long serialVersionUID = 1L;
    
    public List<DiaSemana> getDiasDaSemana() {
        return Arrays.asList(DiaSemana.values());
    }
    
    public List<String> getMesesDoAno() {
        return Stream.of(MesesDoAno.values()).map(MesesDoAno::getValue).collect(Collectors.toList());
    }
    
    public List<String> getGrauParentescos() {
        return Stream.of(CatequeseContactosGrauParentesco.values()).map(CatequeseContactosGrauParentesco::getValue).collect(Collectors.toList());
    }
    
    public String retornaAnoToDescricaoNominal(Integer ano) { 
        return String.valueOf(LocalDate.now().getYear() - ano).concat(" Anos");
    }
    
    public List<CatequeseAnoLetivoEstados> getEstadosAnoLetivo() {
        return Arrays.asList(CatequeseAnoLetivoEstados.values());
    }
    
    public LocalDate dataMax() {
    	return LocalDate.now().minusYears(5);
    }
    
    public List<CatequeseInscricoesEstado> getEstadosInscricao() {
    	return Arrays.asList(CatequeseInscricoesEstado.values());
    }
}

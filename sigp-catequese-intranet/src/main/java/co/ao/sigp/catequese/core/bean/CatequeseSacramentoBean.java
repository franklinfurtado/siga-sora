
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.core.bean;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.ao.sigp.catequese.domain.model.CatequeseSacramento;
import co.ao.sigp.catequese.domain.service.CatequeseSacramentoService;
import lombok.Getter;

/**
 *
 * @author franklinfurtado
 */
@Controller("catequeseSacramentoBean")
@Scope("view")
public class CatequeseSacramentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CatequeseSacramentoService service;

	@Getter
	private List<CatequeseSacramento> listaSacramentos;

	@Getter
	private List<String> listaNomeSacramentos;

	@PostConstruct
	public void carregarListaSacramentos() {

		// TODO - Aplicar cash na lista de sacramentos

		if (listaNomeSacramentos == null) {

			listaNomeSacramentos = service.listarTodos().stream().map(CatequeseSacramento::getNomeSacramento).collect(Collectors.toList());
		}

		if (listaSacramentos == null) {

			listaSacramentos = service.listarTodos();
		}
	}
}

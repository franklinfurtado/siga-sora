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

import co.ao.sigp.catequese.domain.model.UtilitarioDistrito;
import co.ao.sigp.catequese.domain.model.UtilitarioEndereco;
import co.ao.sigp.catequese.domain.model.UtilitarioMunicipio;
import co.ao.sigp.catequese.domain.repository.UtilitarioDistritoRepository;
import co.ao.sigp.catequese.domain.repository.UtilitarioMunicipioRepository;

/**
 *
 * @author franklin.furtado
 */
@Service
public class UtilitarioEnderecoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UtilitarioDistritoRepository repositorioDistrito;
	
	@Autowired
	private UtilitarioMunicipioRepository repositorioMunicipio;
	

	public List<UtilitarioDistrito> listarDistritosPorMunicipio(Integer id) {
		return repositorioDistrito.listarDistritosPorMunicipio(id);
	}

	public List<UtilitarioMunicipio> listarMunicipioDeLuanda() {
		return repositorioMunicipio.listarMunicipioDeLuanda();
	}

	public UtilitarioDistrito pesquisarDistrito(UtilitarioEndereco endereco) {
		if (endereco != null && endereco.getIdDistrito() != null && endereco.getIdDistrito().getId() != null) {
			return repositorioDistrito.pesquisarDistrito(endereco.getIdDistrito().getId());
		}
		return null;
	}
}

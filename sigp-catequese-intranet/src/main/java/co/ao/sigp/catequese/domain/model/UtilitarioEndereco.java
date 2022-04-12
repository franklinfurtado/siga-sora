/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author franklin.furtado
 */
@Entity
@Table(name = "utilitario_endereco")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class UtilitarioEndereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	@EqualsAndHashCode.Include
	private Integer id;

	@Size(max = 100, message = "O nome do bairro deve ter no máximo 100 caracteres")
	@Column(name = "bairro")
	private String bairro;

	@Size(max = 100, message = "O nome da rua deve ter no máximo 100 caracteres")
	@Column(name = "rua")
	private String rua;

	@JoinColumn(name = "idDistrito", referencedColumnName = "id")
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	private UtilitarioDistrito idDistrito;

	@Transient
	private UtilitarioMunicipio idMunicipio;

	@PrePersist
	@PreUpdate
	public void antesSalvar() {
		
		if (this.idDistrito != null && this.idDistrito.getId() != null) {
		
			this.idDistrito.setIdMunicipio(idMunicipio);
		}
	}

	@PostLoad
	public void depoisCarregar() {
		
		if (this.idDistrito != null) {
		
			this.idMunicipio = this.idDistrito.getIdMunicipio();
		}
	}

	public String enderecoCompleto() {
		
		String thisLocal = this.getIdDistrito().getIdMunicipio().getIdProvincia().getNomeProvincia();
		
		thisLocal = thisLocal.concat(", ").concat(this.getIdDistrito().getIdMunicipio().getNomeMunicipio());
		
		thisLocal = thisLocal.concat(", ").concat(this.getIdDistrito().getNomeDistrito());

		if (!this.getBairro().equals("")) {
		
			thisLocal = thisLocal.concat(", ").concat(this.getBairro());
		}

		if (!this.getRua().equals("")) {
			
			thisLocal = thisLocal.concat(", ").concat(this.getRua());
		}

		return thisLocal;
	}

	public void prepararNovoRegisto() {
		
		if (this.idDistrito == null) {
		
			this.idDistrito = new UtilitarioDistrito();
			
			this.idDistrito.setIdMunicipio(new UtilitarioMunicipio());
			
			this.idDistrito.getIdMunicipio().setIdProvincia(new UtilitarioProvincia());
			
			this.idDistrito.getIdMunicipio().getIdProvincia().setIdPais(new UtilitarioPais(1));
		}
	}
}

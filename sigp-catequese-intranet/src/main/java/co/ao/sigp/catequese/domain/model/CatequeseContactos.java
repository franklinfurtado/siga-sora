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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author franklin.furtado
 */
@Entity
@Table(name = "catequese_contactos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class CatequeseContactos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	@EqualsAndHashCode.Include
	private Integer id;

	@Email(message = "Email inválido")
	@Size(max = 45, message = "O email deve ter no máximo 45 caracteres")
	@Column(name = "email")
	private String email;

	@Size(max = 45, message = "O telefone do primeiro contacto de emergencia deve ter no máximo 45 caracteres")
	@Column(name = "telefone_contacto_emergencia1")
	private String telefoneContactoEmergencia1;

	@Size(max = 45, message = "O nome do primeiro contacto de emergencia deve ter no máximo 45 caracteres")
	@Column(name = "nome_contacto_emergencia1")
	private String nomeContactoEmergencia1;

	@Size(max = 45, message = "O telefone do segundo contacto de emergencia deve ter no máximo 45 caracteres")
	@Column(name = "telefone_contacto_emergencia2")
	private String telefoneContactoEmergencia2;

	@Size(max = 45, message = "O nome do segundo contacto de emergencia deve ter no máximo 45 caracteres")
	@Column(name = "nome_contacto_emergencia2")
	private String nomeContactoEmergencia2;

	@Size(max = 45, message = "O primeiro telefone deve ter no máximo 45 caracteres")
	@Column(name = "numero_telefone1")
	private String numeroTelefone1;

	@Size(max = 45, message = "O segundo telefone deve ter no máximo 45 caracteres")
	@Column(name = "numero_telefone2")
	private String numeroTelefone2;

	@Size(max = 45, message = "O grau de parentesco do contacto de emergencia deve ter no máximo 45 caracteres")
	@Column(name = "grau_parentesco_contacto_emergencia1")
	private String grau_parentesco_contacto_emergencia1;
	

	@PrePersist @PreUpdate
	public void antesSalvar() {

		this.numeroTelefone1 = prepararNumeroTelefone(this.numeroTelefone1);

		this.numeroTelefone2 = prepararNumeroTelefone(this.numeroTelefone2);

		this.telefoneContactoEmergencia1 = prepararNumeroTelefone(this.telefoneContactoEmergencia1);

		this.telefoneContactoEmergencia2 = prepararNumeroTelefone(this.telefoneContactoEmergencia2);
	}

	public String prepararNumeroTelefone(String numeroTelefone) {

		return numeroTelefone != null ? numeroTelefone.replace("-", "") : null;
	}
}

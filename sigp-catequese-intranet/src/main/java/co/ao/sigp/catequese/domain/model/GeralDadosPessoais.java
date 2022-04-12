/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author franklin.furtado
 */
@Entity
@Table(name = "geral_dados_pessoais")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
public class GeralDadosPessoais implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	@EqualsAndHashCode.Include
	private Integer id;

	@Basic(optional = false)
	@NotBlank(message = "Informe o nome")
	@Size(max = 45, message = "O nome deve ter no máximo 45 caracteres")
	@Column(name = "nome")
	private String nome;

	@Basic(optional = false)
	@NotBlank(message = "Informe o sobrenome")
	@Size(max = 45, message = "O sobrenome deve ter no máximo 45 caracteres")
	@Column(name = "sobrenome")
	private String sobrenome;

	@Basic(optional = false)
	@NotBlank(message = "Informe o nome completo")
	@Size(max = 255, message = "O nome completo deve ter no máximo 255 caracteres")
	@Column(name = "nome_completo")
	private String nomeCompleto;

	@Basic(optional = false)
	@NotNull(message = "Informe a data de nascimento")
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	@Size(max = 45, message = "O nome do pai deve ter no máximo 45 caracteres")
	@Column(name = "nome_pai")
	private String nomePai;

	@Size(max = 45, message = "O nome da mãe deve ter no máximo 45 caracteres")
	@Column(name = "nome_mae")
	private String nomeMae;

	@JoinColumn(name = "id_contactos", referencedColumnName = "id")
	@ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private CatequeseContactos idContactos;

	@JoinColumn(name = "id_endereco", referencedColumnName = "id")
	@ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private UtilitarioEndereco idEndereco;

	@JoinColumn(name = "id_foto_pessoa", referencedColumnName = "id")
	@ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private GeralFotoPessoa geralFotoPessoa;

	@JoinColumn(name = "id_grupo_movimento", referencedColumnName = "id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private ParoquiaGruposMovimentos idGrupoMovimento;

	@Transient
	private ParoquiaPastoral idPastoral;

	@PostLoad
	public void depoisCarregar() {

		if (this.idGrupoMovimento != null)

			this.idPastoral = this.idGrupoMovimento.getIdPastoral();
	}

	@PrePersist
	@PreUpdate
	public void antesSalvar() {

		String nomes[] = nomeCompleto.split(" ");

		this.nome = nomes[0];

		this.sobrenome = nomes[nomes.length - 1];

		if (this.idGrupoMovimento != null && this.idGrupoMovimento.getId() != null)

			this.idGrupoMovimento.setIdPastoral(idPastoral);
	}

	public void prepararNovoRegisto() {

		if (this.idContactos == null)

			this.idContactos = new CatequeseContactos();
		
		if (this.idEndereco == null)

			this.idEndereco = new UtilitarioEndereco();

		this.idEndereco.prepararNovoRegisto();
	}

	public void prepararEditar() {

		if (idContactos == null)

			this.idContactos = new CatequeseContactos();

		if (idEndereco == null) 

			this.idEndereco = new UtilitarioEndereco();

		this.idEndereco.prepararNovoRegisto();
		
		if (this.idGrupoMovimento == null)

			idGrupoMovimento = new ParoquiaGruposMovimentos();
		
		this.idGrupoMovimento.prepararNovoRegisto();
	}
}

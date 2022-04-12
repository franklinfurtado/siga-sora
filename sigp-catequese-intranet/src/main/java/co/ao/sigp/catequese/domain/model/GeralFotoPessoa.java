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
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author franklinfurtado
 */
@Entity
@Table(name = "geral_foto_pessoa")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class GeralFotoPessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Integer id;

    @Basic(optional = false)
    @NotEmpty(message = "Informe o nome do arquivo")
    @Size(max = 150, message = "O nome do arquivo deve ter no máximo 150 caracteres")
    @Column(name = "nomeArquivo")
    private String nomeArquivo;
    
    @NotEmpty(message = "Informe a descrição do arquivo")
    @Size(max = 150, message = "A descrição do arquivo deve ter no máximo 150 caracteres")
    @Column(name = "descricao")
    private String descricao;
    
    @Basic(optional = false)
    @NotEmpty(message = "Informe o tipo de conteúdo do arquivo")
    @Size(max = 80, message = "O tipo de conteúdo do arquivo deve ter no máximo 80 caracteres")
    @Column(name = "contentType")
    private String contentType;
    
    @NotNull(message = "Informe o tamanho do arquivo")
    @Column(name = "tamanho")
    private Long tamanho;
}

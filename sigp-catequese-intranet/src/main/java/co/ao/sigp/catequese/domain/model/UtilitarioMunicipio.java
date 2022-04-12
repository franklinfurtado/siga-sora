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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author franklin.furtado
 */
@Entity
@Table(name = "utilitario_municipio")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class UtilitarioMunicipio implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Integer id;
    
    @Basic(optional = false)
    @NotBlank(message = "Informe o nome do município")
    @Size(max = 45, message = "O nome do município deve ter no máximo 45 caracteres")
    @Column(name = "nome_municipio")
    private String nomeMunicipio;
    
    @JoinColumn(name = "idProvincia", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @NotNull(message = "Informe a província onde o município pertence")
    private UtilitarioProvincia idProvincia;
}

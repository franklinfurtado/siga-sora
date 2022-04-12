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
@Table(name = "utilitario_provincia")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UtilitarioProvincia implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotBlank(message = "Informe o nome da província")
    @Size(max = 45, message = "O nome da província deve ter no máximo 45 caracteres")
    @Column(name = "nome_provincia")
    private String nomeProvincia;
    
    @JoinColumn(name = "idPais", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @NotNull(message = "Informe o país onde a província pertence")
    private UtilitarioPais idPais;
}

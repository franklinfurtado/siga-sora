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
@Table(name = "catequese_sala_catequese")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class CatequeseSalaCatequese implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Integer id;
    
    @Basic(optional = false)
    @NotBlank(message = "Informe da sala de catequese")
    @Size(max = 45, message = "O nome da sala deve ter no máximo 45 caracteres")
    @Column(name = "nome_sala")
    private String nomeSala;
    
    @Column(name = "total_cadeiras")
    private Integer totalCadeiras;
    
    @NotNull(message = "Informe o centro de catequese")
    @JoinColumn(name = "id_centro_catequese", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CatequeseCentroCatequese idCentroCatequese;
}

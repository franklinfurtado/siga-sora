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
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author franklin.furtado
 */
@Entity
@Table(name = "catequese_turma_catequese")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class CatequeseTurmaCatequese implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Integer id;
    
    @NotNull(message = "Informe o horário de catequese")
    @JoinColumn(name = "id_horario_catequese", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CatequeseHorarioCatequese idHorarioCatequese;
    
    @NotNull(message = "Informe a sala de catequese")
    @JoinColumn(name = "id_sala_catequese", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CatequeseSalaCatequese idSalaCatequese;
    
    @NotNull(message = "Informe o ano letivo")
    @JoinColumn(name = "id_ano_letivo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CatequeseAnoLetivo idAnoLetivo;
    
    @NotNull(message = "Informe a etapa da catequese")
    @JoinColumn(name = "id_etapa_catequese", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CatequeseEtapaCatequese idEtapaCatequese;
}

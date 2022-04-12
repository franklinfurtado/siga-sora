/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author franklin.furtado
 */
@Entity
@Table(name = "catequese_catecumeno")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class CatequeseCatecumeno implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @EqualsAndHashCode.Include
    private Integer id;
    
    @Basic(optional = false)
    @NotEmpty(message = "Informe o código do catecumeno")
    @Size(max = 45, message = "O código do catecumeno deve ter no máximo 45 caracteres")
    private String codigoCatecumeno;
    
    @Basic(optional = false)
    @NotNull(message = "Informe se o catecumeno encontra-se removido ou não")
    private int removido;

    @UpdateTimestamp
    private LocalDate dataRemocao;
    
    @JoinColumn(name = "idDadosPessoais", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GeralDadosPessoais idDadosPessoais;

    private boolean temGrupoMovimento;
    
    @JoinColumn(name = "idGrupoMovimento", referencedColumnName = "id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private ParoquiaGruposMovimentos idGrupoMovimento;
    
    @JoinColumn(name = "idSalaCatequeseAtual", referencedColumnName = "id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private CatequeseSalaCatequese idSalaCatequeseAtual;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catequeseCatecumeno", fetch = FetchType.LAZY)
    private List<CatequeseCatecumenoSacramento> catequeseCatecumenoSacramentoList;
}

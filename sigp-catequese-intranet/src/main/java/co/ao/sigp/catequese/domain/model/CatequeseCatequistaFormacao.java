/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author franklin.furtado
 */
@Entity
@Table(name = "catequese_catequista_formacao")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class CatequeseCatequistaFormacao implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Integer id;
    
    @Column(name = "data_inicio_formacao")
    private LocalDate dataInicioFormacao;
    
    @Column(name = "data_fim_formacao")
    private LocalDate dataFimFormacao;
    
    @Basic(optional = false)
    @NotEmpty(message = "Informe o nome da formação")
    @Size(max = 150, message = "O nome da formação deve ter no máximo 150 caracteres")
    @Column(name = "nome_formacao")
    private String nomeFormacao;
    
    @NotNull(message = "Informe o catequista")
    @JoinColumn(name = "id_catequista", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CatequeseCatequista idCatequista;
    
    @NotNull(message = "Informe a formação")
    @JoinColumn(name = "id_formacao", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CatequeseFormacao idFormacao;
    
    @PrePersist @PreUpdate
    public void antesSalvarEditar() {
    	
    	this.nomeFormacao = idFormacao.getNomeFormacao();
    }
}

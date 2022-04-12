/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author franklin.furtado
 */
@Entity
@Table(name = "catequese_centro_catequese")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class CatequeseCentroCatequese implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Integer id;
    
    @Basic(optional = false)
    @NotEmpty(message = "Informe o nome do centro de catequese")
    @Size(max = 45, message = "O nome do centro de catequese deve ter no máximo 45 caracteres")
    @Column(name = "nome_centro")
    private String nomeCentro;
    
    @Basic(optional = false)
    @NotEmpty(message = "Informe o nome do responsável do centro")
    @Size(max = 45, message = "O nome do responsável deve ter no máximo 45 caracteres")
    @Column(name = "nome_responsavel")
    private String nomeResponsavel;
    
    @Size(max = 45, message = "O telefone do responsável deve ter no máximo 45 caracteres")
    @Column(name = "telefone_responsavel1")
    private String telefoneResponsavel1;
    
    @Size(max = 45, message = "O telefone do responsável deve ter no máximo 45 caracteres")
    @Column(name = "telefone_responsavel2")
    private String telefoneResponsavel2;
    
    @JoinTable(name = "catequese_centro_catequese_etapa_catequese", joinColumns = {
        @JoinColumn(name = "id_centro_catequese", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_etapa_catequese", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<CatequeseEtapaCatequese> catequeseEtapaCatequeseList;
    
    @JoinTable(name = "catequese_centro_catequese_horario_catequese", joinColumns = {
        @JoinColumn(name = "id_centro_catequese", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_horario_catequese", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<CatequeseHorarioCatequese> catequeseHorarioCatequeseList;
    
    @JoinColumn(name = "id_catequista_responsavel_centro", referencedColumnName = "id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private CatequeseCatequista idCatequistaResponsavelCentro;
    
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private UtilitarioEndereco idEndereco;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id", fetch = FetchType.LAZY)
    private List<CatequeseSalaCatequese> catequeseSalaCatequeseList;
}

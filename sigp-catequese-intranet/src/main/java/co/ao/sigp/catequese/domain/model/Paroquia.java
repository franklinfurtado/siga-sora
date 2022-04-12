/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author franklin.furtado
 */
@Entity
@Table(name = "paroquia")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Paroquia implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @EqualsAndHashCode.Include
    private Integer id;
    
    @Basic(optional = false)
    @NotNull(message = "Informe o nome da paróquia")
    @Size(max = 45, message = "O nome da paróquia deve ter no máximo 45 caracteres")
    private String nomeParoquia;
    
    @NotNull(message = "Informe o nome do pároco")
    @Size(max = 45, message = "O nome do pároco deve ter no máximo 45 caracteres")
    private String nomeParoco;
    
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private UtilitarioEndereco idEndereco;

    public Paroquia(Integer id) {
        this.id = id;
    }

    public Paroquia() {
    }
    
    
}

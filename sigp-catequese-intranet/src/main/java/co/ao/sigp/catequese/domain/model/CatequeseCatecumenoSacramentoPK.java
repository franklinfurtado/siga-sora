/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author franklin.furtado
 */
@Embeddable
@Data
@AllArgsConstructor
public class CatequeseCatecumenoSacramentoPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_sacramento")
    private int idSacramento;

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_catequista")
    private int idCatequista;

    public CatequeseCatecumenoSacramentoPK() {
    }
}

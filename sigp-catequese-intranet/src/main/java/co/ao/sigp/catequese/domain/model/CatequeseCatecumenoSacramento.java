/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author franklin.furtado
 */
@Entity
@Table(name = "catequese_catecumeno_sacramento")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class CatequeseCatecumenoSacramento implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected CatequeseCatecumenoSacramentoPK catequeseCatecumenoSacramentoPK;

    @Column(name = "data_conclusao_sacramento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataConclusaoSacramento;

    private String paroquia;

    @JoinColumn(name = "catequeseCatecumeno", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CatequeseCatecumeno catequeseCatecumeno;

    @JoinColumn(name = "catequeseSacramento", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CatequeseSacramento catequeseSacramento;

    public CatequeseCatecumenoSacramento() {
    }

    public CatequeseCatecumenoSacramento(CatequeseCatecumenoSacramentoPK catequeseCatecumenoSacramentoPK) {
        this.catequeseCatecumenoSacramentoPK = catequeseCatecumenoSacramentoPK;
    }

    public CatequeseCatecumenoSacramento(int idSacramento, int idCatecumeno) {
        this.catequeseCatecumenoSacramentoPK = new CatequeseCatecumenoSacramentoPK(idSacramento, idCatecumeno);
    }
}

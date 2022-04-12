/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author franklinfurtado
 */
@Getter
@AllArgsConstructor
public enum CatequeseContactosGrauParentesco {
    PAI("Pai"),
    MAE("Mãe"),
    IRMAO("Irmã"),
    IRMA("Irmã"),
    TIO("Tio(a)"),
    TIA("Tia"),
    PRIMO("Primo"),
    AVO("Avó"),
    OUTRO("Outro");

    private final String value;
}

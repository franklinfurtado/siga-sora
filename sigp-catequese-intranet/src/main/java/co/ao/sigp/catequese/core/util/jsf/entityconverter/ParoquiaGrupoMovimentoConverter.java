package co.ao.sigp.catequese.core.util.jsf.entityconverter;

import javax.faces.convert.FacesConverter;

import co.ao.sigp.catequese.domain.model.ParoquiaGruposMovimentos;

/**
 *
 * @author franklinfurtado
 */
@FacesConverter("paroquiaGrupoMovimentoConverter")
public class ParoquiaGrupoMovimentoConverter extends EntityConverter<ParoquiaGruposMovimentos> {

    public ParoquiaGrupoMovimentoConverter() {
        super(ParoquiaGruposMovimentos.class, "id");
    }
}

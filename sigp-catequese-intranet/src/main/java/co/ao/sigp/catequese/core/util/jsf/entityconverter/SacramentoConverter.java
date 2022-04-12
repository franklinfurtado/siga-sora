package co.ao.sigp.catequese.core.util.jsf.entityconverter;


import javax.faces.convert.FacesConverter;

import co.ao.sigp.catequese.domain.model.CatequeseSacramento;

/**
 *
 * @author franklinfurtado
 */
@FacesConverter("sacramentoConverter")
public class SacramentoConverter extends EntityConverter<CatequeseSacramento> {

    public SacramentoConverter() {
        super(CatequeseSacramento.class, "id");
    }
}

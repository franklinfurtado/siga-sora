package co.ao.sigp.catequese.core.util.jsf.entityconverter;

import co.ao.sigp.catequese.domain.model.ParoquiaPastoral;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author franklinfurtado
 */
@FacesConverter("paroquiaPastoralConverter")
public class ParoquiaPastoralConverter extends EntityConverter<ParoquiaPastoral> {

    public ParoquiaPastoralConverter() {
        super(ParoquiaPastoral.class, "id");
    }
}

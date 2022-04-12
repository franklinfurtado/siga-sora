package co.ao.sigp.catequese.core.util.jsf.entityconverter;


import co.ao.sigp.catequese.domain.model.CatequeseCentroCatequese;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author franklinfurtado
 */
@FacesConverter("centroCatequeseConverter")
public class CentroCatequeseConverter extends EntityConverter<CatequeseCentroCatequese> {

    public CentroCatequeseConverter() {
        super(CatequeseCentroCatequese.class, "id");
    }
}

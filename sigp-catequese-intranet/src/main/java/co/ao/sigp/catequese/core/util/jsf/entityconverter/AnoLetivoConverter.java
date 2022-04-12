package co.ao.sigp.catequese.core.util.jsf.entityconverter;


import javax.faces.convert.FacesConverter;

import co.ao.sigp.catequese.domain.model.CatequeseAnoLetivo;

/**
 *
 * @author franklinfurtado
 */
@FacesConverter("anoLetivoConverter")
public class AnoLetivoConverter extends EntityConverter<CatequeseAnoLetivo> {

    public AnoLetivoConverter() {
        super(CatequeseAnoLetivo.class, "id");
    }
}

package co.ao.sigp.catequese.core.util.jsf.entityconverter;


import co.ao.sigp.catequese.domain.model.CatequeseEtapaCatequese;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author franklinfurtado
 */
@FacesConverter("etapaCatequeseConverter")
public class EtapaCatequeseConverter extends EntityConverter<CatequeseEtapaCatequese> {

    public EtapaCatequeseConverter() {
        super(CatequeseEtapaCatequese.class, "id");
    }
}

package co.ao.sigp.catequese.core.util.jsf.entityconverter;


import co.ao.sigp.catequese.domain.model.UtilitarioDistrito;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author franklinfurtado
 */
@FacesConverter("distritoConverter")
public class DistritoConverter extends EntityConverter<UtilitarioDistrito> {

    public DistritoConverter() {
        super(UtilitarioDistrito.class, "id");
    }
}

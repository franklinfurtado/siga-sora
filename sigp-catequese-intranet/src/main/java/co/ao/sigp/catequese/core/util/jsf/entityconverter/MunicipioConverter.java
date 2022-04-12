package co.ao.sigp.catequese.core.util.jsf.entityconverter;


import co.ao.sigp.catequese.domain.model.UtilitarioMunicipio;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author franklinfurtado
 */
@FacesConverter("municipioConverter")
public class MunicipioConverter extends EntityConverter<UtilitarioMunicipio> {

    public MunicipioConverter() {
        super(UtilitarioMunicipio.class, "id");
    }
}

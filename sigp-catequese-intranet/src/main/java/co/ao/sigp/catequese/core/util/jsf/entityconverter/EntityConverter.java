package co.ao.sigp.catequese.core.util.jsf.entityconverter;

import java.lang.reflect.InvocationTargetException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.apache.commons.beanutils.BeanUtils;

public class EntityConverter<T> implements Converter<Object> {

    private final Class<T> targetClass;
    private final String classID;

    public EntityConverter(Class<T> targetClass, String classID) {
        this.targetClass = targetClass;
        this.classID = classID;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String texto) {
        T object;
        if (texto.equals("")) {
            return null;
        }
        try {
            object = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.setProperty(object, classID, Integer.parseInt(texto));
            return object;
        } catch (NumberFormatException e) {
            return null;
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        try {
            if (object == null || object.equals("")) {
                return "";
            }
            Object value = BeanUtils.getProperty(object, classID);
            return value == null ? null : value.toString();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            return null;
        }
    }

}

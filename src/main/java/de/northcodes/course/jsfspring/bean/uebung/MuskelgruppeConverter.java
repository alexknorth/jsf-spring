package de.northcodes.course.jsfspring.bean.uebung;

import de.northcodes.course.jsfspring.model.Muskelgruppe;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "muskelgruppeConverter")
public class MuskelgruppeConverter implements Converter {

    @Override
    public Muskelgruppe getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return Muskelgruppe.byName(value);
            }
            catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Muskelgruppe."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o instanceof Muskelgruppe) {
            if (o != null) {
                return String.valueOf(((Muskelgruppe) o).getName());
            }
        }

        return null;
    }

}
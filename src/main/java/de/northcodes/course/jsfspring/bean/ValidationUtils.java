package de.northcodes.course.jsfspring.bean;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

public class ValidationUtils {

    public static boolean isFieldEmpty(Object value) {
        String fieldValue = (value == null) ? null : value.toString().trim();
        return fieldValue == null || fieldValue.isEmpty();
    }

    public static void validateRequiredField(FacesContext context, UIComponent component, Object value, String errorMessage) {
        if (isFieldEmpty(value)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
        }
    }
}

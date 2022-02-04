package de.northcodes.course.jsfspring.bean.uebung;

import de.northcodes.course.jsfspring.model.Muskelgruppe;
import de.northcodes.course.jsfspring.model.Uebung;
import de.northcodes.course.jsfspring.service.UebungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
@ManagedBean
public class UebungList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UebungService uebungService;

    public List<Uebung> getUebungList() {
        return this.uebungService.getUebungList();
    }
}

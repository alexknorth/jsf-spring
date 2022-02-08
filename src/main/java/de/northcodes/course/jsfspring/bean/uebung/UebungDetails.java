package de.northcodes.course.jsfspring.bean.uebung;

import javax.faces.view.ViewScoped;

import de.northcodes.course.jsfspring.model.Muskelgruppe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.northcodes.course.jsfspring.model.Uebung;
import de.northcodes.course.jsfspring.service.UebungService;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.ManagedBean;

@Component
@ViewScoped
@ManagedBean
public class UebungDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UebungService uebungService;

    private Long uebungId;

    private Uebung uebung;

    public void onLoad() {
        if (this.uebungId > 0) {
            this.uebung = this.uebungService.getUebungById(this.uebungId);
        } else {
            this.uebung = new Uebung();
        }
    }

    public void save() {
        this.uebungService.saveUebung(this.uebung);
    }

    public List<Muskelgruppe> completeMuskelgruppe(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Muskelgruppe> muskelgruppeList = Arrays.asList(Muskelgruppe.values());

        return muskelgruppeList
                .stream()
                .filter(m -> m.getName().toLowerCase().contains(queryLowerCase))
                .collect(Collectors.toList());
    }

    public Long getUebungId() {
        return uebungId;
    }

    public void setUebungId(Long uebungId) {
        this.uebungId = uebungId;
    }

    public Uebung getUebung() {
        return this.uebung;
    }

    public void setUebung(Uebung uebung) {
        this.uebung = uebung;
    }
}

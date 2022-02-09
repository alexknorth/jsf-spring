package de.northcodes.course.jsfspring.bean.uebung;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
public class UebungDetail implements Serializable {

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
        this.addMessage(FacesMessage.SEVERITY_INFO, "Speichern erfolgreich", "Die Übung '" + this.uebung.getName() + "' wurde erfolgreich gespeichert.");
    }

    public List<Muskelgruppe> completeMuskelgruppe(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Muskelgruppe> muskelgruppeList = Arrays.asList(Muskelgruppe.values());

        return muskelgruppeList
                .stream()
                .filter(m -> m.getName().toLowerCase().contains(queryLowerCase))
                .collect(Collectors.toList());
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
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

package de.northcodes.course.jsfspring.bean.uebung;

import javax.faces.view.ViewScoped;

import de.northcodes.course.jsfspring.model.Muskelgruppe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.northcodes.course.jsfspring.model.Uebung;
import de.northcodes.course.jsfspring.service.UebungService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.ManagedBean;

@Component
@ViewScoped
@ManagedBean
public class UebungDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Muskelgruppe> muskelgruppeList = new ArrayList<>();

    @Autowired
    private UebungService uebungService;

    private long uebungId;

    private Uebung uebung;

    public void onload() {
        this.uebung = this.uebungService.getUebungById(this.uebungId);
        this.muskelgruppeList = this.uebung.getBeanpruchteMuskelgruppeList();
    }

    public void save() {
        System.out.println(this.uebung.getName());
    }

    public List<Muskelgruppe> completeMuskelgruppe(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Muskelgruppe> muskelgruppeList = Arrays.asList(Muskelgruppe.values());

        System.out.println("Muskelgruppe Size: " + this.muskelgruppeList.size());
        return muskelgruppeList.stream().filter(m -> m.getName().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }

    public List<Muskelgruppe> getMuskelgruppeList() {
        return muskelgruppeList;
    }

    public void setMuskelgruppeList(List<Muskelgruppe> muskelgruppeList) {
        this.muskelgruppeList = muskelgruppeList;
    }

    public long getUebungId() {
        return uebungId;
    }

    public void setUebungId(long uebungId) {
        this.uebungId = uebungId;
    }

    public Uebung getUebung() {
        return this.uebung;
    }

    public void setUebung(Uebung uebung) {
        this.uebung = uebung;
    }
}

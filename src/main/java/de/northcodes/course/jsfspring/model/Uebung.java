package de.northcodes.course.jsfspring.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;


@Entity
public final class Uebung extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description", nullable = false)
    private String beschreibung;
    
    @Column(name = "muskelgruppe", nullable = false)
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Muskelgruppe.class, fetch = FetchType.EAGER)
    private List<Muskelgruppe> beanpruchteMuskelgruppeList;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "uebung", cascade = CascadeType.ALL)
    private List<Meilenstein> meilensteinList = new ArrayList<>();

    public Uebung() {}
    
    public Uebung(String name, String beschreibung, List<Muskelgruppe> beanspruchteMuskelgruppeList) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.beanpruchteMuskelgruppeList = beanspruchteMuskelgruppeList;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public List<Muskelgruppe> getBeanpruchteMuskelgruppeList() {
        return beanpruchteMuskelgruppeList;
    }

    public List<Meilenstein> getMeilensteinList() {
        return meilensteinList;
    }

    public String getMuskelgruppenStr() {
        List<String> muskelgruppenAsString = this.beanpruchteMuskelgruppeList
                .stream()
                .map(Muskelgruppe::getName)
                .collect(Collectors.toList());

        return String.join(", ", muskelgruppenAsString);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public void setBeanpruchteMuskelgruppeList(List<Muskelgruppe> beanpruchteMuskelgruppeList) {
        this.beanpruchteMuskelgruppeList = beanpruchteMuskelgruppeList;
    }

    public void setMeilensteinList(List<Meilenstein> meilensteinList) {
        this.meilensteinList = meilensteinList;
    }
}

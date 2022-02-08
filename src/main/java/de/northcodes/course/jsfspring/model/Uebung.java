package de.northcodes.course.jsfspring.model;

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
    
    @Column(name = "image_name")
    private String imageName;

    @Column(name = "muskelgruppe", nullable = false)
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Muskelgruppe.class, fetch = FetchType.EAGER)
    private List<Muskelgruppe> beanpruchteMuskelgruppeList;

    @OneToMany(mappedBy = "uebung", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Meilenstein> meilensteinList = new ArrayList<>();

    public Uebung() {}
    
    public Uebung(String name, String beschreibung, List<Muskelgruppe> beanspruchteMuskelgruppeList, String imageName) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.beanpruchteMuskelgruppeList = beanspruchteMuskelgruppeList;
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public String getShortDescription() {
        int i = beschreibung.indexOf('.');
        if (i >= 0 && i < 100) {
            return beschreibung.substring(0, i + 1);
        } else {
            return beschreibung.substring(0, Math.min(beschreibung.length(), 100)) + "...";
        }
    }

    public String getImageName() {
        return imageName;
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

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setBeanpruchteMuskelgruppeList(List<Muskelgruppe> beanpruchteMuskelgruppeList) {
        this.beanpruchteMuskelgruppeList = beanpruchteMuskelgruppeList;
    }

    public void setMeilensteinList(List<Meilenstein> meilensteinList) {
        this.meilensteinList = meilensteinList;
    }

    @Override
    public String toString() {
    	return "Product ID: " + this.getId() + ", name: " + this.getName() +", description: " + this.getBeschreibung();
    }
}

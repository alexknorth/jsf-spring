package de.northcodes.course.jsfspring.bean;

import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.northcodes.course.jsfspring.model.Uebung;
import de.northcodes.course.jsfspring.service.UebungService;

import java.io.Serializable;
import javax.annotation.ManagedBean;

@Component
@ViewScoped
@ManagedBean
public class UebungDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UebungService uebungService;

    private long uebungId;

    private Uebung uebung;


    public void onload() {
        this.uebung = this.uebungService.getUebungById(this.uebungId);
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
}

package de.northcodes.course.jsfspring.service;

import java.util.List;

import de.northcodes.course.jsfspring.model.Uebung;

public interface UebungService {

    List<Uebung> getUebungList();

    Uebung getUebungById(long uebungId);

    Uebung getUebungByName(String name);

    void saveUebung(Uebung uebung);

}

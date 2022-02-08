package de.northcodes.course.jsfspring.service;

import de.northcodes.course.jsfspring.model.Meilenstein;

import java.util.List;

public interface MeilensteinService {

    void saveMeilenstein(Meilenstein meilenstein);

    List<Meilenstein> getMeilensteinListByUebungId(long uebungId);

}

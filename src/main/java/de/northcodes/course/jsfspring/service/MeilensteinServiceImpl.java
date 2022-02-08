package de.northcodes.course.jsfspring.service;

import de.northcodes.course.jsfspring.model.Meilenstein;
import de.northcodes.course.jsfspring.persistence.MeilensteinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeilensteinServiceImpl implements MeilensteinService {

    @Autowired
    private MeilensteinRepository meilensteinRepository;

    @Override
    public void saveMeilenstein(Meilenstein meilenstein) {
        this.meilensteinRepository.save(meilenstein);
    }

    @Override
    public List<Meilenstein> getMeilensteinListByUebungId(long uebungId) {
        return this.meilensteinRepository.findAllByUebungId(uebungId);
    }

}

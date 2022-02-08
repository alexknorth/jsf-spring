package de.northcodes.course.jsfspring.service;

import de.northcodes.course.jsfspring.model.Uebung;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.northcodes.course.jsfspring.persistence.UebungRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class UebungServiceImpl implements UebungService {

	private static final Logger log = LoggerFactory.getLogger(UebungServiceImpl.class);

	@Autowired
	private UebungRepository uebungRepository;

	@Override
	public List<Uebung> getUebungList() {
		return StreamSupport
				.stream(this.uebungRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	@Override
	public Uebung getUebungById(long id) {
		return this.uebungRepository.findById(id).orElse(null);
	}

	@Override
	public void saveUebung(Uebung uebung) {
		this.uebungRepository.save(uebung);
	}

}

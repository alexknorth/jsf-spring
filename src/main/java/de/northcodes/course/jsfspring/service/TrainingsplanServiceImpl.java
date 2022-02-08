package de.northcodes.course.jsfspring.service;

import de.northcodes.course.jsfspring.model.Trainingsplan;
import de.northcodes.course.jsfspring.persistence.TrainingsplanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TrainingsplanServiceImpl implements TrainingsplanService {

    @Autowired
    private TrainingsplanRepository trainingsplanRepository;

    @Override
    public List<Trainingsplan> getTrainingsplanList() {
        return StreamSupport
                .stream(this.trainingsplanRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Trainingsplan getTrainingsplanById(long id) {
        return this.trainingsplanRepository.findById(id).orElse(null);
    }

    @Override
    public Trainingsplan saveTrainingsplan(Trainingsplan trainingsplan) {
        return this.trainingsplanRepository.save(trainingsplan);
    }

}

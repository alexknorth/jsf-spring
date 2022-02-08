package de.northcodes.course.jsfspring.service;

import de.northcodes.course.jsfspring.model.Trainingsplan;

import java.util.List;

public interface TrainingsplanService {

    List<Trainingsplan> getTrainingsplanList();

    Trainingsplan getTrainingsplanById(long id);

    Trainingsplan saveTrainingsplan(Trainingsplan trainingsplan);
}

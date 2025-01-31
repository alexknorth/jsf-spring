package de.northcodes.course.jsfspring.service;
import de.northcodes.course.jsfspring.model.Workout;
import de.northcodes.course.jsfspring.persistence.UserRepository;
import de.northcodes.course.jsfspring.persistence.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    @Autowired
    WorkoutRepository workoutRepository;

    public void saveWorkout(Workout workout) {
        workoutRepository.save(workout);
    }

    public List<Workout> getCompletedWorkouts() {
        return workoutRepository.findAllByOrderByStartedAtAsc();
    }

}


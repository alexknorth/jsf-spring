package de.northcodes.course.jsfspring.bean;

import de.northcodes.course.jsfspring.model.*;
import de.northcodes.course.jsfspring.persistence.SetRepository;
import de.northcodes.course.jsfspring.persistence.WorkoutExerciseRepository;
import de.northcodes.course.jsfspring.persistence.WorkoutRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Component
@ManagedBean
public class WorkoutBean implements Serializable {
    private static Workout workout;
    private List<WorkoutExercise> exercises;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private WorkoutExerciseRepository workoutExerciseRepository;

    @Autowired
    private SetRepository setRepository;

    @Autowired
    private UserManager userManager;

    Logger log = LoggerFactory.getLogger(WorkoutBean.class);

    public WorkoutBean() {
        workout = new Workout();
        workout.setStartedAt(LocalDateTime.now());
        exercises = new ArrayList<>();
    }

    @Transactional
    public void initializeWorkout(Template template) {
        workout = new Workout();
        workout.setTemplate(template);
        workout.setUser(userManager.getCurrentUser());
        workout.setStartedAt(LocalDateTime.now());
        this.log.info("test");
        // Find the latest workout with the given template
        Workout latestWorkout = workoutRepository.findTopByTemplateOrderByIdDesc(template);

        if (latestWorkout != null) {
            exercises = workoutExerciseRepository.findByWorkoutId(latestWorkout.getId());
            for (WorkoutExercise exercise : exercises) {
                List<Set> sets = setRepository.findByWorkoutExerciseId(exercise.getId());
                exercise.getSets().clear();
                exercise.getSets().addAll(sets);
            }
            for (WorkoutExercise exercise : exercises) {
                log.info("Exercise: {}", exercise.getExercise().getName());
            }
        } else {
            exercises = new ArrayList<>();
        }
    }

    public void addExercise(Exercise exercise) {
        WorkoutExercise workoutExercise = new WorkoutExercise();
        workoutExercise.setWorkout(workout);
        workoutExercise.setExercise(exercise);
        workoutExercise.setSets(new ArrayList<>());
        exercises.add(workoutExercise);
    }

    public void addSet(WorkoutExercise workoutExercise) {
        Set newSet = new Set();
        newSet.setWorkoutExercise(workoutExercise);
        newSet.setWeight(0);
        newSet.setReps(0);
        newSet.setRestTime(60);
        workoutExercise.getSets().add(newSet);
    }

    @Transactional
    public String finishWorkout() {
        log.info("Finishing workout");
        workout.setFinishedAt(LocalDateTime.now());
        workoutRepository.save(workout);
        return "workout?faces-redirect=true";
    }

    public void cancelWorkout() {
        workout = new Workout();
        workout.setStartedAt(LocalDateTime.now());
        exercises.clear();
    }

    public static void setTemplate(Template template){
        workout.setTemplate(template);
    }

    public List<WorkoutExercise> getExercises() {
        this.log.info("exercises: " + exercises);
        return exercises;
    }

    public Workout getWorkout() {
        return workout;
    }
}

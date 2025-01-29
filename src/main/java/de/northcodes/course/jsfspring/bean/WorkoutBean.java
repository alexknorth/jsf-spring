package de.northcodes.course.jsfspring.bean;

import de.northcodes.course.jsfspring.model.*;
import de.northcodes.course.jsfspring.persistence.WorkoutExerciseRepository;
import de.northcodes.course.jsfspring.persistence.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Component
public class WorkoutBean implements Serializable {
    private static Workout workout;
    private List<WorkoutExercise> exercises;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private WorkoutExerciseRepository workoutExerciseRepository;

    public WorkoutBean() {
        workout = new Workout();
        workout.setStartedAt(LocalDateTime.now());
        exercises = new ArrayList<>();
    }

    public void initializeWorkout(Template template) {
        workout = new Workout();
        workout.setTemplate(template);
        workout.setStartedAt(LocalDateTime.now());

        // Find the latest workout with the given template
        Workout latestWorkout = workoutRepository.findTopByTemplateOrderByIdDesc(template);

        if (latestWorkout != null) {
            exercises = workoutRepository.findWorkoutExercisesByWorkoutId(latestWorkout.getId());
            for (WorkoutExercise exercise : exercises) {
                List<Set> sets = workoutExerciseRepository.findSetsByWorkoutExerciseId(exercise.getId());
                exercise.setSets(sets);
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

    public void finishWorkout() {
        workout.setFinishedAt(LocalDateTime.now());
        // Speichern in der Datenbank wäre hier nötig
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
        return exercises;
    }

    public Workout getWorkout() {
        return workout;
    }
}

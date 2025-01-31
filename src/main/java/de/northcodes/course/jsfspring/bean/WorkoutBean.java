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

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@SessionScoped
@Component
@ManagedBean
public class WorkoutBean implements Serializable {

    private String timer;
    private Timer timerTask;

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

    @PostConstruct
    public void init() {
        startTimer();
    }

    @Transactional
    public void initializeWorkout(Template template) {
        workout = new Workout();
        workout.setTemplate(template);
        workout.setUser(userManager.getCurrentUser());
        workout.setStartedAt(LocalDateTime.now());
        this.log.info("Initializing workout for template: {}", template.getName());

        // Find the latest workout with the given template
        Workout latestWorkout = workoutRepository.findTopByTemplateOrderByIdDesc(template);

        if (latestWorkout != null) {
            this.log.info("Latest workout found with ID: {}", latestWorkout.getId());
            exercises = workoutExerciseRepository.findByWorkoutId(latestWorkout.getId());
            this.log.info("Number of exercises found: {}", exercises.size());
            for (WorkoutExercise exercise : exercises) {
                this.log.info("Exercise found: {}", exercise.getExercise().getName());
                List<Set> sets = setRepository.findByWorkoutExerciseId(exercise.getId());
                this.log.info("Number of sets found for exercise {}: {}", exercise.getExercise().getName(), sets.size());
                exercise.getSets().clear();
                exercise.getSets().addAll(sets);
            }
        } else {
            this.log.info("No previous workout found for template: {}", template.getName());
            exercises = new ArrayList<>();
        }
        startTimer();
    }

    private void startTimer() {
        timerTask = new Timer();
        timerTask.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateTimer();
            }
        }, 0, 1000);
    }

    private void updateTimer() {
        Duration duration = Duration.between(workout.getStartedAt(), LocalDateTime.now());
        long minutes = duration.toMinutes();
        long seconds = duration.getSeconds() % 60;
        timer = String.format("%02d:%02d", minutes, seconds);
    }

    public void addExercise(Exercise exercise) {
        this.log.info("aufgerufen addExercise");
        WorkoutExercise workoutExercise = new WorkoutExercise();
        workoutExercise.setWorkout(workout);
        workoutExercise.setExercise(exercise);

        // Initialize the sets list with a single empty Set element
        Set initialSet = new Set();
        initialSet.setWorkoutExercise(workoutExercise);
        initialSet.setWeight(0);
        initialSet.setReps(0);
        initialSet.setRestTime(60);
        List<Set> sets = new ArrayList<>();
        sets.add(initialSet);
        workoutExercise.setSets(sets);

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

        // Ensure the user is set
        User currentUser = userManager.getCurrentUser();
        if (currentUser == null) {
            log.error("Current user is null. Cannot finish workout.");
            throw new IllegalStateException("Current user is null. Cannot finish workout.");
        }
        workout.setUser(currentUser);

        // Save the workout first
        workout = workoutRepository.save(workout);

        List<WorkoutExercise> managedExercises = new ArrayList<>();
        for (WorkoutExercise exercise : exercises) {
            exercise.setWorkout(workout);
            for (Set set : exercise.getSets()) {
                set.setWorkoutExercise(exercise);
            }
            managedExercises.add(workoutExerciseRepository.save(exercise)); // Save and manage the exercise
        }
        workout.setWorkoutExercises(managedExercises);
        workoutRepository.save(workout);
        cleanup();
        return "workout?faces-redirect=true";
    }

    @Transactional
    public String cancelWorkout() {
        log.info("Cancelling workout");
        workout = new Workout();
        workout.setStartedAt(LocalDateTime.now());
        exercises.clear();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        cleanup();
        return "workout?faces-redirect=true";
    }

    @PreDestroy
    public void cleanup() {
        if (timerTask != null) {
            timerTask.cancel();
        }
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

    public String getTimer() {
        return timer;
    }
}

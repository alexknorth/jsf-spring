package de.northcodes.course.jsfspring.model;

import de.northcodes.course.jsfspring.persistence.WorkoutExerciseRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "workouts")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "template_id")
    private Template template;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<WorkoutExercise> workoutExercises;

    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public List<WorkoutExercise> getWorkoutExercises() {
        return workoutExercises;
    }

    public void setWorkoutExercises(List<WorkoutExercise> workoutExercises) {
        this.workoutExercises = workoutExercises;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public String getDate() {
        if (finishedAt != null) {
            return finishedAt.toLocalDate().toString();
        }
        return null;
    }

    public String getTotalTime() {
        if (startedAt != null && finishedAt != null) {
            Duration duration = Duration.between(startedAt, finishedAt);
            long minutes = duration.toMinutes();
            long seconds = duration.minusMinutes(minutes).getSeconds();
            return String.format("%02d:%02d", minutes, seconds);
        }
        return null;
    }

    public int getTotalWeight() {
        int totalWeight = 0;
        for (WorkoutExercise exercise : workoutExercises) {
            for (Set set : exercise.getSets()) {
                totalWeight += (int) (set.getWeight() * set.getReps());
            }
        }
        return totalWeight;
    }
}
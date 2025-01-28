package de.northcodes.course.jsfspring.bean;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;

import de.northcodes.course.jsfspring.JsfSpringApplication;
import de.northcodes.course.jsfspring.model.Exercise;
import de.northcodes.course.jsfspring.persistence.ExerciseRepository;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class ExerciseBean implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(ExerciseBean.class);

    @Autowired
    private ExerciseRepository exerciseRepository;

    private List<Exercise> exercises;
    private String searchQuery;

    @PostConstruct
    public void init() {
        exercises = (List<Exercise>) exerciseRepository.findAll();
    }

    public void reset() {
        PrimeFaces.current().resetInputs("search");
    }

    public void searchExercises(AjaxBehaviorEvent event) {
        this.log.info("searchExercises: " + searchQuery);
        if (searchQuery == null || searchQuery.isBlank()) {
            exercises = (List<Exercise>) exerciseRepository.findAll();
        } else {
            exercises = exerciseRepository.findByNameContainingIgnoreCase(searchQuery);
        }
    }

    public List<Exercise> getExercises() {
        return exercises;
    }


    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
}
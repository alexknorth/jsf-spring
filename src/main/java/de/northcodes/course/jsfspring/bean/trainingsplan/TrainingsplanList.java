package de.northcodes.course.jsfspring.bean.trainingsplan;

import de.northcodes.course.jsfspring.model.Trainingsplan;
import de.northcodes.course.jsfspring.service.TrainingsplanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
@ManagedBean
public class TrainingsplanList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private TrainingsplanService trainingsplanService;

    public List<Trainingsplan> getTrainingsplanList() {
        return this.trainingsplanService.getTrainingsplanList();
    }
}

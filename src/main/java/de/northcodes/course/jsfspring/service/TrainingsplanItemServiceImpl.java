package de.northcodes.course.jsfspring.service;

import de.northcodes.course.jsfspring.model.TrainingsplanItem;
import de.northcodes.course.jsfspring.persistence.TrainingsplanItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingsplanItemServiceImpl implements TrainingsplanItemService {

    @Autowired
    private TrainingsplanItemRepository trainingsplanItemRepository;

    @Override
    public void saveItem(TrainingsplanItem item) {
        this.trainingsplanItemRepository.save(item);
    }

}

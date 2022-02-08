package de.northcodes.course.jsfspring.bean.trainingsplan;

import de.northcodes.course.jsfspring.model.Trainingsplan;
import de.northcodes.course.jsfspring.model.TrainingsplanItem;
import de.northcodes.course.jsfspring.model.Uebung;
import de.northcodes.course.jsfspring.service.TrainingsplanService;
import de.northcodes.course.jsfspring.service.UebungService;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ViewScoped
@Component
@ManagedBean
public class TrainingsplanDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long trainingsplanId;

	private Trainingsplan trainingsplan;

	private TrainingsplanItem trainingsplanItem = new TrainingsplanItem();

	@Autowired
	private TrainingsplanService trainingsplanService;

	@Autowired
	private UebungService uebungService;

	public void onLoad() {
		if (this.trainingsplanId > 0) {
			this.trainingsplan = this.trainingsplanService.getTrainingsplanById(this.trainingsplanId);
		} else {
			this.trainingsplan = new Trainingsplan();
		}
	}

	public void save() {
		Trainingsplan saved = this.trainingsplanService.saveTrainingsplan(this.trainingsplan);
		this.trainingsplan = saved;
	}

	public void saveItem() {
		this.trainingsplanItem.setTrainingsplan(this.trainingsplan);
		this.trainingsplan.getTrainingsplanItemList().add(this.trainingsplanItem);

		this.trainingsplanItem = new TrainingsplanItem();
	}

	public void deleteItem(String uebungName) {
		this.trainingsplan.getTrainingsplanItemList().removeIf(i -> i.getUebung().getName().equals(uebungName));
		PrimeFaces.current().ajax().update("list");
	}

	public List<String> completeText(String query) {
		String queryLowerCase = query.toLowerCase();
		List<String> uebungStrList = new ArrayList<>();
		List<Uebung> uebungList = this.uebungService.getUebungList();
		for (Uebung uebung : uebungList) {
			uebungStrList.add(uebung.getName());
		}

		return uebungStrList.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
	}

	public Long getTrainingsplanId() {
		return trainingsplanId;
	}

	public void setTrainingsplanId(Long trainingsplanId) {
		this.trainingsplanId = trainingsplanId;
	}

	public Trainingsplan getTrainingsplan() {
		return trainingsplan;
	}

	public void setTrainingsplan(Trainingsplan trainingsplan) {
		this.trainingsplan = trainingsplan;
	}

	public TrainingsplanItem getTrainingsplanItem() {
		return trainingsplanItem;
	}

	public void setTrainingsplanItem(TrainingsplanItem trainingsplanItem) {
		this.trainingsplanItem = trainingsplanItem;
	}
}

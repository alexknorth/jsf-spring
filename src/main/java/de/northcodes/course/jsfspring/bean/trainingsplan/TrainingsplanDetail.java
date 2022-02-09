package de.northcodes.course.jsfspring.bean.trainingsplan;

import de.northcodes.course.jsfspring.model.Meilenstein;
import de.northcodes.course.jsfspring.model.Trainingsplan;
import de.northcodes.course.jsfspring.model.TrainingsplanItem;
import de.northcodes.course.jsfspring.model.Uebung;
import de.northcodes.course.jsfspring.service.MeilensteinService;
import de.northcodes.course.jsfspring.service.TrainingsplanItemService;
import de.northcodes.course.jsfspring.service.TrainingsplanService;
import de.northcodes.course.jsfspring.service.UebungService;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ViewScoped
@Component
@ManagedBean
public class TrainingsplanDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long trainingsplanId;

	private Trainingsplan trainingsplan;

	private TrainingsplanItem trainingsplanItem = new TrainingsplanItem();

	private long newGewicht = 0;

	@Autowired
	private TrainingsplanService trainingsplanService;

	@Autowired
	private UebungService uebungService;

	@Autowired
	private MeilensteinService meilensteinService;

	@Autowired
	private TrainingsplanItemService trainingsplanItemService;

	public void onLoad() {
		if (this.trainingsplanId > 0) {
			this.trainingsplan = this.trainingsplanService.getTrainingsplanById(this.trainingsplanId);
		} else {
			this.trainingsplan = new Trainingsplan();
		}
	}

	public void savePlan() {
		for (TrainingsplanItem item : this.trainingsplan.getTrainingsplanItemList()) {
			if (item.getId() == null) {
				this.setMeilenstein(item);
			}
		}

		Trainingsplan saved = this.trainingsplanService.saveTrainingsplan(this.trainingsplan);
		this.trainingsplan = saved;

		this.addMessage(FacesMessage.SEVERITY_INFO, "Speichern erfolgreich.", "Der Trainingsplan '" + this.trainingsplan.getName() + "' wurde erfolgreich gespeichert.");
	}

	public void saveItem() {
		boolean isOk = this.validateItem();

		if (isOk) {
			this.trainingsplanItem.setTrainingsplan(this.trainingsplan);
			this.trainingsplan.getTrainingsplanItemList().add(this.trainingsplanItem);

			this.trainingsplanItem = new TrainingsplanItem();
		}
	}

	public void deleteItem(String uebungName) {
		this.trainingsplan.getTrainingsplanItemList().removeIf(i -> i.getUebung().getName().equals(uebungName));
		PrimeFaces.current().ajax().update("list");
	}

	public void setMeilenstein(TrainingsplanItem item) {
		Meilenstein meilenstein = new Meilenstein();
		meilenstein.setUebung(item.getUebung());
		meilenstein.setGewicht(item.getGewicht());
		meilenstein.setAnzahlSets(item.getAnzahlSets());
		meilenstein.setAnzahlReps(item.getAnzahlReps());

		this.meilensteinService.saveMeilenstein(meilenstein);
	}

	public void setMeilensteinAndUpdateGewicht(TrainingsplanItem item) {
		this.setMeilenstein(item);
		this.trainingsplanItemService.saveItem(item);

		this.addMessage(FacesMessage.SEVERITY_INFO, "Speichern erfolgreich.", "Das Gewicht wurde aktualisiert und ein Meilenstein für die Übung '" + item.getUebung().getName() + "' gesetzt.");
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

	private boolean validateItem() {
		boolean isOk = true;

		boolean uebungAlreadyExists = this.trainingsplan.getTrainingsplanItemList()
				.stream()
				.anyMatch(item -> item.getUebung().getId().equals(this.trainingsplanItem.getUebung().getId()));

		if (this.trainingsplanItem.getUebung() == null) {
			isOk = false;
			this.addMessage(FacesMessage.SEVERITY_ERROR, "Keine Übung ausgewählt.", "Es ist keine Übung ausgewählt. Bitte eine auswählen.");
		} else if (uebungAlreadyExists) {
			isOk = false;
			this.addMessage(FacesMessage.SEVERITY_ERROR, "Übung existiert bereits.", "Die Übung befindet sich bereits in der Liste. Bitte eine andere auswählen.");
		}

		return isOk;
	}

	public void validateEnddatum(FacesContext context, UIComponent component, Object value) {
		Date enddatum = (Date) value;
		if (enddatum.before(this.trainingsplan.getStartDatum())) {
			this.addMessage(FacesMessage.SEVERITY_ERROR, "Enddatum vor Startdatum", "Das Enddatum darf nicht vor dem Startdatum liegen.");
		}
	}

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().
				addMessage(null, new FacesMessage(severity, summary, detail));
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

	public long getNewGewicht() {
		return newGewicht;
	}

	public void setNewGewicht(long newGewicht) {
		this.newGewicht = newGewicht;
	}
}

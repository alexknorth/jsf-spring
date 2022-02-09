package de.northcodes.course.jsfspring.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Trainingsplan extends AbstractEntity {

	private String name;

	@Column(name = "start_datum", nullable = false)
	private Date startDatum;

	@Column(name = "end_datum", nullable = false)
	private Date endDatum;

	@OneToMany(mappedBy = "trainingsplan", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<TrainingsplanItem> trainingsplanItemList;

	public Trainingsplan() {
		this.trainingsplanItemList = new HashSet<>();
	}

	public Trainingsplan(String name, Date startDatum, Date endDatum, Set<TrainingsplanItem> trainingsplanItemList) {
		this.name = name;
		this.startDatum = startDatum;
		this.endDatum = endDatum;
		this.trainingsplanItemList = trainingsplanItemList;
	}

	public Trainingsplan(String name, Date startDatum, Set<TrainingsplanItem> trainingsplanItemList) {
		this.name = name;
		this.startDatum = startDatum;
		this.trainingsplanItemList = trainingsplanItemList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDatum() {
		return startDatum;
	}

	public void setStartDatum(Date startDatum) {
		this.startDatum = startDatum;
	}

	public Date getEndDatum() {
		return endDatum;
	}

	public void setEndDatum(Date endDatum) {
		this.endDatum = endDatum;
	}

	public Set<TrainingsplanItem> getTrainingsplanItemList() {
		return trainingsplanItemList;
	}

	public void setTrainingsplanItemList(Set<TrainingsplanItem> trainingsplanItemList) {
		this.trainingsplanItemList = trainingsplanItemList;
	}
}


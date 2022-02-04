package de.northcodes.course.jsfspring.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Trainingsplan extends AbstractEntity {

	private String name;

	@Column(name = "start_datum", nullable = false)
	private LocalDate startDatum;

	@Column(name = "end_datum")
	private LocalDate endDatum;

	@OneToMany(mappedBy = "trainingsplan", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<TrainingsplanItem> trainingsplanItemList;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_user", referencedColumnName = "id", nullable = false)
	private User user;

	protected Trainingsplan() {
	}

	public Trainingsplan(String name, LocalDate startDatum, LocalDate endDatum, List<TrainingsplanItem> trainingsplanItemList) {
		this.name = name;
		this.startDatum = startDatum;
		this.endDatum = endDatum;
		this.trainingsplanItemList = trainingsplanItemList;
	}

	public Trainingsplan(String name, LocalDate startDatum, List<TrainingsplanItem> trainingsplanItemList) {
		this.name = name;
		this.startDatum = startDatum;
		this.trainingsplanItemList = trainingsplanItemList;
	}

	public String getName() {
		return name;
	}

	public LocalDate getStartDatum() {
		return startDatum;
	}

	public LocalDate getEndDatum() {
		return endDatum;
	}

	public List<TrainingsplanItem> getPlanItemList() {
		return trainingsplanItemList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

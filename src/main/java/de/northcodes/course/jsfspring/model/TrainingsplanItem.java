package de.northcodes.course.jsfspring.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "plan_item")
public class TrainingsplanItem extends AbstractEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn (name = "fk_uebung", referencedColumnName = "id", nullable = false)
	private Uebung uebung;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_plan", referencedColumnName = "id", nullable = false)
	private Trainingsplan trainingsplan;

	private int anzahlSets;
	private int anzahlReps;
	private double gewicht;
    
	protected TrainingsplanItem() {
	}

	public TrainingsplanItem(Trainingsplan trainingsplan, Uebung uebung, int anzahlSets, int anzahlReps, double gewicht) {
		this.trainingsplan = trainingsplan;
		this.uebung = uebung;
		this.anzahlSets = anzahlSets;
		this.anzahlReps = anzahlReps;
		this.gewicht = gewicht;
	}

	public Uebung getUebung() {
		return uebung;
	}

	public int getAnzahlSets() {
		return anzahlSets;
	}

	public int getAnzahlReps() {
		return anzahlReps;
	}

	public double getGewicht() {
		return gewicht;
	}

	public Trainingsplan getPlan() {
		return trainingsplan;
	}
}

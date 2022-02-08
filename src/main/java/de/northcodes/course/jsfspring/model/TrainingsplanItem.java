package de.northcodes.course.jsfspring.model;

import javax.persistence.*;

@Entity
@Table(name = "plan_item")
public class TrainingsplanItem extends AbstractEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn (name = "fk_uebung", referencedColumnName = "id", nullable = false)
	private Uebung uebung;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "plan_id", referencedColumnName = "id", nullable = false)
	private Trainingsplan trainingsplan;

	private int anzahlSets;
	private int anzahlReps;
	private double gewicht;
    
	public TrainingsplanItem() {
		this.uebung = new Uebung();
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

	public void setUebung(Uebung uebung) {
		this.uebung = uebung;
	}

	public Trainingsplan getTrainingsplan() {
		return trainingsplan;
	}

	public void setTrainingsplan(Trainingsplan trainingsplan) {
		this.trainingsplan = trainingsplan;
	}

	public int getAnzahlSets() {
		return anzahlSets;
	}

	public void setAnzahlSets(int anzahlSets) {
		this.anzahlSets = anzahlSets;
	}

	public int getAnzahlReps() {
		return anzahlReps;
	}

	public void setAnzahlReps(int anzahlReps) {
		this.anzahlReps = anzahlReps;
	}

	public double getGewicht() {
		return gewicht;
	}

	public void setGewicht(double gewicht) {
		this.gewicht = gewicht;
	}
}

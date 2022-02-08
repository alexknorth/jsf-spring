package de.northcodes.course.jsfspring.model;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Meilenstein extends AbstractEntity{
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_uebung",referencedColumnName = "id", nullable = false)
	private Uebung uebung;
	
	private double gewicht;
	private int anzahlSets;
	private int anzahlReps;

	@CreationTimestamp
	private Date datum;

	public Meilenstein(TrainingsplanItem trainingsplanItem, int anzahlSets, int anzahlReps, double gewicht) {
		this.uebung = trainingsplanItem.getUebung();
		this.anzahlSets = anzahlSets;
		this.anzahlReps = anzahlReps;
		this.gewicht = gewicht;
	}

	public Meilenstein() {
	}

	public Uebung getUebung() {
		return uebung;
	}

	public double getGewicht() {
		return gewicht;
	}

	public int getAnzahlSets() {
		return anzahlSets;
	}

	public int getAnzahlReps() {
		return anzahlReps;
	}

	public Date getDatum() {
		return datum;
	}

	public void setUebung(Uebung uebung) {
		this.uebung = uebung;
	}

	public void setGewicht(double gewicht) {
		this.gewicht = gewicht;
	}

	public void setAnzahlSets(int anzahlSets) {
		this.anzahlSets = anzahlSets;
	}

	public void setAnzahlReps(int anzahlReps) {
		this.anzahlReps = anzahlReps;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}
}

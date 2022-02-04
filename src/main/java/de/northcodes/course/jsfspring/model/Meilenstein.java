package de.northcodes.course.jsfspring.model;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

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
	private LocalDate datum;

	public Meilenstein(TrainingsplanItem trainingsplanItem, int anzahlSets, int anzahlReps, double gewicht) {
		this.uebung = trainingsplanItem.getUebung();
		this.anzahlSets = anzahlSets;
		this.anzahlReps = anzahlReps;
		this.gewicht = gewicht;
	}

	protected Meilenstein() {
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

	public LocalDate getDatum() {
		return datum;
	}
}

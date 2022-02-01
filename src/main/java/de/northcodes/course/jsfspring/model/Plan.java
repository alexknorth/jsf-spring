package de.northcodes.course.jsfspring.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Plan extends AbstractEntity {

	private String name;

	@Column(name = "start_datum", nullable = false)
	private LocalDate startDatum;

	@Column(name = "end_datum")
	private LocalDate endDatum;

	@OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PlanItem> planItemList;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_user", referencedColumnName = "id", nullable = false)
	private User user;

	protected Plan() {
	}

	public Plan(String name, LocalDate startDatum, LocalDate endDatum, List<PlanItem> planItemList) {
		this.name = name;
		this.startDatum = startDatum;
		this.endDatum = endDatum;
		this.planItemList = planItemList;
	}

	public Plan(String name, LocalDate startDatum, List<PlanItem> planItemList) {
		this.name = name;
		this.startDatum = startDatum;
		this.planItemList = planItemList;
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

	public List<PlanItem> getPlanItemList() {
		return planItemList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

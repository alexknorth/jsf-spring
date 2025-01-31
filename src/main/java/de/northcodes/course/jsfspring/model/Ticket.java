package de.northcodes.course.jsfspring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Table(name = AbstractEntity.SHOP_PREFIX + "ticket")
public final class Ticket extends AbstractEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name = "creation_date", nullable = false)
    private String creationDate;

    @Column(name = "creation_time", nullable = false)
    private String creationTime;

    @Column(name = "creator", nullable = true)
    private String creator;

    @Column(name = "ticket_name", nullable = false)
    private String ticketName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "prio", nullable = false)
    private String prio;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "affected_system", nullable = false)
    private String affectedSystem;

    @Column(name = "resolver_group", nullable = false)
    private String resolverGroup;

    @Column(name = "solution_txt", nullable = true)
    private String solutionTxt;

    @Column(name = "solving_date", nullable = true)
    private String solvingDate;

    @Column(name = "solving_time", nullable = true)
    private String solvingTime;

    public Ticket() {}

    public Ticket(String creation_date, String creation_time, String creator, String ticket_name, String description, String prio, String status, String affected_system, String resolver_group, String solution_txt, String solving_date, String solving_time) {
        this.creationDate = creation_date;
        this.creationTime = creation_time;
        this.creator = creator;
        this.ticketName = ticket_name;
        this.description = description;
        this.prio = prio;
        this.status = status;
        this.affectedSystem = affected_system;
        this.resolverGroup = resolver_group;
        this.solutionTxt = solution_txt;
        this.solvingDate = solving_date;
        this.solvingTime = solving_time;
    }
    // Methode zum Setzen von Datum und Uhrzeit bei der Erstellung
    public void initializeCreationDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        this.creationDate = now.format(dateFormatter);
        this.creationTime = now.format(timeFormatter);
    }

    // Methode zum Setzen von Lösungsdatum und -zeit
    public void initializeSolvingDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        this.solvingDate = now.format(dateFormatter);
        this.solvingTime = now.format(timeFormatter);
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreator() { return creator; }

    public void setCreator(String creator) { this.creator = creator; }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrio() {
        return prio;
    }

    public void setPrio(String prio) {
        this.prio = prio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAffectedSystem() {
        return affectedSystem;
    }

    public void setAffectedSystem(String affectedSystem) {
        this.affectedSystem = affectedSystem;
    }

    public String getResolverGroup() {
        return resolverGroup;
    }

    public void setResolverGroup(String resolverGroup) {
        this.resolverGroup = resolverGroup;
    }

    public String getSolutionTxt() {
        return solutionTxt;
    }

    public void setSolutionTxt(String solutionTxt) {
        this.solutionTxt = solutionTxt;
    }

    public String getSolvingDate() {
        return solvingDate;
    }

    public void setSolvingDate(String solvingDate) {
        this.solvingDate = solvingDate;
    }

    public String getSolvingTime() {
        return solvingTime;
    }

    public void setSolvingTime(String solvingTime) {
        this.solvingTime = solvingTime;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticket_id='" + getId() + '\'' +
                ", ticket_name='" + ticketName + '\'' +
                ", status='" + status + '\'' +
                ", prio=" + prio +
                '}';
    }
}

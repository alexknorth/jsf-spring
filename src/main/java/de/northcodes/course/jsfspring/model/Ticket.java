package de.northcodes.course.jsfspring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = AbstractEntity.SHOP_PREFIX + "ticket")
public final class Ticket extends AbstractEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name = "ticket_id", nullable = false)
    private String ticketId;

    @Column(name = "creation_date", nullable = false)
    private String creationDate;

    @Column(name = "creation_time", nullable = false)
    private String creationTime;

    @Column(name = "ticket_name", nullable = false)
    private String ticketName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "prio", nullable = false)
    private Integer prio;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "affected_system", nullable = false)
    private String affectedSystem;

    @Column(name = "resolver_group", nullable = false)
    private String resolverGroup;

    @Column(name = "solution_txt", nullable = false)
    private String solutionTxt;

    @Column(name = "solving_date", nullable = false)
    private String solvingDate;

    @Column(name = "solving_time", nullable = false)
    private String solvingTime;

    protected Ticket() {}

    public Ticket(String ticketId, String creation_date, String creation_time, String ticket_name, String description, Integer prio, String status, String affected_system, String resolver_group, String solution_txt, String solving_date, String solving_time) {
        this.ticketId = ticketId;
        this.creationDate = creation_date;
        this.creationTime = creation_time;
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

    public String getTicketId() {
        return ticketId;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public String getTicketName() {
        return ticketName;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrio() {
        return prio;
    }

    public String getStatus() {
        return status;
    }

    public String getAffectedSystem() {
        return affectedSystem;
    }

    public String getResolverGroup() {
        return resolverGroup;
    }

    public String getSolutionTxt() {
        return solutionTxt;
    }

    public String getSolvingDate() {
        return solvingDate;
    }

    public String getSolvingTime() {
        return solvingTime;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticket_id='" + ticketId + '\'' +
                ", ticket_name='" + ticketName + '\'' +
                ", status='" + status + '\'' +
                ", prio=" + prio +
                '}';
    }
}

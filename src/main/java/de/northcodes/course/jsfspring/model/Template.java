package de.northcodes.course.jsfspring.model;

import javax.persistence.*;

@Entity
@Table(name = "templates")
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean active;

    private String name;

    private String description;

    // Getters and Setters
}


package de.northcodes.course.jsfspring.model;

public class MenuItem {
    private final String label;
    private final String link;

    public MenuItem(String label, String link) {
        this.label = label;
        this.link = link;
    }

    public String getLabel() {
        return label;
    }

    public String getLink() {
        return link;
    }
}

package de.northcodes.course.jsfspring.bean;

import de.northcodes.course.jsfspring.JsfSpringApplication;
import de.northcodes.course.jsfspring.model.Template;
import de.northcodes.course.jsfspring.persistence.TemplateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Component
public class TemplateBean implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(JsfSpringApplication.class);
    @Autowired
    private TemplateRepository templateRepository;

    private Template selectedTemplate;
    private List<Template> templates;

    public TemplateBean() {
        // Default constructor
    }

    public List<Template> getTemplates() {
        if (templates == null) {
            templates = (List<Template>) templateRepository.findAll();
        }
        return templates;
    }

    public void createTemplate(Template template) {
        templateRepository.save(template);
    }

    public Template getSelectedTemplate() {
        return selectedTemplate;
    }

    public void setSelectedTemplate(Template selectedTemplate) {
        this.selectedTemplate = selectedTemplate;
        log.info("Selected template: {}", selectedTemplate.getName());
    }

    public void editTemplate() {
        //
    }

    public void startTemplate() {
        //
    }


}

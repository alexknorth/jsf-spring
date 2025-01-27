package de.northcodes.course.jsfspring.bean;

import de.northcodes.course.jsfspring.model.Template;
import de.northcodes.course.jsfspring.persistence.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
@Component
public class TemplateBean implements Serializable {

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
    }
}

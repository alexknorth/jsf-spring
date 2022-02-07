package de.northcodes.course.jsfspring.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.northcodes.course.jsfspring.model.TransferDetails;
import de.northcodes.course.jsfspring.model.User;
import de.northcodes.course.jsfspring.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SessionScoped
@Component
@ManagedBean
public class TransferHistory implements Serializable {

    @Autowired
    private TransferService transferService;

    @Autowired
    private UserManager userManager;

    private User currentUser;

    public boolean isSignedIn() {
        return currentUser != null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public List<TransferDetails> getOutgoingTransfers() {
        return transferService.getOutgoingTransfers(userManager.getCurrentUser());
    }

    public List<TransferDetails> getIncomingTransfers() {
        return transferService.getIncomingTransfers(userManager.getCurrentUser());
    }
}

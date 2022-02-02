package de.northcodes.course.jsfspring.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;

import de.northcodes.course.jsfspring.model.TransferDetails;
import de.northcodes.course.jsfspring.model.TransferState;
import de.northcodes.course.jsfspring.service.TransferService;
import de.northcodes.course.jsfspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@ViewScoped
@Component
@ManagedBean
public class MoneyTransfer implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private UserManager userManager;
    
    @Autowired
    TransferService transferService;

    @Autowired
    UserService userService;

    private double amount;
    private String bankAccountNumberReceiver;

    public TransferDetails createTransferDetails() {
        TransferDetails transferDetails = new TransferDetails();
        transferService.transferNow(transferDetails);
        transferDetails.setTransferredBy(userManager.getCurrentUser());
        transferDetails.setTransferredTo(userService.getUser(Integer.parseInt(bankAccountNumberReceiver)));
        transferDetails.setTransferDate(new Date());
        transferDetails.setTransferState(TransferState.READY);
        return transferDetails;
    }

    public void transferNow() {
    	transferService.transferNow(createTransferDetails());
    }
    
    public List<TransferDetails> getAllTransfers() {
        return transferService.getAllTransfersByUser(userManager.getCurrentUser());
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBankAccountNumberReceiver() {
        return bankAccountNumberReceiver;
    }

    public void setBankAccountNumberReceiver(String bankAccountNumberReceiver) {
        this.bankAccountNumberReceiver = bankAccountNumberReceiver;
    }
}

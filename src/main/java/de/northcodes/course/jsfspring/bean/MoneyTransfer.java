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

    public static final int FALLBACK_USER = 0;

    @Autowired
    private UserManager userManager;
    @Autowired
    TransferService transferService;
    @Autowired
    UserService userService;

    private double amount;
    private String bankAccountNumberReceiver;

    private TransferDetails createTransferDetails() {
        TransferDetails transferDetails = new TransferDetails();
        transferDetails.setTransferredBy(userManager.getCurrentUser());
        if (isValidReceiver()) {
            transferDetails.setTransferredTo(userService.getUser(Integer.parseInt(bankAccountNumberReceiver)));
            transferDetails.setTransferState(TransferState.READY);
        } else {
            transferDetails.setTransferredTo(userService.getUser(FALLBACK_USER));
            transferDetails.setTransferState(TransferState.FAILED);
        }
        transferDetails.setTransferDate(new Date());
        transferDetails.setAmount(amount);
        transferService.transferNow(transferDetails);
        return transferDetails;
    }

    private boolean isValidReceiver() {
        return userService.getUser(Integer.parseInt(bankAccountNumberReceiver)) != null &&
               userManager.getCurrentUser().getBankAccountNumber() != Integer.parseInt(bankAccountNumberReceiver) &&
               Integer.parseInt(bankAccountNumberReceiver) != 0 &&
               userManager.getCurrentUser().getBankAccountNumber() != 0;
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

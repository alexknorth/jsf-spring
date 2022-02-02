package de.northcodes.course.jsfspring.bean;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequestScoped
@Component
@ManagedBean
public class SignIn {

    @Autowired
    private UserManager userManager;

    private String bankAccountNumber;
    private String password;

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String submit() {
        return userManager.signIn(Integer.parseInt(bankAccountNumber), password);
    }
}

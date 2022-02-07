package de.northcodes.course.jsfspring.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = AbstractEntity.BANK_PREFIX + "user")
public class User extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "bank_account_number", nullable = false, unique = true)
    private int bankAccountNumber;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @Column(name = "email_address", nullable = false, unique = true)
    private String emailAddress;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;
    
    @Column(name = "balance", nullable = false)
    private double balance;

    @OneToMany(mappedBy = "transferredBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TransferDetails> transferredByDetails = new ArrayList<>();

	@OneToMany(mappedBy = "transferredTo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TransferDetails> transferredToDetails = new ArrayList<>();
    
    public User() {}

	public User(
		int bankAccountNumber,
		String password,
		String firstName,
		String lastName,
		String emailAddress,
		String phoneNumber,
		Date birthDate,
		double balance
	) {
		this.bankAccountNumber = bankAccountNumber;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.balance = balance;
	}

	public int getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(int bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<TransferDetails> getTransferredByDetails() {
		return transferredByDetails;
	}

	public void setTransferredByDetails(List<TransferDetails> transferredByDetails) {
		this.transferredByDetails = transferredByDetails;
	}

	public List<TransferDetails> getTransferredToDetails() {
		return transferredToDetails;
	}

	public void setTransferredToDetails(List<TransferDetails> transferredToDetails) {
		this.transferredToDetails = transferredToDetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
  
}

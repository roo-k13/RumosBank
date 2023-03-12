package com.rumos.rumosbank.domain.models;

import jakarta.persistence.*;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private LocalDate birthdate;
    private String nif;
    private String phone;
    @Column(name = "mobile_phone")
    private String mobilePhone;
    private String profession;
    @Column(name = "email_address")
    private String emailAddress;
    private String password;
    @OneToMany
    @JoinColumn(name = "client_id")
    private List<BankAccount> bankAccounts;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getNif() {
        return nif;
    }

    public String getPhone() {
        return phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getProfession() {
        return profession;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public BankAccount getBankAccount(String number) {
        return bankAccounts.stream()
                .filter(account -> account.getNumber().equals(number))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + number));
    }

    public void setFirstName(String name) throws IllegalArgumentException {
        if (isNameInvalid(name))
            throw new IllegalArgumentException("The following first name is not valid: " + name);
        this.firstName = StringUtils.capitalize(name);
    }

    public void setLastName(String name) {
        if (isNameInvalid(name))
            throw new IllegalArgumentException("The following last name is not valid: " + lastName);
        this.lastName = StringUtils.capitalize(name);
    }

    public void setBirthdate(LocalDate birthdate) {
        if (ChronoUnit.YEARS.between(birthdate, LocalDate.now()) < 18) {
            throw new IllegalArgumentException("Birthdate is invalid. Client needs to be latest 18 years old to be registered.");
        }
        this.birthdate = birthdate;
    }

    public void setNif(String nif) {
        if (nif.length() != 9) throw new IllegalArgumentException("NIF value must be exactly 9 digits");
        this.nif = nif;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    private boolean isNameInvalid(String name) {
        return !name.matches("^[a-zA-ZÀ-ÿ]+([ '-][a-zA-ZÀ-ÿ]+)*$");
    }

    public boolean isPasswordCorrect(String password) {
        return Objects.equals(this.password, password);
    }

    @Override
    public String toString() {
        return  "Name: "      + getFirstName() + " " + getLastName() + "\n" +
                "Birthdate: " + getBirthdate() + "\n" +
                "Email: "     + getEmailAddress();
    }
}
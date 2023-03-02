package com.rumos.rumosbank.domain.models;

import jakarta.persistence.*;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @Column(name = "email_address")
    private String emailAddress;
    private String password;
    @OneToMany
    @JoinColumn(name = "client_id")
    private List<BankAccount> bankAccounts;

    /* ------------------------------------------------------------ Names ------------------------------------------------------------ */

    private boolean isNameInvalid(String name) {
        Pattern pattern = Pattern.compile("(?=.{1,40}$)[a-zA-Z]+(?:[-'\\s][a-zA-Z]+)*$");
        Matcher matcher = pattern.matcher(name);
        return !matcher.matches();
    }

    public void setFirstName(String firstName) {
        if (isNameInvalid(firstName)) throw new IllegalArgumentException("The following first name is not valid: " + firstName);
        this.firstName = StringUtils.capitalize(firstName);
    }

    public void setLastName(String lastName) {
        if (isNameInvalid(lastName)) throw new IllegalArgumentException("The following last name is not valid: " + lastName);
        this.lastName = StringUtils.capitalize(lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /* ------------------------------------------------------------ Birthdate ------------------------------------------------------------ */

    public void setBirthdate(LocalDate birthdate) {
        if (ChronoUnit.YEARS.between(birthdate, LocalDate.now()) < 18) {
            throw new IllegalArgumentException("Birthdate is invalid. Client needs to be latest 18 years old to be registered.");
        }
        this.birthdate = birthdate;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    /* ------------------------------------------------------------ NIF ------------------------------------------------------------ */

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    /* ------------------------------------------------------------ Email ------------------------------------------------------------ */

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    /* ------------------------------------------------------------ Password ------------------------------------------------------------ */

    public boolean isPasswordCorrect(String password) {
        return Objects.equals(this.password, password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /* ------------------------------------------------------------ Bank Accounts ------------------------------------------------------------ */

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    /* ------------------------------------------------------------ ToString ------------------------------------------------------------ */

    @Override
    public String toString() {
        return  "Name: "      + getFirstName() + " " + getLastName() + "\n" +
                "Birthdate: " + getBirthdate() + "\n" +
                "Email: "     + getEmailAddress();
    }
}
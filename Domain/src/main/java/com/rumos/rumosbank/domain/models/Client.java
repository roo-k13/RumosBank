package com.rumos.rumosbank.domain.models;

import jakarta.persistence.*;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Entity
@NamedQuery(name = "Client.getByEmail", query = "SELECT c FROM Client c WHERE c.emailAddress = :email")
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

    public final Long getId() {
        return id;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final String getLastName() {
        return lastName;
    }

    public final String getName() {
        return firstName + " " + lastName;
    }

    public final LocalDate getBirthdate() {
        return birthdate;
    }

    public final String getNif() {
        return nif;
    }

    public final String getPhone() {
        return phone;
    }

    public final String getMobilePhone() {
        return mobilePhone;
    }

    public final String getProfession() {
        return profession;
    }

    public final String getEmailAddress() {
        return emailAddress;
    }

    public final List<BankAccount> getBankAccounts() {
        return Collections.unmodifiableList(bankAccounts);
    }

    public BankAccount getBankAccount(String number) {
        return bankAccounts.stream()
                .filter(account -> account.getNumber().equals(number))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + number));
    }

    public final void setFirstName(String name) throws IllegalArgumentException {
        if (isNameInvalid(name))
            throw new IllegalArgumentException("The following first name is not valid: " + name);
        this.firstName = StringUtils.capitalize(name);
    }

    public void setLastName(String name) {
        if (isNameInvalid(name))
            throw new IllegalArgumentException("The following last name is not valid: " + lastName);
        this.lastName = StringUtils.capitalize(name);
    }

    public final void setBirthdate(LocalDate birthdate) {
        if (18 > ChronoUnit.YEARS.between(birthdate, LocalDate.now())) {
            throw new IllegalArgumentException("Birthdate is invalid. Client needs to be latest 18 years old to be registered.");
        }
        this.birthdate = birthdate;
    }

    public final void setNif(String nif) {
        if (9 != nif.length()) throw new IllegalArgumentException("NIF value must be exactly 9 digits");
        this.nif = nif;
    }

    public final void setPhone(String phone) {
        this.phone = phone;
    }

    public final void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public final void setProfession(String profession) {
        this.profession = profession;
    }

    public final void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public final void setPassword(String password) {
        this.password = password;
    }

    public final void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    private static boolean isNameInvalid(CharSequence name) {
        Pattern compile = Pattern.compile("^[a-zA-ZÀ-ÿ]+([ '-][a-zA-ZÀ-ÿ]+)*$");
        return !compile.matcher(name).matches();
    }

    public final boolean isPasswordCorrect(String password) {
        return Objects.equals(this.password, password);
    }

    @Override
    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder(200);
        String pattern = "%-20s%s%n"; // pattern for left-justifying the first string
        stringBuilder.append(String.format(pattern, "ID: ", id))
                .append(String.format(pattern, "First Name:", firstName))
                .append(String.format(pattern, "Last Name:", lastName))
                .append(String.format(pattern, "Birthdate:", birthdate))
                .append(String.format(pattern, "NIF:", nif))
                .append(String.format(pattern, "Phone:", phone))
                .append(String.format(pattern, "Mobile Phone:", mobilePhone))
                .append(String.format(pattern, "Profession:", profession))
                .append(String.format(pattern, "Email Address:", emailAddress))
                .append(String.format(pattern, "Password:", password))
                .append(String.format(pattern, "Bank Accounts:", bankAccounts));
        return stringBuilder.toString();
    }
}
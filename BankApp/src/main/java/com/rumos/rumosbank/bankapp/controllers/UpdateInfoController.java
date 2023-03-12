package com.rumos.rumosbank.bankapp.controllers;

import com.rumos.rumosbank.bankapp.App;
import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.Bank;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdateInfoController extends NavigationBarController {
    private final Bank bank;
    private final Client client;

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField birthdateTextField;
    @FXML
    private TextField nifTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField mobilePhoneTextField;
    @FXML
    private TextField professionTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private Label feedbackMessageLabel;

    public UpdateInfoController() {
        this.bank = Bank.instance;
        this.client = App.getAuthenticatedClient();
    }

    @FXML
    private void initialize() {
        initializeFields();
        feedbackMessageLabel.setVisible(false);
    }

    private void initializeFields() {
        firstNameTextField.setText(client.getFirstName());
        lastNameTextField.setText(client.getLastName());
        birthdateTextField.setText(client.getBirthdate().toString());
        nifTextField.setText(client.getNif());
        phoneTextField.setText(client.getPhone());
        mobilePhoneTextField.setText(client.getMobilePhone());
        professionTextField.setText(client.getProfession());
        emailTextField.setText(client.getEmailAddress());
    }

    @FXML
    private void onSaveChangesButtonClick() {
        updateClient();
        showSuccessFeedback();
    }

    private void updateClient() {
        client.setFirstName(firstNameTextField.getText());
        client.setLastName(lastNameTextField.getText());
        client.setPhone(phoneTextField.getText());
        client.setMobilePhone(mobilePhoneTextField.getText());
        client.setProfession(professionTextField.getText());
        client.setEmailAddress(emailTextField.getText());
        bank.updateClient(client);
    }

    private void showSuccessFeedback() {
        feedbackMessageLabel.setVisible(true);
        feedbackMessageLabel.setText("Your profile has been successfully updated");
    }
}

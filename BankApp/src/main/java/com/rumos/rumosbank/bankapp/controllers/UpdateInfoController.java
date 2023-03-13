package com.rumos.rumosbank.bankapp.controllers;

import com.rumos.rumosbank.bankapp.App;

import com.rumos.rumosbank.domain.Bank;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdateInfoController extends AbstractController {

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
        setClient(App.getAuthenticatedClient());
    }

    @FXML
    private void initialize() {
        initializeFields();
        feedbackMessageLabel.setVisible(false);
    }

    private void initializeFields() {
        firstNameTextField.setText(getClient().getFirstName());
        lastNameTextField.setText(getClient().getFirstName());
        birthdateTextField.setText(getClient().getBirthdate().toString());
        nifTextField.setText(getClient().getNif());
        phoneTextField.setText(getClient().getPhone());
        mobilePhoneTextField.setText(getClient().getMobilePhone());
        professionTextField.setText(getClient().getProfession());
        emailTextField.setText(getClient().getEmailAddress());
    }

    @FXML
    private void onSaveChangesButtonClick() {
        updateClient();
        showSuccessFeedback();
    }

    private void updateClient() {
        getClient().setFirstName(firstNameTextField.getText());
        getClient().setLastName(lastNameTextField.getText());
        getClient().setPhone(phoneTextField.getText());
        getClient().setMobilePhone(mobilePhoneTextField.getText());
        getClient().setProfession(professionTextField.getText());
        getClient().setEmailAddress(emailTextField.getText());
        Bank.updateClient(getClient());
    }

    private void showSuccessFeedback() {
        feedbackMessageLabel.setVisible(true);
        feedbackMessageLabel.setText("Your profile has been successfully updated");
    }
}

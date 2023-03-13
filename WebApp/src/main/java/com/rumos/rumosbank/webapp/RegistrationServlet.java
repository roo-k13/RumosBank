package com.rumos.rumosbank.webapp;

import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.Bank;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "registration", value = "/registration")
public class RegistrationServlet extends AbstractController {

    @Override
    protected final void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Client client = buildClient(req);
        Bank.registerClient(client);
        changeToIndex(req, resp);
    }

    private static Client buildClient(ServletRequest req) {
        Client client = new Client();
        client.setFirstName(req.getParameter("firstName"));
        client.setLastName(req.getParameter("lastName"));
        client.setBirthdate(LocalDate.parse(req.getParameter("birthdate")));
        client.setNif(req.getParameter("nif"));
        client.setPhone(req.getParameter("phone"));
        client.setMobilePhone(req.getParameter("mobilePhone"));
        client.setProfession(req.getParameter("profession"));
        client.setEmailAddress(req.getParameter("emailAddress"));
        client.setPassword(req.getParameter("password"));
        return client;
    }
}

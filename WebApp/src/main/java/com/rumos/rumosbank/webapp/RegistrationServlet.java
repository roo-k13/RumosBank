package com.rumos.rumosbank.webapp;

import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.Bank;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;

@WebServlet(name = "registration", value = "/registration")
public class RegistrationServlet extends AbstractController {

    @Override
    protected final void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        Client client = new Client();
        client.setFirstName(req.getParameter("first_name"));
        client.setLastName(req.getParameter("last_name"));
        client.setBirthdate(LocalDate.parse(req.getParameter("birthdate")));
        client.setEmailAddress(req.getParameter("email"));
        client.setPassword(req.getParameter("password"));
        Bank.instance.registerClient(client);
    }
}

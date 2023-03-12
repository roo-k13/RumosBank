package com.rumos.rumosbank.webapp;

import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.Bank;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;

@WebServlet(name = "registration", value = "/registration")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
        Client client = new Client();
        client.setFirstName(request.getParameter("first_name"));
        client.setLastName(request.getParameter("last_name"));
        client.setBirthdate(LocalDate.parse(request.getParameter("birthdate")));
        client.setEmailAddress(request.getParameter("email"));
        client.setPassword(request.getParameter("password"));
        Bank.instance.registerClient(client);
    }
}

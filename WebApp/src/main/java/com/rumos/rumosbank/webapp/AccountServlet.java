package com.rumos.rumosbank.webapp;

import com.rumos.rumosbank.domain.models.BankAccount;
import com.rumos.rumosbank.domain.models.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "account", value = "/account")
public class AccountServlet extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String number = req.getParameter("number");
        Client client = (Client) req.getSession().getAttribute(CLIENT);
        BankAccount bankAccount = client.getBankAccount(number);
        req.getSession().setAttribute("account", bankAccount);
        req.getSession().setAttribute("cards", bankAccount.getCards());
        changeToAccount(req, resp);
    }
}

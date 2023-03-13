package com.rumos.rumosbank.webapp;

import com.rumos.rumosbank.domain.models.BankAccount;
import com.rumos.rumosbank.domain.models.Client;

import com.rumos.rumosbank.domain.models.cards.Card;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "account", value = "/account")
public class AccountServlet extends AbstractController {

    @Override
    protected final void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String number = req.getParameter("number");
        HttpSession session = req.getSession();
        Client client = (Client) session.getAttribute(CLIENT_ATTRIBUTE);
        BankAccount bankAccount = client.getBankAccount(number);
        session.setAttribute("account", bankAccount);
        List<Card> cards = bankAccount.getCards();
        session.setAttribute("cards", cards);
        changeToAccount(req, resp);
    }
}

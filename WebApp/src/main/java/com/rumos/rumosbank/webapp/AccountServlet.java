package com.rumos.rumosbank.webapp;

import com.rumos.rumosbank.domain.models.BankAccount;
import com.rumos.rumosbank.domain.models.Client;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "account", value = "/account")
public class AccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String number = request.getParameter("number");
        Client client = (Client) request.getSession().getAttribute("client");
        BankAccount bankAccount = client.getBankAccount(number);
        request.getSession().setAttribute("account", bankAccount);
        request.getSession().setAttribute("cards", bankAccount.getCards());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("account.jsp");
        requestDispatcher.forward(request, response);
    }
}

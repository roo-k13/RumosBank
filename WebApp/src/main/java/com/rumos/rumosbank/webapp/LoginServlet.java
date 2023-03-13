package com.rumos.rumosbank.webapp;

import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.Bank;
import jakarta.persistence.NoResultException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends AbstractController {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            Client authenticatedClient = Bank.instance.authenticate(email, password);
            HttpSession session = req.getSession();
            session.setAttribute(CLIENT, authenticatedClient);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("accounts.jsp");
            requestDispatcher.forward(req, resp);
        } catch (NoResultException exception) {
            changeToIndex(req, resp);
        }
    }
}

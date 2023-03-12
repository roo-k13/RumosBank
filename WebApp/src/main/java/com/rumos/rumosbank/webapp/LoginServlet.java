package com.rumos.rumosbank.webapp;

import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.Bank;
import jakarta.persistence.NoResultException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            Client authenticatedClient = Bank.instance.authenticate(email, password);
            HttpSession session = request.getSession();
            session.setAttribute("client", authenticatedClient);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("accounts.jsp");
            requestDispatcher.forward(request, response);
        } catch (NoResultException exception) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}

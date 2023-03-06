package com.rumos.rumosbank.webapp;

import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.services.Bank;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "profile", value = "/profile")
public class ProfileServlet extends HttpServlet {
    private final Client client;

    private ProfileServlet(HttpServletRequest request) {
        HttpSession session = request.getSession();
        client = (Client) session.getAttribute("client");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        client.setPhone(request.getParameter("phone"));
        client.setMobilePhone(request.getParameter("mobilePhone"));
        client.setProfession(request.getParameter("job"));
        client.setEmailAddress(request.getParameter("email"));
        Bank.instance.updateClient(client);
        RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
        dispatcher.forward(request, response);
    }
}

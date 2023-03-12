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
    private Client client;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        reloadPage(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        client = (Client) session.getAttribute("client");
        getParameters(request);
        updateClient();
        reloadPage(request, response);
    }

    private void getParameters(HttpServletRequest request) {
        client.setPhone(request.getParameter("phone"));
        client.setMobilePhone(request.getParameter("mobilePhone"));
        client.setProfession(request.getParameter("profession"));
        client.setEmailAddress(request.getParameter("emailAddress"));
    }

    private void updateClient() {
        Bank.instance.updateClient(client);
    }

    private void reloadPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
            dispatcher.forward(request, response);
    }
}

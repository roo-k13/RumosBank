package com.rumos.rumosbank.webapp;

import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.Bank;

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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        reloadPage(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Client client = (Client) session.getAttribute("client");
        getParameters(client, req);
        updateClient(client);
        reloadPage(req, resp);
    }

    private void getParameters(Client client, HttpServletRequest request) {
        client.setPhone(request.getParameter("phone"));
        client.setMobilePhone(request.getParameter("mobilePhone"));
        client.setProfession(request.getParameter("profession"));
        client.setEmailAddress(request.getParameter("emailAddress"));
    }

    private void updateClient(Client client) {
        Bank.instance.updateClient(client);
    }

    private void reloadPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
            dispatcher.forward(request, response);
    }
}

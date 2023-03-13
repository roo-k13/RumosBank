package com.rumos.rumosbank.webapp;

import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.Bank;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "profile", value = "/profile")
public final class ProfileServlet extends MainController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        reloadPage(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Client client = (Client) session.getAttribute(CLIENT);
        getParameters(client, req);
        updateClient(client);
        reloadPage(req, resp);
    }

    private static void getParameters(Client client, ServletRequest request) {
        String phone = request.getParameter("phone");
        client.setPhone(phone);
        String mobilePhone = request.getParameter("mobilePhone");
        client.setMobilePhone(mobilePhone);
        String profession = request.getParameter("profession");
        client.setProfession(profession);
        String emailAddress = request.getParameter("emailAddress");
        client.setEmailAddress(emailAddress);
    }

    private static void updateClient(Client client) {
        Bank.instance.updateClient(client);
    }

    private static void reloadPage(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
        dispatcher.forward(request, response);
    }
}

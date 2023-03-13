package com.rumos.rumosbank.webapp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;

public abstract class AbstractController extends HttpServlet {
    private static final String INDEX_PATH = "index.jsp";
    private static final String ACCOUNT_PATH = "account.jsp";
    static final String CLIENT_ATTRIBUTE = "client";

    static void changeToIndex(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        changePage(request, response, INDEX_PATH);
    }

    static void changeToAccount(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        changePage(request, response, ACCOUNT_PATH);
    }

    private static void changePage(ServletRequest request, ServletResponse response, String path)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }
}

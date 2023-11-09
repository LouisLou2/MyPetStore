package org.csu.mypetstore.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.csu.mypetstore.domain.Account;

import java.io.IOException;

public class SignOffServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";

    private Account account;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account)session.getAttribute("account");
        account = null;
        //HttpSession session = request.getSession();
        session.setAttribute("account", account);

        request.getRequestDispatcher(MAIN).forward(request, response);
    }
}

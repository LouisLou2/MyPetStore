package org.csu.mypetstore.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.csu.mypetstore.domain.Account;

import java.io.IOException;

public class ShippingFormServlet extends HttpServlet {
    private static final String SHIPPINGFORM = "/WEB-INF/jsp/order/ShippingForm.jsp";
    private static final String SIGNONFORM = "/WEB-INF/jsp/account/SignonForm.jsp";

    private Account account;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        account = (Account)session.getAttribute("account");

        if (account == null){
            request.getRequestDispatcher(SIGNONFORM).forward(request, response);
        } else{
            request.getRequestDispatcher(SHIPPINGFORM).forward(request, response);
        }
    }
}

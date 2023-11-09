package org.csu.mypetstore.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.LogService;
import org.csu.mypetstore.service.OrderService;

import java.io.IOException;

public class ConfirmOrderFormServlet extends HttpServlet {
    private static final String CONFIRM_ORDER_FORM = "/WEB-INF/jsp/order/ConfirmOrder.jsp";
    private static final String SHIPPINGFORM = "/WEB-INF/jsp/order/ShippingForm.jsp";

    private String shippingAddressRequired;
    private Order order;
    private OrderService orderService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        shippingAddressRequired = request.getParameter("shippingAddressRequired");
        order = new Order();

        HttpSession session = request.getSession();
        order = (Order)session.getAttribute("order");
        Account account = (Account)session.getAttribute("account");

        if (shippingAddressRequired == null){
            if(account != null){
                HttpServletRequest httpRequest= request;
                String strBackUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
            }

            request.getRequestDispatcher(CONFIRM_ORDER_FORM).forward(request, response);
        }
        else{
            shippingAddressRequired = null;

            if(account != null){
                HttpServletRequest httpRequest= request;
                String strBackUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
            }

            request.getRequestDispatcher(SHIPPINGFORM).forward(request, response);
        }

    }
}

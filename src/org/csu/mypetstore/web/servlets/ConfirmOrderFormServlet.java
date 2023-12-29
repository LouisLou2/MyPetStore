package org.csu.mypetstore.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.csu.mypetstore.domain.Order;

import java.io.IOException;

public class ConfirmOrderFormServlet extends HttpServlet {
    private static final String CONFIRM_ORDER_FORM = "/WEB-INF/jsp/order/ConfirmOrder.jsp";
    private static final String SHIPPINGFORM = "/WEB-INF/jsp/order/ShippingForm.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order=(Order)request.getSession().getAttribute("order");
        String cardType = request.getParameter("cardType");
        String creditCard = request.getParameter("creditCard");
        String expiryDate = request.getParameter("expiryDate");
        String billToFirstName = request.getParameter("billToFirstName");
        String billToLastName = request.getParameter("billToLastName");
        String billCountry = request.getParameter("billCountry");
        String billState = request.getParameter("billState");
        String billCity = request.getParameter("billCity");
        String billZip = request.getParameter("billZip");
        String billAddress1 = request.getParameter("billAddress1");
        String billAddress2 = request.getParameter("billAddress2");
        //set bill info
        order.setCardType(cardType);
        order.setCreditCard(creditCard);
        order.setExpiryDate(expiryDate);
        order.setBillToFirstName(billToFirstName);
        order.setBillToLastName(billToLastName);
        order.setBillCountry(billCountry);
        order.setBillState(billState);
        order.setBillCity(billCity);
        order.setBillZip(billZip);
        order.setBillAddress1(billAddress1);
        order.setBillAddress2(billAddress2);
        
        String hasDifferentShipAddress = request.getParameter("hasDifferentShippingInfo");
        if(hasDifferentShipAddress==null){
            order.setShipInfoWithBillInfo();
        }else{
            String shipToFirstName = request.getParameter("shipToFirstName");
            String shipToLastName = request.getParameter("shipToLastName");
            String shipCountry = request.getParameter("shipCountry");
            String shipState = request.getParameter("shipState");
            String shipCity = request.getParameter("shipCity");
            String shipZip = request.getParameter("shipZip");
            String shipAddress1 = request.getParameter("shipAddress1");
            String shipAddress2 = request.getParameter("shipAddress2");

            order.setShipToFirstName(shipToFirstName);
            order.setShipToLastName(shipToLastName);
            order.setShipCountry(shipCountry);
            order.setShipState(shipState);
            order.setShipCity(shipCity);
            order.setShipZip(shipZip);
            order.setShipAddress1(shipAddress1);
            order.setShipAddress2(shipAddress2);
        }
        request.setAttribute("order", order);
        //更新session中的order
        request.getSession().setAttribute("order", order);
        request.getRequestDispatcher(CONFIRM_ORDER_FORM).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

package org.csu.mypetstore.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;

import java.io.IOException;
import java.util.List;

public class ViewListOrderServlet extends HttpServlet {
    private static final String VIEWLISTORDER = "/WEB-INF/jsp/order/ListOrders.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        List<Order> orderList = OrderService.getOrdersByUsernameWithPage(username, 1);
        int totalPage = OrderService.getTotalPage(username);
        request.setAttribute("orderList", orderList);
        request.setAttribute("totalPage", totalPage);

        request.getRequestDispatcher(VIEWLISTORDER).forward(request, response);
    }
}

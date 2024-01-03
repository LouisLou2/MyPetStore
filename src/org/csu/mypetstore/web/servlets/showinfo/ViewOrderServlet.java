package org.csu.mypetstore.web.servlets.showinfo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.csu.mypetstore.constant.enums.TipsEnum;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;

import java.io.IOException;

public class ViewOrderServlet extends HttpServlet {
    private static final String VIEWORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean isOkay = true;
        int orderId = 0;
        try {
            orderId = Integer.parseInt(request.getParameter("orderId"));
        } catch (NumberFormatException e) {
            request.setAttribute("message", TipsEnum.errorViewOrder);
            isOkay = false;
        }
        Order order = OrderService.getOrder(orderId);
        if (order == null) {
            isOkay = false;
            request.setAttribute("message", TipsEnum.errorViewOrder);
        }
        if(!isOkay){
            request.getRequestDispatcher(ERROR).forward(request, response);
            return;
        }
        //将其放入request中
        request.setAttribute("order", order);
        request.setAttribute("message", TipsEnum.submitOrderSuccess);
        request.getRequestDispatcher(VIEWORDER).forward(request, response);
    }
}

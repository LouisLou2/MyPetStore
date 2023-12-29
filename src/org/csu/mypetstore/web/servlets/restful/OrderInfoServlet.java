package org.csu.mypetstore.web.servlets.restful;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.csu.mypetstore.constant.enums.ResultCodeEnum;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.domain.RestResponse;
import org.csu.mypetstore.service.OrderService;

import java.io.IOException;
import java.util.List;

public class OrderInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RestResponse restResponse = new RestResponse();
        
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        String username = account.getUsername();
        String pageStr = req.getParameter("page");
        List<Order> orderList = null;
        int page=1;
        if(pageStr!=null){
            page = Integer.parseInt(pageStr);
        }
        orderList = OrderService.getOrdersByUsernameWithPage(username, page);
        restResponse.setCode(ResultCodeEnum.SUCCESS);
        restResponse.insertLoading("orderList",orderList);
        resp.getWriter().write(restResponse.ToJsonStr());
        resp.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

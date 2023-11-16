package org.csu.mypetstore.web.servlets.restful;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.csu.mypetstore.constant.enums.ResultCodeEnum;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.domain.RestResponse;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.OrderService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfirmOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RestResponse restResponse = new RestResponse();
        restResponse.setCode(ResultCodeEnum.SUCCESS);
        String orderId=null;
        try{
            HttpSession session = req.getSession();
            //拿到订单对象
            Order order = (Order) session.getAttribute("order");
            //删除session中的订单对象
            session.removeAttribute("order");
            Account account = (Account) req.getSession().getAttribute("account");
            List<String>itemIdList =new ArrayList<>();
            for (LineItem lineItem:  order.getLineItems()) {
                itemIdList.add(lineItem.getItemId());
            }
            //批量删除购物车中的商品
            for(String itemId:itemIdList){
                CartService.deleteCartItem(account.getUsername(),itemId);
            }
            //将订单对象存入数据库
            orderId=OrderService.insertOrder(order);
            restResponse.insertLoading("orderId",orderId);
        }catch (Exception e){
            restResponse.setCode(ResultCodeEnum.FAIL);
            restResponse.insertLoading("error",e.getMessage());
        }
        resp.getWriter().write(restResponse.ToJsonStr());
        resp.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

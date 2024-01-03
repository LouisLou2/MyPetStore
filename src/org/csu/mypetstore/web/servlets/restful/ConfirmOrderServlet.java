package org.csu.mypetstore.web.servlets.restful;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.csu.mypetstore.constant.enums.ErrorEnum;
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
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RestResponse restResponse = new RestResponse();
        HttpSession session = req.getSession();
        Object theOrder=session.getAttribute("order");
        if(theOrder==null){
            req.setAttribute("error", ErrorEnum.ShopError.ORDER_NOTEXIST.getMessage());
            req.getRequestDispatcher(ERROR).forward(req, resp);
            return;
        }
        restResponse.setCode(ResultCodeEnum.SUCCESS);
        String orderId;
        Order order=(Order)theOrder;
        try{
            //删除session中的订单对象
            session.removeAttribute("order");
            //session.setAttribute("infoFilled", false);
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

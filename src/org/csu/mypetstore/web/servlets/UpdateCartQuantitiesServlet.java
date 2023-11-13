package org.csu.mypetstore.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.csu.mypetstore.constant.enums.ResultCodeEnum;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.RestResponse;
import org.csu.mypetstore.service.CartService;

import java.io.IOException;

public class UpdateCartQuantitiesServlet extends HttpServlet {

    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";

    private String workingItemId;
    private Cart cart;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RestResponse restResponse = new RestResponse();
        String username = request.getParameter("username");
        String itemId = request.getParameter("itemId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        //更新购物车中的商品数量
        CartService.updateCartItemQuantity(username,itemId,quantity);
        restResponse.setCode(ResultCodeEnum.SUCCESS);
        restResponse.insertLoading("message","更新成功");
        response.getWriter().println(restResponse.ToJsonStr());
        //close
        response.getWriter().close();
    }
}

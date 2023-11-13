package org.csu.mypetstore.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.csu.mypetstore.constant.enums.ResultCodeEnum;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.RestResponse;
import org.csu.mypetstore.service.CartService;

import java.io.IOException;
//做成restful接口
public class RemoveItemFromCartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RestResponse restResponse = new RestResponse();
        String itemId = request.getParameter("itemId");
        Account account = (Account) request.getSession().getAttribute("account");
        String username = account.getUsername();
        //删除购物车中的商品
        CartService.deleteCartItem(username,itemId);
        restResponse.setCode(ResultCodeEnum.SUCCESS);
        restResponse.insertLoading("message","删除成功");
        response.getWriter().println(restResponse.ToJsonStr());
        //close
        response.getWriter().close();
    }
}

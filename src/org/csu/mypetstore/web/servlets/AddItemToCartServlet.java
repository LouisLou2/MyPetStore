package org.csu.mypetstore.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.csu.mypetstore.constant.enums.ResultCodeEnum;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Dto.CartItemDto;
import org.csu.mypetstore.domain.RestResponse;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.CatalogService;

import java.io.IOException;
//将此作为restful接口
public class AddItemToCartServlet extends HttpServlet {
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RestResponse restResponse = new RestResponse();
        String workingItemId = request.getParameter("itemId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Account account = (Account) request.getSession().getAttribute("account");
        if(account==null){
            restResponse.setCode(ResultCodeEnum.UNAUTHORIZED);
            restResponse.insertLoading("error","请先登录");
            response.getWriter().println(restResponse.ToJsonStr());
            //close
            response.getWriter().close();
            return;
        }
        String username = account.getUsername();
        int quan=CatalogService.getInventoryQuantity(workingItemId);
        if(quan<quantity){
            restResponse.insertLoading("erorr","库存不足");
        }else{
            CartService.insertCartItem(username,new CartItemDto(workingItemId,quantity));
            restResponse.setCode(ResultCodeEnum.SUCCESS);
            restResponse.insertLoading("message","添加成功");
        }
        response.getWriter().println(restResponse.ToJsonStr());
        //close
        response.getWriter().close();
    }
}

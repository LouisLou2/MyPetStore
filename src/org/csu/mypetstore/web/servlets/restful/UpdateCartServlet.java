package org.csu.mypetstore.web.servlets.restful;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.csu.mypetstore.constant.enums.ErrorEnum;
import org.csu.mypetstore.constant.enums.ResultCodeEnum;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.RestResponse;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.CatalogService;

import java.io.IOException;
import java.math.BigDecimal;

public class UpdateCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RestResponse restResponse = new RestResponse();
        Account account = (Account) req.getSession().getAttribute("account");
        String username = account.getUsername();
        String itemId = req.getParameter("itemId");
        String quantity = req.getParameter("quantity");
        int quantityInt = Integer.parseInt(quantity);
        if (username == null || itemId == null || quantityInt<1) {
            restResponse.setCode(ResultCodeEnum.FAIL);
            restResponse.insertLoading("error", ErrorEnum.ShopError.INVALID_PARAM.getMessage());
        }else if(quantityInt>CatalogService.getInventoryQuantity(itemId)){
            restResponse.setCode(ResultCodeEnum.FAIL);
            restResponse.insertLoading("error", ErrorEnum.ShopError.INSUFFICIENT.getMessage());
        }
        else{
            CartService.updateCartItemQuantity(username, itemId, quantityInt);
            BigDecimal totalPrice = CatalogService.getItemPrice(itemId).multiply(new BigDecimal(quantityInt));
            String totalPriceStr = totalPrice.toString();
            restResponse.setCode(ResultCodeEnum.SUCCESS);
            restResponse.insertLoading("totalPrice", totalPriceStr);
        }
        resp.getWriter().println(restResponse.ToJsonStr());
        resp.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

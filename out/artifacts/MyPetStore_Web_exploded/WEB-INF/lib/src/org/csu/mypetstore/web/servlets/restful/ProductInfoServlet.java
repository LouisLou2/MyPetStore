package org.csu.mypetstore.web.servlets.restful;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.csu.mypetstore.constant.enums.ResultCodeEnum;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.RestResponse;
import org.csu.mypetstore.service.CatalogService;

import java.io.IOException;
import java.util.List;

public class ProductInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RestResponse restResponse = new RestResponse();
        String productId = req.getParameter("productId");
        String page = req.getParameter("page");
        List<Item> itemList = null;
        if(page==null)
            itemList = CatalogService.getItemListByProductWithPage(productId, 1);
        else
            itemList = CatalogService.getItemListByProductWithPage(productId, Integer.parseInt(page));
        restResponse.setCode(ResultCodeEnum.SUCCESS);
        restResponse.insertLoading("itemList",itemList);
        resp.getWriter().write(restResponse.ToJsonStr());
        resp.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

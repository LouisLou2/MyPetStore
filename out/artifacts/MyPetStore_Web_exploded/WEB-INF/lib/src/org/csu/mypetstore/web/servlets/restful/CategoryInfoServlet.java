package org.csu.mypetstore.web.servlets.restful;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.csu.mypetstore.constant.enums.ResultCodeEnum;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.domain.RestResponse;
import org.csu.mypetstore.service.CatalogService;

import java.io.IOException;
import java.util.List;

public class CategoryInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RestResponse restResponse = new RestResponse();
        String productId = req.getParameter("categoryId");
        String page = req.getParameter("page");
        List<Product> productList = null;
        if(page==null)
            productList = CatalogService.getProductListWithPage(productId, 1);
        else
            productList = CatalogService.getProductListWithPage(productId, Integer.parseInt(page));
        restResponse.setCode(ResultCodeEnum.SUCCESS);
        restResponse.insertLoading("productList",productList);
        resp.getWriter().write(restResponse.ToJsonStr());
        resp.getWriter().close();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

package org.csu.mypetstore.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;

import java.io.IOException;
import java.util.List;

public class ViewCategoryServlet extends HttpServlet {

    private static final String VIEW_CATEGORY = "/WEB-INF/jsp/catalog/Category.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String categoryId = request.getParameter("categoryId");
        Category category = CatalogService.getCategory(categoryId);
        List<Product> productList = CatalogService.getProductListWithPage(categoryId, 1);
        int totalPage = CatalogService.getProductTotalPage(categoryId);
        request.setAttribute("category", category);
        request.setAttribute("productList", productList);
        //向requestScope里面放数据:totalPage
        request.setAttribute("totalPage", totalPage);

        //跳转页面
        request.getRequestDispatcher(VIEW_CATEGORY).forward(request, response);
    }
}

package org.csu.mypetstore.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;

import java.io.IOException;
import java.util.List;

public class ViewProductServlet extends HttpServlet {
    private static final String VIEW_PRODUCT = "/WEB-INF/jsp/catalog/Product.jsp";
    private String productId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productId = request.getParameter("productId");
        Product product = CatalogService.getProduct(productId);
        List<Item> itemList = CatalogService.getItemListByProductWithPage(productId, 1);
        int totalPage = CatalogService.getItemTotalPage(productId);
        HttpSession session = request.getSession();
        
        request.setAttribute("product", product);
        request.setAttribute("itemList", itemList);
        request.setAttribute("totalPage", totalPage);

        request.getRequestDispatcher(VIEW_PRODUCT).forward(request, response);
    }
}

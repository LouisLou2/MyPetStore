package org.csu.mypetstore.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;

import java.io.IOException;
import java.util.List;

public class SearchProductServlet extends HttpServlet {
    private static final String SEARCH_PRODUCTS = "/WEB-INF/jsp/catalog/SearchProducts.jsp";

    private String keyword;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        keyword = request.getParameter("keyword");
        //request.setAttribute("keyword", keyword);
        CatalogService service = new CatalogService();
        List<Product> productList = service.searchProductList(keyword);

        //保存数据
        HttpSession session = request.getSession();
        session.setAttribute("keyword", keyword);
        session.setAttribute("productList", productList);

        Account account = (Account)session.getAttribute("account");
        //跳转页面
        request.getRequestDispatcher(SEARCH_PRODUCTS).forward(request, response);
    }
}

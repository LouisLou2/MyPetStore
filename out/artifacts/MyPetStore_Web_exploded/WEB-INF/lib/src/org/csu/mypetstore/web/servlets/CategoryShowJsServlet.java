package org.csu.mypetstore.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CategoryShowJsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("categoryId");
        System.out.println(categoryId+"             ddddddddd");
        CatalogService catalogService = new CatalogService();
        List<Product> productList = catalogService.getProductListByCategory(categoryId);
        String resp = "Product ID      Name\n";
        int i = 0;
        while(i < productList.size()){
            Product product = productList.get(i);
            resp += product.getProductId() + "      " + product.getName() + "\n";
            i++;
        }
        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();
        out.write(resp);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

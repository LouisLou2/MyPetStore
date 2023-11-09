package org.csu.mypetstore.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.service.CatalogService;

import java.io.IOException;

public class ViewItemServlet extends HttpServlet {
    private static final String VIEW_ITEM = "/WEB-INF/jsp/catalog/Item.jsp";
    private String itemId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        itemId = request.getParameter("itemId");
        CatalogService service = new CatalogService();
        Item item = service.getItem(itemId);
        
        HttpSession session = request.getSession();
        session.setAttribute("item", item);
        
        String picture = CatalogService.getPictureLocation(item.getProduct().getProductId());
        request.setAttribute("picture", picture);
        
        request.getRequestDispatcher(VIEW_ITEM).forward(request, response);
    }
}

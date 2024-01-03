package org.csu.mypetstore.web.servlets.showinfo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.csu.mypetstore.constant.enums.PageCapacityEnum;
import org.csu.mypetstore.domain.Dto.SimpleProduct;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.service.CatalogService;

import java.io.IOException;
import java.util.List;

public class ViewItemServlet extends HttpServlet {
    private static final String VIEW_ITEM = "/WEB-INF/jsp/catalog/Item.jsp";
    private String itemId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        itemId = request.getParameter("itemId");
        Item item = CatalogService.getItem(itemId);
        List<SimpleProduct> simpleItemList = CatalogService.getSimilarSimpleProduct(item, PageCapacityEnum.SIMILAR_ITEM_LIMIT);
        request.setAttribute("item", item);
        request.setAttribute("simpleProductList", simpleItemList);
        request.getRequestDispatcher(VIEW_ITEM).forward(request, response);
    }
}

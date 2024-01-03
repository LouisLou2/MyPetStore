package org.csu.mypetstore.web.servlets.showinfo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.service.CartService;

import java.io.IOException;
import java.util.List;

public class ViewCartServlet extends HttpServlet {
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        List<CartItem>cartItems = CartService.getCartItemListWithPage(username,1);
        Cart cart = new Cart();
        cart.setCartItemList(cartItems);
        cart.setPage(1);
        request.setAttribute("cart",cart);
        request.getRequestDispatcher(VIEW_CART).forward(request, response);
    }
}

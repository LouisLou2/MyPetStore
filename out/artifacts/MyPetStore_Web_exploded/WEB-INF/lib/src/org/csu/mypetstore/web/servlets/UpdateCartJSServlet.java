package org.csu.mypetstore.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.service.CatalogService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class UpdateCartJSServlet extends HttpServlet {

    //private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";

    //private String workingItemId;
    private Cart cart;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //workingItemId = request.getParameter("workingItemId");

        //从对话中，获取购物车
        HttpSession session = request.getSession();
        cart = (Cart)session.getAttribute("cart");

        Iterator<CartItem> cartItemIterator = cart.getAllCartItems();

        while (cartItemIterator.hasNext()) {
            //产品数量增加
            CartItem cartItem = (CartItem) cartItemIterator.next();
            String itemId = cartItem.getItem().getItemId();

            try {
                int quantity = Integer.parseInt((String) request.getParameter("quantity"));
                cart.setQuantityByItemId(itemId, quantity);
                if (quantity < 1) {
                    cartItemIterator.remove();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        session.setAttribute("cart", cart);


        Cart cart2 = (Cart)session.getAttribute("cart");
        Iterator<CartItem> cartItemIterator2 = cart2.getAllCartItems();
        String quantityAll = "";
        while (cartItemIterator2.hasNext()) {
            //产品数量增加
            CartItem cartItem2 = cartItemIterator2.next();
            int quantity2 = cartItem2.getQuantity();
            quantityAll += quantity2 + "," + cartItem2.getTotal() + "," + cart2.getSubTotal();

        }

        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();
        out.write(quantityAll);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

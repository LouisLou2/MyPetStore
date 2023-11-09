package org.csu.mypetstore.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Item;

import java.io.IOException;

public class RemoveItemFromCartServlet extends HttpServlet {

    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";

    private String workingItemId;
    private Cart cart;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        workingItemId = request.getParameter("workingItemId");

        HttpSession session = request.getSession();
        cart = (Cart)session.getAttribute("cart");

        Item item = cart.removeItemById(workingItemId);

        if(item == null) {
            session.setAttribute("message", "Attempted to remove null CartItem from Cart.");

            Account account = (Account)session.getAttribute("account");
            request.getRequestDispatcher(ERROR).forward(request, response);
        }else{

            Account account = (Account)session.getAttribute("account");

            request.getRequestDispatcher(VIEW_CART).forward(request, response);
        }
    }
}

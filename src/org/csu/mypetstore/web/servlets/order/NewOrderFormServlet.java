package org.csu.mypetstore.web.servlets.order;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.csu.mypetstore.constant.enums.ErrorEnum;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.CartService;

import java.io.IOException;
import java.util.List;

public class NewOrderFormServlet extends HttpServlet {
    private static final String NEW_ORDER = "/WEB-INF/jsp/order/NewOrderForm.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account)session.getAttribute("account");
        List<CartItem> cartItemList=CartService.getCartItemList(account.getUsername());
        if(cartItemList.isEmpty()){
            request.setAttribute("error", ErrorEnum.ShopError.EMPTY_CART.getMessage());
            request.getRequestDispatcher(ERROR).forward(request, response);
            return;
        }
        Cart cart = new Cart(cartItemList);
        Order order = new Order();
        order.initOrder(account, cart);
        request.setAttribute("order", order);
        session.setAttribute("order", order);
        request.getRequestDispatcher(NEW_ORDER).forward(request, response);
    }
}

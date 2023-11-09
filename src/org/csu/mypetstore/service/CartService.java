package org.csu.mypetstore.service;

import org.csu.mypetstore.constant.enums.PageCapacityEnum;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.repository.CartItemDAO;
import org.csu.mypetstore.repository.Impl.CartItemDAOImpl;

import java.util.List;

public class CartService {
    private static CartItemDAO cartDAO;
    static {
        cartDAO = CartItemDAOImpl.INSTANCE;
    }
    public static int getCartItemCount(String username){
        return cartDAO.getCartItemCount(username);
    }
    public static int getCartPageCount(String username){
        int count=cartDAO.getCartItemCount(username);
        return (count% PageCapacityEnum.CART ==0)?count/PageCapacityEnum.CART:count/PageCapacityEnum.CART+1;
    }
    public static List<CartItem> getCartItemListWithPage(String username, int page){
        return cartDAO.getCartItemListWithPage(username,page);
    }
}

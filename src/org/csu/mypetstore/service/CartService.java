package org.csu.mypetstore.service;

import org.csu.mypetstore.constant.enums.PageCapacityEnum;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Dto.CartItemDto;
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
    public static List<CartItem> getCartItemList(String username){
        return cartDAO.getCartItemList(username);
    }
    public static void insertCartItem(String username, CartItemDto cartItemDto) {
        cartDAO.SmartAddCartItem(username,cartItemDto);
    }
    public static void insertCartItem(String username, CartItem cartItem) {
        cartDAO.insertItem(username,cartItem);
    }
    public static void deleteCartItem(String username, String itemId) {
        cartDAO.deleteItem(username,itemId);
    }
    public static void updateCartItemQuantity(String username, String itemId, int quantity) {
        cartDAO.updateQuantity(username,itemId,quantity);
    }
}

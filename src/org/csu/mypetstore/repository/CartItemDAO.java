package org.csu.mypetstore.repository;

import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Dto.CartItemDto;

import java.util.List;

public interface CartItemDAO {
    List<CartItem> getCartItemListWithPage(String username,int page);
    List<CartItem> getCartItemList(String username);
    int getCartItemCount(String username);
    void updateQuantity(String username,String itemId,int quantity);
    boolean containsItem(String username,String itemId);
    void insertItem(String usernamem, CartItem cartItem);
    void insertItem(String username, CartItemDto cartItemDto);
    void SmartAddCartItem(String username, CartItemDto cartItemDto);
    void deleteItem(String username,String itemId);
}

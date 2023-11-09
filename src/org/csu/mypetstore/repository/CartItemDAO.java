package org.csu.mypetstore.repository;

import org.csu.mypetstore.domain.CartItem;

import java.util.List;

public interface CartItemDAO {
    List<CartItem> getCartItemListWithPage(String username,int page);
    int getCartItemCount(String username);
}

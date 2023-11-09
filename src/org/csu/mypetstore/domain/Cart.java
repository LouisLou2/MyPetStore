package org.csu.mypetstore.domain;

//import com.ibatis.common.util.PaginatedArrayList;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class Cart implements Serializable {

  private static final long serialVersionUID = 8329559983943337176L;
  private  ConcurrentHashMap<String, CartItem> itemMap = new ConcurrentHashMap<String, CartItem>();

  public Iterator<CartItem> getCartItems() {
    return itemMap.values().iterator();
  }

  public int getNumberOfItems() {
    return itemMap.size();
  }

  public Iterator<CartItem> getAllCartItems() {
    return itemMap.values().iterator();
  }

  public boolean containsItemId(String itemId) {
    return itemMap.containsKey(itemId);
  }
  public void addItem(Item item, boolean isInStock) {
    CartItem cartItem = (CartItem) itemMap.get(item.getItemId());
    if (cartItem == null) {
      cartItem = new CartItem();
      cartItem.setItem(item);
      cartItem.setQuantity(0);
      cartItem.setInStock(isInStock);
      itemMap.put(item.getItemId(), cartItem);
    }
    cartItem.incrementQuantity();
  }

  public Item removeItemById(String itemId) {
    CartItem cartItem = (CartItem) itemMap.remove(itemId);
    if (cartItem == null) {
      return null;
    } else {
      return cartItem.getItem();
    }
  }

  public void incrementQuantityByItemId(String itemId) {
    CartItem cartItem = (CartItem) itemMap.get(itemId);
    cartItem.incrementQuantity();
  }

  public void setQuantityByItemId(String itemId, int quantity) {
    CartItem cartItem = (CartItem) itemMap.get(itemId);
    cartItem.setQuantity(quantity);
  }

  public BigDecimal getSubTotal() {
    BigDecimal subTotal = BigDecimal.valueOf(0);
    for(CartItem cartItem : itemMap.values()) {
      Item item = cartItem.getItem();
      BigDecimal listPrice = item.getListPrice();
      BigDecimal quantity = new BigDecimal(cartItem.getQuantity());
      BigDecimal subTotal1 = listPrice.multiply(quantity);
      subTotal = subTotal.add(subTotal1);
    }
    return subTotal;
  }
}

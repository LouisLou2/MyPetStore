package org.csu.mypetstore.domain;

//import com.ibatis.common.util.PaginatedArrayList;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

public class Cart implements Serializable {

  private static final long serialVersionUID = 8329559983943337176L;
  private List<CartItem> cartItemList;
  private int page;
  public Cart() {
  }
  public Cart(List<CartItem>cartItemList) {
    this.cartItemList = cartItemList;
    getSubTotal();
  }
  public Iterator<CartItem> getCartItems() {
    return cartItemList.iterator();
  }
  public void setCartItemList(List<CartItem> cartItemList) {
    this.cartItemList = cartItemList;
    getSubTotal();
  }
  public int getNumberOfItems() {
    return cartItemList.size();
  }
  public int getPage(){
    return page;
  }
  public void setPage(int page){
    this.page = page;
  }
  public BigDecimal getSubTotal() {
    BigDecimal subTotal = BigDecimal.valueOf(0);
    for(CartItem cartItem : cartItemList) {
      Item item = cartItem.getItem();
      BigDecimal listPrice = item.getListPrice();
      BigDecimal quantity = new BigDecimal(cartItem.getQuantity());
      BigDecimal subTotal1 = listPrice.multiply(quantity);
      subTotal = subTotal.add(subTotal1);
    }
    return subTotal;
  }
}

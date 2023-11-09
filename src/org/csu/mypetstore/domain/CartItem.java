package org.csu.mypetstore.domain;

import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
@AllArgsConstructor
public class CartItem implements Serializable {
  private static final long serialVersionUID = 6620528781626504362L;

  private Item item;
  private int quantity;
  private boolean inStock;
  private BigDecimal total;
  private long time;

  public CartItem(){
    this.time = System.currentTimeMillis();
    this.quantity=1;
    this.inStock=true;
    total=new BigDecimal(0);
  }
  public long getTime() {
    return time;
  }
  public void setTime(long time){
    this.time = time;
  }
  
  public boolean isInStock() {
    return inStock;
  }

  public void setInStock(boolean inStock) {
    this.inStock = inStock;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
    calculateTotal();
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
    calculateTotal();
  }
  public void incrementQuantity() {
    ++quantity;
    calculateTotal();
  }

  private void calculateTotal() {
    if (item != null && item.getListPrice() != null) {
      total = item.getListPrice().multiply(new BigDecimal(quantity));
    } else {
      total = null;
    }
  }
}

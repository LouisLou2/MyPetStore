package org.csu.mypetstore.domain.Dto;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class CartItemDto {
    private String itemId;
    private int quantity;
    private long time;
    public CartItemDto(){
        itemId = "";
        quantity = 0;
        time = System.currentTimeMillis();
    }
    public CartItemDto(String itemId, int quantity){
        this.itemId = itemId;
        this.quantity = quantity;
        time = System.currentTimeMillis();
    }
}

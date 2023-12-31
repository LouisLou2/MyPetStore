package org.csu.mypetstore.repository;

import org.csu.mypetstore.domain.Item;

import java.math.BigDecimal;
import java.util.List;

public interface ItemDAO {
    int PAGE_SIZE = 10;
    int getItemCount(String productId);
    void decrementInventoryQuantity(String itemId,int increment);
    int getInventoryQuantity(String itemId);
    List<Item> getItemListByProduct(String productId);
    Item getItem(String itemId);
    List<Item> getItemListByProductWithPage(String productId, int page);
    List<Item> getItemListByProductWithLimit(String productId, int limit,boolean rand);
    BigDecimal getItemPrice(String itemId);
}

package org.csu.mypetstore.repository;

import org.csu.mypetstore.domain.Item;

import java.util.List;

public interface ItemDAO {
    int PAGE_SIZE = 10;
    int getItemCount(String productId);
    void updateInventoryQuantity(String itemId,int increment);

    int getInventoryQuantity(String itemId);

    List<Item> getItemListByProduct(String productId);

    Item getItem(String itemId);
    List<Item> getItemListByProductWithPage(String productId, int page);
}

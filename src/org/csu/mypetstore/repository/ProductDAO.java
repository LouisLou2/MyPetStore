package org.csu.mypetstore.repository;

import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.domain.ProductBasic;

import java.util.List;

public interface ProductDAO {

    int PAGE_SIZE = 10;
    List<Product> getProductListByCategory(String categoryId);
    List<Product> getProductListWithPage(String categoryId, int page);
    Product getProduct(String productId);
    int getProductCount(String categoryId);
    List<Product> searchProductList(String keywords);
    List<ProductBasic> searchProductIdNameList(String keywords, int num);
    String getPictureLocation(String productId);
}

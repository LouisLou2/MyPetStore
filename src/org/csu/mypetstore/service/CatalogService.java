package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Dto.SimpleItem;
import org.csu.mypetstore.domain.Dto.SimpleProduct;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.domain.ProductBasic;
import org.csu.mypetstore.repository.CategoryDAO;
import org.csu.mypetstore.repository.Impl.CategoryDAOImpl;
import org.csu.mypetstore.repository.Impl.ItemDAOImpl;
import org.csu.mypetstore.repository.Impl.ItemHomePageDAOImpl;
import org.csu.mypetstore.repository.Impl.ProductDAOImpl;
import org.csu.mypetstore.repository.ItemDAO;
import org.csu.mypetstore.repository.ItemHomePageDAO;
import org.csu.mypetstore.repository.ProductDAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogService {
    //业务逻辑层调用数据访问层
    private static CategoryDAO categoryDAO;
    private static ProductDAO productDAO;
    private static ItemDAO itemDAO;
    private static ItemHomePageDAO itemHomePageDAO;
    static{
        categoryDAO = CategoryDAOImpl.INSTANCE;
        productDAO = ProductDAOImpl.INSTANCE;
        itemDAO = ItemDAOImpl.INSTANCE;
        itemHomePageDAO = ItemHomePageDAOImpl.INSTANCE;
    }
    public static List<Category> getCategoryList() {
        return categoryDAO.getCategoryList();
    }

    public static Category getCategory(String categoryId) {
        return categoryDAO.getCategory(categoryId);
    }
    public static int getProductTotalPage(String categoryId) {
        int totalPage = 0;
        int totalProduct = productDAO.getProductCount(categoryId);
        if(totalProduct % ProductDAOImpl.PAGE_SIZE == 0)
            totalPage = totalProduct / ProductDAOImpl.PAGE_SIZE;
        else
            totalPage = totalProduct / ProductDAOImpl.PAGE_SIZE + 1;
        return totalPage;
    }
    public static int getItemTotalPage(String productId) {
        int totalPage = 0;
        int totalItem = itemDAO.getItemCount(productId);
        if(totalItem % ItemDAOImpl.PAGE_SIZE == 0)
            totalPage = totalItem / ItemDAOImpl.PAGE_SIZE;
        else
            totalPage = totalItem / ItemDAOImpl.PAGE_SIZE + 1;
        return totalPage;
    }
    public static int getProductCount(String categoryId) {
        return productDAO.getProductCount(categoryId);
    }
    public static Product getProduct(String productId) {

        return productDAO.getProduct(productId);
    }

    public static List<Product> getProductListByCategory(String categoryId) {
        return productDAO.getProductListByCategory(categoryId);
    }
    public static List<Product> getProductListWithPage(String categoryId, int page) {
        return productDAO.getProductListWithPage(categoryId, page);
    }

    /* TODO enable using more than one keyword*/
    public static List<Product> searchProductList(String keyword) {
        return productDAO.searchProductList("%" + keyword.toLowerCase() + "%");
    }
    public static List<ProductBasic> searchProductIdNameListWithLimit(String keyword, int page) {
        return productDAO.searchProductIdNameList("%" + keyword.toLowerCase() + "%", page);
    }

    public static List<Item> getItemListByProduct(String productId) {
        return itemDAO.getItemListByProduct(productId);
    }
    public static List<Item> getItemListByProductWithPage(String productId, int page) {
        return itemDAO.getItemListByProductWithPage(productId, page);
    }

    public static Item getItem(String itemId) {
        return itemDAO.getItem(itemId);
    }
    public static BigDecimal getItemPrice(String itemId) {
        return itemDAO.getItemPrice(itemId);
    }
    public static int getInventoryQuantity(String itemId) {
        return itemDAO.getInventoryQuantity(itemId);
    }
    public static boolean isItemInStock(String itemId) {
        return itemDAO.getInventoryQuantity(itemId) > 0;
    }
    
    public static String getPictureLocation(String productId) {
        return productDAO.getPictureLocation(productId);
    }
    public static List<SimpleItem>getItemShownInHome() {
        List<SimpleItem> simpleItemList = new ArrayList<>();
        List<String> itemIdList = itemHomePageDAO.getItemIdList();
        Item item;
        Product product;
        for(String itemId : itemIdList) {
            item=itemDAO.getItem(itemId);
            product=item.getProduct();
            SimpleItem simpleItem = new SimpleItem(item.getItemId(), product.getName(), product.getPicture(),item.getQuantity(),item.getListPrice(),product.getDescription());
            simpleItemList.add(simpleItem);
        }
        return simpleItemList;
    }
    public static List<SimpleItem> getSimilarSimpleItem(Item item,int limit) {
        return itemDAO.getItemListByProductWithLimit(item.getProduct().getProductId(), limit, true).stream().map(item1 -> {
            Product product = item1.getProduct();
            SimpleItem simpleItem = new SimpleItem(item1.getItemId(), product.getName(), product.getPicture(),item1.getQuantity(),item1.getListPrice(),product.getDescription());
            return simpleItem;
        }).collect(Collectors.toList());
    }
    public static List<SimpleProduct> getSimilarSimpleProduct(Item item, int limit){
        return productDAO.getIdListByCategoryWithLimit(item.getProduct().getCategoryId(), limit, true);
    }
}

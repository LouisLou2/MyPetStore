package org.csu.mypetstore.repository.Impl;

import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.repository.ItemDAO;
import org.csu.mypetstore.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    public static ItemDAOImpl INSTANCE;
    public static final int PAGE_SIZE = 10;
    private static final String getItemCountString = "SELECT count(*) FROM ITEM WHERE PRODUCTID = ?";
    private static final String getItemListWithPageString = "SELECT I.ITEMID, LISTPRICE, UNITCOST, " +
            "SUPPLIER AS supplierId, I.PRODUCTID AS \"product.productId\", NAME AS \"product.name\", " +
            "DESCN AS \"product.description\", CATEGORY AS \"product.categoryId\", STATUS,  ATTR1 AS attribute1, " +
            "ATTR2 AS attribute2, ATTR3 AS attribute3, ATTR4 AS attribute4, ATTR5 AS attribute5 FROM ITEM I, PRODUCT P " +
            "WHERE P.PRODUCTID = I.PRODUCTID AND I.PRODUCTID = ?" + " limit ?, ?";
    private static final String updateInventoryQuantityString = "UPDATE INVENTORY SET QTY = QTY - ? " +
            "WHERE ITEMID = ?";
    private static final String getInventoryQuantityString = "SELECT QTY AS value FROM INVENTORY WHERE ITEMID = ?";
    private static final String getItemListByProductString = "SELECT I.ITEMID, LISTPRICE, UNITCOST, " +
            "SUPPLIER AS supplierId, I.PRODUCTID AS \"product.productId\", NAME AS \"product.name\", " +
            " DESCN AS \"product.description\", CATEGORY AS \"product.categoryId\", STATUS,  ATTR1 AS attribute1, " +
            "ATTR2 AS attribute2, ATTR3 AS attribute3, ATTR4 AS attribute4, ATTR5 AS attribute5 FROM ITEM I, PRODUCT P " +
            "WHERE P.PRODUCTID = I.PRODUCTID AND I.PRODUCTID = ?";
    private static final String getItemString = "select I.ITEMID, LISTPRICE, UNITCOST, SUPPLIER AS supplierId, " +
            "I.PRODUCTID AS \"product.productId\", NAME AS \"product.name\", DESCN AS \"product.description\", " +
            "CATEGORY AS \"product.categoryId\", STATUS, ATTR1 AS attribute1, ATTR2 AS attribute2, " +
            "ATTR3 AS attribute3, ATTR4 AS attribute4, ATTR5 AS attribute5, QTY AS quantity from ITEM I, " +
            "INVENTORY V, PRODUCT P where P.PRODUCTID = I.PRODUCTID and I.ITEMID = V.ITEMID and I.ITEMID = ?";

    static {
        INSTANCE = new ItemDAOImpl();
    }

    @Override
    public void updateInventoryQuantity(String itemId,int increment) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(updateInventoryQuantityString);
            pStatement.setInt(1, increment);
            pStatement.setString(2, itemId);
            pStatement.executeUpdate();
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getInventoryQuantity(String itemId) {
        int result = -1;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(getInventoryQuantityString);
            pStatement.setString(1, itemId);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> getItemListByProduct(String productId) {
        List<Item> itemList = new ArrayList<Item>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(getItemListByProductString);
            pStatement.setString(1, productId);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setItemId(resultSet.getString(1));
                item.setListPrice(resultSet.getBigDecimal(2));
                item.setUnitCost(resultSet.getBigDecimal(3));
                item.setSupplierId(resultSet.getInt(4));
                Product product = new Product();
                product.setProductId(resultSet.getString(5));
                product.setName(resultSet.getString(6));
                product.setDescription(resultSet.getString(7));
                product.setCategoryId(resultSet.getString(8));
                item.setProduct(product);
                item.setStatus(resultSet.getString(9));
                item.setAttribute1(resultSet.getString(10));
                item.setAttribute2(resultSet.getString(11));
                item.setAttribute3(resultSet.getString(12));
                item.setAttribute4(resultSet.getString(13));
                item.setAttribute5(resultSet.getString(14));
                itemList.add(item);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public int getItemCount(String productId) {
        int count = 0;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(getItemCountString);
            pStatement.setString(1, productId);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Item getItem(String itemId) {
        Item item = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(getItemString);
            pStatement.setString(1, itemId);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                item = new Item();
                item.setItemId(resultSet.getString(1));
                item.setListPrice(resultSet.getBigDecimal(2));
                item.setUnitCost(resultSet.getBigDecimal(3));
                item.setSupplierId(resultSet.getInt(4));
                Product product = new Product();
                product.setProductId(resultSet.getString(5));
                product.setName(resultSet.getString(6));
                product.setDescription(resultSet.getString(7));
                product.setCategoryId(resultSet.getString(8));
                item.setProduct(product);
                item.setStatus(resultSet.getString(9));
                item.setAttribute1(resultSet.getString(10));
                item.setAttribute2(resultSet.getString(11));
                item.setAttribute3(resultSet.getString(12));
                item.setAttribute4(resultSet.getString(13));
                item.setAttribute5(resultSet.getString(14));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    public List<Item> getItemListByProductWithPage(String productId, int page) {
        //getItemListWithPageString
        List<Item> itemList = new ArrayList<Item>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(getItemListWithPageString);
            pStatement.setString(1, productId);
            pStatement.setInt(2, (page - 1) * PAGE_SIZE);
            pStatement.setInt(3, PAGE_SIZE);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setItemId(resultSet.getString(1));
                item.setListPrice(resultSet.getBigDecimal(2));
                item.setUnitCost(resultSet.getBigDecimal(3));
                item.setSupplierId(resultSet.getInt(4));
                Product product = new Product();
                product.setProductId(resultSet.getString(5));
                product.setName(resultSet.getString(6));
                product.setDescription(resultSet.getString(7));
                product.setCategoryId(resultSet.getString(8));
                item.setProduct(product);
                item.setStatus(resultSet.getString(9));
                item.setAttribute1(resultSet.getString(10));
                item.setAttribute2(resultSet.getString(11));
                item.setAttribute3(resultSet.getString(12));
                item.setAttribute4(resultSet.getString(13));
                item.setAttribute5(resultSet.getString(14));
                itemList.add(item);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }
}

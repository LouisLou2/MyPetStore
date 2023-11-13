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
    private static final String getItemListWithPageString = 
            "  SELECT I.ITEMID, LISTPRICE, UNITCOST, SUPPLIER AS supplierId, I.PRODUCTID AS \"product.productId\", " +
            "  NAME AS \"product.name\", DESCN AS \"product.description\", CATEGORY AS \"product.categoryId\", PICTURE AS \"product.picture\", " +
            "  STATUS, ATTR1 AS attribute1, ATTR2 AS attribute2, ATTR3 AS attribute3, ATTR4 AS attribute4, " +
            "  ATTR5 AS attribute5, QTY AS quantity " +
            "  FROM ITEM I, PRODUCT P, INVENTORY V " +
            "  WHERE I.PRODUCTID = ? and P.PRODUCTID = I.PRODUCTID and I.ITEMID = V.ITEMID LIMIT ?, ?";
    private static final String decrementInventoryQuantityString = "UPDATE INVENTORY SET QTY = QTY - ? " +
            "WHERE ITEMID = ?";
    private static final String updateInventoryQuantityString = "UPDATE INVENTORY SET QTY = ? " +
            "WHERE ITEMID = ?";
    private static final String getInventoryQuantityString = "SELECT QTY AS value FROM INVENTORY WHERE ITEMID = ?";
    private static final String getItemListByProductString = "SELECT I.ITEMID, LISTPRICE, UNITCOST, " +
            "SUPPLIER AS supplierId, I.PRODUCTID AS \"product.productId\", NAME AS \"product.name\", " +
            " DESCN AS \"product.description\", CATEGORY AS \"product.categoryId\", PICTURE AS \"product.picture\", STATUS,  ATTR1 AS attribute1, " +
            "ATTR2 AS attribute2, ATTR3 AS attribute3, ATTR4 AS attribute4, ATTR5 AS attribute5 FROM ITEM I, PRODUCT P " +
            "WHERE P.PRODUCTID = I.PRODUCTID AND I.PRODUCTID = ?";
    private static final String getItemString = "select I.ITEMID, LISTPRICE, UNITCOST, SUPPLIER AS supplierId, " +
            "I.PRODUCTID AS \"product.productId\", NAME AS \"product.name\", DESCN AS \"product.description\", " +
            "CATEGORY AS \"product.categoryId\", PICTURE AS \"product.picture\", STATUS, ATTR1 AS attribute1, ATTR2 AS attribute2, " +
            "ATTR3 AS attribute3, ATTR4 AS attribute4, ATTR5 AS attribute5, QTY AS quantity from ITEM I, " +
            "INVENTORY V, PRODUCT P where P.PRODUCTID = I.PRODUCTID and I.ITEMID = V.ITEMID and I.ITEMID = ?";

    static {
        INSTANCE = new ItemDAOImpl();
    }
    
    @Override
    public void decrementInventoryQuantity(String itemId,int increment) {
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = DBUtil.getConnection();
            pStatement = connection.prepareStatement(decrementInventoryQuantityString);
            pStatement.setInt(1, increment);
            pStatement.setString(2, itemId);
            pStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection);
            DBUtil.closePreparedStatement(pStatement);
        }
    }

    @Override
    public int getInventoryQuantity(String itemId) {
        int result = -1;
        ResultSet resultSet = null;
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            pStatement = connection.prepareStatement(getInventoryQuantityString);
            pStatement.setString(1, itemId);
            resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        return result;
    }

    @Override
    public List<Item> getItemListByProduct(String productId) {
        List<Item> itemList = new ArrayList<Item>();
        ResultSet resultSet = null;
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            pStatement = connection.prepareStatement(getItemListByProductString);
            pStatement.setString(1, productId);
            resultSet = pStatement.executeQuery();
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
                product.setPicture(resultSet.getString(9));
                item.setProduct(product);
                item.setStatus(resultSet.getString(10));
                item.setAttribute1(resultSet.getString(11));
                item.setAttribute2(resultSet.getString(12));
                item.setAttribute3(resultSet.getString(13));
                item.setAttribute4(resultSet.getString(14));
                item.setAttribute5(resultSet.getString(15));
                item.setQuantity(resultSet.getInt(16));
                itemList.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        return itemList;
    }

    @Override
    public int getItemCount(String productId) {
        int count = 0;
        ResultSet resultSet = null;
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            pStatement = connection.prepareStatement(getItemCountString);
            pStatement.setString(1, productId);
            resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        return count;
    }
    

    @Override
    public Item getItem(String itemId) {
        Item item = null;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement pStatement = null;
        try {
            connection = DBUtil.getConnection();
            pStatement = connection.prepareStatement(getItemString);
            pStatement.setString(1, itemId);
            resultSet = pStatement.executeQuery();
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
                product.setPicture(resultSet.getString(9));
                item.setProduct(product);
                item.setStatus(resultSet.getString(10));
                item.setAttribute1(resultSet.getString(11));
                item.setAttribute2(resultSet.getString(12));
                item.setAttribute3(resultSet.getString(13));
                item.setAttribute4(resultSet.getString(14));
                item.setAttribute5(resultSet.getString(15));
                item.setQuantity(resultSet.getInt(16));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        return item;
    }

    public List<Item> getItemListByProductWithPage(String productId, int page) {
        List<Item> itemList = new ArrayList<Item>();
        ResultSet resultSet = null;
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            pStatement = connection.prepareStatement(getItemListWithPageString);
            pStatement.setString(1, productId);
            pStatement.setInt(2, (page - 1) * PAGE_SIZE);
            pStatement.setInt(3, PAGE_SIZE);
            resultSet = pStatement.executeQuery();
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
                product.setPicture(resultSet.getString(9));
                item.setProduct(product);
                item.setStatus(resultSet.getString(10));
                item.setAttribute1(resultSet.getString(11));
                item.setAttribute2(resultSet.getString(12));
                item.setAttribute3(resultSet.getString(13));
                item.setAttribute4(resultSet.getString(14));
                item.setAttribute5(resultSet.getString(15));
                item.setQuantity(resultSet.getInt(16));
                itemList.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        return itemList;
    }
}

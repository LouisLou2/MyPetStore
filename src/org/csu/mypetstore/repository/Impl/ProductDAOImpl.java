package org.csu.mypetstore.repository.Impl;

import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.domain.ProductBasic;
import org.csu.mypetstore.repository.ProductDAO;
import org.csu.mypetstore.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    public static ProductDAOImpl INSTANCE;
    public static final int PAGE_SIZE = 10;
    //查询语句
    private static final String getProductCountString = "SELECT count(*) FROM PRODUCT WHERE CATEGORY = ?";
    private static final String getProductListByCategoryString = "SELECT PRODUCTID, NAME, DESCN as description, " +
            "CATEGORY as categoryId, PICTURE as picture FROM PRODUCT WHERE CATEGORY = ?";
    private static final String getProductListWithPageString = "SELECT PRODUCTID, NAME, DESCN as description, " +
            "CATEGORY as categoryId, PICTURE as picture FROM PRODUCT WHERE CATEGORY = ? limit ?, ?";
    private static final String getProductString = "SELECT PRODUCTID, NAME, DESCN as description, CATEGORY as categoryId, PICTURE as picture " +
            "FROM PRODUCT WHERE PRODUCTID = ?";
    private static final String searchProductListString = "select PRODUCTID, NAME, DESCN as description, " +
            "CATEGORY as categoryId, PICTURE as picture from PRODUCT WHERE lower(name) like ?";
    private static final String searchProductIdNameListString="select PRODUCTID, NAME from PRODUCT WHERE lower(name) like ? limit ?";//加入限制
    static{
        INSTANCE = new ProductDAOImpl();
    }
    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        List<Product> products = new ArrayList<Product>();
        ResultSet resultSet = null;
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            pStatement = connection.prepareStatement(getProductListByCategoryString);
            pStatement.setString(1, categoryId);
            resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
                product.setPicture(resultSet.getString(5));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        return products;
    }
    @Override
    public List<Product> getProductListWithPage(String categoryId, int page) {
        List<Product> products = new ArrayList<Product>();
        ResultSet resultSet = null;
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            pStatement = connection.prepareStatement(getProductListWithPageString);
            pStatement.setString(1, categoryId);
            pStatement.setInt(2, (page - 1) * PAGE_SIZE);
            pStatement.setInt(3, PAGE_SIZE);
            resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
                product.setPicture(resultSet.getString(5));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }

        return products;
    }
    @Override
    public int getProductCount(String categoryId) {
        int count = 0;
        ResultSet resultSet = null;
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            pStatement = connection.prepareStatement(getProductCountString);
            pStatement.setString(1, categoryId);
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
    public Product getProduct(String productId) {
        Product product = null;
        Connection connection=null;
        PreparedStatement pStatement=null;
        ResultSet resultSet=null;
        try {
            connection = DBUtil.getConnection();
            pStatement = connection.prepareStatement(getProductString);
            pStatement.setString(1, productId);
            resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
                product.setPicture(resultSet.getString(5));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        return product;
    }

    @Override
    public List<Product> searchProductList(String keywords) {
        List<Product> productList = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            pStatement = connection.prepareStatement(searchProductListString);
            pStatement.setString(1, keywords);
            resultSet = pStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
                product.setPicture(resultSet.getString(5));
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        return productList;
    }
    public String getPictureLocation(String productId) {
        String pictureLocation = null;
        ResultSet resultSet = null;
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            pStatement = connection.prepareStatement("SELECT PICTURE FROM PRODUCT WHERE PRODUCTID = ?");
            pStatement.setString(1, productId);
            resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                pictureLocation = resultSet.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        return pictureLocation;
    }
    public List<ProductBasic> searchProductIdNameList(String keywords,int num){
        List<ProductBasic> productList = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            pStatement = connection.prepareStatement(searchProductIdNameListString);
            pStatement.setString(1, keywords);
            pStatement.setInt(2, num);
            resultSet = pStatement.executeQuery();

            while (resultSet.next()) {
                ProductBasic product = new ProductBasic();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }
        return productList;
    }
}

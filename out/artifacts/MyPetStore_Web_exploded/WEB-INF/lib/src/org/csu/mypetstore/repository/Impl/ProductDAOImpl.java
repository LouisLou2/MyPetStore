package org.csu.mypetstore.repository.Impl;

import org.csu.mypetstore.domain.Product;
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
            "CATEGORY as categoryId FROM PRODUCT WHERE CATEGORY = ?";
    private static final String getProductListWithPageString = "SELECT PRODUCTID, NAME, DESCN as description, " +
            "CATEGORY as categoryId FROM PRODUCT WHERE CATEGORY = ? limit ?, ?";
    private static final String getProductString = "SELECT PRODUCTID, NAME, DESCN as description, CATEGORY as categoryId " +
            "FROM PRODUCT WHERE PRODUCTID = ?";
    private static final String searchProductListString = "select PRODUCTID, NAME, DESCN as description, " +
            "CATEGORY as categoryId from PRODUCT WHERE lower(name) like ?";
    
    static{
        INSTANCE = new ProductDAOImpl();
    }
    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        List<Product> products = new ArrayList<Product>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(getProductListByCategoryString);
            pStatement.setString(1, categoryId);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));

                products.add(product);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }

        return products;
    }
    @Override
    public List<Product> getProductListWithPage(String categoryId, int page){
        List<Product> products = new ArrayList<Product>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(getProductListWithPageString);
            pStatement.setString(1, categoryId);
            pStatement.setInt(2, (page - 1) * PAGE_SIZE);
            pStatement.setInt(3, PAGE_SIZE);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
                products.add(product);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }

        return products;
    }
    @Override
    public int getProductCount(String categoryId){
        int count = 0;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(getProductCountString);
            pStatement.setString(1, categoryId);
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }
    @Override
    public Product getProduct(String productId) {
        Product product = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(getProductString);
            pStatement.setString(1, productId);
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> searchProductList(String keywords) {
        List<Product> productList = new ArrayList<Product>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(searchProductListString);
            pStatement.setString(1, keywords);
            ResultSet resultSet = pStatement.executeQuery();

            while (resultSet.next()){
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));

                productList.add(product);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }
    public String getPictureLocation(String productId){
        String pictureLocation = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement("SELECT PICTURE FROM PRODUCT WHERE PRODUCTID = ?");
            pStatement.setString(1, productId);
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                pictureLocation = resultSet.getString(1);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return pictureLocation;
    }
}
